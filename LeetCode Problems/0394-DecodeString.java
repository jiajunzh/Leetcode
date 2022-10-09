package problem;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1. Problem
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated 
 * exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, 
 * etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for 
 * those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 *
 * The test cases are generated so that the length of the output will never exceed 105.
 *
 * 2. Examples
 * Example 1
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * 
 * Example 2
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * 
 * Example 3
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 *
 * 3. Constraints
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 */
public class DecodeString {

  /**
   * 1. Approach 
   * Stack
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param s
   * @return
   */
  public String decodeString(String s) {
    final Deque<Integer> counts = new ArrayDeque<>();
    final Deque<StringBuilder> decodedStrings = new ArrayDeque<>();
    StringBuilder currString = new StringBuilder();
    int prev = 0;
    for (char c : s.toCharArray()) {
      if (isDigit(c)) {
        prev = prev * 10 + (c - '0');
      } else if (c == '[') {
        counts.push(prev);
        decodedStrings.push(currString);
        currString = new StringBuilder();
        prev = 0;
      } else if (c == ']') {
        final StringBuilder decodedString = decodedStrings.pop();
        final int count = counts.pop();
        for (int i = 0; i < count; i++) {
          decodedString.append(currString);
        }
        currString = decodedString;
      } else {
        currString.append(c);
      }
    }
    return currString.toString();
  }

  private boolean isDigit(char c) {
    return c >= '0' && c <= '9';
  }
}
