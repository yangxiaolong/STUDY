package ag.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Solution3_1 {

	public static void main(String[] args) {
		int s = lengthOfLongestSubString("abcdefgh");
		System.out.println(s);
	}

	public static int lengthOfLongestSubString(String s) {
		int n = s.length();
		int result = 0;
		for (int i = 0; i <= n - 1; i++) {
			for (int j = i + 1; j <= n; j++) {
				if (allUnqiue(s, i, j)) {
					result = Math.max(result, j - i);
				}
			}
		}
		return result;
	}

	private static boolean allUnqiue(String s, int i, int j) {
		Set<Character> set = new HashSet<>();
		for (int k = i; k < j; k++) {
			char ch = s.charAt(k);
			if (set.contains(ch)) {
				return false;
			}
			set.add(ch);
		}
		return true;
	}

}
