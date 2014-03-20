#include <stdio.h>
#include <string.h>
#include "llog_priv.h"

void print_usage();
int check_param(char* n);

int main(int argc,char* argv[])
{
	int i,j;
	int num;
	char* n;
	unsigned long s;
	char result[200];
	char tmp[200];

	if(argc < 3)
	{
		print_usage();
		return -1;
	}

	memset(result, 0, sizeof(result));
	memset(tmp, 0, sizeof(tmp));

	n = argv[1];
	s = atoi(argv[2]);
	log("s:%lu", s);

	//some confirms
	log("usigned long:%lu, unsigned int:%u, int:%d", -1, -1, -1);

	if( (num=check_param(n)) < 0 )
	{
		log("invalid param:%s\n", n);
		print_usage();
		return -1;
	}

	memcpy(tmp,n,strlen(n));

	log("tmp:%s", tmp);

	//when tmp[i]>tmp[i+1] remove tmp[i]
	for( i=0;i<s;i++ )
	{
		if(i>=num)
			break;
		for( j=0;j<num;j++ )
		{
			if( tmp[j]>tmp[j+1] )
				break;
		}
		memcpy(result,tmp,j);
		memcpy(result+j,tmp+j+1,num-j-1);
		result[num-1] = '\0';
		
		//for next round
		memcpy(tmp,result,sizeof(result));

		log("i:%d, num:%d,result:%s ",i,num,result);
	}

	for( i=0;i<num;i++ )
	{
		if( tmp[i] != '0' )
			break;
	}
	memcpy(result,tmp+i,num-i);
	log("result:%s ",result);
}

void print_usage()
{
	log("usage:\n");
	log("\t ./a.out param1  param2\n");
	log("\t param1:decimal num,digita capacity of 200 at most\n");
	log("\t param2:decimal num,digita capacity of 10 at most\n");
	log("\t ./a.out 123405876  3\n");
}

int check_param(char* n)
{
	int i;
	int len = strlen(n);

	for( i=0;i<len && isdigit(*n++);i++ )
		;

	if( i != len )
		return -1;

	return len;
}
