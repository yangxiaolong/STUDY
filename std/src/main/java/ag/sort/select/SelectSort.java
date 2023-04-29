package ag.sort.select;

import java.util.Arrays;

/**
 * Created by yangxiaolong on 2020\8\20 0020.
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 34, 1, 6, 78, -2 };
        sort(arr);
    }

    private static void sort(int[] arr) {
        // o(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;// 最小值的下标
            int min = arr[i];// 假定的最小值
            for (int j = i + 1; j < arr.length; j++) {// 往后找,找到最小的下标
                if (arr[j] < min) {// 假定的最小值不是最小值
                    min = arr[j];// 重置min
                    index = j;// 变更最小值的下标
                }
            }
            // 将最小值和假定最小值交换
            if (index != i) {
                arr[index] = arr[i];// 下标值设置为假定最小值
                arr[i] = min;// 假定最小值设置为最小值
            }
            System.out.println("第" + (i + 1) + "次排序后的数组");
            System.out.println(Arrays.toString(arr));
        }
    }

}
