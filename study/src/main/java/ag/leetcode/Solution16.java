package ag.leetcode;

import java.util.Arrays;

public class Solution16 {

	public static void main(String[] args) {
		int[] nums = new int[] { -1, 2, 1, -4 };
		int target = target(nums, 1);
		System.out.println(target);
	}

	public static int target(int[] nums, int target) {
		Arrays.sort(nums);
		int ans = nums[0] + nums[1] + nums[2];
		int n = nums.length;
		for (int i = 0; i < n - 1; i++) {
			int start = i + 1;
			int end = n - 1;
			while (start < end) {
				int sum = nums[i] + nums[start] + nums[end];
				if (Math.abs(target - sum) < Math.abs(target - ans)) {
					ans = sum;
				}
				if (sum > target) {
					end--;
				} else if (sum < target) {
					start++;
				} else {
					return ans;
				}
			}
		}
		return ans;
	}

}
