package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 *
 * A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer 
 * with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 * 2. Examples
 * Example 1
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * 
 * Example 2
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 * 3. Constraints
 * 1 <= n <= 104
 */
public class PerfectSquares {

  /**
   * 1. Approach
   * DP
   * 
   * 2. Complexity
   * - Time O(N * Sqrt(N))
   * - Space O(N)
   * 
   * @param n
   * @return
   */
  public int numSquares(int n) {
    final int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i <= n; i++) {
      int square = i * i;
      if (square > n) break;
      for (int j = square; j <= n; j++) {
        dp[j] = Math.min(dp[j], dp[j - square] + 1);
      }
    }
    return dp[n];
  }
}
