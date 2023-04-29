package ag.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Solution78 {

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 2, 3 };
		List<List<Integer>> subsets = subsets(nums);
		System.out.println(subsets);
	}

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		backtrack(0, nums, res, new ArrayList<Integer>());
		return res;
	}

	private static void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
		res.add(new ArrayList<>(tmp));
		for (int j = i; j < nums.length; j++) {
			tmp.add(nums[j]);
			backtrack(j + 1, nums, res, tmp);
			tmp.remove(tmp.size() - 1);
		}
	}

}
