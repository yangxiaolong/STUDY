package leetcode.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther yangxiaolong
 * @create 2024/8/6
 */
public class LeetCode17 {

/*
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

1 -> (空)
2 -> (a, b, c)
3 -> (d, e, f)
4 -> (g, h, i)
5 -> (j, k, l)
6 -> (m, n, o)
7 -> (p, q, r, s)
8 -> (t, u, v)
9 -> (w, x, y, z)
0 -> (空)

示例 1：

输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
示例 2：

输入：digits = ""
输出：[]
示例 3：

输入：digits = "2"
输出：["a","b","c"]

 */


    private final Map<Character, String> phoneMap = new HashMap<>();

    public LeetCode17() {
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.isEmpty()) {
            return combinations;
        }
        backtrack(combinations, digits, 0, new StringBuilder());
        return combinations;
    }

    private void backtrack(List<String> combinations, String digits, int index, StringBuilder combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
            return;
        }

        String letters = phoneMap.get(digits.charAt(index));
        for (char letter : letters.toCharArray()) {
            combination.append(letter);
            backtrack(combinations, digits, index + 1, combination);
            combination.deleteCharAt(index);
        }
    }

    public static void main(String[] args) {
        LeetCode17 solution = new LeetCode17();
        List<String> combinations = solution.letterCombinations("234");
        for (String combination : combinations) {
            System.out.println(combination);
        }
    }

}
