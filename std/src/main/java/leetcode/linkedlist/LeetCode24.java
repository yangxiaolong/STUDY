package leetcode.linkedlist;

import leetcode.ListNode;

/**
 * @auther yangxiaolong
 * @create 2024/7/29
 */
public class LeetCode24 {

    /*
    24. 两两交换链表中的节点
    递归  链表

    给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
    你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。

    输入：head = [1,2,3,4]
    输出：[2,1,4,3]
    示例 2：

    输入：head = []
    输出：[]
    示例 3：

    输入：head = [1]
    输出：[1]
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1, head);
        ListNode cur = dummy;
        ListNode n1;
        ListNode n2;
        while ((n1 = cur.next) != null && (n2 = cur.next.next) != null) {
            cur.next = n2;
            n1.next = n2.next;
            n2.next = n1;

            cur = n1;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        System.out.println(swapPairs(l1));
    }

    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode one = head;
        ListNode two = one.next;
        ListNode three = two.next;

        two.next = one;
        one.next = swapPairs2(three);

        return two;
    }

    public ListNode swapPairs3(ListNode h) {
        if (h == null || h.next == null) {
            return h;
        }
        ListNode d = new ListNode(-1, h);
        ListNode pre = d;
        while (pre != null) {
            ListNode cur = pre.next;
            if (cur != null && cur.next != null) {
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }
            pre = cur;
        }
        return d.next;
    }

}
