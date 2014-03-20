#include "stdafx.h"
#include "crc.h"
#include "log.h"
#include "xstring.h"
#include "Xml.h"
#include "osfile.h"
#include "TsParser.h"
#include "OcgMem.h"
#include "MptsTransfer.h"
#include "main.h"
#include "osfile.h"
#include "osdir.h"

/* TS流分析器 */
CTsParser::CTsParser()
{
	m_pat_filter = NULL;
	m_pat_buf = NULL;
	m_pat_size = 0;
	m_sdt_filter = NULL;

	m_bufsize = 12*188*1024;
	m_head = 0;
	m_tail = 0;
	m_buf = (unsigned char*)OCG_MALLOC( m_bufsize );
}

CTsParser::~CTsParser()
{
	uint16_t pmt_pid, serv_id;
	SProgInfo spi;
	TSSDTInfo sdt;
	void *position;

	if( m_pat_filter )
	{
		m_demux.DestroyFilter( m_pat_filter );
		m_pat_filter = NULL;
	}
	
	if( m_pat_buf )
	{
		OCG_FREE( m_pat_buf );
		m_pat_buf = NULL;
	}
	if( m_sdt_filter )
	{
		m_demux.DestroyFilter( m_sdt_filter );
		m_sdt_filter = NULL;
	}
	if( m_buf )
	{
		OCG_FREE( m_buf );
		m_buf = NULL;
	}

	position = m_pmtmap.GetFirst( &pmt_pid, &spi );
	while( position )
	{
		if( spi.filter )
			m_demux.DestroyFilter( spi.filter );
		if( spi.pmt_buf )
			OCG_FREE( spi.pmt_buf );
		position = m_pmtmap.GetNext( &pmt_pid, &spi, position );
	}
	m_pmtmap.RemoveAll();

	/* 清除SDT信息 */
	position = m_sdtmap.GetFirst( &serv_id, &sdt );
	while( position ) {

		if( sdt.sdt_service_node ) {
			TSSDTServiceInfo *serv_del, *serv_tmp = sdt.sdt_service_node;
			while( serv_tmp ) {
				serv_del = serv_tmp;
				serv_tmp = serv_tmp->next;
				serv_del->next = NULL;
				OCG_FREE( serv_del );
			}
		}
		
		if( sdt.sdt_multilingual_node ) {
			TSSDTMultilingualInfo *multi_del, *multi_tmp = sdt.sdt_multilingual_node;
			while( multi_tmp ) {
				multi_del = multi_tmp;
				multi_tmp = multi_tmp->next;
				multi_del->next = NULL;
				OCG_FREE( multi_del );
			}
		}
		position = m_sdtmap.GetNext( &serv_id, &sdt, position );
	}
	m_sdtmap.RemoveAll();
}

/* PAT/PMT-SECTION输出回调函数 */
void CTsParser::FilterProc( void* filter, unsigned char *buf, int32_t size, uint32_t lParam )
{
	if( filter == ((CTsParser*)lParam)->m_pat_filter )
		((CTsParser*)lParam)->OnPatSection( filter, buf, size );
	else if( filter ==  ((CTsParser*)lParam)->m_sdt_filter )
		((CTsParser*)lParam)->OnSdtSection( filter, buf, size );
	else
		((CTsParser*)lParam)->OnPmtSection( filter, buf, size );
}

