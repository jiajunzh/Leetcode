package problem;

/**
 * 1. Problem 
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 *
 * A falling path starts at any element in the first row and chooses the element in the next row that is either directly 
 * below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 *
 * 2. Examples
 * Example 1
 * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 * Explanation: There are two falling paths with a minimum sum as shown.
 * 
 * Example 2
 * Input: matrix = [[-19,57],[-40,-5]]
 * Output: -59
 * Explanation: The falling path with a minimum sum is shown.
 *
 * 3. Constraints
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 */
public class MinimumFallingPathSum {
  
  /**
   * 1. Approach 
   * Dynamic Programming.
   * 
   * 1) State 
   * Define dp[i][j] as the minimum falling path sum from row 1 to row i that falls ultimately on position (i, j)
   * Path to position (i, j) could be from either of position (i - 1, j - 1), (i - 1, j ) or (i - 1, j + 1)
   * 
   * Note to check the boundary of the matrix.
   * 
   * 2) Recurrence Relation
   * dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j]
   * 
   * 3) Base Case
   * dp[0][j] = matrix[0][j]
   * 
   * 4) Solution
   * Min(dp[n - 1][j])
   * 
   * 2. Complexity 
   * - Time O(M * N)
   * - Space O(M * N)
   */
  public int minFallingPathSum(int[][] matrix) {
    int n = matrix.length, m = matrix[0].length;
    final int[] dp = new int[m + 1];
    int minSum = Integer.MAX_VALUE;

    for (int i = 1; i <= n; i++) {
      int prev = 0;
      for (int j = 1; j <= m; j++) {
        int min = dp[j];
        if (j > 1) min = Math.min(min, prev);
        if (j < m) min = Math.min(min, dp[j + 1]);
        prev = dp[j];
        dp[j] = min + matrix[i - 1][j - 1];
        if (i == n) {
          minSum = Math.min(minSum, dp[j]);
        }
      }
    }

    return minSum;
  }
}
