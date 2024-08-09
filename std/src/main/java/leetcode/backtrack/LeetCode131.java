package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther yangxiaolong
 * @create 2024/8/7
 */
public class LeetCode131 {
/*
131. 分割回文串
字符串
动态规划
回溯

 */


    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), s, 0);
        return result;
    }

    private void backtrack(List<List<String>> result, List<String> tempList, String s, int start) {
        // 如果起始位置等于字符串长度，说明找到了一组解
        if (start == s.length()) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            // 如果[start, end]范围内的子串是回文串
            if (isPalindrome(s, start, end)) {
                // 添加当前子串到临时列表
                tempList.add(s.substring(start, end + 1));
                // 递归地对剩余部分进行分割
                backtrack(result, tempList, s, end + 1);
                // 回溯，移除最后一个添加的子串
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode131 solution = new LeetCode131();
        String s = "aabbc";
        List<List<String>> result = solution.partition(s);
        System.out.println(result);
    }

}