/* SDT-SECTION处理函数 */
bool CTsParser::OnSdtSection( void* filter, unsigned char *buf, int32_t size )
{
	unsigned char *p, *q, *q1;
	int32_t len, mutili_len;
	uint16_t service_id, sdt_service_id;
	uint32_t crc32, ival;
	unsigned char version_number, section_number, last_section_number;
	void *position;
	TSSDTInfo* sdt_info = NULL;
	TSSDTServiceInfo* sdt_service_des = NULL, *serv_tmp = NULL;
	TSSDTMultilingualInfo* sdt_multilingual_des = NULL, *multi_tmp = NULL;

	/* pat/pmt的尺寸不可能超过1024字节 */
	if( 1024 < size || filter != m_sdt_filter )
		return false;

	/* CRC校验 */
	crc32 = GetCrc32( buf, size-4 );
	memcpy( &ival, buf+size-4, 4 );
	if( ival != htonl(crc32) )
		return false;

	version_number = (buf[5] >> 1) & 0x1f;
	section_number = buf[6];
	last_section_number = buf[7];
	if( last_section_number < section_number )
		return false;

	p = buf;
	p += 11;
	while( p < buf + (size - 4) )
	{
		/* service_id */
		service_id = (p[0]<<8) | p[1];
		p += 2;

		/* EIT... */
		p += 1;

		/* descriptor_loop_length */
		len = ((p[0]&0xf)<<8) + p[1];
		p += 2;

		position = m_sdtmap.GetFirst( &sdt_service_id, NULL );
		while( position )
		{
			sdt_info = m_sdtmap.GetValue( position );
			if( sdt_info->sdt_service_id == service_id ) {
				sdt_info->bIsSucceed = true;
				break;
			}
			else {
				sdt_info = NULL;
			}

			position = m_sdtmap.GetNext( &sdt_service_id, NULL, position );
		}
		/* 或许当前pat信息还没解析出来，存储结构还未建立好 */
		if( !sdt_info )
			return false;

		/* descriptors: descriptor_tag(1)+descriptor_length(1)+... */
		q = p;
		while( p < q + len && p < buf + (size - 4) )
		{
			/* descriptor_tag */
			switch( p[0] )
			{
			case 0x48:/* service_descriptor */
				sdt_service_des = (TSSDTServiceInfo*)OCG_MALLOC( sizeof(TSSDTServiceInfo) );
				if( !sdt_service_des )
					return false;	
				memset( sdt_service_des, 0, sizeof(TSSDTServiceInfo) );
				
				p += 2;
				/* 获取 service_descriptor 信息 */
				sdt_service_des->sdt_service_type = *p;
				p += 1;
				
				memcpy( &sdt_service_des->sdt_service_provider_name, p + 1, *p );
				sdt_service_des->sdt_service_provider_name[ *p ] = 0;
				p += *p;
				p += 1;
				
				memcpy( &sdt_service_des->sdt_service_name, p + 1, *p );
				sdt_service_des->sdt_service_name[ *p ] = 0;
				p += *p;
				p += 1;
				
				/* 建立链表关系 */
				if( !sdt_info->sdt_service_node ) {
					sdt_info->sdt_service_node = sdt_service_des;
				}
				else {
					serv_tmp = sdt_info->sdt_service_node;
					while( serv_tmp ) {
						if( !serv_tmp->next )
							break;
						serv_tmp = serv_tmp->next;
					}
					serv_tmp->next = sdt_service_des;
					serv_tmp->next->next = NULL;
				}
				
				break;
				
			case 0x5d:/* multilingul_service_name_descriptor */
				mutili_len = p[1];
				p += 2;
				q1 = p;
				while( p < q1 + mutili_len && p < buf + size ) {
					sdt_multilingual_des = (TSSDTMultilingualInfo*)OCG_MALLOC( sizeof(TSSDTMultilingualInfo) );
					if( !sdt_multilingual_des )
						return false;
					memset( sdt_multilingual_des, 0, sizeof(TSSDTMultilingualInfo) );
					
					/* 获取 multilingual_name_descriptor */
					memcpy( &sdt_multilingual_des->sdt_ISO_639_language_code, p, 4 );
					sdt_multilingual_des->sdt_ISO_639_language_code <<= 8;
					sdt_multilingual_des->sdt_ISO_639_language_code = htonl( sdt_multilingual_des->sdt_ISO_639_language_code );
					p += 3;
					
					memcpy( &sdt_multilingual_des->sdt_service_provider_name, p + 1, *p );
					sdt_multilingual_des->sdt_service_provider_name[ *p ] = 0;
					p += *p;
					p += 1;
					
					memcpy( &sdt_multilingual_des->sdt_service_name, p + 1, *p );
					sdt_multilingual_des->sdt_service_name[ *p ] = 0;
					p += *p;
					p += 1;
					
					/* 建立链表关系 */
					if( !sdt_info->sdt_multilingual_node ) {
						sdt_info->sdt_multilingual_node = sdt_multilingual_des;
					}
					else {
						multi_tmp = sdt_info->sdt_multilingual_node;
						while( multi_tmp ) {
							if( !multi_tmp->next )
								break;
							multi_tmp = multi_tmp->next;
						}
						multi_tmp->next = sdt_multilingual_des;
						multi_tmp->next->next = NULL;
					}
				}
				break;

			default:
				/* others descriptor_tag */
				p += 2 + p[1];
				break;
			}	
		}
	}
	return true;
}

