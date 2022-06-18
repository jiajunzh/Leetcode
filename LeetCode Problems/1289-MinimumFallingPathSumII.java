package problem;

/**
 * 1. Problem 
 * Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.
 *
 * A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two 
 * elements chosen in adjacent rows are in the same column.
 *
 * 2. Examples
 * Example 1
 * Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 13
 * Explanation: 
 * The possible falling paths are:
 * [1,5,9], [1,5,7], [1,6,7], [1,6,8],
 * [2,4,8], [2,4,9], [2,6,7], [2,6,8],
 * [3,4,8], [3,4,9], [3,5,7], [3,5,9]
 * The falling path with the smallest sum is [1,5,7], so the answer is 13.
 * 
 * Example 2
 * Input: grid = [[7]]
 * Output: 7
 *
 * 3. Constraints
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * -99 <= grid[i][j] <= 99
 */
public class MinimumFallingPathSumII {

  /**
   * 1. Approach
   * Dynamic Programming. 
   * 
   * Define dp[i][j] as the minimum falling path sum with path falling at (i,j).
   * dp[i][j] = Min(dp[i][k]) + grid[i][j] where k != j
   * 
   * 2. Complexity 
   * - Time O(N ^ 2)
   * - Space O(N)
   * 
   * @param grid
   * @return
   */
  public int minFallingPathSum(int[][] grid) {
    final int n = grid.length;
    final int[] dp = new int[n];

    int firstMin = 0, secondMin = 0;
    for (int i = 0; i < n; i++) {
      final int firstMinTmp = firstMin, secondMinTmp = secondMin;
      firstMin = Integer.MAX_VALUE;
      secondMin = Integer.MAX_VALUE;
      for (int j = 0; j < n; j++) {
        dp[j] = (firstMinTmp == dp[j] ? secondMinTmp : firstMinTmp) + grid[i][j];
        if (firstMin > dp[j]) {
          secondMin = firstMin;
          firstMin = dp[j];
        } else if (secondMin > dp[j]) {
          secondMin = dp[j];
        }
      }
    }

    return firstMin;
  }
}
