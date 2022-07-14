package problem;

/**
 * 1. Problem 
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 * 2. Example 
 * Example 1
 * Input: matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * Output: 15
 * Explanation: 
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * 
 * Example 2
 * Input: matrix = 
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * Output: 7
 * Explanation: 
 * There are 6 squares of side 1.  
 * There is 1 square of side 2. 
 * Total number of squares = 6 + 1 = 7.
 *
 * 3. Constraints
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 */
public class CountSquareSubmatricesWithAllOnes {

  /**
   * 1. Approach 
   * DP
   * 
   * 2. Complexity 
   * - Time O(M * N)
   * - Space O(M * N)
   * 
   * @param matrix
   * @return
   */
  public int countSquares(int[][] matrix) {
    final int m = matrix.length, n = matrix[0].length;
    final int[][] dp = new int[m + 1][n + 1];
    int count = 0;
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (matrix[i - 1][j - 1] == 1) {
          dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
          count += dp[i][j];
        }
      }
    }
    return count;
  }
}
