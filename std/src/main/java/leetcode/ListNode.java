package leetcode;

import java.util.StringJoiner;

/**
 * @auther yangxiaolong
 * @create 2024/7/28
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return new StringJoiner(" -> ").add("" + val).add("" + next).toString();
    }

}
