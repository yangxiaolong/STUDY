package leetcode;

/**
 * @auther yangxiaolong
 * @create 2024/8/8
 */
public class LeetCode509 {

/*
509. 斐波那契数
递归
记忆化搜索
数学
动态规划

斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，
后面的每一项数字都是前面两项数字的和。也就是：

F(0) = 0，F(1) = 1
F(n) = F(n - 1) + F(n - 2)，其中 n > 1
给定 n ，请计算 F(n) 。

示例 1：

输入：n = 2
输出：1
解释：F(2) = F(1) + F(0) = 1 + 0 = 1
示例 2：

输入：n = 3
输出：2
解释：F(3) = F(2) + F(1) = 1 + 1 = 2
示例 3：

输入：n = 4
输出：3
解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 */


    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    public int fib2(int n) {
        // 边界条件
        if (n == 0) return 0;
        if (n == 1) return 1;

        // 创建一个数组用于存储中间结果
        int[] dp = new int[n + 1];

        // 初始化前两个值
        dp[0] = 0;
        dp[1] = 1;

        // 计算从第 2 个到第 n 个斐波那契数
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 返回第 n 个斐波那契数
        return dp[n];
    }

    public int fib3(int n) {
        // 边界条件
        if (n == 0) return 0;
        if (n == 1) return 1;

        int sum = 0;
        int dp0 = 0;
        int dp1 = 1;
        // 计算从第 2 个到第 n 个斐波那契数
        for (int i = 2; i <= n; i++) {
            sum = dp0 + dp1;
            dp0 = dp1;
            dp1 = sum;
        }

        // 返回第 n 个斐波那契数
        return sum;
    }

    public static void main(String[] args) {
        LeetCode509 solution = new LeetCode509();
        System.out.println(solution.fib3(10)); // 输出 55
    }

}
