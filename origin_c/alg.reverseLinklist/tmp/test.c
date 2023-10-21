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
listNode* reverseList(listNode* head)
{
    listNode *pre=NULL, *tmp=NULL;
    
    while(head != NULL)
    {
        tmp = head->next;
        head->next = pre;
        pre = head;
        head = tmp;
    }
    return pre;
}

void dumpList(listNode* head)
{
    listNode *p = head;
    while(p != NULL)
    {
        printf("%d,",p->val);
        p = p->next;
    }
    printf("\n");
}

//head节点也保存数据
listNode* makeList()
{
    int i;
    listNode* q;
    listNode* head = (listNode*)malloc(sizeof(listNode));
    head->val = 0;
    head->next = NULL;

    for(i=10; i>=1; i--)
    {
        q = (listNode*)malloc(sizeof(listNode));
        q->val = i;
        q->next = head->next;
        head->next = q;

    }
    return head;
}

int main()
{
    listNode *head = makeList();
    dumpList(head);

    head = reverseList(head);
    dumpList(head);

}
