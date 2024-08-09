package leetcode.hash;

import java.util.*;

/**
 * @auther yangxiaolong
 * @create 2024/7/22
 */
public class LeetCode49 {

    /*
    49. 字母异位词分组
    数组
    哈希表
    字符串
    排序
    int[] 26个小写字母

    给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
    字母异位词 是由重新排列源单词的所有字母得到的一个新单词。

    示例 1:
    输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
    输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
    示例 2:
    输入: strs = [""]
    输出: [[""]]
    示例 3:
    输入: strs = ["a"]
    输出: [["a"]]
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            char[] chs = str.toCharArray();
            for (char c : chs) {
                counts[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < counts.length; i++) {
                int count = counts[i];
                if (count > 0) {
                    //"abcc" -> "a1b1c2"     "cba" -> "a1b1c1"
                    sb.append((char) ('a' + i)).append(count);
                }
            }

            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        // 遍历数组
        // 获取每个字符串的排序后的字符串
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);
            List<String> list = map.getOrDefault(sortedStr, new ArrayList<>());
            list.add(s);
            map.put(sortedStr, list);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strings = {"eat", "tea", "tan", "ate", "nat", "bat"};
        LeetCode49 leetCode49 = new LeetCode49();
        List<List<String>> list = leetCode49.groupAnagrams1(strings);
        System.out.println(list);
    }

}
