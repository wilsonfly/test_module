#include "llog_priv.h"

int main()
{
	int times = 5;
	log("%s:%d\n","test my log times",times);
	log("say something here\n");
	log("no need feed_line sign any more");
	log();
	llog();

	log("debug_level:%d",DEBUG_LEVEL );
	log_fetal("in fetal");
	log_error("in error");
	log_warn("in warn");
	log_info("in info");
	log_info("in info,%s","from info");
	llog_info("in info,%s","from info");
	log_debug("in debug");
	log_debug("in debug,%s","from debug");
	llog_debug("in debug,%s","from debug");

}
