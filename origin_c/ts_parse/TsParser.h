#ifndef __TSPARSER_H__
#define __TSPARSER_H__

#include "Bcl.h"
#include "BclMap.h"
#include "Demux.h"

/* service��Ϣ */
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

/* PCR��Ϣ */
typedef struct tag_TSPCRInfo 
{
	/* ��õĵ�һ��pcr */
	int64_t pcr_first_base;
	int16_t pcr_first_ext;
	/* ����pcr��package��PID (-1��δָ��) */
	int32_t pcr_ts_PID;
	/* �Ƿ��Ѿ����pcr (falseû�л��) */
	bool bIsFindPCR;
	
	/* �����ٸ�pcr��ȡһ��pcr */
	int32_t pcr_num;
	int32_t pcr_count;
	/* ǰ������pcr֮�����package��Ŀ��ȥ���հ������Ŀ */
	int64_t pack_num;
	int64_t data_pack_num;

	/* ����һ��pcr */
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
	/* service_type (0x01���ֵ���ҵ�� 0x02������������ҵ�� 0x0c���ݹ㲥ҵ��) */
	unsigned char sdt_service_type;
	/* service_provider_name��'\0'��β */
	char sdt_service_provider_name[128];
	/* service_name��'\0'��β */
	char sdt_service_name[128];

	struct tag_TSSDTServiceInfo* next;

}TSSDTServiceInfo;

typedef struct tag_TSSDTMultilingualInfo
{
	int32_t sdt_ISO_639_language_code;
	/* service_provider_name��'\0'��β */
	char sdt_service_provider_name[128];
	/* service_name��'\0'��β */
	char sdt_service_name[128];

	struct tag_TSSDTMultilingualInfo* next;

}TSSDTMultilingualInfo;

/* ���SDT��Ϣ�Ľṹ */
typedef struct tag_TSSDTInfo
{
	uint16_t sdt_service_id;
	TSSDTServiceInfo* sdt_service_node;
	TSSDTMultilingualInfo* sdt_multilingual_node;
	bool bIsSucceed;

	struct tag_TSSDTInfo* next;

}TSSDTInfo;

/* TS�������� */
class CTsParser
{
public:
	CTsParser();
	~CTsParser();

	int32_t Parse( char *url, char *buf, int size, int32_t usetime );
	int32_t ParseMsgGtwInfo( char *url, char *buf, int size, int32_t usetime );

protected:

	/* TS�������� */
	unsigned char *m_buf;
	/* TS����������С */
	int32_t m_bufsize;
	/* TS������������Ч��ͷλ�� */
	int32_t m_head;
	/* TS������������Ч��βλ�� */
	int32_t m_tail;

	/* TS-SECTION������ */
	CDemux m_demux;
	/* PAT-SECTION������ */
	void* m_pat_filter;
	/* ALL PAT-SECTIONS */
	unsigned char *m_pat_buf;
	/* ALL PAT-SECTIONS�������ߴ� */
	int m_pat_size;

	/* SDT-SECTION������ */
	void* m_sdt_filter;
	/* SDT�ṹ */
	CBclMap<uint16_t, TSSDTInfo> m_sdtmap;

	/* ����PMT */
	CBclMap<uint16_t, SProgInfo> m_pmtmap;

protected:
	int32_t GetBitrate( char *url );

	/* PAT/PMT-SECTION����ص����� */
	static void FilterProc( void* filter, unsigned char *buf, int32_t size, uint32_t lParam );
	bool OnPatSection( void* filter, unsigned char *buf, int32_t size );
	bool OnSdtSection( void* filter, unsigned char *buf, int32_t size );
	bool OnPmtSection( void* filter, unsigned char *buf, int32_t size );

	/* �õ�pcr��Ϣ������-1����������һ��������0��pcr��Ϣ��������÷���1 */
	int32_t GetPCRInfo( unsigned char* srcData, int32_t srcSize, TSPCRInfo* pcr_info );
};

#endif /* __TSPARSER_H__ */
