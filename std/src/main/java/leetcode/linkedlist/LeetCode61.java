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
        if (head == null) {
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
        if (k % len == 0) {
            return head;
        }

        int rotate = len - k % len;
        tail.next = head;

        while (rotate-- > 0) {
            tail = tail.next;
        }
        ListNode newHead = tail.next;
        tail.next = null;
        return newHead;
    }

}
