package leetcode.doublepointer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @auther yangxiaolong
 * @create 2024/8/5
 */
public class LeetCode42 {

    /*
    42. 接雨水 栈   数组  双指针 动态规划    单调栈

    给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

    输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
    输出：6
    解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
    示例 2：

    输入：height = [4,2,0,3,2,5]
    输出：9
     */
    public int trap(int[] h) {
        int ans = 0;
        Deque<Integer> s = new LinkedList<>();
        int n = h.length;
        for (int i = 0; i < n; ++i) {
            while (!s.isEmpty() && h[i] > h[s.peek()]) {
                int mid = s.pop();
                if (s.isEmpty()) {
                    break;
                }
                // height = min(h[s.peek()], h[i]) - h[mid]
                int height = Math.min(h[s.peek()], h[i]) - h[mid];
                // wide = i - s.peek() - 1
                int wide = i - s.peek() - 1;
                ans = ans + height * wide;
            }
            s.push(i);
        }

        return ans;
    }

    public int trap2(int[] h) {
        int ans = 0;
        int l = 0;
        int r = h.length - 1;
        int pre_l = 0;// 前缀最大值
        int sub_r = 0;// 后缀最大值
        while (l < r) {
            pre_l = Math.max(pre_l, h[l]);
            sub_r = Math.max(sub_r, h[r]);
            if (h[l] < h[r]) {
                ans += pre_l - h[l];
                l++;
            } else {
                ans += sub_r - h[r];
                r--;
            }
        }
        return ans;
    }

}
