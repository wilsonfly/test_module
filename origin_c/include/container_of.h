/*
 * @file    container_of.h
 * @brief   get addr between struct_t and member
 * @author  Wilson Sun
 * @history 2013-05-23 created
 */

#ifndef __CONTAINER_OF_H__
#define __CONTAINER_OF_H__


#define offset(type_t,member)  ((size_t) &((type_t*)0)->member )

#define container_of_s( member_addr, type_t, member ) \
	((char*)member_addr - offset(type_t,member))

#define container_of( member_addr, type_t, member ) \
	({ \
	 const typeof( ((type_t*)0)->member )* __member_addr = member_addr; \
	(type_t*)( (char*)__member_addr - offset(type_t,member) ); \
	 })



#endif /* __CONTAINER_OF_H__ */
