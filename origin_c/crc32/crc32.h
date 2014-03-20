/** 
 * @file crc32.h
 * @brief 32位CRC校验
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
 * @brief 计算CRC校验值
 * 
 * @param buf 指向的缓冲区
 * @param size 缓冲区的长度
 * 
 * @return 成功,返回计算后的校验值;否则,返回-1
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
 * @brief 做CRC校验 
 * 
 * @param buf 指向的缓冲区
 * @param size 缓冲区的长度
 * 
 * @return 若校验成功,返回true;否则,返回false
 */
bool sw_check_buf_crc32( unsigned char *buf, int size );

////////////////////////////////////////////////////////////////////////////////////
#ifdef __cplusplus
};
#endif

#endif //__crc32_H__
