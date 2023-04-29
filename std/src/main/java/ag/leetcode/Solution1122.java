package ag.leetcode;

import java.util.Arrays;

public class Solution1122 {

    /**
     * 给定两个排序后的数组 A 和 B，编写一个方法，将两个数组合并 并排序。
     * A = [1,2,3]
     * B = [2,5,6]
     * 输出: [1,2,2,3,5,6]
     */
    public static void main(String[] args) {
        int[] A = new int[]{1, 5, 7};
        int[] B = new int[]{2, 3, 6, 8, 9};
        Solution1122 my = new Solution1122();
        my.merge(A, B);
    }

    public void merge(int[] A, int[] B) {
        if (A == null) {
            A = new int[0];
        }
        if (B == null) {
            B = new int[0];
        }
        int[] R = new int[A.length + B.length];
        System.arraycopy(A, 0, R, 0, A.length);
        System.arraycopy(B, 0, R, A.length, B.length);
        Arrays.sort(R);
        System.out.println(Arrays.toString(R));
    }


}