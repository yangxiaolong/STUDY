package ag.reverseKGroup;

public class ReverseLinkedList {

    static class LinkedNode {
        Node head;
        Node tail;

        public void add(Node newNode) {
            if (head == null) {
                tail = head = newNode;
            }
            Node t = tail;
            t.next = tail = newNode;
        }

    }

    static void reverse(LinkedNode list) {
        Node head = list.head;
        if (head == null || head.next == null) {
            return;
        }
        Node prev = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        list.head = prev;
        list.tail = head;
    }

    public static void main(String[] args) {
        LinkedNode list = new LinkedNode();
        list.add(new Node(1));
        list.add(new Node(2));
        list.add(new Node(3));
        list.add(new Node(4));
        list.add(new Node(5));
        System.out.println(list.head.toString());

        reverse(list);

        System.out.println(list.head.toString());
    }

    static String reverse(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder reverse = new StringBuilder();
        /*
         * int len = str.length(); for (int i = 0; i <= len - 1; i++) { reverse =
         * str.charAt(i) + reverse; } return reverse;
         */
        char[] arr = str.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            reverse.append(arr[i]);
        }
        return reverse.toString();
    }

}
