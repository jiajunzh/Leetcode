package problem;

/**
 * 1. Problem
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common 
 * subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) 
 * deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * 2. Examples
 * Example 1
 * Input: text1 = "abcde", text2 = "ace" 
 * Output: 3  
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * 
 * Example 2
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * 
 * Example 3
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 * 3. Constraints
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 */
public class longestCommonSubsequence {

  /**
   * 1. Approach
   * Dynamic Programming.
   * 
   * dp[i][j] as the length of the longest common subsequence of text 1 ending at i and text 2 ending at j. i belongs [0, t1.length - 1], j belongs [0, t2.length - 1]
   * if char1[i] = char2[j] => dp[i][j] = dp[i - 1][j - 1] + 1
   * else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
   * 
   * 2. Complexity
   * - Time O(N * M)
   * - Space O(N * M)
   * 
   * 3. Improvement 
   * 1) The space could be improved by only keeping two arrays, one for previous and one for current. This could 
   * optimize the space to O(N)
   * 
   * 2) On top of the 1), we could swap text 1 and text 2 if text 1 has longer length than text 2. Then the space will
   * be O(min(N, M))
   * 
   * @param text1
   * @param text2
   * @return
   */
  public int longestCommonSubsequence(String text1, String text2) {
    final int t1 = text1.length(), t2 = text2.length();
    final int[][] dp = new int[t1 + 1][t2 + 1];

    for (int i = 1; i <= t1; i++) {
      for (int j = 1; j <= t2; j++) {
        char c1 = text1.charAt(i - 1);
        char c2 = text2.charAt(j - 1);

        if (c1 == c2) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
        }
      }
    }

    return dp[t1][t2];
  }
}
