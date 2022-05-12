package problem;

/**
 * 1. Problem
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, 
 * you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 *
 * Return the minimum cost to reach the top of the floor.
 *
 * 2. Examples
 * Example 1
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 * 
 * Example 2
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 *
 * 3. Constraints
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 */
public class MinCostClimbingStairs {

  /**
   * 1. Approach 
   * Dynamic Programming with Constant Space.
   * 
   * - Dp[i] the min cost of climbing to ith stairs [0, n] n + 1 steps
   * - Final Solution is Dp[n]
   * - Dp[i] = Math.min(Dp[i - 1] + cost[i - 1], Dp[i - 2] + cost[i - 2])
   * - Dp[0] = 0, Dp[1] = cost[0]
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * 3. Mistakes
   * - Note here you could either start from index0, index1 without any previous effort.
   * 
   * @param cost
   * @return
   */
  public int minCostClimbingStairs(int[] cost) {
    int dp1 = 0, dp0 = 0;
    for (int i = 2; i <= cost.length; i++) {
      int curr = Math.min(dp1 + cost[i - 1], dp0 + cost[i - 2]);
      dp0 = dp1;
      dp1 = curr;
    }
    return dp1;
  }
}
