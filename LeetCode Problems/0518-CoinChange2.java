package problem;

/**
 * 1. Problem 
 * You are given an integer array coins representing coins of different denominations and an integer amount representing 
 * a total amount of money.
 *
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any 
 * combination of the coins, return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 * 2. Examples 
 * Example 1
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 
 * Example 2
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * 
 * Example 3
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 * 3. Constraints
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * All the values of coins are unique.
 * 0 <= amount <= 5000
 */
public class CoinChange2 {

  /**
   * 1. Approach 
   * Dynamic Programming. 
   * 
   * Define dp[i] as the number of ways to make up for amount i. 
   * 
   * One important thing to notice here is the problem wants combination instead of permutation. For example, 
   * 2 + 3 + 5 is the same thing as 2 + 5 + 3.
   * 
   * In order to achieve that, we could iterate through each coin and make dp as the number of ways with combinations of 
   * all iterated coins so far. 
   * 
   * For example, amount = 11 and coins = [2,5,10], then we will have dp as below 
   *                              0   1   2   3   4   5   6   7   8   9   10   11
   * with no coin combination     1   0   0   0   0   0   0   0   0   0   0    0
   * with coin 2 combination      1   0   1   0   1   0   1   0   1   0   1    0
   * with coin 2&5 combination    1   0   1   0   1   1   1   1   0   1   2    1
   * with coin 2&5&10 combination 1   0   1   0   1   1   1   1   0   1   3    1
   * 
   * For each coin,
   * dp[i] += dp[i - coin]
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param amount
   * @param coins
   * @return
   */
  public int change(int amount, int[] coins) {
    final int[] dp = new int[amount + 1];
    dp[0] = 1;

    for (int coin : coins) {
      for (int i = coin; i <= amount; i++) {
        dp[i] += dp[i - coin];
      }
    }

    return dp[amount];
  }
}
