#include <stdio.h>
#include "llog_priv.h"

int main()
{
	char* p = "aa";
	char* q = "aa";
	char* s = NULL;
	if( !strcmp(p,q) ) log("equal");

	/*
	 * one NULL param will lead to a seagment fault
	 */
	//if( !strcmp(s,q) ) log("equal");
	//if( !strcmp(p,s) ) log("equal");

}
