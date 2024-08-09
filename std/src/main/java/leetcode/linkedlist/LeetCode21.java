package leetcode.linkedlist;

import leetcode.ListNode;

/**
 * @auther yangxiaolong
 * @create 2024/7/29
 */
public class LeetCode21 {

    /*
    21. 合并两个有序链表
    递归  链表

    将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
    输入：l1 = [1,2,4], l2 = [1,3,4]
    输出：[1,1,2,3,4,4]
    示例 2：

    输入：l1 = [], l2 = []
    输出：[]
    示例 3：

    输入：l1 = [], l2 = [0]
    输出：[0]
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);

        ListNode tail = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        tail.next = list1 == null ? list2 : list1;
        return dummy.next;
    }

}
