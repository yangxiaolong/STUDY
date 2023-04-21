package ag.leetcode;

import java.util.HashMap;
import java.util.Stack;

public class Solution20 {

	private static HashMap<Character, Character> mappings;

	static {
		mappings = new HashMap<Character, Character>();
		mappings.put(')', '(');
		mappings.put('}', '{');
		mappings.put(']', '[');
	}

	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (mappings.containsKey(c)) {
				char topElement = stack.empty() ? '#' : stack.pop();
				if (topElement != mappings.get(c)) {
					return false;
				}
			} else {
				stack.push(c);
			}
		}
		return stack.isEmpty();
	}

	public static void main(String[] args) {
		isValid("[][[[");
	}
}