/* PAT-SECTION处理函数 */
bool CTsParser::OnPatSection( void* filter, unsigned char *buf, int32_t size )
{
	unsigned char *p, *q;
	uint16_t service_id;
	uint16_t pmt_pid;
	SProgInfo spi;
	void *position;
	uint32_t crc32, ival;
	unsigned char version_number, section_number, last_section_number;
	TSSDTInfo sdt;

	/* pat/pmt的尺寸不可能超过1024字节 */
	if( 1024 < size || filter != m_pat_filter )
		return false;

	/* CRC校验 */
	crc32 = GetCrc32( buf, size-4 );
	memcpy( &ival, buf+size-4, 4 );
	if( ival != htonl(crc32) )
		return false;

	version_number = (buf[5] >> 1) & 0x1f;
	section_number = buf[6];
	last_section_number = buf[7];
	if( last_section_number < section_number )
		return false;

	/* 如果PAT版本号改变，则删除所有PAT/PMT记录，重新生成所有信息 */
	if( m_pat_buf && ( version_number != ((m_pat_buf[5] >> 1) & 0x1f) || 
		last_section_number != m_pat_buf[7] ) )
	{
		OCG_FREE( m_pat_buf );
		m_pat_buf = NULL;
		m_pat_size = 0;

		position = m_pmtmap.GetFirst( &pmt_pid, &spi );
		while( position )
		{
			if( spi.filter )
				m_demux.DestroyFilter( spi.filter );
			if( spi.pmt_buf )
				OCG_FREE( spi.pmt_buf );
			position = m_pmtmap.GetNext( &pmt_pid, &spi, position );
		}
		m_pmtmap.RemoveAll();
	}
	if( m_pat_buf == NULL )
	{
		m_pat_size = ( last_section_number + 1 ) * 1024;
		m_pat_buf = (unsigned char*)OCG_MALLOC( m_pat_size );
		if( m_pat_buf == NULL )
			return false;
		memset( m_pat_buf, 0xff, m_pat_size );
	}

	/* 如果SECTION已经存在，则不必再次处理 */
	p = m_pat_buf;
	while( *p != 0xff && p < m_pat_buf + m_pat_size )
	{
		if( p[6] == section_number )
			return false;
		p += 3 + ((p[1]&0xf)<<8) + p[2];
	}

	/* BUFFER不够大: 不可能出现 */
	if( m_pat_buf + m_pat_size < p + size )
	{
		OCG_ASSERT( false );
		return false;
	}

	/* 保存PAT-SECTION */
	memcpy( p, buf, size );

	/* 提取PMT信息 */
	q = p;
	p += 8;
	while( p + 4 <= q + size - 4 )
	{
		service_id = (p[0]<<8) | p[1];
		p += 2;
		if( service_id != 0 )
		{
			pmt_pid = ((p[0]&0x1f)<<8) | p[1];

			/* 如果是第一次找到该PMT，则添加 */
			if( m_pmtmap.Search( pmt_pid, &spi ) == NULL )
			{
				memset( &spi, 0, sizeof(spi) );

				/* 开始过滤PMT */
				spi.filter = m_demux.CreateFilterExt( pmt_pid, 0x02, 0xff, 
						service_id, 0xffff, 1024, CTsParser::FilterProc, (uint32_t)this );
				/* 添加 */
				m_pmtmap.Insert( pmt_pid, spi );
			}

			/* 如果是第一次找到该SDT，则添加 */
			if( NULL == m_sdtmap.Search( service_id, &sdt ) )
			{
				memset( &sdt, 0, sizeof(sdt) );
				sdt.sdt_service_id = service_id;
				sdt.bIsSucceed = false;

				/* 添加 */
				m_sdtmap.Insert( service_id, sdt );
			}
		}
		p += 2;
	}
	return true;
}

