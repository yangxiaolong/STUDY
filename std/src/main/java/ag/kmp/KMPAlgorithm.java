package ag.kmp;

import java.util.Arrays;

public class KMPAlgorithm {

    public static void main(String[] args) {
        String s1 = "BBC ABCDAB ABCDABCDABDE";
        String s2 = "ABCDABD";
        int[] next = kmpNext(s2);
        System.out.println(Arrays.toString(next));
        int index = kmpMatch(s1, s2, next);
        System.out.println(index);
    }

    /**
     * @param str1
     * @param str2
     * @param next
     *            子串对应的部分匹配表
     * @return
     */
    public static int kmpMatch(String str1, String str2, int[] next) {
        // 遍历str1
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 需要考虑不相等的情况str1.charAt(i) != str2.charAt(j)
            // KMP算法核心,去调整j的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {// 找到了
                return i - j + 1;
            }
        }
        return -1;
    }

    // 获取到一个字符串(子串)的部分匹配值表
    public static int[] kmpNext(String dest) {
        // 创建一个 next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;// 如果字符串长度为1,部分匹配值为0
        for (int i = 1, j = 0; i < next.length; i++) {
            // 当dest.charAt(i) != dest.charAt(j), 我们需要从next[j- 1]获取新的j
            // 知道我们发现有dest.charAt(i) == dest.charAt(j)成立才退出,
            // 这是kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            if (dest.charAt(i) == dest.charAt(j)) {// 表示部分匹配值+1
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
