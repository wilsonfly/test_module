/** 
 * @file crc32.h
 * @brief 32λCRCУ��
 */

#ifndef __CRC32_H__
#define __CRC32_H__

#ifdef __cplusplus
extern "C"
{
#endif
////////////////////////////////////////////////////////////////////////////////////

#ifndef bool
#define bool char
#endif

/** 
 * @brief ����CRCУ��ֵ
 * 
 * @param buf ָ��Ļ�����
 * @param size �������ĳ���
 * 
 * @return �ɹ�,���ؼ�����У��ֵ;����,����-1
 */
unsigned long sw_get_buf_crc32( unsigned char *buf, int size );

/**
 * @brief calc crc with external crc
 * @return new crc with the buf and external crc
 */
unsigned long sw_get_buf_crc32_ext( unsigned char *buf, int size, unsigned int crc_ext );

/**
 * @brief  calc the crc of file
 * @return crc of file
 */
unsigned long sw_get_file_crc32( char* file );

/** 
 * @brief ��CRCУ�� 
 * 
 * @param buf ָ��Ļ�����
 * @param size �������ĳ���
 * 
 * @return ��У��ɹ�,����true;����,����false
 */
bool sw_check_buf_crc32( unsigned char *buf, int size );

////////////////////////////////////////////////////////////////////////////////////
#ifdef __cplusplus
};
#endif

#endif //__crc32_H__
