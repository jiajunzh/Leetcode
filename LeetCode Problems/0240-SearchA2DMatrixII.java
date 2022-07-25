package problem;

/**
 * 1. Problem 
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the 
 * following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * 2. Examples 
 * Example 1
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 * 
 * Example 2
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *
 * 3. Constraints
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -109 <= target <= 109
 */
public class SearchA2DMatrixII {

  /**
   * 1. Approach 
   * Search Space Reduction / Pruning.
   * 
   * We initialize a pointer at the bottom left of the matrix and then move only up or right based on condition.
   * 
   * For each matrix[i][j], we guarantee that all elements to the southwest of it are explored/pruned.
   * 1) if matrix[i][j] > target, then we want to search for smaller elements, meaning all elements to the southeast are 
   * useless as they are greater than matrix[i][j]/target. In the meantime, we guarantee that all elements to the 
   * southwest of it are explored/pruned. That is to say, we don't need to explore anything below the row of this element.
   * Then we search upwards.
   * 
   * Question: is it possible that the answer is at somewhere [i1][j1] where i1 < i && j1 < j?
   * No. When we traverse from row i1 to i, we guarantee that there is no element in row i1 could be the answer.
   * 
   * 2) Similarly, if matrix[i][j] < target, then we want to search for larger elements => we search towards right.
   * 
   * 2. Complexity
   * - Time O(N + M)
   * - Space O(1)
   * 
   * @param matrix
   * @param target
   * @return
   */
  public boolean searchMatrix(int[][] matrix, int target) {
    final int n = matrix.length, m = matrix[0].length;
    int i = n - 1, j = 0;
    while (i >= 0 && j < m) {
      if (matrix[i][j] > target) {
        i--;
      } else if (matrix[i][j] < target) {
        j++;
      } else {
        return true;
      }
    }
    return false;
  }

  /**
   * 1. Approach 
   * Binary Search
   * 
   * 2. Complexity 
   * - Time O(N * logM)
   * - Space O(1)
   * 
   * @param matrix
   * @param target
   * @return
   */
  public boolean searchMatrix2(int[][] matrix, int target) {
    final int n = matrix.length, m = matrix[0].length;
    for (int i = 0; i < n; i++) {
      int low = 0, high = m;
      // Find the minimum element value such that k > target.
      while (low < high) {
        int mid = low + (high - low) / 2;
        if (matrix[i][mid] == target) return true;
        if (matrix[i][mid] > target) {
          high = mid;
        } else {
          low = mid + 1;
        }
      }
    }
    return false;
  }
}
