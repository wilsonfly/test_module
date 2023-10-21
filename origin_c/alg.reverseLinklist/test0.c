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

//这里的链表都是有个空的头节点的
void reverseList(listNode* head)
{
    listNode* p = head->next;
    listNode* q;

    if(p->next != NULL)
    {
        //首节点变尾节点
        q = p->next;
        p->next = NULL;
        p = q;
    }

    while(p != NULL)
    {
        q = p;
        p = p->next;

        q->next = head->next;
        head->next = q;
    }
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

listNode* makeList()
{
    int i;
    listNode* head = (listNode*)malloc(sizeof(listNode));//头节点
    listNode* p = head;
    listNode* q;

    for(i=1; i<=10; i++)
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
    listNode *list = makeList();
    dumpList(list);

    reverseList(list);
    dumpList(list);

}
