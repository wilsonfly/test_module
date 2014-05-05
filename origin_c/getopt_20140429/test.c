#include <stdio.h>
#include "llog_priv.h"
#include <unistd.h>

extern char *optarg;
extern int optind, opterr, optopt;

int main(int argc, char**argv )
{
	int ch = 0;
	int i ;
	char optstring[] = "ab:cd::";

	for( i=0; i<argc; i++)
	{
		log("argc:%d, i:%d, argv:%s", argc, i, argv[i]);
	}
	log("optstring[%s]",optstring);

	//opterr = 0;
	while( (ch=getopt(argc,argv,optstring)) != -1)
	{
		log("optind:%d,optopt:%d,opterr:%d,optarg:%s,argv[optind-1]:%s", optind, optopt, opterr, optarg, argv[optind-1]);
		switch(ch)
		{
		case 'a':
			log("option a");
			break;
		case 'b':
			log("option b, args:%s", optarg);
			break;
		case 'c':
			log("option c");
			break;
		case 'd':
			log("option d, args:%s", optarg);
			break;
		default:
			log("option default");
			break;
		}
	}

	log("end");
}