/* PMT-SECTION处理函数 */
bool CTsParser::OnPmtSection( void* filter, unsigned char *buf, int32_t size )
{
	uint16_t pmt_pid;
	SProgInfo *pspi;
	void *position;
	uint32_t crc32, ival;
	int32_t len;
	unsigned char *p;

	/* pat/pmt的尺寸不可能超过1024字节 */
	if( 1024 < size || buf[0] != 0x02 )
		return false;

	/* CRC校验 */
	crc32 = GetCrc32( buf, size-4 );
	memcpy( &ival, buf+size-4, 4 );
	if( ival != htonl(crc32) )
	{
		log_error("mpts pmt crc error!");
		return false;
	}

	position = m_pmtmap.GetFirst( &pmt_pid, NULL );
	while( position )
	{
		pspi = m_pmtmap.GetValue( position );
		if( pspi->filter == filter )
		{
			/* 保存 */
			if( pspi->pmt_buf && pspi->pmt_size < size )
			{
				OCG_FREE( pspi->pmt_buf );
				pspi->pmt_buf = NULL;
			}
			if( pspi->pmt_buf == NULL )
			{
				pspi->pmt_size = size + 1024;
				pspi->pmt_buf = (unsigned char *)OCG_MALLOC( pspi->pmt_size );
			}
			memcpy( pspi->pmt_buf, buf, size );

			p = pspi->pmt_buf;

			len = 3 + ((p[1]&0xf)<<8) + p[2];
			if( size < len || pspi->pmt_size < len )
			{
				OCG_ASSERT( false );
				break;
			}
			break;
		}
		position = m_pmtmap.GetNext( &pmt_pid, NULL, position );
	}
	return true;
}

/* 获得MessageGateway的带宽 */
int32_t CTsParser::ParseMsgGtwInfo( char *url, char *buf, int size, int32_t usetime )
{
	void* pf = NULL;
	int32_t len, val = 0;
	char data[8] = {0};
	char path[256] = {0};
	char* dst;

	if( NULL == url )
		goto ERROR_EXIT;

	memset( path, 0, sizeof(path) );
	sprintf( path, "%s/%s", g_root_dir, url);
	os_dir_trim_path( path );

	pf = g_filecache->OpenFile( path, OS_FILE_READ_ONLY );
	if( NULL == pf )
		goto ERROR_EXIT;

	g_filecache->SeekFile( pf, -8, SEEK_END );
	
	len = g_filecache->ReadFile( pf, data, 4 );
	
	g_filecache->CloseFile(pf);
	memcpy( &val, data, 4 );
	val = htonl(val);

	
	dst = buf;
	dst += sprintf( dst, "<?xml version=\"1.0\" encoding=\"GB2312\"?>\r\n" );
	dst += sprintf( dst, "<ts url=\"%s\" data_bit_rate=\"%d\">\r\n", url, (int32_t)val );
	dst += sprintf( dst, "</ts>\r\n" );

	return ( dst - buf );

ERROR_EXIT:
	return -1;
}

