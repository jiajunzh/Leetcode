package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 * 2. Examples
 * Example 1
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * 
 * Example 2
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 3. Constraints
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class SpiralMatrix {

  /**
   * 1. Approach 
   * Slicing/Boundary
   * 
   * 2. Complexity 
   * - Time O(N * M)
   * - Space O(1) if not consider space for result storage
   * 
   * @param matrix
   * @return
   */
  public List<Integer> spiralOrder(int[][] matrix) {
    final int n = matrix.length, m = matrix[0].length;
    final List<Integer> result = new ArrayList<>();
    int upRow = 0, downRow = n - 1, leftCol = 0, rightCol = m - 1;
    while (result.size() < n * m) {
      for (int col = leftCol; col <= rightCol; col++) {
        result.add(matrix[upRow][col]);
      }
      for (int row = upRow + 1; row <= downRow; row++) {
        result.add(matrix[row][rightCol]);
      }
      if (upRow != downRow) {
        for (int col = rightCol - 1; col >= leftCol; col--) {
          result.add(matrix[downRow][col]);
        }
      }
      if (leftCol != rightCol) {
        for (int row = downRow - 1; row > upRow; row--) {
          result.add(matrix[row][leftCol]);
        }
      }
      upRow++;
      downRow--;
      leftCol++;
      rightCol--;
    }
    return result;
  }
}
