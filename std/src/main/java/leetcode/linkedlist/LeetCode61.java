package leetcode.linkedlist;

import leetcode.ListNode;

/**
 * @auther yangxiaolong
 * @create 2024/8/2
 */
public class LeetCode61 {

    /*
    61. 旋转链表
    链表
    双指针

    给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置
    输入：head = [1,2,3,4,5], k = 2
    输出：[4,5,1,2,3]
    输入：head = [0,1,2], k = 4
    输出：[2,0,1]
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }

        // 获取链表的len
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {// tail move to tail
            tail = tail.next;
            len++;
        }

        // do nothing
        // k 为 n 的倍数时，新链表将与原链表相同
        if (k % len == 0) {
            return head;
        }

        // 首尾相连
        tail.next = head;

        int rotate = len - k % len;
        while (rotate-- > 0) {
            tail = tail.next;
        }

        //
        ListNode newHead = tail.next;
        tail.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        ListNode result = new LeetCode61().rotateRight(node1, 2);
        System.out.println(result);
    }

}
