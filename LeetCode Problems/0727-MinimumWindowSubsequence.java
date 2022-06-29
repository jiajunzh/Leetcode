package problem;

/**
 * 1. Problem 
 * Given strings s1 and s2, return the minimum contiguous substring part of s1, so that s2 is a subsequence of the part.
 *
 * If there is no such window in s1 that covers all characters in s2, return the empty string "". If there are multiple 
 * such minimum-length windows, return the one with the left-most starting index.
 *
 * 2. Examples 
 * Example 1
 * Input: s1 = "abcdebdde", s2 = "bde"
 * Output: "bcde"
 * Explanation: 
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of s2 in the window must occur in order.
 * 
 * Example 2
 * Input: s1 = "jmeqksfrsdcmsiwvaovztaqenprpvnbstl", s2 = "u"
 * Output: ""
 *
 * 3. Constraints
 * 1 <= s1.length <= 2 * 104
 * 1 <= s2.length <= 100
 * s1 and s2 consist of lowercase English letters.
 */
public class MinimumWindowSubsequence {

  /**
   * 1. Approach 
   * Sliding Window. 
   * 
   * This problem could be split into two steps:
   * 1) Extend the window to fulfill the requirement that s2 is a subsequence of s1.
   * 2) Shrink the window from start pointer to get the minimum substring fulfilling the requirement 
   * 
   * For each qualified window, mark the minimum window found till now and then start the next window search from 
   * index = start + 1. This is important in terms of which index to start for next window searching. 
   * One edge case is "baaaaaaaabbb" for s2 = "bbb" if you start from the end + 1 of previous window. As it will 
   * first find "baaaaaaaabb" and then start from the last "b" which ignores the previous two bs that are useful.
   * 
   * 2. Complexity 
   * - Time O(N * N)
   * - Space O(1)
   * 
   * 3. Alternative 
   * - Another approach to take is DP. Define dp[i][j] as the length of shortest substring ENDING at s[i]
   *   s.t. s[0:i] contains t[0:j] (or t[0:j] is a subsequence of s[0:i]); It there's no such substring within s[0:i]
   *   exist, then dp[i][j] = 0;
   *   See https://leetcode.com/problems/minimum-window-subsequence/discuss/1110149/JAVA-Standard-and-Optimal-DP-Solution-or-Detailed-explaination
   *   
   * @param s1
   * @param s2
   * @return
   */
  public String minWindow1(String s1, String s2) {
    final int n = s1.length();
    int i = 0;
    int minStart = -1;
    int minLen = n + 1;
    while (i < n) {
      int end = extendWindow(s1, s2, i);
      if (end == -1) break;
      int start = shrinkWindow(s1, s2, end);
      int len = end - start + 1;
      if (len < minLen) {
        minLen = len;
        minStart = start;
      }
      i = start + 1;
    }
    return minStart == -1 ? "" : s1.substring(minStart, minStart + minLen);
  }
  
  private int extendWindow(final String s1, final String s2, int i) {
    final int n = s1.length();
    final int m = s2.length();
    int j = 0;
    while (i < n && j < m) {
      if (s1.charAt(i) == s2.charAt(j)) j++;
      i++;
    }
    if (j < m) {
      return -1;
    }
    return i - 1;
  }

  private int shrinkWindow(final String s1, final String s2, int i) {
    final int m = s2.length();
    int j = m - 1;
    while (i >= 0 && j >= 0) {
      if (s1.charAt(i) == s2.charAt(j)) j--;
      i--;
    }
    return i + 1;
  }

  /**
   * 1. Approach 
   * DP
   * 
   * 2. Complexity 
   * - Time O(N * M)
   * - Space O(N * M)
   * 
   * @param s1
   * @param s2
   * @return
   */
  public String minWindow2(String s1, String s2) {
    final int n = s1.length();
    final int m = s2.length();
    final int[][] dp = new int[n + 1][m + 1];
    dp[0][0] = (s1.charAt(0) == s2.charAt(0)) ? 1 : 0;
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (s1.charAt(i) == s2.charAt(j)) {
          if (j == 0) dp[i][j] = 1;
          else dp[i][j] = dp[i - 1][j - 1] == 0 ? 0 : dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = dp[i - 1][j] == 0 ? 0 : dp[i - 1][j] + 1;
        }
      }
    }
    int minLen = n + 1;
    int minStart = -1;
    for (int i = 0; i < n; i++) {
      if (dp[i][m - 1] == 0) continue;
      if (minLen > dp[i][m - 1]) {
        minLen = dp[i][m - 1];
        minStart = i + 1 - dp[i][m - 1];
      }
    }
    return minStart == -1 ? "" : s1.substring(minStart, minStart + minLen);
  }
}
