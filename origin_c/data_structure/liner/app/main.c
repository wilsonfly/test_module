#include <stdio.h>
#include <stdlib.h>
#include "seqstack.h"
#include "linkqueue.h"

/* 
 * check the queue whether in ascending order
 * */
int check(linkqueue *lq)
{
	listnode *p = lq->front->next;
	while(p->next)
	{
		if(p->data > p->next->data)
			return 0;
		p = p->next;
	}
	return 1;
}

int main()
{
	int i, flag = 1, t, time = 0, value;
	linkqueue *lq;
	seqstack *st_min, *st_fivemin, *st_hour;
	lq = linkqueue_create();
	st_min = seqstack_create();
	st_fivemin = seqstack_create();
	st_hour = seqstack_create();

	for(i = 0; i < 27; i++)
		linkqueue_enqueue(lq, i+1);
	linkqueue_display(lq);

	/* 4 11 11*/
	while(flag)
	{
		linkqueue_dequeue(lq, &t);
		time++;
		if(st_min->top != 3)
		{
			seqstack_push(st_min, t);
		}
		else
		{
			while(!seqstack_is_empty(st_min))
			{
				seqstack_pop(st_min, &value);
				linkqueue_enqueue(lq, value);
			}
			if(st_fivemin->top != 10)
				seqstack_push(st_fivemin, t);
			else
			{
				while(!seqstack_is_empty(st_fivemin))
				{
					seqstack_pop(st_fivemin, &value);
					linkqueue_enqueue(lq, value);
				}
				if(st_hour->top != 10)
					seqstack_push(st_hour, t);
				else
				{
					while(!seqstack_is_empty(st_hour))
					{
						seqstack_pop(st_hour, &value);
						linkqueue_enqueue(lq, value);
					}
					linkqueue_enqueue(lq, t);
					if(check(lq))
						break;
				}

			}
		}
	}

	printf("%d \n", time);

	linkqueue_display(lq);

	return 0;
}
