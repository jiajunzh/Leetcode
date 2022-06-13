package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * There is a row of m houses in a small city, each house must be painted with one of the n colors (labeled from 1
 * to n), some houses that have been painted last summer should not be painted again.
 *
 * A neighborhood is a maximal group of continuous houses that are painted with the same color.
 *
 * For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods [{1}, {2,2}, {3,3}, {2}, {1,1}].
 * Given an array houses, an m x n matrix cost and an integer target where:
 *
 * houses[i]: is the color of the house i, and 0 if the house is not painted yet.
 * cost[i][j]: is the cost of paint the house i with the color j + 1.
 * Return the minimum cost of painting all the remaining houses in such a way that there are exactly target 
 * neighborhoods. If it is not possible, return -1.
 *
 * 2. Examples 
 * Example 1
 * Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * Output: 9
 * Explanation: Paint houses of this way [1,2,2,1,1]
 * This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
 * Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
 * 
 * Example 2
 * Input: houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * Output: 11
 * Explanation: Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
 * This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}]. 
 * Cost of paint the first and last house (10 + 1) = 11.
 * 
 * Example 3
 * Input: houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
 * Output: -1
 * Explanation: Houses are already painted with a total of 4 neighborhoods [{3},{1},{2},{3}] different of target = 3.
 *
 * 3. Constraints
 * m == houses.length == cost.length
 * n == cost[i].length
 * 1 <= m <= 100
 * 1 <= n <= 20
 * 1 <= target <= m
 * 0 <= houses[i] <= n
 * 1 <= cost[i][j] <= 104
 */
public class PaintHouseIII {

  // Maximum cost possible plus 1
  final int MAX_COST = 1000001;

  /**
   * 1. Approach 
   * Dynamic Programming. This problem has three critical dimensions: 
   * - number of houses m
   * - number of colors n 
   * - number of neighbors target
   * 
   * Define dp[i][t][j] as the minimum cost of painting house i (1-indexed) with color j (1-indexed) and 
   * houses from 1 to i would form t neighborhood.
   * 
   * There are two cases: 
   * 1) The color at house i is the same as the color at house i - 1
   * dp[i][t][j] = dp[i - 1][t][j] + costs[i - 1][j - 1]
   * 2) The color at house i is different from the color at house i - 1
   * dp[i][t][j] = Min(dp[i - 1][t][l]) + costs[i - 1][j - 1] where j != l.
   * 
   * Base Case
   * dp[0][t][j] = 0 so that the cost would be costs[0][j] for the first house.
   * dp[i][t][j] = MAX_COST where i != 0
   * 
   * 2. Complexity 
   * - Time O(M * T * N^2)
   * - Space O(M * T * N^2) -> could be optimized to O(T * N^2)
   * 
   * 3. Mistakes
   * - Don't initialize dp[i][t][j] to Integer.MAX_VALUE because of integer overflow.
   * 
   * @param houses
   * @param cost
   * @param m
   * @param n
   * @param target
   * @return
   */
  public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
    final int[][][] dp = new int[m + 1][target + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      for (int t = 0; t <= target; t++) {
        Arrays.fill(dp[i][t], MAX_COST);
      }
    }

    for (int i = 1; i <= m; i++) {
      int color = houses[i - 1];
      for (int t = 1; t <= Math.min(i, target); t++) {
        for (int j = 1; j <= n; j++) {
          int previousMin = MAX_COST;
          for (int l = 1; l <= n; l++) {
            if (l != j) {
              previousMin = Math.min(previousMin, dp[i - 1][t - 1][l]);
            }
          }

          if (color == 0 || color == j) {
            dp[i][t][j] = Math.min(dp[i - 1][t][j], previousMin) + (color == 0 ? cost[i - 1][j - 1] : 0);
          }
        }
      }
    }

    int minCost = MAX_COST;
    for (int j = 1; j <= n; j++) {
      minCost = Math.min(minCost, dp[m][target][j]);
    }
    return minCost == MAX_COST ? -1 : minCost;
  }
}
