package leetcode.stack;

import java.util.Stack;

/**
 * @auther yangxiaolong
 * @create 2024/8/5
 */
public class LeetCode1047 {

/*
1047. 删除字符串中的所有相邻重复项    栈   字符串
给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。

在 S 上反复执行重复项删除操作，直到无法继续删除。

在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。



示例：

输入："abbaca"
输出："ca"
解释：
例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。

 */

    public static String removeDuplicates(String str) {
        Stack<Character> stack = new Stack<>();
        char[] chs = str.toCharArray();
        for (char ch : chs) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
//        while(!stack.isEmpty()) {
//            sb.append(stack.pop());
//        }
        for (Character ch : stack) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "abbaca";  // 示例输入
        String output = removeDuplicates(input);
        System.out.println(output);  // 应输出 "ca"
    }
}
