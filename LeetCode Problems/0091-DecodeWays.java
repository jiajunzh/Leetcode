package problem;

/**
 * 1. Problem 
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the 
 * mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 * 2. Examples 
 * Example 1
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * 
 * Example 2
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * 
 * Example 3
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *
 * 3. Constraints
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 */
public class DecodeWays {

  /**
   * 1. Approach
   * Dynamic Programming.
   * 
   * 1) State 
   * Define dp[i] as the number of decoding ways from string [0, i) with length i.
   * 
   * 2) Recurrence Relation
   * dp[i] = dp[i - 1] if the char at i - 1 is not 0  +  dp[i - 2] if the char at i - 2 and i - 1 is [10, 26]
   * 
   * 3) Base Case 
   * dp[0] => An empty string could be only decoded in one way as empty string
   * dp[1] => A string with only one letter could only be decoded in one way if the char is not 0 or zero way if the 
   * char is 0.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * 3. Improvement 
   * - Space Optimization 
   * 
   * @param s
   * @return
   */
  public int numDecodings(String s) {
    final int n = s.length();
    final int[] dp = new int[n + 1];

    dp[0] = 1;
    dp[1] = s.charAt(0) == '0' ? 0 : 1;
    for (int i = 2; i <= n; i++) {
      final char c1 = s.charAt(i - 2);
      final char c2 = s.charAt(i - 1);
      if (c2 != '0') {
        dp[i] = dp[i - 1];
      }

      if (c1 == '1' || (c1 == '2' && c2 >= '0' && c2 < '7')) {
        dp[i] += dp[i - 2];
      }
    }

    return dp[n];
  }
}