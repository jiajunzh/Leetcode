package problem;

import java.util.Arrays;

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
