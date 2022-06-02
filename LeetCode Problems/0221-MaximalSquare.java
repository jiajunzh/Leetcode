package problem;

/**
 * 1. Problem
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * 2. Examples
 * Example 1
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 * 
 * Example2
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * 
 * Example 3
 * Input: matrix = [["0"]]
 * Output: 0
 *
 * 3. Constraints
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 */
public class MaximalSquare {
  
  /**
   * 1. Approach 
   * DP. Define dp[i][j] as the maximum length of the square containing all 1s whose right bottom corner is at (i, j).
   * A square could be defined by horizontal, vertical and diagonal length.
   * Horizontal => dp[i][j - 1]
   * Vertical => dp[i - 1][j]
   * Diagonal => dp[i - 1][j - 1]
   * 
   * 2. Complexity
   * - Time O(M * N)
   * - Space O(M * N)
   * 
   * 3. Improvement
   * 1) Space Optimization 
   * 
   * @param matrix
   * @return
   */
  public int maximalSquare(char[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    int[][] dp = new int[m + 1][n + 1];
    int max = 0;

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (matrix[i - 1][j - 1] == '1') {
          dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
          max = Math.max(dp[i][j], max);
        }
      }
    }
    return max * max;
  }
}