/* 读取数据并分析PSI/SI */
int32_t CTsParser::Parse( char *url, char *buf, int size, int32_t usetime )
{
	unsigned char *p = NULL;
	char *dst;
	uint16_t pid = 0, pmt_pid = 0, service_id = 0, serv_id = 0;;
	void *position/*, *sdt_position*/;
	SProgInfo spi;
	uint32_t now, start_tick;
	bool over;
	int32_t len, timeout = 2001, pcr_complete = 0;
	void *receiver = NULL;
	double pcr_val = 0, TotalBitrate = 0, DataBitrate = 0;
	TSPCRInfo pcr_info;
	TSSDTInfo sdt;

	if( (dst = strstr( url, ".obj" )) != NULL ) {
		return ParseMsgGtwInfo( url, buf, size, usetime );
	}

	if( m_pat_buf )
	{
		OCG_FREE( m_pat_buf );
		m_pat_buf = NULL;
	}
	position = m_pmtmap.GetFirst( &pmt_pid, &spi );
	while( position )
	{
		if( spi.filter )
			m_demux.DestroyFilter( spi.filter );
		if( spi.pmt_buf )
			OCG_FREE( spi.pmt_buf );
		position = m_pmtmap.GetNext( &pmt_pid, &spi, position );
	}
	m_pmtmap.RemoveAll();

	/* 清除SDT信息 */
	position = m_sdtmap.GetFirst( &serv_id, &sdt );
	while( position ) {
		if( sdt.sdt_service_node ) {
			TSSDTServiceInfo *serv_del, *serv_tmp = sdt.sdt_service_node;
			while( serv_tmp ) {
				serv_del = serv_tmp;
				serv_tmp = serv_tmp->next;
				serv_del->next = NULL;
				OCG_FREE( serv_del );
			}
			sdt.sdt_service_node = NULL;
		}
		
		if( sdt.sdt_multilingual_node ) {
			TSSDTMultilingualInfo *multi_del, *multi_tmp = sdt.sdt_multilingual_node;
			while( multi_tmp ) {
				multi_del = multi_tmp;
				multi_tmp = multi_tmp->next;
				multi_del->next = NULL;
				OCG_FREE( multi_del );
			}
			sdt.sdt_multilingual_node = NULL;
		}
		position = m_sdtmap.GetNext( &serv_id, &sdt, position );
	}
	m_sdtmap.RemoveAll();

	serv_id = 0;
	pmt_pid = 0;
	m_head = 0;
	m_tail = 0;

	/* 刚开始 或 协议切换，构造RECEIVER */
	if( receiver )
		g_render->Stop( receiver );
	receiver = g_render->Rebuild( receiver, url );
	if( receiver == NULL )
	{
		log_error( "set parse receiver failed: %s\n", url );
		goto ERROR_EXIT;
	}
	if( g_render->Start( receiver ) != 0 )
		goto ERROR_EXIT;

	os_sys_usleep( 50 );

	now = 0;
	start_tick = os_sys_get_time();

	/* 分析 */
	while( now == 0 || now <= start_tick + timeout )
	{
		/* 将剩余数据移到缓冲区头部 */
		if( 0 < m_head && m_bufsize < m_tail + 188 )
		{
			m_tail -= m_head;
			if( 0 < m_tail )
				memmove( m_buf, m_buf+m_head, m_tail );
			m_head = 0;
		}

		/* 读新的数据 */
		size = g_render->ReadData( receiver, (char*)m_buf+m_tail, m_bufsize-m_tail );
		/* http等速度比较慢,解析时间从取得数据算起 */
		if( 0 < size && 2001 == timeout ) {
			now = 0;
			start_tick = os_sys_get_time();
			timeout = usetime;
		}

		/* 文件结束 */
		if( size < 0 )
			break;

		m_tail += size;

		/* 定位TS头部 */
		p = m_buf + m_head;
		while( p + 188 <= m_buf + m_tail )
		{
			if( p + 376 < m_buf + m_tail )
			{
				if( *p == 0x47 && *(p+188) == 0x47 && *(p+376) == 0x47 )
					break;
			}
			else if( p + 188 < m_buf + m_tail )
			{
				if( *p == 0x47 && *(p+188) == 0x47 )
					break;
			}
			else if( *p == 0x47 )
				break;
			p++;
		}

		/* 处理TS包: 过滤SI/PSI，更改PID */
		while( p + 188 <= m_buf + m_tail )
		{
			if( *p != 0x47 )
			{
				while( *p != 0x47 && p + 188 <= m_buf + m_tail )
					p++;
				continue;
			}

			pid = ((p[1]&0x1f)<<8) | p[2];

			/* 填充包 */
			if( pid == 0x1fff )
			{
			}
			/* PAT-ts */
			else if( pid == 0 )
			{
				/* 创建PAT-SECTION过滤器 */
				if( m_pat_filter == NULL )
				{
					m_pat_filter = m_demux.CreateFilterExt( 
						0, 0, 0xff, 0, 0, 1024, CTsParser::FilterProc, (uint32_t)this );
				}
				m_demux.OnData( p, 188 );
			}
			/* PMT-ts */
			else if( m_pmtmap.Search( pid, NULL ) )
			{
				m_demux.OnData( p, 188 );
			}
			/* SDT, table_id=0x42: actual_transport_stream, 0x46:other_transport_stream */
			else if( pid == 0x11 )
			{
#if 0	//暂时注释掉解析sdt			
				/* 检查sdt的完整性 */
				if( !sdt_compl ) {
					sdt_position = m_sdtmap.GetFirst( &service_id, &sdt );
					while( sdt_position )
					{
						if( !sdt.bIsSucceed )
						{
							sdt_compl = false;
							break;
						}
						sdt_compl = true;
						sdt_position = m_sdtmap.GetNext( &service_id, &sdt, sdt_position );
					}

					if( !sdt_compl ) {
						/* 创建SDT-SECTION过滤器 */
						if( m_sdt_filter == NULL )
						{
							m_sdt_filter = m_demux.CreateFilterExt( 
								0x11, 0x42, 0xff, 0, 0, 1024, CTsParser::FilterProc, (uint32_t)this );
						}
						m_demux.OnData( p, 188 );
					}
				}
#endif
			}

			if( !pcr_complete ) {
				/* 检查当前package是否有pcr */
				pcr_complete = GetPCRInfo( p, 188, &pcr_info );
				if( -1 == pcr_complete ) {
					pcr_complete = 0;
					log_error("analyze pcr error\n");
				}
				else if( 1 == pcr_complete ) {
					pcr_complete = 0;
				}
				else if( 2 == pcr_complete ) {
					/* 由于变量字节的限制，运算偶尔会导致内存溢出，故把27M的1000提到分子 */
					pcr_val = ((pcr_info.pcr_back_base - pcr_info.pcr_first_base) * 300 * 2 + (pcr_info.pcr_back_ext - pcr_info.pcr_first_ext)) / (27 * 1000);
					if( 0 != pcr_val ) {
						TotalBitrate = (pcr_info.pack_num * 188 * 8 * 1000) / pcr_val;
						DataBitrate = (pcr_info.data_pack_num * 188 * 8 * 1000) / pcr_val;
					}
					pcr_complete = 0;
				}
				else if( 3 == pcr_complete ) {
					pcr_val = ((pcr_info.pcr_back_base - pcr_info.pcr_first_base) * 300 * 2 + (pcr_info.pcr_back_ext - pcr_info.pcr_first_ext)) / (27 * 1000);
					if( 0 != pcr_val ) {
						TotalBitrate = (pcr_info.pack_num * 188 * 8 * 1000) / pcr_val;
						DataBitrate = (pcr_info.data_pack_num * 188 * 8 * 1000) / pcr_val;
					}
				}
			}

			p += 188;

			/* 检查PMT的完整性，如果PMT完整就可以输出了 */
			over = true;
			position = m_pmtmap.GetFirst( &pmt_pid, &spi );
			if( position == NULL )
			{
				over = false;
			}
			else
			{
				while( position )
				{
					if( spi.pmt_buf == NULL )
					{
						over = false;
						break;
					}
					position = m_pmtmap.GetNext( &pmt_pid, &spi, position );
				}
			}
			if( over ) {
				if( pcr_info.bIsFindPCR ) {
					if( 3 == pcr_complete ) {
						goto PARSE_END;
					}
					else {
						/* pmt完成，pcr没找足够，改变条件继续找 */
						timeout = usetime / 2;
					}
				}
				else {
					/* pmt完成但一个pcr都没找到，改变条件继续找 */
					timeout = usetime / 2;
				}
			}

#if 0	//暂时注释掉有sdt的结束判断
			if( over ) {
				if( sdt_compl ) {
					if( pcr_info.bIsFindPCR ) {
						if( 3 == pcr_complete ) {
							goto PARSE_END;
						}
						else {
							/* pmt sdt完成，pcr没找足够，改变条件继续找 */
							timeout = usetime / 2;
						}
					}
					else {
						/* pmt sdt完成但一个pcr都没找到，改变条件继续找 */
						timeout = usetime / 2;
					}
				}
				else {
					/* pmt完成，改变条件继续找 */
					timeout = usetime / 2;
				}
			}
#endif
		}
		m_head = p - m_buf;

		now = os_sys_get_time();
		if( now < start_tick )
			now = start_tick;
	}

PARSE_END:

	dst = buf;
	dst += sprintf( dst, "<?xml version=\"1.0\" encoding=\"GB2312\"?>\r\n" );
	if( 0 < TotalBitrate ) {
		dst += sprintf( dst, "<ts url=\"%s\" bit_rate=\"%d\" data_bit_rate=\"%d\">\r\n", url, (int32_t)TotalBitrate, (int32_t)DataBitrate );
	}
	else {
		dst += sprintf( dst, "<ts url=\"%s\">\r\n", url );
	}

	/* 输出 */
	position = m_pmtmap.GetFirst( &pmt_pid, &spi );
	while( position )
	{
		if( spi.pmt_buf )
		{
			p = spi.pmt_buf;
			len = 3 + ( ((p[1]&0xf)<<8) | p[2] );
			service_id = (p[3]<<8) | p[4];
			pid = ((p[8]&0x1f)<<8) | p[9];
			dst += sprintf( dst, "\t<service service_id=\"%d\" pmt_pid=\"%d\" pcr_pid=\"%d\">\r\n",
								service_id, pmt_pid, pid );
			p += 12 + (((p[10]&0xf)<<8) | p[11]);
			while( p < spi.pmt_buf + len - 4 )
			{
				pid = ((p[1]&0x1f)<<8) | p[2];
				dst += sprintf( dst, "\t\t<stream stream_type=\"%d\" pid=\"%d\" />\r\n", p[0], pid );
				p += 5 + (((p[3]&0xf)<<8) | p[4]);
			}
			dst += sprintf( dst, "\t</service>\r\n" );
		}
		position = m_pmtmap.GetNext( &pmt_pid, &spi, position );
	}

#if 0	//有sdt的信息暂时注释掉
	while( position )
	{
		if( spi.pmt_buf )
		{
			p = spi.pmt_buf;
			len = 3 + ( ((p[1]&0xf)<<8) | p[2] );
			service_id = (p[3]<<8) | p[4];
			pid = ((p[8]&0x1f)<<8) | p[9];
			dst += sprintf( dst, "\t<service service_id=\"%d\" pmt_pid=\"%d\" pcr_pid=\"%d\">\r\n",
				service_id, pmt_pid, pid );
			p += 12 + (((p[10]&0xf)<<8) | p[11]);
			while( p < spi.pmt_buf + len - 4 )
			{
				pid = ((p[1]&0x1f)<<8) | p[2];
				dst += sprintf( dst, "\t\t<stream stream_type=\"%d\" pid=\"%d\" />\r\n", p[0], pid );
				p += 5 + (((p[3]&0xf)<<8) | p[4]);
			}

			/* 加入sdt信息 */
			sdt_position = m_sdtmap.GetFirst( &serv_id, &sdt );
			while( sdt_position )
			{	
				if( service_id == sdt.sdt_service_id ) {
					/* service_descriptor */
					sdt_serv_out = sdt.sdt_service_node;
					while( sdt_serv_out ) {
						dst += sprintf( dst, "\t\t<sdt_serv_info service_type=\"%d\" service_provider_name=\"%s\" service_name=\"%s\" />\r\n", 
							sdt_serv_out->sdt_service_type, sdt_serv_out->sdt_service_provider_name, sdt_serv_out->sdt_service_name );
						sdt_serv_out = sdt_serv_out->next;
					}
					
					/* mutilingual_name_descriptor */
					sdt_multi_out = sdt.sdt_multilingual_node;
					while( sdt_multi_out ) {
						dst += sprintf( dst, "\t\t<sdt_language_info iso_language_code=\"0x%3x\" service_provider_name=\"%s\" service_name=\"%s\" />\r\n", 
							sdt_multi_out->sdt_ISO_639_language_code, sdt_multi_out->sdt_service_provider_name, sdt_multi_out->sdt_service_name );
						sdt_multi_out = sdt_multi_out->next;
					}
				}
				sdt_position = m_sdtmap.GetNext( &serv_id, &sdt, sdt_position );
			}
			dst += sprintf( dst, "\t</service>\r\n" );
		}
		position = m_pmtmap.GetNext( &pmt_pid, &spi, position );
	}
#endif
	dst += sprintf( dst, "</ts>\r\n" );
	*dst = 0;
	dst++;

	if (receiver)
	{
		g_render->DestroyReceiver( receiver );
		receiver = NULL;
	}
	return dst - buf;

ERROR_EXIT:
	if (receiver) 
	{
		g_render->DestroyReceiver( receiver );
		receiver = NULL;
	}
	return -1;
}

