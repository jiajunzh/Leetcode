package problem;

/**
 * 1. Problem 
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes 
 * the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * 2. Examples 
 * Example 1
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * 
 * Example 2
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 *
 * 3. Constraints
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 */
public class MinimumPathSum {
  
  /**
   * 1. Approach  
   * Dynamic Programming 
   * Define dp[i][j] => Minimum path sum from (0, 0) to (i, j)
   * dp[i][j] = Min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
   *
   * Base Case: dp[0][0] = grid[0][0]
   * 
   * 2. Complexity 
   * - Time O(M * N)
   * - Space O(M * N) => Think about 1D DP and In place DP for space optimization
   */
  public int minPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    final int[][] dp = new int[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0) {
          dp[i][j] = 0;
        } else if (i == 0) {
          dp[i][j] = dp[i][j - 1];
        } else if (j == 0) {
          dp[i][j] = dp[i - 1][j];
        } else {
          dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]);
        }
        dp[i][j] += grid[i][j];
      }
    }

    return dp[m - 1][n - 1];
  }
}
