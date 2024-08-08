package leetcode;

/**
 * @auther yangxiaolong
 * @create 2024/7/28
 */
public class LeetCode206 {

    /*
    206. 反转链表
    递归
    链表
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public static ListNode reverseList1(ListNode head) {
        return reverse(head, null);
    }

    public static ListNode reverse(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        ListNode next = cur.next;
        cur.next = pre;
        return reverse(next, cur);
    }

}
