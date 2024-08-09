package leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/8/7
 */
public class LeetCode90 {

/*
90. 子集 II
位运算
数组
回溯

给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的
子集
（幂集）。

解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。

示例 1：

输入：nums = [1,2,2]
输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
示例 2：

输入：nums = [0]
输出：[[],[0]]
 */


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 对数组进行排序

        backtrack(result, new ArrayList<>(), nums, new boolean[nums.length], 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, boolean[] used, int start) {
        result.add(new ArrayList<>(tempList)); // 添加当前的子集到结果中

        for (int i = start; i < nums.length; i++) {
            // 如果当前元素与前一个元素相同并且前一个元素还没有被添加到当前子集中，则跳过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            // 选择当前元素
            tempList.add(nums[i]);
            used[i] = true;

            // 递归地生成子集
            backtrack(result, tempList, nums, used, i + 1);

            // 回溯，撤销选择
            tempList.remove(tempList.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        LeetCode90 solution = new LeetCode90();
        int[] nums = {1, 2, 2};
        List<List<Integer>> result = solution.subsetsWithDup(nums);
        System.out.println(result);
    }

}