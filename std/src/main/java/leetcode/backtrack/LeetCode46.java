package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/8/8
 */
public class LeetCode46 {
/*
46. 全排列
数组
回溯

给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。

示例 1：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
示例 2：

输入：nums = [0,1]
输出：[[0,1],[1,0]]
示例 3：

输入：nums = [1]
输出：[[1]]
 */


    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] nums, boolean[] used) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));//深度拷贝
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 如果该数字还未被使用
            if (!used[i]) {
                used[i] = true;
                tempList.add(nums[i]);
                // 递归进行下一层排列
                backtrack(result, tempList, nums, used);
                // 回溯撤销选择
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        LeetCode46 solution = new LeetCode46();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = solution.permute(nums);
        for (List<Integer> permutation : permutations) {
            System.out.println(permutation);
        }
    }

}

