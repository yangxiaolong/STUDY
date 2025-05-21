package leetcode.linkedlist;

import leetcode.ListNode;

/**
 * @auther yangxiaolong
 * @create 2024/8/4
 */
public class LeetCode86 {

    /*
    86. 分隔链表
    链表
    双指针

    给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
    你应当 保留 两个分区中每个节点的初始相对位置。

    输入：head = [1,4,3,2,5,2], x = 3
    输出：[1,2,2,4,3,5]

    输入：head = [2,1], x = 2
    输出：[1,2]

     */
    public static ListNode partition(ListNode head, int x) {
        ListNode dummySmall = new ListNode();
        ListNode tailSmall = dummySmall;
        ListNode dummyBig = new ListNode();
        ListNode tailBig = dummyBig;

        while (head != null) {
            if (head.val < x) {
                tailSmall.next = head;
                tailSmall = tailSmall.next;
            } else {
                tailBig.next = head;
                tailBig = tailBig.next;
            }
            head = head.next;
        }
        tailBig.next = null;
        tailSmall.next = dummyBig.next;

        return dummySmall.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        ListNode partition = LeetCode86.partition(n1, 3);
        System.out.println(partition);
    }

}
