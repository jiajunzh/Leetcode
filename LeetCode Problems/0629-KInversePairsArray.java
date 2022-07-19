package problem;

/**
 * 1. Problem 
 * For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and 
 * nums[i] > nums[j].
 *
 * Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there 
 * are exactly k inverse pairs. Since the answer can be huge, return it modulo 109 + 7.
 *
 * 2. Examples
 * Example 1
 * Input: n = 3, k = 0
 * Output: 1
 * Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.
 * 
 * Example 2
 * Input: n = 3, k = 1
 * Output: 2
 * Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 *
 * 3. Constraints
 * 1 <= n <= 1000
 * 0 <= k <= 1000
 */
public class KInversePairsArray {
  
  /**
   * 1. Approach 
   * Dynamic Programming.
   *
   * 2. Complexity 
   * - Time O(N * K * Min(N, K)) => Time Limit Exceeded
   * - Space O(N * K)
   * 
   * @param n
   * @param k
   * @return
   */
  public int kInversePairs1(int n, int k) {
    final int[][] dp = new int[n + 1][k + 1];
    for (int i = 1; i <= n; i++) {
      dp[i][0] = 1;
    }
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= k; j++) {
        for (int l = Math.max(0, j - i + 1); l <= j; l++) {
          dp[i][j] += dp[i - 1][l];
          dp[i][j] %= 1_000_000_007;
        }
      }
    }
    return dp[n][k];
  }
  
  /**
   * 1. Approach 
   * Dynamic Programming + Prefix Sum => Inspired by approach 1 and improves time from O(N * K * Min(N, K)) to O(N * K)
   * 
   * 2. Complexity 
   * - Time O(N * K)
   * - Space O(N * K)
   * 
   * @param n
   * @param k
   * @return
   */
  public int kInversePairs2(int n, int k) {
    final int[][] dp = new int[n + 1][k + 1];
    int MOD = 1_000_000_007;
    for (int i = 1; i <= n; i++) {
      dp[i][0] = 1;
    }
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= k; j++) {
        dp[i - 1][j] += dp[i - 1][j - 1];
        dp[i - 1][j] %= MOD;
      }
      for (int j = 1; j <= k; j++) {
        dp[i][j] = (dp[i - 1][j] + MOD - ((j - i) >= 0 ? dp[i - 1][j - i] : 0)) % MOD;
      }
    }
    return dp[n][k];
  }

  /**
   * 1. Approach 
   * Dynamic Programming + Prefix Sum.
   * On top of approach2, we could also calculate the prefix sum while calculating the answer [i, j]
   *
   * 2. Complexity 
   * - Time O(N * K)
   * - Space O(N * K)
   *
   * 3. Improvement
   * - Space also could be optimized to O(K) with 1D DP
   * 
   * @param n
   * @param k
   * @return
   */
  public int kInversePairs3(int n, int k) {
    final int[][] dp = new int[n + 1][k + 1];
    int MOD = 1_000_000_007;
    for (int i = 1; i <= n; i++) {
      dp[i][0] = 1;
    }
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= k; j++) {
        dp[i - 1][j] += dp[i - 1][j - 1];
        dp[i - 1][j] %= MOD;
        dp[i][j] = (dp[i - 1][j] + MOD - ((j - i) >= 0 ? dp[i - 1][j - i] : 0)) % MOD;
      }
    }
    return dp[n][k];
  }
}
