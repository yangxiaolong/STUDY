package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/8/8
 */
public class LeetCode47 {
/*
47. 全排列 II
数组
回溯

给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。

示例 1：

输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]
示例 2：

输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

 */


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        Arrays.sort(nums);
        backtrace(rst, new ArrayList<>(), nums, new boolean[nums.length]);
        return rst;
    }

    private void backtrace(List<List<Integer>> rst, ArrayList<Integer> tmp, int[] nums, boolean[] used) {
        if (tmp.size() == nums.length) {
            rst.add(new ArrayList<>(tmp));//深度拷贝
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if ((i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {// 有三个条件
                continue;
            }

            used[i] = true;
            tmp.add(nums[i]);
            backtrace(rst, tmp, nums, used);
            used[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode47 solution = new LeetCode47();
        int[] nums = {1, 1, 2};
        List<List<Integer>> result = solution.permuteUnique(nums);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

}
