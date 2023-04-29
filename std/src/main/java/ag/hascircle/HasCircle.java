package ag.hascircle;


import ag.reverseKGroup.Node;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by yangxiaolong on 2020\8\10 0010.
 */
public class HasCircle {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(n2);
        boolean b = hasCircle(n1);
        System.out.println(b);
        boolean b1 = slowQuickPoint(n1);
        System.out.println(b1);
        boolean b2 = slowFastPoint2(n1);
        System.out.println(b2);
    }

    private static boolean hasCircle(Node head) {
        if (head == null) {
            return false;
        }
        Set<Node> set = new HashSet<>();
        Node node = head;
        while (node != null) {
            if (set.contains(node)) {
                return true;
            }
            set.add(node);
            node = node.getNext();
        }
        return false;
    }

    private static boolean slowQuickPoint(Node head) {
        if (head == null || head.getNext() == null) {
            return false;
        }
        Node slow = head;
        Node fast = head.getNext();
        while (slow != fast) {
            if (fast == null || fast.getNext() == null) {
                return false;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return true;
    }


    private static boolean slowFastPoint2(Node head) {
        if (head == null || head.getNext() == null) {
            return false;
        }
        Node slow = head;
        Node fast = head.getNext();
        while (slow != fast) {
            if (fast == null || fast.getNext() == null) {
                return false;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return true;
    }


}
