/*
 * @file    llog_priv.h
 * @brief   private debug interface
 * @author  Wilson Sun
 * @history 2013-04-13 created
 */

#ifndef __LLOG_PRIV_H__ 
#define __LLOG_PRIV_H__

#include <stdio.h>

/***************** about log & llog ******************/
#ifdef DEBUG
#define log(format,...)		printf("####[%s, %d] " format "\n",__FUNCTION__,__LINE__,##__VA_ARGS__)
#define llog(format,...)	printf("####[%s, %s, %d] " format "\n",__FILE__,__FUNCTION__,__LINE__,##__VA_ARGS__)
#else
#define	log(format,...)			/* can't see me ^o^ */
#define	llog(format,...)		/* can't see me ^o^ */
#endif

/*************** about log_level:0-4 ****************/
#define log_fetal(format,...)	/* can't see me ^o^ */
#define log_error(format,...)	/* can't see me ^o^ */
#define log_warn(format,...)	/* can't see me ^o^ */
#define log_info(format,...)	/* can't see me ^o^ */
#define log_debug(format,...)	/* can't see me ^o^ */
#define llog_fetal(format,...)	/* can't see me ^o^ */
#define llog_error(format,...)	/* can't see me ^o^ */
#define llog_warn(format,...)	/* can't see me ^o^ */
#define llog_info(format,...)	/* can't see me ^o^ */
#define llog_debug(format,...)	/* can't see me ^o^ */

/******************* top_level:0 ********************/
#ifndef DEBUG_LEVEL
#define DEBUG_LEVEL 99
#endif

#if DEBUG_LEVEL <= 4
#undef  log_fetal
#undef  llog_fetal
#define log_fetal(format,...)	log("fetal:"format"!!!",##__VA_ARGS__)
#define llog_fetal(format,...)	llog("fetal:"format"!!!",##__VA_ARGS__)
#endif

#if DEBUG_LEVEL <= 3
#undef  log_error
#undef  llog_error
#define log_error(format,...)	log("error:"format"!!",##__VA_ARGS__)
#define llog_error(format,...)	llog("error:"format"!!",##__VA_ARGS__)
#endif

#if DEBUG_LEVEL <= 2
#undef  log_warn
#undef  llog_warn
#define log_warn(format,...)	log("warn:"format"!",##__VA_ARGS__)
#define llog_warn(format,...)	llog("warn:"format"!",##__VA_ARGS__)
#endif

#if DEBUG_LEVEL <= 1
#undef  log_info
#undef  llog_info
#define log_info(format,...)	log(format,##__VA_ARGS__)
#define llog_info(format,...)	llog(format,##__VA_ARGS__)
#endif

#if DEBUG_LEVEL <= 0
#undef  log_debug
#undef  llog_debug
#define log_debug(format,...)	log(format,##__VA_ARGS__)
#define llog_debug(format,...)	llog(format,##__VA_ARGS__)
#endif


#endif /* __LLOG_PRIV_H__ */
