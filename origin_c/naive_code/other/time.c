#include<stdio.h>
#include<time.h>
#include<errno.h>
#include<string.h>
#include<unistd.h>
#define N 40
int main(int argc,char **argv)
{
	FILE *fp;
	int line=0;
	char buf[N];
	if((fp=fopen("test.txt","a+"))==NULL)
	{
		fprintf(stderr,"fopen failed:%s\n",strerror(errno));
		return -1;
	}
	while(fgets(buf,N,fp)!=NULL)
	{	
		if(strlen(buf)<N-1||buf[N-2]=='\n')
			line++;
	}
	while(1)
	{
		time_t t;
		time(&t);
		struct tm *t1;
		t1=localtime(&t);
		sprintf(buf,"%d  %d/%d/%d %d:%d:%d\n",++line ,t1->tm_year+1900,
			t1->tm_mon+1,
			t1->tm_mday,
			t1->tm_hour,
			t1->tm_min,
			t1->tm_sec);

		printf("%s",buf);
		fputs(buf,fp);
		fflush(fp);
		sleep(1);
	}
	fclose(fp);
	return 0;
}
