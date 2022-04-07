package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem
 * There are n piles of coins on a table. Each pile consists of a positive number of coins of assorted denominations.
 *
 * In one move, you can choose any coin on top of any pile, remove it, and add it to your wallet.
 *
 * Given a list piles, where piles[i] is a list of integers denoting the composition of the ith pile from top to
 * bottom, and a positive integer k, return the maximum total value of coins you can have in your wallet if you choose exactly k coins optimally.
 *
 * 2. Example
 * Example 1:
 * Input: piles = [[1,100,3],[7,8,9]], k = 2
 * Output: 101
 * Explanation:
 * The above diagram shows the different ways we can choose k coins.
 * The maximum total we can obtain is 101.
 * 
 * Example 2:
 * Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
 * Output: 706
 * Explanation:
 * The maximum total can be obtained if we choose all coins from the last pile.
 *
 * 3. Constraints
 * n == piles.length
 * 1 <= n <= 1000
 * 1 <= piles[i][j] <= 105
 * 1 <= k <= sum(piles[i].length) <= 2000
 */
public class MaximumValueOfKCoinsFromPiles {

  /**
   * 1. Approach 
   * DP. Define dp[i][k] as the maximum value of coins with piles 0 ... i with coin k. The expression would be 
   * dp[i][k] = Max(dp[i - 1][k], dp[i - 1][k - l] + pile.get(l - 1))
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(K)
   * 
   * 3. Improvement
   * - The initial version of the dp approach used 2D array. However, one thing to notice is the element at 
   * the bottom will not be accessed later if iterating from bottom to the top. That is to say, only 1D 
   * array will be needed for dp.
   * - The other improvement is to pre-calculate the prefix sum of each pile so we don't need to re-calculate
   * them each time.
   * 
   * @param piles
   * @param k
   * @return
   */
  public int maxValueOfCoins(List<List<Integer>> piles, int k) {
    final int[] dp = new int[k + 1];

    for (int i = 1; i <= piles.size(); i++) {
      List<Integer> pile = piles.get(i - 1);
      List<Integer> preSum = new ArrayList<>();
      
      int sum = 0;
      for (int value: pile) {
        sum += value;
        preSum.add(sum);
      }
      
      for (int j = k; j > 0; j--) {
        for (int l = 0; l < Math.min(pile.size(), j); l++) {
          dp[j] = Math.max(dp[j - l - 1] + preSum.get(l), dp[j]);
        }
      }
    }

    return dp[k];
  }
}
