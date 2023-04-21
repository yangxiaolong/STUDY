package ag.dynamic;

import java.util.Arrays;

public class KnapsackProblem {

    public static void main(String[] args) {
        int[] w = { 1, 4, 3 };// 物品的重量
        int[] val = { 1500, 3000, 2000 };// 物品的价值, 这里的val[i] 就是前面讲的v[i]
        int m = 4;// 背包的容量
        int n = val.length;// 物品的个数

        // 创建二维数组,表
        // v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        // 为了记录放入商品的情况,我们定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        // 初始化第一行和第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;// 第一列设置为0
        }
        // 第一行设置为0
        Arrays.fill(v[0], 0);

        // 根据前面得到的公式来动态规划处理
        for (int i = 1; i < v.length; i++) {// 不处理第一行
            for (int j = 1; j < v[0].length; j++) {// 不处理第一列
                // 公式
                if (w[i - 1] > j) {// 因为我们的程序从1开始,所以公式中w[i - 1]修改为w[i- 1]
                    v[i][j] = v[i - 1][j];
                } else {
                    // v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    // 为了记录存放到背包的商品的情况,我们不能使用简单的Math.max(), 需要使用if else
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        // 把当前情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        // 输出一下看情况
        for (int[] ints : v) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        int i = path.length - 1;// 行的最大下标
        int j = path[0].length - 1;// 列的最大下标
        while (i > 0 && j > 0) {// 从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];// 背包容量减1
            }
            i--;
        }
    }

}
