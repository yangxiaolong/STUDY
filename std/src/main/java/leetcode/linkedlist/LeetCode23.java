package leetcode.linkedlist;

import leetcode.ListNode;

/**
 * @auther yangxiaolong
 * @create 2024/7/30
 */
public class LeetCode23 {

    /*
    23. 合并 K 个升序链表
    链表
    分治
    堆（优先队列）
    归并排序

    我们可以想到一种最朴素的方法：用一个变量 ans 来维护以及合并的链表，第 i 次循环把第 i 个链表和 ans 合并，答案保存到 ans 中。
    */
    public ListNode mergeKListsLoop(ListNode[] lists) {
        ListNode ans = null;
        for (ListNode list : lists) {
            ans = mergeTwoLists(ans, list);
        }
        return ans;
    }

    /*
    给你一个链表数组，每个链表都已经按升序排列。
    请你将所有链表合并到一个升序链表中，返回合并后的链表。
    示例 1：

    输入：lists = [[1,4,5],[1,3,4],[2,6]]
    输出：[1,1,2,3,4,4,5,6]
    解释：链表数组如下：
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    将它们合并到一个有序链表中得到。
    1->1->2->3->4->4->5->6
    示例 2：

    输入：lists = []
    输出：[]
    示例 3：

    输入：lists = [[]]
    输出：[]
     */

    /*
    方法二：分治合并
    思路

    考虑优化方法一，用分治的方法进行合并。

    将 k 个链表配对并将同一对中的链表合并；
    第一轮合并以后， k 个链表被合并成了k/2个链表，平均长度为2n/k，然后是k/4个链表，
    k/8个链表等等；
    重复这一过程，直到我们得到了最终的有序链表。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }

        if (l > r) {
            return null;
        }

        int mid = (l + r) >> 1;
        ListNode l1 = merge(lists, l, mid);
        ListNode l2 = merge(lists, mid + 1, r);
        return mergeTwoLists(l1, l2);
    }

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
