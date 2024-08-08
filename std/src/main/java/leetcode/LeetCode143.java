package leetcode;

/**
 * @auther yangxiaolong
 * @create 2024/8/4
 */
public class LeetCode143 {

    /*
    143. 重排链表
    栈
    递归
    链表
    双指针

    给定一个单链表 L 的头节点 head ，单链表 L 表示为：

    L0 → L1 → … → Ln - 1 → Ln
    请将其重新排列后变为：

    L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
    不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

    输入：head = [1,2,3,4]
    输出：[1,4,2,3]

    输入：head = [1,2,3,4,5]
    输出：[1,5,2,4,3]

     */
    public static void reorderList(ListNode head) {
        //find mid of listNode
        ListNode mid = findMid(head);
        ListNode midNext = mid.next;

        //unlink the second half
        mid.next = null;

        //reverse the second half
        ListNode second = reverse(midNext);

        //merge two list
        mergeTwoList(head, second);
    }

    public static void mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return;
        }

        while (l1 != null && l2 != null) {
            ListNode l1n = l1.next;
            ListNode l2n = l2.next;
            l1.next = l2;
            l2.next = l1n;
            l1 = l1n;
            l2 = l2n;
        }
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
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

    public static ListNode findMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        reorderList(n1);
    }

}