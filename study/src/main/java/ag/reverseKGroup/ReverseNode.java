package ag.reverseKGroup;

import java.util.Arrays;

public class ReverseNode {

	public static Node reverse(Node head) {
		/*
		 * if (head == null || head.getNext() == null) { return head; } Node preNode =
		 * null; Node curNode = head; Node nextNode = null; while (curNode != null) {
		 * nextNode = curNode.getNext(); curNode.setNext(preNode); preNode = curNode;
		 * curNode = nextNode; } return preNode;
		 */
		if (head == null || head.getNext() == null) {
			return head;
		}
		Node newNode = reverse(head.getNext());
		head.getNext().setNext(head);
		head.setNext(null);
		return newNode;
	}

	public static Node reverseKGroup(Node head, int k) {
		if (head == null || head.getNext() == null || k == 1) {
			return head;
		}
		Node cur = head;
		for (int i = 1; i < k && cur != null; i++) {
			cur = cur.getNext();
		}
		if (cur == null) {
			return head;
		}
		Node next = cur.getNext();
		cur.setNext(null);
		Node newHead = reverse(head);
		Node newNext = reverseKGroup(next, k);
		head.setNext(newNext);
		return newHead;
	}

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		n1.setNext(n2);
		n2.setNext(n3);
		n3.setNext(n4);
		n4.setNext(n5);
		n5.setNext(n6);
		n6.setNext(n7);
		n7.setNext(n8);
		Node newNode = reverse(n1);
		System.out.println(Arrays.asList(newNode));
		newNode = reverseKGroup(newNode, 3);
		System.out.println(Arrays.asList(newNode));
		newNode = reverse(newNode);
		System.out.println(Arrays.asList(newNode));
	}

}
