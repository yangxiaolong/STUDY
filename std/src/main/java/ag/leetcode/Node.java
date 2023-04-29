package ag.leetcode;

public class Node {

	private int data;

	private ListNode next;

	public Object getData() {
		return data;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node(int data) {
		super();
		this.data = data;
	}

}
