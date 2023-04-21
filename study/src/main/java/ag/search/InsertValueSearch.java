package ag.search;

/**
 * Created by yangxiaolong on 2020\8\27 0027.
 */
public class InsertValueSearch {

    private static Integer count = 0;

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int search = search(arr, 0, arr.length - 1, 9);
        System.out.println(search);
    }

    private static int search(int[] arr, int left, int right, int findValue) {
        System.out.println("count: " + (++count));
        if (left > right || findValue < arr[left] || findValue > arr[right]) {
            return -1;
        }
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (findValue > midValue) {
            return search(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return search(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

}
