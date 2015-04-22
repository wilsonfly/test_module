#include <stdio.h>
#include "llog_priv.h"


int main()
{

    //sizeof('a') == sizeof(int)
	log("sizeof('a'):%d, sizeof(char):%d, sizeof(int):%d", sizeof('a'), sizeof(char), sizeof(int));

	int size = 0;
#pragma pack () 
//#pragma pack (0)  
#pragma pack (1)  
//#pragma pack (2)  
//#pragma pack (3)  
//#pragma pack (4)  
//#pragma pack (5)  
//#pragma pack (6)  
//#pragma pack (7)  
//#pragma pack (8)  
//#pragma pack (9)  
//#pragma pack (10)  
	struct naturalalign  
	{  
		char a;  
		int b;  
		char c;  
	}struct1;  
#pragma unpack

	size = sizeof(struct1);
	log("size:%d",size);
}
