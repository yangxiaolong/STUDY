package ag.sort.bubble;

import java.util.Arrays;

/**
 * Created by yangxiaolong on 2020\8\21 0021.
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 34, 23, 6, 78, 1};
        sort(arr);
    }

    private static void sort(int[] arr) {
        // o(n^2)
        int temp;
        boolean flag = false;// 标识变量,标识是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {// 需要进行 length - 1 趟排序
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "次排序后的数组");
            System.out.println(Arrays.toString(arr));
            if (!flag) {// 在一趟排序中,一次交换都没有发生过
                break;
            } else {
                flag = false;// 重置排序,进行下一次判断
            }
        }
    }

}
