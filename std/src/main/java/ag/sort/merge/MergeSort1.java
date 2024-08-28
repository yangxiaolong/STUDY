package ag.sort.merge;

import java.util.Arrays;

/**
 * @auther yangxiaolong
 * @create 2024/8/25
 */
public class MergeSort1 {

    public static void main(String[] args) {
        int[] arr = {8, 4, 7, 6, 5, 1, 2, 3};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, right, mid);
    }

    public static void merge(int[] arr, int left, int right, int mid) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        // Corrected the loop to copy elements back into the original array.
        for (int k = 0; k < temp.length; k++) {
            arr[left + k] = temp[k];
        }
    }

}
