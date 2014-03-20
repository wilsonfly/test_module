#ifndef __DEMUX_H__
#define __DEMUX_H__


#ifdef __cplusplus
extern "C"
{
#endif


/* TS过滤器输出回调函数 */
typedef void (*PTsFilterCallback)( void *handle, void* filter, unsigned char *buf, int32_t size, uint32_t lParam );

/* 创建解复用器 */
void *Demux_Create();

/* 销毁解复用器 */
void Demux_Destroy( void *handle );

/* 创建一个过滤器 */
void* Demux_CreateFilter( void *handle, uint16_t pid, unsigned char *pattern, unsigned char *mask, int32_t len, 
							int32_t qsize, PTsFilterCallback pProc, uint32_t lParam  );

/* 用另外一种方式创建一个过滤器 */
void* Demux_CreateFilterEx( void *handle, uint16_t pid, unsigned char tid, unsigned char tid_mask,
							uint16_t tid_ext, uint16_t tid_ext_mask,
							int32_t qsize, PTsFilterCallback pProc, uint32_t lParam  );
/* 销毁一个过滤器 */
void Demux_DestroyFilter( void *handle, void* filter );

/* 送入TS流数据 */
void Demux_OnData( void *handle, unsigned char *buf, int32_t size );


#ifdef __cplusplus
}
#endif

#endif /* __DEMUX_H__ */
