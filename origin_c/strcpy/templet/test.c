#include <stdio.h>
#include "llog_priv.h"

int main()
{
	log();
    sleep(1);
    printf("after sleep\n");
    
    printf("%f\n",5);
    printf("%d\n",5.01);

}
