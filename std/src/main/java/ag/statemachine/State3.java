package ag.statemachine;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class State3 {

    private static final Set<Node> set = new LinkedHashSet<>();

    private final static Integer max = 15;
    private final static Integer min = 7;

    public static void main(String[] args) {
        Node node = new Node(0, max, 0, min);
        change(node);
    }

    public static void change(Node node) {
        set.add(node);
        System.out.println(node);
        Integer u1 = node.u1;
        Integer r1 = node.r1;
        Integer u2 = node.u2;
        Integer r2 = node.r2;
        Node n = new Node(max, 0, u2, r2);
        if (!set.contains(n)) {
            System.out.println("装满" + max);
            change(n);
        }
        n = new Node(0, max, u2, r2);
        if (!set.contains(n)) {
            System.out.println("倒空" + max);
            change(n);
        }
        n = new Node(u1, r1, min, 0);
        if (!set.contains(n)) {
            System.out.println("装满" + min);
            change(n);
        }
        n = new Node(u1, r1, 0, min);
        if (!set.contains(n)) {
            System.out.println("倒空" + min);
            change(n);
        }
        if (u1 > r2) {
            n = new Node(u1 - r2, max - (u1 - r2), u2 + r2, 0);
            if (!set.contains(n)) {
                System.out.println(max + "->" + min + " 装满" + min);
                change(n);
            }
        }
        if (u1 < min) {
            n = new Node(0, max, u1, min - u1);
            if (!set.contains(n)) {
                System.out.println(max + "->" + min + " 倒空" + min);
                change(n);
            }
        }
    }

    static class Node {
        Integer u1;//used of max
        Integer r1;//result of max
        Integer u2;//used of min
        Integer r2;//result of min

        public Node(Integer u1, Integer r1, Integer u2, Integer r2) {
            this.u1 = u1;
            this.r1 = r1;
            this.u2 = u2;
            this.r2 = r2;
        }

        @Override
        public String toString() {
            return "(" + u1 + "," + r1 + " " + u2 + "," + r2 + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(u1, node.u1) && Objects.equals(r1, node.r1) && Objects.equals(u2, node.u2) && Objects.equals(r2, node.r2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(u1, r1, u2, r2);
        }
    }
}