package problem;

/**
 * 1. Problem 
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with
 * a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x k cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 
 * with color 2, and so on...
 * 
 * Return the minimum cost to paint all houses.
 *
 * 2. Examples 
 * Example 1
 * Input: costs = [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation:
 * Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
 * Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 * 
 * Example 2
 * Input: costs = [[1,3],[2,4]]
 * Output: 5
 *
 * 3. Constraints
 * costs.length == n
 * costs[i].length == k
 * 1 <= n <= 100
 * 2 <= k <= 20
 * 1 <= costs[i][j] <= 20
 *
 * Follow up: Could you solve it in O(nk) runtime?
 */
public class PaintHouseII {

  /**
   * 1. Approach
   * Dynamic Programming. It is a problem that extends from PaintHour I (3 colors -> k colors). Only difference is to 
   * keep an array with k length for dp.
   * 
   * For each color, the min cost at house i dp[i][k] = Min(dp[i - 1][l]) + costs[i][k] where l != k
   * 
   * 2. Complexity 
   * - Time O(N * k ^ 2)
   * - Space O(K)
   * 
   * 3. Improvement
   * - A time optimization from O(N * k ^ 2) to O(N * k) is to keep the top minimum and secondary minimum element in the 
   *   last dp array so that we only need O(1) to get the Min(dp[i - 1][l]). See approach 2 below.
   *   
   * @param costs
   * @return
   */
  public int minCostII1(int[][] costs) {
    final int n = costs.length, k = costs[0].length;
    int[] dp = new int[k];

    for (int[] cost : costs) {
      int[] tmp = new int[k];
      for (int j = 0; j < k; j++) {
        int min = Integer.MAX_VALUE;
        for (int l = 0; l < k; l++) {
          if (l != j) {
            min = Math.min(min, dp[l]);
          }
        }
        tmp[j] = cost[j] + min;
      }
      dp = tmp;
    }

    int min = Integer.MAX_VALUE;
    for (int candidate : dp) {
      min = Math.min(min, candidate);
    }
    return min;
  }
  
  public int minCostII2(int[][] costs) {
    final int n = costs.length, k = costs[0].length;
    int[] dp = new int[k];
    int min = 0;
    int secondaryMin = 0;

    for (int[] cost : costs) {
      int[] tmp = new int[k];
      int tmpMin = Integer.MAX_VALUE;
      int tmpSecondaryMin = Integer.MAX_VALUE;
      for (int j = 0; j < k; j++) {
        tmp[j] = cost[j] + (dp[j] == min ? secondaryMin : min);
        if (tmp[j] <= tmpMin) {
          tmpSecondaryMin = tmpMin;
          tmpMin = tmp[j];
        } else if (tmp[j] <= tmpSecondaryMin) {
          tmpSecondaryMin = tmp[j];
        }
      }
      min = tmpMin;
      secondaryMin = tmpSecondaryMin;
      dp = tmp;
    }

    return min;
  }
}
