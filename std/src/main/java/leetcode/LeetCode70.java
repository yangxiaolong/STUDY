package leetcode;

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

        int n_1 = putIfAbsent(n - 1, cache);
        int n_2 = putIfAbsent(n - 2, cache);
        return n_1 + n_2;
    }

    public int putIfAbsent(int n, int[] cache) {
        int num = cache[n];
        if (num != 0) {
            return num;
        } else {
            int n_1 = putIfAbsent(n - 1, cache);
            int n_2 = putIfAbsent(n - 2, cache);
            num = n_1 + n_2;
            cache[n] = num;
        }
        return num;
    }

    public static void main(String[] args) {
        LeetCode70 leetCode70 = new LeetCode70();
        System.out.println(leetCode70.climbStairs(2));
    }

}
