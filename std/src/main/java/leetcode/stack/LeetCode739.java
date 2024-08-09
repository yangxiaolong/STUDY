package leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @auther yangxiaolong
 * @create 2024/8/1
 */
public class LeetCode739 {

    /*
    739. 每日温度   单调栈

    给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
    下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    示例 1:

    输入: temperatures = [73,74,75,71,69,72,76,73]
    输出: [1,1,4,2,1,1,0,0]
    示例 2:

    输入: temperatures = [30,40,50,60]
    输出: [1,1,1,0]
    示例 3:

    输入: temperatures = [30,60,90]
    输出: [1,1,0]
     */
    public static int[] dailyTemperatures(int[] t) {
        int n = t.length;
        int[] r = new int[n];
        Deque<Integer> s = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 当前元素 > s.peek(),s.pop()
            while (!s.isEmpty() && t[i] > t[s.peek()]) {
                int index = s.pop();
                r[index] = i - index;
            }

            // 当前元素 <= s.peek(),s.push()
            s.push(i);
        }
        return r;
    }

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(temperatures);

        // 打印结果
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

}
