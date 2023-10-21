#include <stdio.h>

typedef struct Node{
    int data;
    struct Node *next;
}Node;

/**
 * 判断是否有环
 * @param head 链表头节点
 */
int isCycle(Node *head) {
    Node *p1 = head;
    Node *p2 = head;
    while (p2!=NULL && p2->next!=NULL){
        p1 = p1->next;
        p2 = p2->next->next;
        if(p1 == p2){
            return 1;
        }
    }
    return 0;
}

void main()
{
    Node node1;
    Node node2;
    Node node3;
    Node node4;
    Node node5;
    node1.data = 5;
    node2.data = 3;
    node3.data = 7;
    node4.data = 2;
    node5.data = 6;
    node1.next = &node2;
    node2.next = &node3;
    node3.next = &node4;
    node4.next = &node5;
    //node5.next = &node2;
    node5.next = NULL;

    printf("isCycle:%d\n",isCycle(&node1));
}
