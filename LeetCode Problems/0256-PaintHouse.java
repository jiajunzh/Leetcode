package problem;

/**
 * 1. Problem 
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. The cost of 
 * painting each house with a certain color is different. You have to paint all the houses such that no two adjacent 
 * houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
 *
 * For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting 
 * house 1 with color green, and so on...
 * 
 * Return the minimum cost to paint all houses.
 *
 * 2. Examples
 * Example 1
 * Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 * 
 * Example 2
 * Input: costs = [[7,6,2]]
 * Output: 2
 *
 * 3. Constraints
 * costs.length == n
 * costs[i].length == 3
 * 1 <= n <= 100
 * 1 <= costs[i][j] <= 20
 */
public class PaintHouse {

  /**
   * 1. Approach 
   * Dynamic Programming.
   * 
   * Define dp[i][j] the minimum cost of painting house [0, i] with color j.
   * dp[i][j] = Math.min(dp[i - 1][k]) + costs[i][j] where k != j.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param costs
   * @return
   */
  public int minCost(int[][] costs) {
    int color1 = 0;
    int color2 = 0;
    int color3 = 0;
    
    for (int[] cost : costs) {
      int tmp1 = color1, tmp2 = color2, tmp3 = color3;
      color1 = Math.min(tmp2, tmp3) + cost[0];
      color2 = Math.min(tmp1, tmp3) + cost[1];
      color3 = Math.min(tmp1, tmp2) + cost[2];
    }
    
    return Math.min(color1, Math.min(color2, color3));
  }
}
