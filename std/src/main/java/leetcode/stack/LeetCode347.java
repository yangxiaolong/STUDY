package leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @auther yangxiaolong
 * @create 2024/8/9
 */
public class LeetCode347 {
/*
347. 前 K 个高频元素
数组
哈希表
分治
桶排序
计数
快速选择
排序
堆（优先队列）

给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]

 */

    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count the frequency of each element.
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.merge(num, 1, Integer::sum);
//            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a priority queue to keep track of the top k frequent elements.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a1, a2) -> (a1[1] - a2[1]));

        int finalK = k;
        frequencyMap.forEach((key, value) -> {
            minHeap.offer(new int[]{key, value});
            if (minHeap.size() > finalK) {
                minHeap.poll(); // Remove the least frequent element if heap size exceeds k.
            }
        });

//        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
//            minHeap.offer(new int[]{entry.getKey(), entry.getValue()});
//            if (minHeap.size() > k) {
//                minHeap.poll(); // Remove the least frequent element if heap size exceeds k.
//            }
//        }

        for (int[] ints : minHeap) {
            System.out.println(ints[0] + ":" + ints[1]);
        }

        // Step 3: Extract the top k frequent elements from the priority queue.
        int[] result = new int[k];
        while (--k >= 0) {
            result[k] = minHeap.poll()[0];
        }

        return result;
    }

    public static void main(String[] args) {
        LeetCode347 solution = new LeetCode347();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = solution.topKFrequent(nums, k);
        System.out.print("Top " + k + " frequent elements: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

}
