package problem;

/**
 * 1. Problem 
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 *
 * 2. Examples
 * Example 1
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * 
 * Example 2
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * 
 * Example 3
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 *
 * 3. Constraints
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lowercase English letters.
 *
 * Follow up: Could you solve it using only O(s2.length) additional memory space?
 */
public class InterleavingString {

  /**
   * 1. Approach 
   * Dynamic Programming.
   * 
   * Define dp[i][j] as a boolean whether s3[0:i + j - 1] is an interleaving string by taking i letters from s1 and j 
   * letters from s2.
   * 
   * dp[i][j] = dp[i - 1][j] && s1[i - 1] == s3.charAt[i + j - 1] 
   *         OR dp[i][j - 1] && s2[j - 1] == s3.charAt[i + j - 1] 
   * 
   * Base Case 
   * - s1.length + s2.length != s3.length => always false
   * - s1 = "", s2 = "", s3 = "" => true
   * - s1 = "", s2 = nonEmptyString, s3 = nonEmptyString => true if s2 = s3
   * - s1 = nonEmptyString, s2 = "", s3 = nonEmptyString => true if s1 = s3
   * 
   * 2. Complexity 
   * - Time O(M * N)
   * - Space O(M)
   * 
   * @param s1
   * @param s2
   * @param s3
   * @return
   */
  public boolean isInterleave(String s1, String s2, String s3) {
    // define dp[i][j] as a boolean whether s3[0:i + j - 1] is an interleaving string by taking i letters from s1 and j letters from s2.
    final int n = s1.length(), m = s2.length();
    if (n + m != s3.length()) {
      return false;
    }
    final boolean[] dp = new boolean[m + 1];

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= m; j++) {
        if (i == 0 && j == 0) {
          dp[j] = true;
        } else if (i == 0) {
          dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
        } else if (j == 0) {
          dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
        } else {
          dp[j] = (dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
        }
      }
    }

    return dp[m];
  }
}
