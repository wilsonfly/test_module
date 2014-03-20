#include "stdafx.h"
#include "Demux.h"



/* SI-SECTION�Ľ⸴���� */
typedef struct tagFilterNode
{
	/* TS�������� */
	unsigned char continuity_counter;
	/* TS���ݰ��Ƿ����� */
	int discontinuity;
	/* SECTION��ȱ��������(byte) */
	int need;
	/* �Ƿ�����ͷ����3���ֽ� */
	int complete_header;

	/* SECTION���ݻ����� */
	unsigned char *buf;
	/* SECTION���ݻ�������С */
	int bufsize;
	/* SECTION���ݻ���������������С */
	int datsize;

	/* �������� */
	uint16_t pid;
	unsigned char pattern[16];
	unsigned char mask[16];
	int len;

	/* SECTION������� */
	PTsFilterCallback pOutputProc;
	/* SECTION��������������û����� */
	uint32_t lParam;

	/* ��һ��FILTER */
	struct tagFilterNode *next;
}SFilterNode;

typedef struct
{
	/* ���������� */
	SFilterNode *list;
	/* �����ϴβ���TS���ݵĻ����� */
	unsigned char tsbuf[188];
	/* �����ϴβ���TS���ݴ�С */
	int tssize;
}SDemux;


/* ��SECTION������������TS�� */
static bool Demux_ParseTs188( void* handle, SFilterNode *filter, unsigned char *data );


/* DVB������ϵͳ��ʼ�� */
void *Demux_Create()
{
	SDemux *demux;

	demux = (SDemux*)malloc( sizeof(SDemux) );
	if( demux )
		memset( demux, 0, sizeof(SDemux) );
	return demux;
}

/* �ͷ�DVB������ϵͳ */
void Demux_Destroy( void *handle )
{
	SDemux *demux = (SDemux *)handle;
	SFilterNode *node, *next;

	node = demux->list;
	while( node )
	{
		next = node->next;
		free( node );
		node = next;
	}
	free( demux );
}

/* ����һ�������� */
void* Demux_CreateFilter( void *handle, uint16_t pid, unsigned char *pattern, unsigned char *mask, int len, 
							int qsize, PTsFilterCallback pProc, uint32_t lParam  )
{
	SDemux *demux = (SDemux *)handle;
	SFilterNode *node = (SFilterNode*)malloc( sizeof(SFilterNode) + qsize );
	if( node )
	{
		memset( node, 0, sizeof(SFilterNode) );

		node->bufsize = 4096;
		node->continuity_counter = 31;
		node->discontinuity= 1;

		node->pid = pid;
		memcpy( node->pattern, pattern, len<(int)sizeof(node->pattern) ? len:(int)sizeof(node->pattern) );
		memcpy( node->mask, mask, len<(int)sizeof(node->mask) ? len:(int)sizeof(node->mask) );
		node->len = len;

		node->buf = ((unsigned char*)node) + sizeof(SFilterNode);
		node->bufsize = qsize;
		memset( node->buf, 0, node->bufsize );

		node->pOutputProc = pProc;
		node->lParam = lParam;
	}
	node->next = demux->list;
	demux->list = node;
	return node;
}

/* ������һ�ַ�ʽ����һ�������� */
void* Demux_CreateFilterEx( void *handle, uint16_t pid, unsigned char tid, unsigned char tid_mask,
							uint16_t tid_ext, uint16_t tid_ext_mask,
							int qsize, PTsFilterCallback pProc, uint32_t lParam  )
{
	unsigned char pattern[8], mask[8];

	memset( pattern, 0, sizeof(pattern) );
	memset( mask, 0, sizeof(mask) );

	if( tid != 0xff )
	{
		pattern[0] = tid;
		mask[0] = tid_mask;
	}
	if( tid_ext != 0xffff )
	{
		pattern[3] = tid_ext >> 8;
		pattern[4] = tid_ext & 0xff;
		mask[3] = tid_ext_mask >> 8;
		mask[4] = tid_ext_mask & 0xff;
	}

	return Demux_CreateFilter( handle, pid, pattern, mask, 8, qsize, pProc, lParam );
}

