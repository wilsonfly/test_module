#include <stdio.h>
#include <stdlib.h>

/*
leetcode 206
给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
 */
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     struct ListNode *next;
 * };
 */

struct ListNode {
    int val;
    struct ListNode *next;
};

struct ListNode* reverseList(struct ListNode* head)
{
    if(head == NULL)
        return NULL;

    struct ListNode* pre=NULL;
    struct ListNode* tmp;

    while(head!=NULL)
    {
        tmp = head->next;
        head->next = pre;
        pre = head;
        head = tmp;
    }
    return pre;
}

void dumpList(struct ListNode* head)
{
    struct ListNode* p = head;

    while(p != NULL)
    {
        printf("%d,",p->val);
        p = p->next;
    }
    printf("\n");
}

//这里的链表不带空头节点
struct ListNode* makeList()
{
    int i;
    struct ListNode* p;
    struct ListNode* q;

    struct ListNode* head = (struct ListNode*)malloc(sizeof(struct ListNode));//头节点
    head->val = 0;
    head->next = NULL;
    p = head;

    for(i=1; i<=10; i++)
    {
        q = (struct ListNode*)malloc(sizeof(struct ListNode));
        q->val = i;
        q->next = NULL;

        p->next = q;
        p = q;
    }
    return head;
}

int main()
{
    struct ListNode *list = makeList();
    dumpList(list);

    //reverseList(list);//list指向位置没变，但已经变成最后一个节点了
    //dumpList(list);
    dumpList(reverseList(list));
}
