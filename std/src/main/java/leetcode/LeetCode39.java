package leetcode;

/**
 * @auther yangxiaolong
 * @create 2024/8/7
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode39 {

/*
39. 组合总和

给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。
你可以按 任意顺序 返回这些组合。

candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。

对于给定的输入，保证和为 target 的不同组合数少于 150 个。

示例 1：

输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。
示例 2：

输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]
示例 3：

输入: candidates = [2], target = 1
输出: []
 */


    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序是为了避免重复的组合
        backtrack(result, new ArrayList<>(), candidates, target, 0, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] candidates, int target, int start, int sum) {
        if (sum > target) {// 如果当前组合的和已经大于目标值，则提前结束
            return;
        }
        if (sum == target) { // 找到一组解
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 剪枝
            if (i > start && candidates[i] == candidates[i - 1]) continue; // 避免重复组合
            current.add(candidates[i]);
            backtrack(result, current, candidates, target, i, sum + candidates[i]); // 不是i+1，因为每个元素可以重复使用
            current.remove(current.size() - 1); // 回溯
        }
    }

    public static void main(String[] args) {
        LeetCode39 solution = new LeetCode39();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> combinations = solution.combinationSum(candidates, target);

        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }

}
