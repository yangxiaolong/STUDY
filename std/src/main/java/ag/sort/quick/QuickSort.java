package ag.sort.quick;

import java.util.Arrays;

/**
 * Created by yangxiaolong on 2020\4\22 0022.
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = { -15, 55, 0, -10, 0, 49, -90, 0, 0 };
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int left, int right) {
        int l = left;// 左下标
        int r = right;// 右下标
        // 中轴值
        int pivot = arr[(l + r) / 2];// pivot 中轴
        // while循环的目的是让比pivot值小的放到左边
        // 比pivot值大的放到右边
        while (l < r) {
            // 在pivot的左边一直找,找到大于等于pivot的值,才退出
            while (arr[l] < pivot) {
                l++;
            }
            // 在pivot的右边一直找,找到小于等于pivot的值,才退出
            while (arr[r] > pivot) {
                r--;
            }
            // 如果l >= r,说明pivot的左右两边的值,已经按照左边全部小于等于pivot
            // 右边全部大于等于pivot
            if (l >= r) {
                break;
            }

            // 如果前面条件不满足,就要交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完之后,发现arr[l] == pivot, r--,前移
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换完之后,发现arr[r] == pivot, l++,后移
            if (arr[r] == pivot) {
                l++;
            }
        }
        // 如果l == r,必须l++,r--;否则会出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (l < right) {
            quickSort(arr, l, right);
        }
    }

}
