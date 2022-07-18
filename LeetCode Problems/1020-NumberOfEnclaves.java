package problem;

/**
 * 1. Problem 
 * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
 *
 * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the
 * boundary of the grid.
 *
 * Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
 *
 * 2. Examples
 * Example 1
 * Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
 * 
 * Example 2
 * Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation: All 1s are either on the boundary or can reach the boundary.
 *
 * 3. Constraints
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] is either 0 or 1.
 */
public class NumberOfEnclaves {

  /**
   * 1. Approach 
   * DFS
   * 
   * 2. Complexity 
   * - Time O(N * M)
   * - Space O(N * M)
   * 
   * 3. Alternatives
   * - Another way to look at this problem is clean up/traverse the land on the border which you could walk off and then
   *   simply count the number of lands within the border.
   * 
   * @param grid
   * @return
   */
  public int numEnclaves(int[][] grid) {
    final int n = grid.length, m = grid[0].length;
    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1) {
          int currCount = dfs(grid, n, m, i, j);
          if (currCount != -1) {
            count += currCount;
          }
        }
      }
    }
    return count;
  }

  private int dfs(int[][] grid, int n, int m, int i, int j) {
    if (i < 0 || i >= n || j < 0 || j >= m) return -1;
    if (grid[i][j] == 0) return 0;
    grid[i][j] = 0;
    int up = dfs(grid, n, m, i - 1, j);
    int down = dfs(grid, n, m, i + 1, j);
    int left = dfs(grid, n, m, i, j - 1);
    int right = dfs(grid, n, m, i, j + 1);
    if (up == -1 || down == -1 || left == -1 || right == -1) return -1;
    return 1 + up + down + left + right;
  }
}
