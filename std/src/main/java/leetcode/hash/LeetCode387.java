package leetcode.hash;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther yangxiaolong
 * @create 2024/8/5
 */
public class LeetCode387 {
/*
387. 字符串中的第一个唯一字符   队列 哈希表 字符串 计数
给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。

示例 1：

输入: s = "leetcode"
输出: 0
示例 2:

输入: s = "loveleetcode"
输出: 2
示例 3:

输入: s = "aabb"
输出: -1

 */

    public static int firstUniqChar(String s) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        char[] chs = s.toCharArray();
        for (char ch : chs) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        if (map.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < chs.length; i++) {
            Integer count = map.get(chs[i]);
            if (count == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String input = "leetcode";
        System.out.println("The index of the first unique character is: " + firstUniqChar(input));
    }

}
