#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "llog_priv.h"
#include "container_of.h"

#define ALIGN_BASE 16

#pragma pack (1)  
typedef struct _block_header
{
	char*			real_addr;
	unsigned int	real_size;
	unsigned int	available_size;
	char			butt;
}block_header_t;
#pragma unpack

typedef struct _malloc_block
{
	block_header_t*	p_header;
	char*			p_chunk;
}malloc_block_t;

void* smalloc( size_t size );
void sfree(void* addr);

int main()
{
	int count = 3;
	char* p = NULL;
	while( --count )
	{
		p = smalloc(33);
		log("%p %% %d = %d", p, ALIGN_BASE, ((unsigned int)p)%ALIGN_BASE);
		sfree(p);
	}
	return 0;
}
/************************************************************************************
	|-----------------------------------block---------------------------------------|
	|-------a-------|----b-----|---------------------c------------------------|--d--|
	|      header   |          |                    trunk                           |
	b: 1-(ALIGN_BASE-1) bytes, useless
	d: (ALIGN_BASE-1)-1 bytes, additional bytes to user
	b+d: ALIGN_BASE bytes
************************************************************************************/
void* smalloc( size_t size )
{
	unsigned int real_size;
	char* real_addr = NULL;
	char* chunk_addr = NULL;
	malloc_block_t block;

	memset(&block,0,sizeof(malloc_block_t));

	real_size = size + sizeof(block_header_t) + ALIGN_BASE;
	real_addr = (char*)malloc( real_size );
	if( !real_addr )
	{
		log("malloc failed");
		goto FAILED;
	}
	memset(real_addr, 0, real_size);
//	block.p_header = (block_header_t*)&block;
	block.p_header = (block_header_t*)real_addr;//notice this
	block.p_header->real_addr = real_addr;
	block.p_header->real_size = real_size;
	chunk_addr = real_addr + sizeof(block_header_t) + (ALIGN_BASE - ((unsigned int)real_addr+sizeof(block_header_t))%ALIGN_BASE);
	block.p_header->available_size = real_size - (chunk_addr  - real_addr);
	block.p_header->butt = 0xFF;
	block.p_chunk = chunk_addr;

	log("real_addr:\t\t%p--%p", &block.p_header->real_addr, real_addr);
	log("real_addr:\t\t%p--%p", &block.p_header->real_addr, block.p_header->real_addr);
	log("real_size:\t\t%p--%d", &block.p_header->real_size, block.p_header->real_size);
	log("available_size:\t%p--%d", &block.p_header->available_size, block.p_header->available_size);
	log("butt_addr:\t\t%p--%d", &block.p_header->butt, block.p_header->butt);
	log("p_chunk:\t\t%p--%p", &block.p_chunk, block.p_chunk);

	return (void*)block.p_chunk;

FAILED:
	log("malloc failed");
	return NULL;
}

void sfree(void* addr)
{
	int i = 0;
	block_header_t* *p_block = NULL;
	
	char* p = (char*)addr;
	if( !p )
		return;

	while( *--p == 0 )
		;
	p_block = (block_header_t*)container_of(p, block_header_t, butt);
	log("free addr:%p",p_block);
	if( !p_block )
		free(p_block);
	return;
}
