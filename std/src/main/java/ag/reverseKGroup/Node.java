package ag.reverseKGroup;

/**
 * Created by yangxiaolong on 2020\8\10 0010.
 */
public class Node {

    public Node(int data) {
        this.data = data;
    }

    public int data;

    public Node next;

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }

}
