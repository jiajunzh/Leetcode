package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * 2. Examples
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * 3. Constraints
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatingCharacters {

  /**
   * Sliding Window.
   * 
   * Time O(2N)
   * Space O(C)
   * 
   * @param s
   * @return
   */
  public static int lengthOfLongestSubstring(String s) {
    final boolean[] charsInWindow = new boolean[128];

    int left = 0;
    int right = 0;
    int maxLength = 0;

    while (right < s.length()) {
      char rightChar = s.charAt(right);

      while (charsInWindow[rightChar]) {
        char leftChar = s.charAt(left);
        charsInWindow[leftChar] = false;
        left++;
      }

      charsInWindow[rightChar] = true;
      maxLength = Math.max(right - left + 1, maxLength);
      right++;
    }

    return maxLength;
  }

  /**
   * Optimized solution. Keep a HashMap to quickly find the next position if the right
   * pointer hit the duplicate chars instead of moving left pointer one by one.
   * 
   * Mistake:
   * 1. Index in Map is not updated for each due to skipping left pointers.
   * Wrong Way: left = map.get(rightChar) + 1
   * Correction: left = Math.max(map.get(rightChar) + 1, left);
   * 
   * Time O(N)
   * Space O(N)
   * 
   * @param s
   * @return
   */
  public static int lengthOfLongestSubstring2(String s) {
    final Map<Character, Integer> map = new HashMap<>();
    int left = 0;
    int right = 0;
    int maxLength = 0;

    while (right < s.length()) {
      char rightChar = s.charAt(right);

      if (map.containsKey(rightChar)) {
        left = Math.max(map.get(rightChar) + 1, left);
      }

      maxLength = Math.max(maxLength, right - left + 1);
      map.put(rightChar, right);
      right++;
    }

    return maxLength;
  }
  
  public static void main(String[] args) {
    assert(lengthOfLongestSubstring("abcabcbb") == 3);
    assert(lengthOfLongestSubstring2("abcabcbb") == 3);
  }
}
