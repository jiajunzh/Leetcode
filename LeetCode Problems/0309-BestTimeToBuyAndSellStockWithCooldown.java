package problem;

/**
 * 1. Problem 
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * 2. Examples 
 * Example 1
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * 
 * Example 2
 * Input: prices = [1]
 * Output: 0
 *
 * 3. Constraints
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class BestTimeToBuyAndSellStockWithCooldown {

  /**
   * 1. Approach 
   * Dynamic Programming. 
   * Define dp[i] as the max profit you could get from day 0 to day i.
   * 
   * There will be two possible use cases:
   * 1) The stock sold on day i, then the sold stock should be bought on any day between day 0 to day i - 1, say, day j.
   * S0, S1, S2, S3 ... Sj-2, Sj-1, Sj, Sj+1 ... Si
   * If the stock was bought on day Sj, then no stock should be bought or sold on day j - 1.
   * dp[i] = Max(dp[i], dp[j - 2] + prices[i] - prices[j])
   * 
   * 2) The stock is not sold on day i.
   * dp[i] = dp[i - 1]
   * 
   * 2. Complexity 
   * - Time O(N^2)
   * - Space O(N)
   * 
   * @param prices
   * @return
   */
  public int maxProfit1(int[] prices) {
    final int[] dp = new int[prices.length];

    for (int i = 1; i < prices.length; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] = Math.max(dp[i], (j >= 2 ? dp[j - 2] : 0) + prices[i] - prices[j]);
      }
      dp[i] = Math.max(dp[i], dp[i - 1]);
    }

    return dp[prices.length - 1];
  }

  /**
   * 1. Approach 
   * Dynamic Programming. The problem could be represented by a state machine with three statuses
   * 1. Sell: max profit when a stock is sold on the current day
   * 2. Hold: max profit when a stock is on hold on the current day
   * 3. CoolDown: max profit when it is cool down on the current day
   * 
   * Sell[i] = Hold[i - 1] + prices[i] or Sell[i - 1]
   * Hold[i] = CoolDown[i - 1] - prices[i] or Hold[i - 1]
   * CoolDown[i] = Sell[i - 1] or CoolDown[i - 1]
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param prices
   * @return
   */
  public int maxProfit2(int[] prices) {
    int sell = 0, hold = Integer.MIN_VALUE, coolDown = 0;

    for (int i = 0; i < prices.length; i++) {
      hold = Math.max(hold, coolDown - prices[i]);
      coolDown = Math.max(coolDown, sell);
      sell = Math.max(sell, hold + prices[i]);
    }

    return sell;
  }
}
