package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 *
 * Find the maximum profit you can achieve. You may complete at most k transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * 2. Examples 
 * Example 1
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0)
 * and sell on day 6 (price = 3), profit = 3-0 = 3.
 *
 * 3. Constraints
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 */
public class BestTimeToBuyAndSellStockIV {

  /**
   * 1. Approach 
   * Dynamic Programming.
   * 
   * Define T[i][j] as the maximum profit on day i with j transactions performed.
   * T[i][j] = Math.max(T[i - 1][j], T[l][j - 1] + price[i] - price[l]) l = 0, 1, 2 .... i - 1
   * 
   * 2. Complexity 
   * - Time O(N^2 * k)
   * - Space O(N * k)
   * 
   * 3. Improvement
   * - A potential optimization
   *   https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/discuss/1948310/Intuitive-Easy-Java-solution-w-Explanation
   * @param k
   * @param prices
   * @return
   */
  public int maxProfit1(int k, int[] prices) {
    if (prices.length == 0) return 0;
    final int n = prices.length;
    final int[][] dp = new int[n][k + 1];

    for (int i = 1; i < n; i++) {
      int price = prices[i];
      for (int j = 1; j <= k; j++) {
        dp[i][j] = dp[i - 1][j];
        for (int l = i - 1; l >= 0; l--) {
          dp[i][j] = Math.max(dp[i][j], dp[l][j - 1] + price - prices[l]);
        }
      }
    }

    return dp[n - 1][k];
  }

  /**
   * 1. Approach
   * Dynamic Programming.
   * 
   * Define 
   * - dp[i][0][j] the maximum profit from day 0 to i with j transactions performed
   * - dp[i][1][j] the maximum profit from day 0 to i with j transactions performed with one stock on hold
   * 
   * dp[i][0][j] = Max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i - 1])
   * dp[i][1][j] = Max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i - 1])
   * 
   * 2. Complexity 
   * - Time O(N * k)
   * - Space O(N * k)
   * 
   * 3. Mistakes
   * - Need to initialize dp[0][1] as Integer.MIN_VALUE as otherwise on the first day, sell might take in the first 
   *   stock as sold stock (It considers before day1, the maximum profit is zero when holding a stock in hand, which is 
   *   wrong cause no stock could be held before day1). 
   * 
   * @param k
   * @param prices
   * @return
   */
  public int maxProfit2(int k, int[] prices) {
    final int[][][] dp = new int[prices.length + 1][2][k + 1];
    Arrays.fill(dp[0][1], Integer.MIN_VALUE);

    for (int i = 1; i <= prices.length; i++) {
      for (int j = 1; j <= k; j++) {
        dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i - 1]);
        dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i - 1]);
      }
    }

    return dp[prices.length][0][k];
  }

  /**
   * 1. Approach 
   * DP with Space Optimized.
   *
   * 2. Complexity 
   * - Time O(N * k)
   * - Space O(k)
   * 
   * @param k
   * @param prices
   * @return
   */
  public int maxProfit3(int k, int[] prices) {
    final int[] sell = new int[k + 1];
    final int[] hold = new int[k + 1];
    Arrays.fill(hold, Integer.MIN_VALUE);

    for (int i = 0; i < prices.length; i++) {
      for (int j = k; j > 0; j--) {
        sell[j] = Math.max(sell[j], hold[j] + prices[i]);
        hold[j] = Math.max(hold[j], sell[j - 1] - prices[i]);
      }
    }

    return sell[k];
  }
}
