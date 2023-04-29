package ag.sort.heap;

import java.util.Arrays;

/**
 * Created by yangxiaolong on 2020\5\13 0013.
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {3, 6, 7, 1, 99, 87, 56};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        // 1.把无序数组构建成二叉堆。
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            downAdjust(arr, i, arr.length);
        }
        // 2.循环删除堆顶元素，移到集合尾部，调节堆产生新的堆顶。
        for (int j = arr.length - 1; j > 0; j--) {
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            // 下沉调整最大堆
            downAdjust(arr, 0, j);
        }
    }

    /**
     * 下沉调整
     *
     * @param arr         待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length      堆的有效大小
     */
    private static void downAdjust(int[] arr, int parentIndex, int length) {
        // temp保存父节点值，用于最后的赋值
        int temp = arr[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            // 如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex + 1 < length && arr[childIndex + 1] > arr[childIndex]) {
                childIndex++;
            }
            // 如果父节点小于任何一个孩子的值，直接跳出
            if (temp >= arr[childIndex])
                break;
            //无需真正交换，单向赋值即可
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        arr[parentIndex] = temp;
    }

}
