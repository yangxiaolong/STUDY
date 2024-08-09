package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/8/7
 */
public class LeetCode216 {

/*
216. 组合总和 III
数组  回溯

找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：

只使用数字1到9
每个数字 最多使用一次
返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。

示例 1:

输入: k = 3, n = 7
输出: [[1,2,4]]
解释:
1 + 2 + 4 = 7
没有其他符合的组合了。
示例 2:

输入: k = 3, n = 9
输出: [[1,2,6], [1,3,5], [2,3,4]]
解释:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
没有其他符合的组合了。
示例 3:

输入: k = 4, n = 1
输出: []
解释: 不存在有效的组合。
在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
 */

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result, 0);
        return result;
    }

    private void backtrack(int start, int k, int target, List<Integer> current, List<List<Integer>> result, int sum) {
        // 如果已经选择了k个数，则检查是否满足目标和
        if (current.size() == k) {
            if (sum == target) {
                result.add(new ArrayList<>(current));
            }
            return;
        }

        // 剪枝：如果剩余的数不够组成一个有效的组合，则提前返回
        if (start > 9 || sum > target) { // 添加了新的剪枝条件
            return;
        }

        // 对于每一个可以选择的数字，递归地添加到组合中
        for (int i = start; i <= 9 - (k - current.size()) + 1; i++) {
//        for (int i = start; i <= 9; i++) {
            current.add(i); // 选择当前数字
            sum += i; // 更新当前组合的和
            backtrack(i + 1, k, target, current, result, sum); // 进行下一层选择
            sum -= i; // 回溯时还原和
            current.remove(current.size() - 1); // 回溯，撤销选择
        }
    }

    public static void main(String[] args) {
        LeetCode216 solution = new LeetCode216();
        List<List<Integer>> combinations = solution.combinationSum3(3, 9);
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }

}
