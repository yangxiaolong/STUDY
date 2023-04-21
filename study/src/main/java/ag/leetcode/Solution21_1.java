package ag.leetcode;

public class Solution21_1 {

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
		merge(n1, m1);
		System.out.println(1);
	}

	public static ListNode merge(ListNode n, ListNode m) {
		if (n == null) {
			return m;
		}
		if (m == null) {
			return n;
		}
		if (n.getData() < m.getData()) {
			n.setNext(merge(n.getNext(), m));
			return n;
		}
		m.setNext(merge(n, m.getNext()));
		return m;
	}

}
