package leetcode.linkedlist;

import leetcode.ListNode;

/**
 * @auther yangxiaolong
 * @create 2024/7/29
 */
public class LeetCode25 {

    /*
    25. K 个一组翻转链表
    递归
    链表

    给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
    k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
    你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

    输入：head = [1,2,3,4,5], k = 2
    输出：[2,1,4,3,5]

    输入：head = [1,2,3,4,5], k = 3
    输出：[3,2,1,4,5]
     */

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        while (true) {
            ListNode tail = pre;
            for (int i = 1; i <= k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next;
                }
            }

            ListNode h = pre.next;
            ListNode next = tail.next;
            tail.next = null;
            pre.next = null;
            reverse(h);
            pre.next = tail;
            h.next = next;

            pre = h;//进入下一次循环
        }
    }

    private static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);

        ListNode pre = dummy;
        while (pre != null) {

            // check nodessize >= k
            int step = k;
            ListNode tail = pre;
            while (step-- > 0) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next;
                }
            }

            ListNode cur = pre.next;// 注意这一句代码
            step = k - 1;
            while (step-- > 0) {
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }

            pre = cur;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        ListNode n = reverseKGroup2(n1, 3);
        System.out.println(n.toString());
    }

}
