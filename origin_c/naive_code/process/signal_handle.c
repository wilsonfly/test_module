#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
void my_fun(int sign_no);

int main()
{
	printf("waiting for signal SIGINT or SIGQUIT\n");
	signal(SIGINT,my_fun);
	signal(SIGQUIT,my_fun);
	pause();
	printf("sleep 3s,then will exit\n");
	sleep(3);
	exit(0);
	return 0;
}
void my_fun(int sign_no)
{
	if(sign_no == SIGINT) //ctrl+c
		printf("I have got SIGINT\n");
	else if(sign_no ==SIGQUIT) /* ctrl+\ */
		printf("I have got SIGQUIT\n");
}
