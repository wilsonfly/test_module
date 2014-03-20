#include <stdio.h>
#include <string.h>
#include "llog_priv.h"

int main()
{
	char url_dvb[256] = "dvb://freq=123&&srate=6875&&qam=64&&mpepid=7238";
	int freq,srate,qam,mpepid;
	char* s;
	s = strstr(url_dvb,"freq=");
	if( s )
		freq = atoi(s+strlen("freq="));
	s = strstr(url_dvb,"srate=");
	if( s )
		srate = atoi(s+strlen("srate="));
	s = strstr(url_dvb,"qam=");
	if( s )
		qam = atoi(s+strlen("qam="));
	s = strstr(url_dvb,"mpepid=");
	if( s )
		mpepid = atoi(s+strlen("mpepid="));

	fprintf(stderr,"freq:%d,srate:%d,qam:%d,mpepid:%d\n",freq,srate,qam,mpepid);

	mpepid = atoi(s+sizeof("mpepid="));
	fprintf(stderr,"freq:%d,srate:%d,qam:%d,mpepid:%d\n",freq,srate,qam,mpepid);

}
