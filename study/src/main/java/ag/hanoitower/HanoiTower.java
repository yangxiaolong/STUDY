package ag.hanoitower;

public class HanoiTower {

    private static int step = 1;

    public static void main(String[] args) {
        hanoi(5, 'A', 'B', 'C');
    }

    // 使用分治算法
    public static void hanoi(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            move(num, a, c);
        } else {
            // 如果我们有 n >= 2的情况,我们总是可以看成两个盘:1.最下面的一个盘;2.上面的所有盘
            // 1. 先把上面的所有盘 A->B,移动过程会使用到c
            hanoi(num - 1, a, c, b);
            move(num, a, c);
            // 3. 把B盘的所有 从B->C,移动过程会使用到a
            hanoi(num - 1, b, a, c);
        }
    }

    private static void move(int num, char a, char c) {
        System.out.println((step++) + "步" + "第" + num + "个盘从 " + a + "-> " + c);
    }

}
