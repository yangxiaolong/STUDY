package leetcode;

import java.util.StringJoiner;

/**
 * @auther yangxiaolong
 * @create 2024/7/28
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return new StringJoiner(" -> ").add("" + val).add("" + next).toString();
    }

}
