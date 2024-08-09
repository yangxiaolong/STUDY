package leetcode.dp;

/**
 * @auther yangxiaolong
 * @create 2024/8/8
 */
public class LeetCode70 {
/*
70. 爬楼梯
记忆化搜索
数学
动态规划

假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

示例 1：

输入：n = 2
输出：2
解释：有两种方法可以爬到楼顶。
1. 1 阶 + 1 阶
2. 2 阶
示例 2：

输入：n = 3
输出：3
解释：有三种方法可以爬到楼顶。
1. 1 阶 + 1 阶 + 1 阶
2. 1 阶 + 2 阶
3. 2 阶 + 1 阶
 */

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int[] cache = new int[n + 1];
        cache[1] = 1;
        cache[2] = 2;

        int n_1 = computeIfAbsent(n - 1, cache);
        int n_2 = computeIfAbsent(n - 2, cache);
        return n_1 + n_2;
    }

    public int computeIfAbsent(int n, int[] cache) {
        int num = cache[n];
        if (num != 0) {
            return num;
        } else {
            int n_1 = computeIfAbsent(n - 1, cache);
            int n_2 = computeIfAbsent(n - 2, cache);
            num = n_1 + n_2;
            cache[n] = num;
        }
        return num;
    }

    public int climbStairs2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        // 创建一个数组用于存储中间结果
        int[] dp = new int[n + 1];

        // 初始化前两个值
        dp[0] = 1;
        dp[1] = 1;

        // 计算从第 2 个到第 n 个斐波那契数
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 返回第 n 个斐波那契数
        return dp[n];
    }

    public int climbStairs3(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int sum = 0;
        int dp1 = 1;
        int dp2 = 2;
        for (int i = 3; i <= n; i++) {
            sum = dp1 + dp2;
            dp1 = dp2;
            dp2 = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        LeetCode70 leetCode70 = new LeetCode70();
        System.out.println(leetCode70.climbStairs3(2));
    }

}
