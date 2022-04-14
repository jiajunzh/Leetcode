package problem;

/**
 * 1. Problem 
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without 
 * changing the order of the remaining elements.
 *
 * 2. Examples
 * Example 1
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 * 
 * Example 2
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 *
 * 3. Constraints
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 */
public class LongestPalindromicSubsequence {

  /**
   * 1. Approach 
   * Dynamic Programming. Imagine we already have the longest palindromic subsequence from Ai+1 to Aj-1. the longest
   * palindromic subsequence from Ai to Aj could be obtained by appending Ai and Aj around the previous subsequence.
   * Two things to notice here: 
   * (1) The longest palindromic subsequence with length N could be built on top of subsequence with length (N - 1)
   * (2) The DP Formula:
   * - dp[i][j] = dp[i + 1][j - 1] + 2 if the Ai == Aj OR Max(dp[i + 1][j], dp[i][j - 1]) if Ai != Aj.
   * 
   * 2. Complexity 
   * - Time O(N^2)
   * - Space O(N^2)
   * 
   * 3. Improvement
   * - Do not forget the base case where length of subsequence should be 1. In this case, the dp[i][j] = 1 instead of 2.
   *
   * @param s
   * @return
   */
  public int longestPalindromeSubseq(String s) {
    final int n = s.length();
    final int[][] dp = new int[n][n];

    for (int len = 1; len <= n; len++) {
      for (int i = 0; i <= n - len; i++) {
        int j = i + len - 1;

        if (s.charAt(i) == s.charAt(j)) {
          if (i == j) {
            dp[i][j] = 1;
          } else {
            dp[i][j] = dp[i + 1][j - 1] + 2;
          }
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }

      }
    }

    return dp[0][n - 1];
  }
}
