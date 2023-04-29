package ag.sort.insert;

import java.util.Arrays;

/**
 * Created by yangxiaolong on 2020\8\20 0020.
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {5, 6, 8, 3, 9, 2, 1, 7};
        sort(arr);
    }

    private static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int j = i - 1;
            while (j >= 0 && insertValue < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = insertValue;
            System.out.println(Arrays.toString(arr));
        }
    }

}
