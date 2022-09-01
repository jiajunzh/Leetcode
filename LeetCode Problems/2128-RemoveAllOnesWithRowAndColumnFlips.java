package problem;

/**
 * 1. Problem 
 * You are given an m x n binary matrix grid.
 *
 * In one operation, you can choose any row or column and flip each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).
 *
 * Return true if it is possible to remove all 1's from grid using any number of operations or false otherwise.
 *
 * 2. Examples
 * Example 1
 * Input: grid = [[0,1,0],[1,0,1],[0,1,0]]
 * Output: true
 * Explanation: One possible way to remove all 1's from grid is to:
 * - Flip the middle row
 * - Flip the middle column
 * 
 * Example 2
 * Input: grid = [[1,1,0],[0,0,0],[0,0,0]]
 * Output: false
 * Explanation: It is impossible to remove all 1's from grid.
 *
 * Example 3
 * Input: grid = [[0]]
 * Output: true
 * Explanation: There are no 1's in grid.
 *
 * 3. Constraints
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is either 0 or 1.
 */
public class RemoveAllOnesWithRowAndColumnFlips {

  /**
   * 1. Approach 
   * Matrix. Whether we could remove all ones with row and column flips could be determined by whether FirstElement(row) == FirstElement(row - 1) 
   * is equal to ElementJ(row) == ElementJ(row - 1)
   * 
   * 2. Complexity 
   * - Time O(M * N)
   * - Space O(1)
   * 
   * @param grid
   * @return
   */
  public boolean removeOnes(int[][] grid) {
    final int n = grid.length, m = grid[0].length;
    for (int i = 1; i < n; i++) {
      for (int j = 1; j < m; j++) {
        if ((grid[i - 1][0] == grid[i][0]) != (grid[i - 1][j] == grid[i][j])) {
          return false;
        }
      }
    }
    return true;
  }
}
