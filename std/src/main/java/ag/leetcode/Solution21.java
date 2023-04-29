package ag.leetcode;

public class Solution21 {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(4);

		ListNode m1 = new ListNode(1);
		ListNode m2 = new ListNode(3);
		ListNode m3 = new ListNode(4);

		n1.setNext(n2);
		n2.setNext(n3);

		m1.setNext(m2);
		m2.setNext(m3);
		ListNode n = merge(n1, m1);
		System.out.println(n);
	}

	public static ListNode merge(ListNode l1, ListNode l2) {
		ListNode prehead = new ListNode(-1);
		ListNode prev = prehead;
		while (l1 != null && l2 != null) {
			if (l1.getData() <= l2.getData()) {
				prev.setNext(l1);
				prev = l1;
				l1 = l1.getNext();
			} else {
				prev.setNext(l2);
				prev = l2;
				l2 = l2.getNext();
			}
			// prev = prev.getNext();
		}
		prev.setNext(l1 == null ? l2 : l1);
		return prehead.getNext();
	}

}
