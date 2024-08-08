package leetcode;

/**
 * @auther yangxiaolong
 * @create 2024/8/3
 */
public class LeetCode92 {

    /*
    92. 反转链表 II
    链表
    方法二：一次遍历「穿针引线」反转链表（头插法）

    给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。

    输入：head = [1,2,3,4,5], left = 2, right = 4
    输出：[1,4,3,2,5]
    示例 2：

    输入：head = [5], left = 1, right = 1
    输出：[5]
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;// the previous of left node
        for (int i = 0; i < left - 1; i++) {// move (left - 1) step to the previous of left node
            pre = pre.next;
        }

        ListNode rightNode = pre;// the right node
        int step = right - left + 1;
        while (step-- > 0) {// move (right - left + 1) step to the right node
            rightNode = rightNode.next;
        }

        ListNode leftNode = pre.next;// the left node
        ListNode rightNext = rightNode.next;// the next of right node

        pre.next = null;
        rightNode.next = null;

        reverse(leftNode);

        pre.next = rightNode;
        leftNode.next = rightNext;

        return dummy.next;
    }


    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*
    头插法 反转链表
     */
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;// the previous of left node
        for (int i = 0; i < left - 1; i++) {// move (left - 1) step to the previous of left node
            pre = pre.next;
        }

        ListNode cur = pre.next;
        ListNode next;
        int step = right - left;// 遍历 (right - left) 步
        while (step-- > 0) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummy.next;
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
        reverseBetween(n1, 2, 4);
    }

}
