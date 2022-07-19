package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * Given a matrix and a target, return the number of non-empty submatrices that sum to target.
 *
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 *
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is 
 * different: for example, if x1 != x1'.
 *
 * 2. Examples
 * Example 1
 * Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * Output: 4
 * Explanation: The four 1x1 submatrices that only contain 0.
 * 
 * Example 2
 * Input: matrix = [[1,-1],[-1,1]], target = 0
 * Output: 5
 * Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
 * 
 * Example 3
 * Input: matrix = [[904]], target = 0
 * Output: 0
 *
 * 3. Constraints
 * 1 <= matrix.length <= 100
 * 1 <= matrix[0].length <= 100
 * -1000 <= matrix[i] <= 1000
 * -10^8 <= target <= 10^8
 */
public class NumberOfSubmatricesThatSumToTarget {

  /**
   * 1. Approach 
   * Prefix Sum on Column. The core of this problem is to settle down four coordinates which (x1, x2, y1, y2).
   * If we fix x1 and x2, then the problem is converted to subarray sum, which could be solved by prefix sum.
   * 
   * Here we still use 1D prefix sum, but one could also use 2D prefix sum which represents the sum of all elements 
   * to the left and above to point (i, j)
   * 
   * 2. Complexity
   * - Time O(R^2 * C)
   * - Space O(C)
   * 
   * @param matrix
   * @param target
   * @return
   */
  public int numSubmatrixSumTarget(int[][] matrix, int target) {
    final int n = matrix.length, m = matrix[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 1; j < n; j++) {
        matrix[j][i] += matrix[j - 1][i];
      }
    }
    int count = 0;
    for (int r1 = 0; r1 < n; r1++) {
      for (int r2 = r1; r2 < n; r2++) {
        final Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for (int c = 0; c < m; c++) {
          sum += matrix[r2][c] - (r1 == 0 ? 0 : matrix[r1 - 1][c]);
          count += map.getOrDefault(sum - target, 0);
          map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
      }
    }
    return count;
  }
}
