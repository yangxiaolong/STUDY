package leetcode;

/**
 * @auther yangxiaolong
 * @create 2024/8/8
 */
public class LeetCode746 {
/*
746. 使用最小花费爬楼梯
数组
动态规划


给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
一旦你支付此费用，即可选择向上爬一个或者两个台阶。
你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。

请你计算并返回达到楼梯顶部的最低花费。

示例 1：

输入：cost = [10,15,20]
输出：15
解释：你将从下标为 1 的台阶开始。
- 支付 15 ，向上爬两个台阶，到达楼梯顶部。
总花费为 15 。
示例 2：

输入：cost = [1,100,1,1,1,100,1,1,100,1]
输出：6
解释：你将从下标为 0 的台阶开始。
- 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
- 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
- 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
- 支付 1 ，向上爬一个台阶，到达楼梯顶部。
总花费为 6 。

 */

    public int minCostClimbingStairs(int[] cost) {
        //dp[i] = min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])
        int dp0 = 0;
        int dp1 = 0;

        int dpi = 0;
        for (int i = 2; i <= cost.length; i++) {
            dpi = Math.min((dp1 + cost[i - 1]), (dp0 + cost[i - 2]));
            dp0 = dp1;
            dp1 = dpi;
        }
        return dpi;
    }

    public static void main(String[] args) {
        LeetCode746 leetCode746 = new LeetCode746();
        int[] arr = {10, 15, 20};
        System.out.println(leetCode746.minCostClimbingStairs(arr));
    }

}
