package problem;

/**
 * 1. Problem 
 * An alphabetical continuous string is a string consisting of consecutive letters in the alphabet. In other words, 
 * it is any substring of the string "abcdefghijklmnopqrstuvwxyz".
 *
 * For example, "abc" is an alphabetical continuous string, while "acb" and "za" are not.
 * Given a string s consisting of lowercase letters only, return the length of the longest alphabetical continuous 
 * substring.
 *
 * 2. Examples
 * Example 1
 * Input: s = "abacaba"
 * Output: 2
 * Explanation: There are 4 distinct continuous substrings: "a", "b", "c" and "ab".
 * "ab" is the longest continuous substring.
 * 
 * Example 2
 * Input: s = "abcde"
 * Output: 5
 * Explanation: "abcde" is the longest continuous substring.
 *
 * 3. Constraints
 * 1 <= s.length <= 105
 * s consists of only English lowercase letters.
 */
public class LengthOfTheLongestAlphabeticalContinuousSubstring {

  /**
   * 1. Approach 
   * Sliding Window
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param s
   * @return
   */
  public int longestContinuousSubstring(String s) {
    final int n = s.length();
    int maxLength = 1;
    int count = 1;
    for (int i = 1; i < n; i++) {
      if (s.charAt(i) - s.charAt(i - 1) == 1) {
        count++;
        maxLength = Math.max(maxLength, count);
      } else {
        count = 1;
      }
    }
    return maxLength;
  }
}
