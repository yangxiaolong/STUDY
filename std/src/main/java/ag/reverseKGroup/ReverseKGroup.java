package ag.reverseKGroup;

/**
 * Created by yangxiaolong on 2020\7\27 0027.
 */
public class ReverseKGroup {

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
        Node n = reverse(n1);
        n = reverseKGroup(n, 3);
        n = reverse(n);
        System.out.println(n.toString());
    }

    private static Node reverseKGroup(Node head, int k) {
        if (head == null || head.getNext() == null || k <= 1) {
            return head;
        }
        Node cur = head;
        for (int i = 1; i < k; i++) {
            if ((cur = cur.getNext()) == null) {
                return head;
            }
        }
        Node next = cur.getNext();
        cur.setNext(null);
        Node newHead = reverse(head);
        Node newNext = reverseKGroup(next, k);
        head.setNext(newNext);
        return newHead;
    }

    private static Node reverse(Node head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        Node pre = null;
        Node cur = head;
        Node next;
        while (cur != null) {
            next = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = next;
        }
        return pre;
    }


}
