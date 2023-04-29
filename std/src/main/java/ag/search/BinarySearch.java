package ag.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yangxiaolong on 2020\8\26 0026.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 100, 100, 100, 300 };
        int search = search(arr, 0, arr.length - 1, 100);
        System.out.println(search);
        List<Integer> search2 = search2(arr, 0, arr.length - 1, 100);
        System.out.println(search2);
    }

    private static int search(int[] arr, int left, int right, int findValue) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findValue > midValue) {// 向右递归
            return search(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {// 向左递归
            return search(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

    private static List<Integer> search2(int[] arr, int left, int right, int findValue) {
        if (left > right) {
            return Collections.emptyList();
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findValue > midValue) {// 向右递归
            return search2(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {// 向左递归
            return search2(arr, left, mid - 1, findValue);
        } else {
            List<Integer> rst = new ArrayList<>();
            rst.add(mid);
            int temp = mid - 1;
            while (temp >= 0 && arr[temp] == findValue) {
                rst.add(temp--);
            }
            temp = mid + 1;
            while (temp <= arr.length - 1 && arr[temp] == findValue) {
                rst.add(temp++);
            }
            return rst;
        }
    }

}
