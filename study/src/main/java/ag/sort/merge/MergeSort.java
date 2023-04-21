package ag.sort.merge;

import java.util.Arrays;

/**
 * Created by yangxiaolong on 2020\8\17 0017.
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {8, 4, 7, 6, 5, 1, 2, 3};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);//向左递归
            mergeSort(arr, mid + 1, right);//向右递归
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {//将左右两边的数据按照大小填充到temp数组
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //剩下的左边或者右边的数据填充到temp数组
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        //将temp数组复制到arr数组
        System.arraycopy(temp, 0, arr, left, temp.length);
    }

}
