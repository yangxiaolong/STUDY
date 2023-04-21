package ag.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		int[] nums = new int[] { 2, 2, 7, 7, 9 };
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
			list.add(i);
			map.put(nums[i], list);
		}

		List<String> result = new ArrayList<String>();
		map.forEach((key, list) -> {
			List<Integer> li = map.get(9 - key);
			if (li != null) {
				list.forEach(s -> {
					li.forEach(t -> {
						result.add("(" + s + "," + t + ")");
					});
				});
				list.clear();
			}
		});
		System.out.println(result);
	}

}
