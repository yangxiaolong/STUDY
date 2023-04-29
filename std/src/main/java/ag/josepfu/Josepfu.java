package ag.josepfu;

public class Josepfu {

    private Boy first;

    public static void main(String[] args) {
        Josepfu josepfu = new Josepfu();
        josepfu.addBoy(5);
        josepfu.list();
        josepfu.out(1, 2, 5);
    }

    private void addBoy(int num) {
        if (num < 1) {
            System.out.println("num 值不正确");
            return;
        }
        Boy curBoy = first;
        for (int i = 1; i <= num; i++) {
            Boy node = new Boy(i);
            if (first == null) {
                first = node;
                first.setNext(node);
                curBoy = first;
            } else {
                curBoy.setNext(node);
                curBoy = node;
                node.setNext(first);
            }
        }
    }

    private void list() {
        if (first == null) {
            return;
        }
        Boy curr = first;
        while (true) {
            System.out.println(curr.getNo());
            if (curr.getNext() == first) {
                break;
            }
            curr = curr.getNext();
        }
    }

    private void out(int startNo, int outNum, int nums) {
        if (first == null || startNo < 1 || startNo > nums) {
            return;
        }
        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }

        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (helper != first) {
            for (int i = 0; i < outNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("当前:" + first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("last:" + first.getNo());
    }

}
