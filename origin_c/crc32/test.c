#include <stdio.h>
#include <fcntl.h>
#include <sys/stat.h>
#include "llog_priv.h"

#define INPUT_FILE "input.txt"
#define OUTPUT_FILE "output.txt"
int main()
{
	unsigned int crc;
	int input_fd = 0;
	int output_fd = 0;
	unsigned int read_size = 0;
	unsigned int write_size = 0;
	unsigned int file_size = 0;
	char buf[1024];
	struct stat file_stat;

	input_fd = open(INPUT_FILE, O_RDONLY);
	if( input_fd < 0 )
	{
		log("open %s fail\n", INPUT_FILE);
		return -1;
	}
	
	if( (output_fd=open(OUTPUT_FILE, O_WRONLY|O_CREAT|O_TRUNC,0666)) < 0 )
	{
		log("open %s fail\n", OUTPUT_FILE);
		return -1;
	}

	if( stat(INPUT_FILE, &file_stat) < 0 )
	{
		log("stat fail\n");
		return -1;
	}
	file_size = file_stat.st_size;

#if 1
	while( (read_size=read(input_fd, buf, sizeof(buf))) > 0 )
	{
		write_size += write(output_fd,buf,read_size);
	}
#endif

	log("file_sieze:%d, write_size:%d\n", file_size, write_size);

	log(" input crc:%u:\n",sw_get_file_crc32(INPUT_FILE));
	log(" input crc:%u:\n",sw_get_file_crc32(OUTPUT_FILE));
}
