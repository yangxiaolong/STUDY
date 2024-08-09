package leetcode.linkedlist;

import leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/7/28
 */
public class LeetCode234 {

    /*
    234. 回文链表
    栈
    递归
    链表
    双指针

    给你一个单链表的头节点 head ，请你判断该链表是否为 回文链表
    。如果是，返回 true ；否则，返回 false 。

    输入：head = [1,2,2,1]
    输出：true

    输入：head = [1,2]
    输出：false
    */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        List<Integer> list = new ArrayList<>();
        ListNode tmp = head;
        while (tmp != null) {
            list.add(tmp.val);
            tmp = tmp.next;
        }

        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            if (!list.get(l).equals(list.get(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public boolean isPalindrome3(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        boolean result = chargePalindrome(head, secondHalfStart);

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private boolean chargePalindrome(ListNode first, ListNode second) {
        while (second != null) {
            if (first.val != second.val) {
                return false;
            }
            first = first.next;
            second = second.next;
        }
        return true;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
