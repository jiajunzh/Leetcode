package problem;

/**
 * 1. Problem 
 * You are painting a fence of n posts with k different colors. You must paint the posts following these rules:
 *
 * Every post must be painted exactly one color.
 * There cannot be three or more consecutive posts with the same color.
 * Given the two integers n and k, return the number of ways you can paint the fence.
 *
 * 2. Examples
 * Example 1
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: All the possibilities are shown.
 * Note that painting all the posts red or all the posts green is invalid because there cannot be three posts in a row 
 * with the same color.
 * 
 * Example 2
 * Input: n = 1, k = 1
 * Output: 1
 * 
 * Example 3
 * Input: n = 7, k = 2
 * Output: 42
 *
 * 3. Constraints
 * 1 <= n <= 50
 * 1 <= k <= 105
 * The testcases are generated such that the answer is in the range [0, 231 - 1] for the given n and k.
 */
public class PaintFence {

  /**
   * 1. Approach 
   * Dynamic Programming.
   * 
   * 1) State 
   * Define dp[i] as the number of ways to paint the fence with i posts
   * 
   * 2) Recurrence Relation 
   * There are two cases to consider here:
   * - Paint post i with the different color as post i - 1. Then there is (k - 1) color to use and the number of 
   *   ways equals to (k - 1) * dp[i - 1]
   * - Paint post i with the same color as post i - 1. In this case, the color post i - 2 should be painted differently 
   *   than post i - 1. Similar to the case above, the number of ways of painting post i - 2 differently than i - 1 is 
   *   (k - 1) * dp[i - 2].
   *  
   * Thus dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2])
   * 
   * 3) Base Case 
   * dp[1] = k
   * dp[2] = k * k
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * 3. Improvement 
   * - Space optimization 
   * 
   * @param n
   * @param k
   * @return
   */
  public int numWays(int n, int k) {
    final int[] dp = new int[n + 1];

    dp[1] = k;
    if (n > 1) dp[2] = k * k;

    for (int i = 3; i <= n; i++) {
      dp[i] = (k - 1) * (dp[i - 1] + dp[i - 2]);
    }

    return dp[n];
  }
}
