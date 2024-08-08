package leetcode;

/**
 * @auther yangxiaolong
 * @create 2024/8/4
 */
public class LeetCode86 {

    /*
    86. 分隔链表
    链表
    双指针

    给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
    你应当 保留 两个分区中每个节点的初始相对位置。

    输入：head = [1,4,3,2,5,2], x = 3
    输出：[1,2,2,4,3,5]

    输入：head = [2,1], x = 2
    输出：[1,2]

     */
    public ListNode partition(ListNode head, int x) {
        ListNode dummySmall = new ListNode();
        ListNode tailSmall = dummySmall;
        ListNode dummyBig = new ListNode();
        ListNode tailBig = dummyBig;

        while (head != null) {
            if (head.val < x) {
                tailSmall.next = head;
                tailSmall = tailSmall.next;
            } else {
                tailBig.next = head;
                tailBig = tailBig.next;
            }
            head = head.next;
        }
        tailBig.next = null;
        tailSmall.next = dummyBig.next;

        return dummySmall.next;
    }

}