/* ����һ�������� */
void Demux_DestroyFilter( void *handle, void* filter )
{
	SDemux *demux = (SDemux *)handle;
	SFilterNode *node, *last = NULL;

	node = demux->list;
	while( node )
	{
		if( node == filter )
		{
			if( last )
				last->next = node->next;
			else
				demux->list = demux->list->next;
			free( node );
			node = NULL;
			break;
		}
		last = node;
		node = node->next;
	}
}



/* ����TS������ */
void Demux_OnData( void *handle, unsigned char *buf, int size )
{
	SDemux *demux = (SDemux *)handle;
	int i, cpysize;
	uint16_t pid;
	SFilterNode *node, *next;

	/* ����ϴβ����������д������ */
	if( 0 < demux->tssize && *buf != 0x47 )
		demux->tssize = 0;

	/* ���ϴβ����������뵱ǰ��������(������188�ֽ�)�ϲ���Ϊ������TS��*/
	if( 0 < demux->tssize )
	{
		cpysize = 188-demux->tssize < size ?  188-demux->tssize : size;
		memcpy( demux->tsbuf+demux->tssize, buf, cpysize );
		demux->tssize += cpysize;
		buf += cpysize;
		size -= cpysize;
	}

	/* �����ϴβ��������� */
	if( 188 <= demux->tssize )
	{
		pid = ((uint16_t)(demux->tsbuf[1] & 0x1f) << 8) + demux->tsbuf[2];
		node = demux->list;
		while( node )
		{
			next = node->next;
			if( pid == node->pid )
				Demux_ParseTs188( handle, node, buf );
			node = node->next;
		}
	}
	else if( 0 < demux->tssize )
		return;

	/* ���˲��������Ѿ�������� */
	demux->tssize = 0;

	/* ����ͬ����0x47֮ǰ����Ч���� */
	while( 0 < size )
	{
		if( size <= 188 && buf[0] == 0x47 )
			break;
		else if( size <= 376 && buf[0] == 0x47 && buf[188] == 0x47 )
			break;
		else if( buf[0] == 0x47 && buf[188] == 0x47 && buf[376] == 0x47 )
			break;
		buf++;
		size--;
	}

	/* ������ǰ������ */
	for( i=0; i+188<=size; i+=188 )
	{
		pid = ((uint16_t)(buf[i+1] & 0x1f) << 8) + buf[i+2];
		node = demux->list;
		while( node )
		{
			next = node->next;
			if( pid == node->pid )
				Demux_ParseTs188( demux, node, buf+i );

			node = next;
		}
	}

	/* ����β��û�з��������� */
	if( i < size )
	{
		size -= i;
		memcpy( demux->tsbuf, buf+i, size );
		demux->tssize = size;
	}
}

