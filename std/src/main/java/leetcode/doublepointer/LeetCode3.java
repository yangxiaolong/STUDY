package leetcode.doublepointer;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther yangxiaolong
 * @create 2024/8/1
 */
public class LeetCode3 {

    /*
    3. 无重复字符的最长子串
    滑动窗口 双指针

    给定一个字符串 s ，请你找出其中不含有重复字符的 最长
    子串
     的长度。

    示例 1:

    输入: s = "abcabcbb"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    示例 2:

    输入: s = "bbbbb"
    输出: 1
    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    示例 3:

    输入: s = "pwwkew"
    输出: 3
    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
         请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     */
    // 解答来源
    // https://leetcode.cn/problems/longest-substring-without-repeating-characters/solutions/227999/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/?envType=study-plan-v2&envId=top-100-liked
    // leetcode下面的评论
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int res = 0;
        char[] cs = s.toCharArray();
        for (int left = 0, right = 0; right < cs.length; right++) {
            char ch = cs[right];

            while (set.contains(ch)) {
                set.remove(cs[left]);
                left++;
            }

            set.add(ch);
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        String input = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(input)); // 输出应为 3
    }

}
