package ag.sort.heap;

import java.util.Arrays;

/**
 * Created by yangxiaolong on 2020\5\13 0013.
 */
public class HeapSort {

	public static void main(String[] args) {
		int[] arr = { 3, 6, 7, 1, 99, 87, 56 };
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	private static void sort(int[] arr) {
		int temp;
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustSort(arr, i, arr.length);
		}
		for (int j = arr.length - 1; j > 0; j--) {
			temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustSort(arr, 0, j);
		}
	}

	private static void adjustSort(int[] arr, int i, int length) {
		int temp = arr[i];
		for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
			if (k + 1 < length && arr[k + 1] > arr[k]) {
				k++;
			}
			if (arr[k] > temp) {
				arr[i] = arr[k];
				i = k;
			} else {
				break;
			}
		}
		arr[i] = temp;
	}

}