/* 得到pcr信息出错返回-1，处理一个包返回0，获取到第一个pcr返回1，获取到其它pcr返回2，获取到指定序号的pcr返回3 */
int32_t CTsParser::GetPCRInfo( unsigned char* srcData, int32_t srcSize, TSPCRInfo* pcr_info )
{
	int32_t pid = 0, ret = 0;
	unsigned char* dat = srcData;
	TSPCRInfo* info = pcr_info;

	if( NULL == srcData || NULL == info) 
		return -1;

	pid = ((dat[1]&0x1f)<<8) | dat[2];

	/* 记录两个pcr之间的package */
	if( info->bIsFindPCR ) {
		info->pack_num++;
		/* 去掉空包 */
		if( 0x1fff != pid ) {
			info->data_pack_num++;
		}
	}

	/* 未确定含有pcr的包 */
	if( -1 == info->pcr_ts_PID ) {
		/* 确定有pcr,找到第一个pcr */
		if((0x20 == (*(dat + 3) & 0x30) || 0x30 == (*(dat + 3) & 0x30)) && 
			(6 < *(dat + 4)) && 0x10 == (*(dat + 5) & 0x10)) {
			/* program_clock_reference_base */
			info->pcr_first_base = (*(dat + 6) << 24) | (*(dat + 7) << 16) | (*(dat + 8) << 8) | (*(dat + 9));
			/* program_clock_reference_extention */
			info->pcr_first_ext = ((*(dat + 10) & 0x01) << 8) | *(dat + 11);

			info->bIsFindPCR = true;
			info->pcr_ts_PID = pid;

			ret = 1;
		}	
	}
	/* 已确定含有pcr的包 */
	else {
		if( pid != info->pcr_ts_PID ) {
			ret = 0;
		}
		else {
			/* 确定有pcr,获得后面指定的pcr */
			if((0x20 == (*(dat + 3) & 0x30) || 0x30 == (*(dat + 3) & 0x30)) && 
				(6 < *(dat + 4)) && 0x10 == (*(dat + 5) & 0x10)) {
				
				info->pcr_count++;
				
				/* program_clock_reference_base */
				info->pcr_back_base = (*(dat + 6) << 24) | (*(dat + 7) << 16) | (*(dat + 8) << 8) | (*(dat + 9));
				/* program_clock_reference_extention */
				info->pcr_back_ext = ((*(dat + 10) & 0x01) << 8) | *(dat + 11);
				
				ret = 2;
				
				if( info->pcr_count == info->pcr_num ) {
					ret = 3;	
				}
			}
		}
	}

	return ret;
}

