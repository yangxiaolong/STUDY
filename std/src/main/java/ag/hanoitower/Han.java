package ag.hanoitower;

/**
 * @auther yangxiaolong
 * @create 2024/6/30
 */
public class Han {

    private static int step = 1;

    public static void main(String[] args) {
        hanoi(5, 'A', 'B', 'C');
    }

    private static void hanoi(int n, char a, char b, char c) {
        if (n == 1) {
            System.out.println("第" + (step++) + "步 " + "第" + n + "个盘从 " + a + " -> " + c);
        } else {
            hanoi(n - 1, a, c, b);
            System.out.println("第" + (step++) + "步 " + "第" + n + "个盘从 " + a + " -> " + c);
            hanoi(n - 1, b, a, c);
        }
    }

}
