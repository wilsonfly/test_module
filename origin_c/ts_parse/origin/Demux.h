#ifndef __DEMUX_H__
#define __DEMUX_H__


#ifdef __cplusplus
extern "C"
{
#endif


/* TS����������ص����� */
typedef void (*PTsFilterCallback)( void *handle, void* filter, unsigned char *buf, int32_t size, uint32_t lParam );

/* �����⸴���� */
void *Demux_Create();

/* ���ٽ⸴���� */
void Demux_Destroy( void *handle );

/* ����һ�������� */
void* Demux_CreateFilter( void *handle, uint16_t pid, unsigned char *pattern, unsigned char *mask, int32_t len, 
							int32_t qsize, PTsFilterCallback pProc, uint32_t lParam  );

/* ������һ�ַ�ʽ����һ�������� */
void* Demux_CreateFilterEx( void *handle, uint16_t pid, unsigned char tid, unsigned char tid_mask,
							uint16_t tid_ext, uint16_t tid_ext_mask,
							int32_t qsize, PTsFilterCallback pProc, uint32_t lParam  );
/* ����һ�������� */
void Demux_DestroyFilter( void *handle, void* filter );

/* ����TS������ */
void Demux_OnData( void *handle, unsigned char *buf, int32_t size );


#ifdef __cplusplus
}
#endif

#endif /* __DEMUX_H__ */
