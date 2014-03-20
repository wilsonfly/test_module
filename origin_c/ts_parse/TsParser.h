#ifndef __TSPARSER_H__
#define __TSPARSER_H__

#include "Bcl.h"
#include "BclMap.h"
#include "Demux.h"

/* service信息 */
typedef struct
{
	/* pmt_pid */
	uint16_t pmt_pid;
	/* serivce_name */
	char service_name[64];
	/* pmt-section */
	unsigned char *pmt_buf;
	/* pmt-section-buffer-size*/
	int32_t pmt_size;
	/* PMT FILTER */
	void *filter;
}SProgInfo;

/* PCR信息 */
typedef struct tag_TSPCRInfo 
{
	/* 获得的第一个pcr */
	int64_t pcr_first_base;
	int16_t pcr_first_ext;
	/* 含有pcr的package的PID (-1是未指定) */
	int32_t pcr_ts_PID;
	/* 是否已经获得pcr (false没有获得) */
	bool bIsFindPCR;
	
	/* 隔多少个pcr再取一个pcr */
	int32_t pcr_num;
	int32_t pcr_count;
	/* 前后两个pcr之间的总package数目及去掉空包后的数目 */
	int64_t pack_num;
	int64_t data_pack_num;

	/* 后面一个pcr */
	int64_t pcr_back_base;
	int16_t pcr_back_ext;

	tag_TSPCRInfo() {
		pcr_first_base = 0;
		pcr_first_ext = 0;
		pcr_ts_PID = -1;

		bIsFindPCR = false;

		pcr_num = 10;
		pcr_count = 0;
		pack_num = 0;
		data_pack_num = 0;

		pcr_back_base = 0;
		pcr_back_ext = 0;
	}

	~tag_TSPCRInfo() {

	}

}TSPCRInfo;

typedef struct tag_TSSDTServiceInfo
{
	/* service_type (0x01数字电视业务 0x02数字无线声音业务 0x0c数据广播业务) */
	unsigned char sdt_service_type;
	/* service_provider_name以'\0'结尾 */
	char sdt_service_provider_name[128];
	/* service_name以'\0'结尾 */
	char sdt_service_name[128];

	struct tag_TSSDTServiceInfo* next;

}TSSDTServiceInfo;

typedef struct tag_TSSDTMultilingualInfo
{
	int32_t sdt_ISO_639_language_code;
	/* service_provider_name以'\0'结尾 */
	char sdt_service_provider_name[128];
	/* service_name以'\0'结尾 */
	char sdt_service_name[128];

	struct tag_TSSDTMultilingualInfo* next;

}TSSDTMultilingualInfo;

/* 存放SDT信息的结构 */
typedef struct tag_TSSDTInfo
{
	uint16_t sdt_service_id;
	TSSDTServiceInfo* sdt_service_node;
	TSSDTMultilingualInfo* sdt_multilingual_node;
	bool bIsSucceed;

	struct tag_TSSDTInfo* next;

}TSSDTInfo;

/* TS流分析器 */
class CTsParser
{
public:
	CTsParser();
	~CTsParser();

	int32_t Parse( char *url, char *buf, int size, int32_t usetime );
	int32_t ParseMsgGtwInfo( char *url, char *buf, int size, int32_t usetime );

protected:

	/* TS流缓冲区 */
	unsigned char *m_buf;
	/* TS流缓冲区大小 */
	int32_t m_bufsize;
	/* TS流缓冲区中有效数头位置 */
	int32_t m_head;
	/* TS流缓冲区中有效数尾位置 */
	int32_t m_tail;

	/* TS-SECTION过滤器 */
	CDemux m_demux;
	/* PAT-SECTION过滤器 */
	void* m_pat_filter;
	/* ALL PAT-SECTIONS */
	unsigned char *m_pat_buf;
	/* ALL PAT-SECTIONS缓冲区尺寸 */
	int m_pat_size;

	/* SDT-SECTION过滤器 */
	void* m_sdt_filter;
	/* SDT结构 */
	CBclMap<uint16_t, TSSDTInfo> m_sdtmap;

	/* 所有PMT */
	CBclMap<uint16_t, SProgInfo> m_pmtmap;

protected:
	int32_t GetBitrate( char *url );

	/* PAT/PMT-SECTION输出回调函数 */
	static void FilterProc( void* filter, unsigned char *buf, int32_t size, uint32_t lParam );
	bool OnPatSection( void* filter, unsigned char *buf, int32_t size );
	bool OnSdtSection( void* filter, unsigned char *buf, int32_t size );
	bool OnPmtSection( void* filter, unsigned char *buf, int32_t size );

	/* 得到pcr信息出错返回-1，正常处理一个包返回0，pcr信息已完整获得返回1 */
	int32_t GetPCRInfo( unsigned char* srcData, int32_t srcSize, TSPCRInfo* pcr_info );
};

#endif /* __TSPARSER_H__ */
