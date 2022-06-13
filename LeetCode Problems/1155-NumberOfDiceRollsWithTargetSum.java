package problem;

/**
 * 1. Problem 
 * You have n dice and each die has k faces numbered from 1 to k.
 *
 * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the
 * dice so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.
 *
 * 2. Examples 
 * Example 1
 * Input: n = 1, k = 6, target = 3
 * Output: 1
 * Explanation: You throw one die with 6 faces.
 * There is only one way to get a sum of 3.
 * 
 * Example 2
 * Input: n = 2, k = 6, target = 7
 * Output: 6
 * Explanation: You throw two dice, each with 6 faces.
 * There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 * 
 * Example 3
 * Input: n = 30, k = 30, target = 500
 * Output: 222616187
 * Explanation: The answer must be returned modulo 109 + 7.
 *
 * 3. Constraints
 * 1 <= n, k <= 30
 * 1 <= target <= 1000
 */
public class NumberOfDiceRollsWithTargetSum {

  private static final int MOD = 1000000007;
  
  /**
   * 1. Approach 
   * Dynamic Programming. The order of the value matters here, meaning 3 + 4 is not equal to 4 + 3.
   * 
   * Define dp[l] as the number of ways of dice rolls with target sum l.
   * dp[i][l] = Sum(dp[i - 1][l - j]) where j is in [1,k]
   * 
   * 2. Complexity 
   * - Time O(N * T * K)
   * - Space O(N)
   * 
   * 3. Mistakes 
   * - The base case is dp[0] = 1. However, the target iteration should set dp[0] = 0. 
   *   When i = 1 (only consider the first dice), it is valid that previous target = 0.
   *   When i > 1, one prerequisite is the target in i - 1 iteration should be valid (thus could not be 0), dp[i - 1][0] should be 0.
   * 
   * @param n
   * @param k
   * @param target
   * @return
   */
  public int numRollsToTarget(int n, int k, int target) {
    final int[] dp = new int[target + 1];

    dp[0] = 1;
    for (int i = 1; i <= n; i++) {
      for (int t = target; t >= 0; t--) {
        dp[t] = 0;
        for (int j = 1; j <= Math.min(t, k); j++) {
          dp[t] = (dp[t] + dp[t - j]) % MOD;
        }
      }
    }

    return dp[target];
  }
}
