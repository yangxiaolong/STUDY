package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/8/6
 */
public class LeetCode77 {

/*
77. 组合
回溯

示例 1：

输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
示例 2：

输入：n = 1, k = 1
输出：[[1]]
 */


    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {
        // 如果当前组合的大小等于k，则添加到结果集中
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        /*
        这个条件的意思是：从start开始，如果剩下的数字数量不足以形成一个大小为k的组合，那么就没有必要继续递归下去了。
        例如，如果我们正在寻找大小为5的组合，而当前已经选择了3个数字，那么从当前位置开始剩下的数字数量至少还需要2个才能构成有效的组合。
        如果剩下的数字数量小于2，我们就可以提前返回，避免无意义的递归。

        请注意，这种剪枝对于这个问题的实际性能提升可能非常有限，因为它本质上是一个全排列问题，
        大部分分支都需要被探索。不过，在某些情况下，这样的剪枝可以帮助减少不必要的计算。
         */
        // 剪枝：如果剩余的数不够组成一个有效的组合，则提前返回
        if (n - start + 1 < k - current.size()) {
            return;
        }

        // 对于每一个可以选择的数字，递归地添加到组合中
        for (int i = start; i <= n; i++) {
            current.add(i); // 选择当前数字
            backtrack(i + 1, n, k, current, result); // 进行下一层选择
            current.remove(current.size() - 1); // 回溯，撤销选择
        }
    }

    public static void main(String[] args) {
        LeetCode77 leetCode77 = new LeetCode77();
        List<List<Integer>> combinations = leetCode77.combine(4, 3);
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }

}