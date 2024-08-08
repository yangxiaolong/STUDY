package leetcode;

/**
 * @auther yangxiaolong
 * @create 2024/8/2
 */
public class LeetCode1721 {
    /*
    1721. 交换链表中的节点
    链表
    双指针

    给你链表的头节点 head 和一个整数 k 。

    交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）
    输入：head = [1,2,3,4,5], k = 2
    输出：[1,4,3,2,5]
    示例 2：

    输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
    输出：[7,9,6,6,8,7,3,0,9,5]
    示例 3：

    输入：head = [1], k = 1
    输出：[1]
    示例 4：

    输入：head = [1,2], k = 1
    输出：[2,1]
    示例 5：

    输入：head = [1,2,3], k = 2
    输出：[1,2,3]
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(-1, head);
        ListNode kNode = dummy;//正数第 k 个节点
        ListNode fast = dummy;
        ListNode lastKNode = dummy;//倒数第 k 个节点

        //移动k步
        while (k-- > 0) {
            fast = fast.next;
            kNode = kNode.next;
        }

        //fast 移动到null, 此时lastKNode移动到倒数第k个节点
        while (fast != null) {
            fast = fast.next;
            lastKNode = lastKNode.next;
        }

        int temp = kNode.val;
        kNode.val = lastKNode.val;
        lastKNode.val = temp;

        return dummy.next;
    }

}
