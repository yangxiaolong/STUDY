package ag.leetcode;

public class Solution61 {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
		n4.setNext(n5);
		ListNode rotateRight = rotateRight(n1, 2);
		System.out.println(rotateRight);
	}

	private static ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.getNext() == null) {
			return head;
		}
		ListNode oldTail = head;
		int n = 1;
		while (oldTail.getNext() != null) {
			oldTail = oldTail.getNext();
			n++;
		}
		oldTail.setNext(head);
		ListNode newTail = head;
		int len = n - k % n;
		for (int i = 1; i < len; i++) {
			newTail = newTail.getNext();
		}
		ListNode newHead = newTail.getNext();
		newTail.setNext(null);
		return newHead;
	}

}
