package leetcode;

/**
 * @auther yangxiaolong
 * @create 2024/7/28
 */
public class LeetCode19 {

    /*
    19. 删除链表的倒数第 N 个结点
    链表
    双指针

    输入：head = [1,2,3,4,5], n = 2
    输出：[1,2,3,5]
    示例 2：

    输入：head = [1], n = 1
    输出：[]
    示例 3：

    输入：head = [1,2], n = 1
    输出：[1]
     */

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);

        // get length of listNode
        int length = 0;
        ListNode tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }

        // dummy -> 1 -> 2 -> 3 -> 4 -> 5
        // loop i from [1, length - n]
        ListNode cur = dummy;
        for (int i = 1; i <= length - n; i++) {
            cur = cur.next;
        }

        // point cur  = cur.next
        cur.next = cur.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // move fast n step  快指针移动 n 步
        while (n-- > 0) {
            fast = fast.next;
        }

        // move fast to tail, then slow is previous node of last n
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // remove slow.next = slow.next.next
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        removeNthFromEnd(n1, 1);

        /*ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        removeNthFromEnd2(n1, 2);*/
    }

}
