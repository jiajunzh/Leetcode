package problem;

/**
 * 1. Problem
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 *
 * 2. Examples
 * Example 1
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,4,7,5,3,6,8,9]
 * 
 * Example 2
 * Input: mat = [[1,2],[3,4]]
 * Output: [1,2,3,4]
 *
 * 3. Constraints
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * -105 <= mat[i][j] <= 105
 */
public class DiagonalTraverse {

  /**
   * 1. Approach
   * Linear
   * 
   * 2. Complexity
   * - Time O(N * M)
   * - Space O(1)
   * 
   * @param mat
   * @return
   */
  public int[] findDiagonalOrder(int[][] mat) {
    final int n = mat.length, m = mat[0].length;
    final int[] result = new int[n * m];
    int row = 0, col = 0;
    for (int i = 0; i < n * m; i++) {
      result[i] = mat[row][col];
      if ((row + col) % 2 == 0) {
        if (col == m - 1) {
          row++;
        } else if (row == 0) {
          col++;
        } else {
          row--;
          col++;
        }
      } else {
        if (row == n - 1) {
          col++;
        } else if (col == 0) {
          row++;
        } else {
          col--;
          row++;
        }
      }
    }
    return result;
  }
}
