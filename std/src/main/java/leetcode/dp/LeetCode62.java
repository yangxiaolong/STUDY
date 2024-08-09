package leetcode.dp;

/**
 * @auther yangxiaolong
 * @create 2024/8/9
 */
public class LeetCode62 {
/*
62. 不同路径
数学
动态规划
组合数学

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
问总共有多少条不同的路径？

输入：m = 3, n = 7
输出：28
示例 2：

输入：m = 3, n = 2
输出：3
解释：
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右
3. 向下 -> 向右 -> 向下
示例 3：

输入：m = 7, n = 3
输出：28
示例 4：

输入：m = 3, n = 3
输出：6
 */

    public int uniquePaths(int m, int n) {
        //1.我们令 dp[i][j] 是到达 i, j 最多路径
        //我们用 dp(i,j) 表示从左上角走到 (i,j) 的路径数量，其中 i 和 j 的范围分别是 [0,m) 和 [0,n)。
        //2.动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
        //3.初始化 注意，对于第一行 dp[0][j]，或者第一列 dp[i][0]，由于都是在边界，所以只能为 1

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        printArray(dp);

        return dp[m - 1][n - 1];
    }

    public static void printArray(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("The array is empty or null.");
            return;
        }

        int m = matrix.length; // 行数
        int n = matrix[0].length; // 列数

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(formatNumber(matrix[i][j]) + " ");
            }
            System.out.println(); // 换行
        }
    }

    public static String formatNumber(int number) {
        // 如果数字是一位数，则前面添加一个空格
        if (number >= 0 && number < 10) {
            return " " + number;
        } else {
            // 如果数字是两位数或更多位数，则直接返回数字
            return Integer.toString(number);
        }
    }

    public static void main(String[] args) {
        LeetCode62 solution = new LeetCode62();
        int m = 3, n = 7;
        System.out.println(solution.uniquePaths(m, n));
    }

}
