package leetcode.linkedlist;

import leetcode.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther yangxiaolong
 * @create 2024/7/28
 */
public class LeetCode160 {

    /*
    160. 相交链表
    哈希表
    链表
    双指针

    给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
    图示两个链表在节点 c1 开始相交：
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode tmp = headA;
        while (tmp != null) {
            set.add(tmp);
            tmp = tmp.next;
        }

        ListNode tmpB = headB;
        while (tmpB != null) {
            if (set.contains(tmpB)) {
                return tmpB;
            }
            tmpB = tmpB.next;
        }

        return null;
    }

}
