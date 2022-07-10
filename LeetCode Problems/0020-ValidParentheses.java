package problem;

import java.util.Map;
import java.util.Stack;

/**
 * 1. Problem 
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 * 2. Examples 
 * Example 1
 * Input: s = "()"
 * Output: true
 * 
 * Example 2
 * Input: s = "()[]{}"
 * Output: true
 * 
 * Example 3
 * Input: s = "(]"
 * Output: false
 *
 * 3. Constraints
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */
public class ValidParentheses {

  /**
   * 1. Approach 
   * Stack.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * 3. Mistakes 
   * - Check if stack is empty before calling stack.pop(). Edge case => ']'
   * - Final return should be stack.isEmpty() rather than true. Edge case => '['
   */
  private static final Map<Character, Character> PARENTHESES_PAIR = Map.of(')', '(',']', '[', '}', '{');

  public boolean isValid(String s) {
    final Stack<Character> stack = new Stack<>();
    for (final char c : s.toCharArray()) {
      if (PARENTHESES_PAIR.containsKey(c)) {
        if (stack.isEmpty() || PARENTHESES_PAIR.get(c) != stack.pop()) {
          return false;
        }
      } else {
        stack.push(c);
      }
    }
    return stack.isEmpty();
  }
}
