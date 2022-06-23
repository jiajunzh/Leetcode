package problem;

/**
 * 1. Problem 
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * 2. Examples 
 * Example 1
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * 
 * Example 2
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at 
 * the same time. You must sell before buying again.
 * 
 * Example 3
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 * 3. Constraints
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */
public class BestTimeToBuyAndSellStockIII {

  /**
   * 1. Approach 
   * Dynamic Programming. We could define the four states below to simulate the process:
   * 
   * OneTxnHold => Max profit with one stock bought but no stock sold 
   * OneTxnSell => Max profit with one stock bought and sold
   * TwoTxnsHold => Max profit with two stocks bought and one sold 
   * TwoTxnsSell => Max profit with two stocks bought and sold 
   * 
   * See below for recurrence relation.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * 3. Alternative 
   * - Another perspective is that we need to find two subsequences without overlapping as below 
   *   ... (bought1)______(sold1) ... (bought2)_______(sold2) ... 
   *   So we could define leftProfit[i] as the maximum profit with one transaction from 0 to i.
   *   and rightProfit[i] as the maximum profit with one transaction from i to n - 1.
   *   Then the maximumProfit[i] = left + right
   *   
   * @param prices
   * @return
   */
  public int maxProfit(int[] prices) {
    final int n = prices.length;
    int oneTxnHold = -prices[0];
    int oneTxnSell = 0;
    int twoTxnsHold = Integer.MIN_VALUE;
    int twoTxnsSell = 0;

    for (int i = 1; i < n; i++) {
      twoTxnsSell = Math.max(twoTxnsSell, twoTxnsHold + prices[i]);
      twoTxnsHold = Math.max(twoTxnsHold, oneTxnSell - prices[i]);
      oneTxnSell = Math.max(oneTxnSell, oneTxnHold + prices[i]);
      oneTxnHold = Math.max(oneTxnHold, -prices[i]);
    }

    return Math.max(oneTxnSell, twoTxnsSell);
  }
}