/* ��SECTION������������TS�� */
static bool Demux_ParseTs188( void* handle, SFilterNode *filter, unsigned char *data )
{
	unsigned char i_expected_counter;
	unsigned char* payload_pos;
	unsigned char* p_new_pos = NULL;
	unsigned char adaptation_field_control;
	uint16_t pid;
	int i_available;

	if( data[0] != 0x47 )
		return false;

	pid = ((uint16_t)(data[1] & 0x1f) << 8) + data[2];
	if( pid != filter->pid )
		return false;

	/* ���TS���Ƿ�����, Continuity check */
	if( filter->continuity_counter == 31 )
	{
		filter->continuity_counter = data[3] & 0xf;
	}
	else
	{
		i_expected_counter = (filter->continuity_counter + 1) & 0xf;
		filter->continuity_counter = data[3] & 0xf;
		/* ������ظ�����ֱ�Ӷ��� */
		if( i_expected_counter == ((filter->continuity_counter + 1) & 0xf) )
		{
			//���ļ�����һ�飬�ڶ���ʱ���ݲ��ܶ�
			;//return true;
		}
		/* ���©������ɾ����ǰ�ķ���������������� */
		if( i_expected_counter != filter->continuity_counter )
		{
			filter->discontinuity = 1;
			filter->datsize = 0;
		}
	}

	adaptation_field_control = (data[3]>>4)&0x3;
	switch( adaptation_field_control )
	{
	/* ���û����Ч���أ��򲻴��� */
	case 0:/* ���� */
		return true;
	case 1:/* �޵����ֶ� */
		payload_pos = data + 4;
		break;
	case 2:/* �޸��� */
		return true;
	case 3: /* �е����ֶκ͸��� */
		payload_pos = data + 5 + data[4];/* ����adaptation_field */
		break;
	}

	/* ����pointer_field */
	if( data[1] & 0x40 )
		p_new_pos = payload_pos + *payload_pos + 1;

	/* ����һ����SECTION */
	if( p_new_pos )
	{
		filter->datsize = 0;
		payload_pos = p_new_pos;
		p_new_pos = NULL;
		filter->need = 3;/* ����3���ֽڣ��õ�Section_length */
		filter->complete_header = 0;
	}
	if( filter->buf == NULL )
		return false;

	/* ������ */
	i_available = 188 + data - payload_pos;

	while( 0 < i_available )
	{
		if( filter->need <= i_available )
		{
			if( filter->bufsize < filter->datsize + filter->need )
			{
				filter->continuity_counter = 31;
				filter->need = 3;
				filter->complete_header = 0;
				filter->datsize = 0;
				return false;
			}
			/* There are enough bytes in this packet to complete the header/section */
			memcpy(filter->buf+filter->datsize, payload_pos, filter->need);
			payload_pos += filter->need;
			filter->datsize += filter->need;
			i_available -= filter->need;

			if( ! filter->complete_header )
			{
				/* Header is complete */
				filter->complete_header = 1;
				filter->need = ((uint16_t)(filter->buf[1] & 0xf)) << 8 | filter->buf[2];
				/* Check that the section isn't too long */
				if(filter->need > filter->bufsize - 3)
				{
					filter->datsize = 0;
					/* If there is a new section not being handled then go forward in the packet */
					if( p_new_pos )
					{
						payload_pos = p_new_pos;
						p_new_pos = NULL;
						filter->need = 3;
						filter->complete_header = 0;
						i_available = 188 + data - payload_pos;
					}
					else
					{
						i_available = 0;
					}
				}
			}
			else
			{
				/* ���SECTION */
				if( filter->pOutputProc )
				{
					int n;
					/* �Ƿ�����ۺϹ������� */
					for( n=0; n<filter->len; n++ )
					{
						if( (filter->buf[n]&filter->mask[n]) != (filter->pattern[n]&filter->mask[n]) )
							break;
					}
					/* ����������ۺϹ���������������������ͷ�� */
					if( filter->len <= n )
						filter->pOutputProc( handle, filter, filter->buf, filter->datsize, filter->lParam );
				}
				filter->datsize = 0;

				/* If there is a new section not being handled then go forward in the packet */
				if(p_new_pos)
				{
					payload_pos = p_new_pos;
					p_new_pos = NULL;
					filter->need = 3;
					filter->complete_header = 0;
					i_available = 188 + data - payload_pos;
				}
				else
				{
					i_available = 0;
				}
			}
		}
		else
		{
			if( filter->bufsize <= filter->datsize + i_available )
			{
				filter->continuity_counter = 31;
				filter->need = 3;
				filter->complete_header = 0;
				filter->datsize = 0;
				return false;
			}
			/* There aren't enough bytes in this packet to complete the header/section */
			memcpy( filter->buf+filter->datsize, payload_pos, i_available );
			filter->datsize += i_available;
			filter->need -= i_available;
			i_available = 0;
		}
	}
	return true;
}
