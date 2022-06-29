package problem;

/**
 * 1. Problem 
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every 
 * character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 * 2. Examples 
 * Example 1
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * 
 * Example 2
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * 
 * Example 3
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 * 3. Constraints
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 *
 * 4. Follow up
 * Could you find an algorithm that runs in O(m + n) time?
 */
public class MinimumWindowSubstring {

  /**
   * 1. Approach 
   * Sliding Window
   * 
   * 2. Complexity 
   * - Time O(S + T)
   * - Space O(1)
   * 
   * 3. Alternative 
   * - Another improvement that could be done is to filter chars that are in s but do not exist in t. Let's say the 
   *   length of filtered s is FS. Then the time could be improved from 2 * S + T to 2 * FS + S + T
   * 
   * @param s
   * @param t
   * @return
   */
  public String minWindow(String s, String t) {
    final int n = s.length();
    final int m = t.length();
    int minStart = -1;
    int minLen = n + 1;
    int i = 0, j = 0;
    final int[] charMap = new int[128];
    int charCnt = 0;
    for (int k = 0; k < m; k++) {
      charMap[t.charAt(k)]++;
      charCnt++;
    }
    int[] windowMap = new int[128];
    int windowCnt = 0;
    while (j < n) {
      while (j < n && windowCnt < charCnt) {
        final char c = s.charAt(j);
        if (windowMap[c] < charMap[c]) {
          windowCnt++;
        }
        windowMap[c]++;
        j++;
      }
      while (windowCnt == charCnt) {
        final char c = s.charAt(i);
        if (windowMap[c] <= charMap[c]) {
          windowCnt--;
        }
        windowMap[c]--;
        i++;
      }
      if (j - i + 1 < minLen) {
        minStart = i - 1;
        minLen = j - i + 1;
      }
    }
    return minStart == -1 ? "" : s.substring(minStart, minStart + minLen);
  }
}
