#include <stdio.h>
#include <stdlib.h>
#include "seqstack.h"

int get_pri(int ope)//(1+2)
{
	switch(ope)
	{
		case '(':
			return 0;
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
	}
}

void compute(seqstack *snum, seqstack *sope, int ope)
{
	int n1, n2, n;
	seqstack_pop(snum, &n2);
	seqstack_pop(snum, &n1);
	switch(ope)
	{
		case '+':
			n = n1 + n2;
			break;
		case '-':
			n = n1 - n2;
			break;
		case '*':
			n = n1 * n2;
			break;
		case '/':
			n = n1 / n2;
	}
	seqstack_push(snum, n);
}

void deal_ope(seqstack *snum, seqstack *sope, int ope)//1+2+3
{
	int old_ope;
	if(seqstack_is_empty(sope) || ope == '(')
	{
		seqstack_push(sope, ope);
		return;
	}
	seqstack_top(sope, &old_ope);
	if(get_pri(ope) > get_pri(old_ope))
	{
		seqstack_push(sope, ope);
		return;
	}
	while(get_pri(ope) <= get_pri(old_ope))
	{
		seqstack_pop(sope, &old_ope);
		compute(snum, sope, old_ope);
		if(seqstack_is_empty(sope))
			break;
		seqstack_top(sope, &old_ope);
	}
	seqstack_push(sope, ope);
}

void deal_bracket(seqstack *snum, seqstack *sope)
{
	int old_ope;
	seqstack_top(sope, &old_ope);
	while(old_ope != '(')
	{
		seqstack_pop(sope, &old_ope);
		compute(snum, sope, old_ope);
		seqstack_top(sope, &old_ope);
	}
	seqstack_pop(sope, &old_ope);
}

int main()
{
	seqstack *snum, *sope;
	int i = 0, value = 0, flag = 0;
	char str[] = "(11+3)*2-5";//123+5
	snum = seqstack_create();
	sope = seqstack_create();

	while(str[i] != '\0')
	{
		if(str[i] >= '0' && str[i] <= '9')
		{
			value = 10 * value + str[i] - '0';
			flag = 1;
		}
		else
		{
			if(flag)
			{
				seqstack_push(snum, value);
				value = 0;
				flag = 0;
			}
			if(str[i] == ')')
				deal_bracket(snum, sope);
			else
				deal_ope(snum, sope, str[i]);
		}
		i++;
	}
	if(flag)
		seqstack_push(snum, value);

	int old_ope;
	while(!seqstack_is_empty(sope))
	{
		seqstack_pop(sope, &old_ope);
		compute(snum, sope, old_ope);
	}
	seqstack_pop(snum, &value);
	printf("%s=%d\n", str, value);


	return 0;
}

