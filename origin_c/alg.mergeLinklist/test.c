#include <stdio.h>
#include <stdlib.h>

/*
题目描述
翻转一个链表。
输入输出样例
输入一个链表，输出该链表翻转后的结果。
Input: 1->2->3->4->5->nullptr
Output: 5->4->3->2->1->nullptr
 */

typedef struct listNode {
    int val;
    struct listNode *next;
}listNode;

//如果其中一个空了，直接把另外链表剩余部分链接上去即可
listNode* mergeList2(listNode* list1, listNode* list2)
{
    listNode* list3 = (listNode*)malloc(sizeof(listNode));
    listNode* p = list1->next;
    listNode* q = list2->next;
    listNode* k = list3;
    listNode* tmp;

    while(p!=NULL && q!=NULL)
    {
        tmp = p->val<=q->val?p:q;
        k->next = tmp;
        k = tmp;
        if(tmp == p)
            p = p->next;
        else
            q = q->next;
    }
    k->next = p==NULL?q:p;
    return list3;
}

listNode* mergeList(listNode* list1, listNode* list2)
{
    listNode* list3 = (listNode*)malloc(sizeof(listNode));
    listNode* p = list1->next;
    listNode* q = list2->next;
    listNode* k = list3;
    listNode* tmp;

    while(p!=NULL || q!=NULL)
    {
        if(p==NULL)
        {
            k->next = q;
            k = q;
            q = q->next;
        }
        else if(q==NULL)
        {
            k->next = p;
            k = p;
            p = p->next;
        }
        else
        {
            tmp = p->val<=q->val?p:q;
            k->next = tmp;
            k = tmp;
            if(tmp == p)
                p = p->next;
            else
                q = q->next;
        }
    }
    return list3;
}

void dumpList(listNode* head)
{
    listNode* p = head->next;

    while(p != NULL)
    {
        printf("%d,",p->val);
        p = p->next;
    }
    printf("\n");
}

listNode* makeList1()
{
    int i;
    listNode* head = (listNode*)malloc(sizeof(listNode));//头节点
    listNode* p = head;
    listNode* q;

    for(i=1; i<=15; i+=2)
    {
        q = (listNode*)malloc(sizeof(listNode));
        q->val = i;
        q->next = NULL;

        p->next = q;
        p = q;
    }
    return head;
}

listNode* makeList2()
{
    int i;
    listNode* head = (listNode*)malloc(sizeof(listNode));//头节点
    listNode* p = head;
    listNode* q;

    for(i=1; i<=10; i+=3)
    {
        q = (listNode*)malloc(sizeof(listNode));
        q->val = i;
        q->next = NULL;

        p->next = q;
        p = q;
    }
    return head;
}

int main()
{
    listNode *list1 = makeList1();
    listNode *list2 = makeList2();
    dumpList(list1);
    dumpList(list2);

#if 0
    listNode *list3 = mergeList(list1, list2);
    dumpList(list3);

#else
    listNode *list4 = mergeList2(list1, list2);
    dumpList(list4);
#endif

}
