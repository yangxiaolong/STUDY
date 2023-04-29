package ag.search;

public class BinarySearchNoRecur {

	public static void main(String[] args) {
		int[] arr = { 1, 4, 80, 100, 100, 100, 103, 104, 200 };
		int searchNoRecursion = searchNoRecursion(arr, 103);
		System.out.println(searchNoRecursion);
	}

	private static int searchNoRecursion(int[] arr, int value) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (arr[mid] < value) {
				left = mid + 1;
			} else if (arr[mid] > value) {
				right = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

}
