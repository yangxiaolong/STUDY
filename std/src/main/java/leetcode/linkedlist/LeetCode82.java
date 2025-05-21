package leetcode.linkedlist;

import leetcode.ListNode;

/**
 * @auther yangxiaolong
 * @create 2024/8/3
 */
public class LeetCode82 {
    /*
    82. 删除排序链表中的重复元素 II
    链表
    双指针

    给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
    输入：head = [1,2,3,3,4,4,5]
    输出：[1,2,5]

    输入：head = [1,1,1,2,3]
    输出：[2,3]
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    // 跳过当前节点
                    cur.next = cur.next.next;
                }
            } else {
                // 保留当前节点
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(4);
        ListNode n7 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        System.out.println(deleteDuplicates(n1));
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(-101, head);
        ListNode pre = dummy;
        ListNode cur = dummy.next;
        while (cur != null) {
            while (cur.next != null && cur.next.val == cur.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {
                pre = cur;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

}
