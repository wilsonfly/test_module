
#include <stdio.h>

int main()
{
	FILE* fp;
	char buf[]="something\n";

	fp = fopen("data.txt","rw");
	fseek(fp,-1L,SEEK_END);
	fwrite(buf,sizeof(buf),1,fp);
}
