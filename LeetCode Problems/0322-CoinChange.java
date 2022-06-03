package problem;

import java.util.Arrays;

public class CoinChange {

  /**
   * 1. Approach 
   * Dynamic Programming 
   * 
   * 2. Complexity
   * - Time O(C * Amount)
   * - Space O(Amount)
   * 
   * @param coins
   * @param amount
   * @return
   */
  public int coinChange(int[] coins, int amount) {
    final int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;

    for (int i = 1; i <= amount; i++) {
      for (int coin : coins) {
        int remaining = i - coin;
        if (remaining >= 0) {
          dp[i] = Math.min(dp[remaining] + 1, dp[i]);
        }
      }
    }

    return dp[amount] == amount + 1 ? -1 : dp[amount];
  }
}
