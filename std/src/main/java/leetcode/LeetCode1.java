package leetcode;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther yangxiaolong
 * @create 2024/7/30
 */
public class LeetCode1 {

    /*
    1. 两数之和
    hash

    给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，
    并返回它们的数组下标。
    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
    你可以按任意顺序返回答案。

    示例 1：
    输入：nums = [2,7,11,15], target = 9
    输出：[0,1]
    解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。

    示例 2：
    输入：nums = [3,2,4], target = 6
    输出：[1,2]

    示例 3：
    输入：nums = [3,3], target = 6
    输出：[0,1]
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];// 7
            if (map.containsKey(num)) {
                return new int[]{map.get(num), i};// 0,1
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] ints = twoSum(nums, 9);
        System.out.println(Arrays.toString(ints));
    }

}
