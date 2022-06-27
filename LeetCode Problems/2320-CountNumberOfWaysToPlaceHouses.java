package problem;

/**
 * 1. Problem 
 * There is a street with n * 2 plots, where there are n plots on each side of the street. The plots on each side are 
 * numbered from 1 to n. On each plot, a house can be placed.
 *
 * Return the number of ways houses can be placed such that no two houses are adjacent to each other on the same side 
 * of the street. Since the answer may be very large, return it modulo 109 + 7.
 *
 * Note that if a house is placed on the ith plot on one side of the street, a house can also be placed on the ith plot 
 * on the other side of the street.
 *
 * 2. Examples 
 * Example 1
 * Input: n = 1
 * Output: 4
 * Explanation: 
 * Possible arrangements:
 * 1. All plots are empty.
 * 2. A house is placed on one side of the street.
 * 3. A house is placed on the other side of the street.
 * 4. Two houses are placed, one on each side of the street.
 * 
 * Example 2
 * Input: n = 2
 * Output: 9
 * Explanation: The 9 possible arrangements are shown in the diagram above.
 *
 * 3. Constraints
 * 1 <= n <= 104
 */
public class CountNumberOfWaysToPlaceHouses {

  /**
   * 1. Approach
   * Dynamic Programming.
   * 
   * This problem could be simplified by only looking at one side of the street as the two sides are totally independent.
   * Suppose the number of ways to place houses are K, then the final answer will be K * K.
   * 
   * For one side, there are two use cases (1 is house and 0 is no house):
   * 1) House is placed at position i => dp[i][1] = dp[i - 1][0]
   * 2) No house is placed at position i => dp[i][0] = dp[i - 1][0] + dp[i - 1][1]
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   *
   * @param n
   * @return
   */
  private static final int MOD = 1_000_000_007;

  public int countHousePlacements(int n) {
    int house = 1;
    int nonHouse = 1;
    for (int i = 2; i <= n; i++) {
      int tmp = nonHouse;
      nonHouse = (nonHouse + house) % MOD;
      house = tmp;
    }
    long oneSide = (nonHouse + house) % MOD;
    return (int) ((oneSide * oneSide) % MOD);
  }
}
