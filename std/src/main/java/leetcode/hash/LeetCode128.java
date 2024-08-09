package leetcode.hash;

import java.util.*;

/**
 * @auther yangxiaolong
 * @create 2024/7/22
 */
public class LeetCode128 {

    /*
    128. 最长连续序列
    并查集
    数组
    哈希表

    给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
    <p>
    请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
    */
    /*
    示例 1：

    输入：nums = [100,4,200,1,3,2]
    输出：4
    解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
    示例 2：

    输入：nums = [0,3,7,2,5,8,4,6,0,1]
    输出：9
     */
    public static int longestConsecutive(int[] nums) {
        // 去除重复的元素
        Set<Integer> numSet = new LinkedHashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;
        for (Integer num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentStreak = 1;

                while (numSet.contains(++num)) {//包含下一个元素, 就++
                    currentStreak++;
                }

                longestStreak = Math.max(currentStreak, longestStreak);
            }
        }

        return longestStreak;
    }

    public static int longestConsecutive1(int[] nums) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currLongest = 1;
                while (set.contains(++num)) {
                    currLongest++;
                }
                longest = Math.max(longest, currLongest);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive1(nums));
    }

}
