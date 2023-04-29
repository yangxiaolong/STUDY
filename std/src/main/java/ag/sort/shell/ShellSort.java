package ag.sort.shell;

import java.util.Arrays;

/**
 * Created by yangxiaolong on 2020\3\12 0012.
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] arr = { 3, 6, 7, 1, 99, 87, 56 };
		int len = arr.length;
		for (int gap = len / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < len; i++) {
				int index = i;
				int insert = arr[index];
				while (index - gap >= 0 && insert < arr[index - gap]) {
					arr[index] = arr[index - gap];
					index -= gap;
				}
				arr[index] = insert;
			}
		}
		System.out.println(Arrays.toString(arr));
	}

}
