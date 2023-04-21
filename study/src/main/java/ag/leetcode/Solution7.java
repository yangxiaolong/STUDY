package ag.leetcode;

public class Solution7 {

	public static int reverse(int x) {
		int rev = 0;
		int MAX = Integer.MAX_VALUE;
		int MIN = Integer.MIN_VALUE;
		while (x != 0) {
			int pop = x % 10;
			x = x / 10;
			if ((rev > MAX / 10) || (rev == MAX / 10 && pop > MAX % 10)) {
				return 0;
			}
			if ((rev < MIN / 10) || (rev == MIN / 10 && pop < MIN % 10)) {
				return 0;
			}
			rev = rev * 10 + pop;
		}
		return rev;
	}

}
