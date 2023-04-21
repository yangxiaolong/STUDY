package ag.leetcode;

public class Solution2 {

	public static ListNode addTwoNumbers(ListNode p, ListNode q) {
		ListNode result = new ListNode();
		ListNode curr = result;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null ? p.getData() : 0);
			int y = (q != null ? q.getData() : 0);
			int sum = x + y + carry;
			carry = sum / 10;
			curr.setNext(new ListNode(sum % 10));
			curr = curr.getNext();
			if (p != null)
				p = p.getNext();
			if (q != null)
				q = q.getNext();
		}
		if (carry == 1) {
			curr.setNext(new ListNode(carry));
		}
		return result.getNext();
	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(2);
		ListNode n2 = new ListNode(4);
		ListNode n3 = new ListNode(9);

		ListNode m1 = new ListNode(5);
		ListNode m2 = new ListNode(6);
		ListNode m3 = new ListNode(4);

		n1.setNext(n2);
		n2.setNext(n3);

		m1.setNext(m2);
		m2.setNext(m3);
		ListNode result = addTwoNumbers(n1, m1);
		System.out.println(result);
	}

}

class ListNode {

	private int data;

	private ListNode next;

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	public ListNode(int data) {
		this.data = data;
	}

	public ListNode() {
	}

	@Override
	public String toString() {
		return "ListNode [data=" + data + ", next=" + next + "]";
	}
	
	

}
