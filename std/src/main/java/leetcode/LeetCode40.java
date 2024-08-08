package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/8/7
 */
public class LeetCode40 {

/*
40. 组合总和 II
数组  回溯

给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用 一次 。

注意：解集不能包含重复的组合。

示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,
输出:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
示例 2:

输入: candidates = [2,5,2,1,2], target = 5,
输出:
[
[1,2,2],
[5]
]

 */


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序以便于剪枝
        boolean[] used = new boolean[candidates.length];
        backtrack(result, new ArrayList<>(), candidates, target, 0, used);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start, boolean[] used) {
        if (remain < 0) return; // 超过目标值
        else if (remain == 0) result.add(new ArrayList<>(tempList)); // 找到一组解
        else {
            for (int i = start; i < candidates.length; i++) {
                // 剪枝
                if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) continue; // 避免重复组合
                if (!used[i]) {
                    used[i] = true;
                    tempList.add(candidates[i]);
                    backtrack(result, tempList, candidates, remain - candidates[i], i + 1, used); // 每个元素只能使用一次
                    used[i] = false;
                    tempList.remove(tempList.size() - 1); // 回溯
                }
            }
        }
    }

    public static void main(String[] args) {
        LeetCode40 solution = new LeetCode40();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> combinations = solution.combinationSum2(candidates, target);

        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }

}
