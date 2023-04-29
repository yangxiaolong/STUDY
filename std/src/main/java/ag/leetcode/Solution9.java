package ag.leetcode;

public class Solution9 {

	public static void main(String[] args) {
		System.out.println(reverse(123321));
	}

	public static boolean reverse(int x) {
		if (x < 0 || x % 10 == 0 && x != 0) {
			return false;
		}
		int rev = 0;
		while (x > rev) {
			rev = rev * 10 + x % 10;
			x = x / 10;
		}
		return rev == x || rev / 10 == x;
	}

}
