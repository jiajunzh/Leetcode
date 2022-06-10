package problem;

/**
 * 1. Problem 
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee 
 * representing a transaction fee.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the 
 * transaction fee for each transaction.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * 2. Examples
 * Example 1
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 
 * Example 2
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 *
 * 3. Constraints
 * 1 <= prices.length <= 5 * 104
 * 1 <= prices[i] < 5 * 104
 * 0 <= fee < 5 * 104
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

  /**
   * 1. Approach
   * Dynamic Programming.
   *
   * Define sell as the maximum profit at position i without stock 
   * Define hold as the maximum profit at position i with one stock bought
   * 
   * - If you don't hold any share at day i, then you either sell a stock at day i with fee 
   *   OR you don't hold any share at day i - 1
   * - If you hold share at day i, then you either purchase a stock at day i
   *   OR you hold share at day i - 1
   *   
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * 3. Improvement
   * - The tmp variable is not needed. For example, if you don't sell any stock at day i, then sell keeps the same 
   *   as day i - 1, we don't need tmp to keep sell. If you sell one stock at day i, tmp - prices[i] means you also 
   *   buy one stock again on day i. It is guaranteed that the max profit of hold will keep the same as day i - 1 as
   *   there is transaction fee involved.
   * 
   */
  public int maxProfit(int[] prices, int fee) {
    int sell = 0;
    int hold = -prices[0];

    for (int i = 0; i < prices.length; i++) {
      int tmp = sell;
      sell = Math.max(sell, hold + prices[i] - fee);
      hold = Math.max(hold, tmp - prices[i]);
    }

    return sell;
  }
}
