#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/stat.h>

int main()
{
	int src_fd,dest_fd;
	char buff[1024];
	int read_len;

	src_fd=open("src_file",O_RDONLY);
	dest_fd=open("dest_file",O_WRONLY|O_CREAT|O_TRUNC,0644);
	if(src_fd<0||dest_fd<0)
	{
		printf("open error\n");
		exit(1);
	}

	lseek(src_fd,-10,SEEK_END);
	while((read_len=read(src_fd,buff,sizeof(buff)))>0)
		write(dest_fd,buff,read_len);

	close(src_fd);
	close(dest_fd);

	return 0;
}

