package ag.leetcode;

public class Solution14 {

	public static void main(String[] args) {
		String[] strs = new String[] { "flower", "flow", "flight" };
		String prefix = longestCommonPrefix(strs);
		System.out.println(prefix);
	}

	public static String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) {
					return "";
				}
			}
		}
		return prefix;
	}

}
