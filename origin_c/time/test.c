#include <stdio.h>
#include <time.h>
#include "llog_priv.h"

int main()
{
	time_t t;
	char s[32];
	t = time(NULL);
	strftime(s,sizeof(s),"%Y-%m-%d %H:%M:%S",localtime(&t));
	log("%s",s);
}

