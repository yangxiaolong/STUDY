package leetcode.dp;

/**
 * @auther yangxiaolong
 * @create 2024/8/9
 */
public class LeetCode63 {

/*
63. 不同路径 II

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
网格中的障碍物和空位置分别用 1 和 0 来表示。

输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
输出：2
解释：3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右

输入：obstacleGrid = [[0,1],[0,0]]
输出：1


1、状态定义：
dp[i][j] 表示走到格子 (i,j) 的方法数。

2、状态转移：
如果网格 (i,j) 上有障碍物，则 dp[i][j] 值为 0，表示走到该格子的方法数为 0；
否则网格 (i,j) 可以从网格 (i−1,j) 或者 网格 (i,j−1) 走过来，
因此走到该格子的方法数为走到网格 (i−1,j) 和网格 (i,j−1) 的方法数之和，即 dp[i,j]=dp[i−1,j]+dp[i,j−1]。

我们可以用一个一维数组来记录到达每一列的路径数量，并且当遇到障碍物时，将该位置的路径数设置为0，因为无法通过该位置。

 */


    /**
     * 计算在存在障碍物的情况下从左上角到右下角的不同路径数量。
     *
     * @param obstacleGrid 一个 m x n 的二维数组，其中 0 表示无障碍，1 表示有障碍物。
     * @return 从左上角到右下角的不同路径数量。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }

        int m = obstacleGrid.length;  // 行数
        int n = obstacleGrid[0].length;  // 列数

        // 如果起点或终点有障碍物，则没有路径
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        // 创建一个二维数组来保存到达每个格子的不同路径数量
        int[][] dp = new int[m][n];

        // 初始化第一行和第一列
        dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[i][0] = (obstacleGrid[i][0] == 0 && dp[i - 1][0] == 1) ? 1 : 0;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = (obstacleGrid[0][j] == 0 && dp[0][j - 1] == 1) ? 1 : 0;
        }

        // 动态规划填充 dp 数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return dp[m - 1][n - 1];  // 返回最后一行最后一列的路径数
    }

    public static void main(String[] args) {
        LeetCode63 solution = new LeetCode63();
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 1}  // 终点有障碍物
        };
        System.out.println(solution.uniquePathsWithObstacles(obstacleGrid));
    }

}
