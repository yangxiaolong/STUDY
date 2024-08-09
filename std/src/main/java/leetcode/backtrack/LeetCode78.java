package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/8/7
 */
public class LeetCode78 {

/*
78. 子集
位运算
数组
回溯

给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的
子集
（幂集）。

解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

示例 1：

输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
示例 2：

输入：nums = [0]
输出：[[],[0]]


提示：

1 <= nums.length <= 10
-10 <= nums[i] <= 10
nums 中的所有元素 互不相同

 */

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, int start) {
        // 添加当前的子集到结果中
        result.add(new ArrayList<>(tempList));

        for (int i = start; i < nums.length; i++) {
            // 选择当前元素
            tempList.add(nums[i]);
            // 递归地生成子集
            backtrack(result, tempList, nums, i + 1);
            // 回溯，撤销选择
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode78 solution = new LeetCode78();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = solution.subsets(nums);
        System.out.println(result);
    }

}
