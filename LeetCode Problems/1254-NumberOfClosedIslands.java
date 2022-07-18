package problem;

/**
 * 1. Problem 
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s 
 * and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 *
 * Return the number of closed islands.
 *
 * 2. Examples 
 * Example 1
 * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation: 
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 * 
 * Example 2
 * Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * Output: 1
 * 
 * Example 3
 * Input: grid = [[1,1,1,1,1,1,1],
 *                [1,0,0,0,0,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,1,0,1,0,1],
 *                [1,0,1,1,1,0,1],
 *                [1,0,0,0,0,0,1],
 *                [1,1,1,1,1,1,1]]
 * Output: 2
 *
 * 3. Constraints
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 */
public class NumberOfClosedIslands {

  /**
   * 1. Approach 
   * DFS 
   * 
   * 2. Complexity 
   * - Time O(M * N)
   * - Space O(M * N)
   * 
   * 3. Mistakes 
   * - Be careful of using dfs(grid, n, m, i - 1, j) && dfs(grid, n, m, i + 1, j) && dfs(grid, n, m, i, j - 1) && dfs(grid, n, m, i, j + 1)
   *   As if dfs(grid, n, m, i - 1, j) = false, the other three directions won't be traversed
   *   
   * @param grid
   * @return
   */
  public int closedIsland(int[][] grid) {
    final int n = grid.length, m = grid[0].length;
    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 0 && dfs(grid, n, m, i, j)) {
          count++;
        }
      }
    }
    return count;
  }

  private boolean dfs(int[][] grid, int n, int m, int i, int j) {
    if (i < 0 || i >= n || j < 0 || j >= m) {
      return false;
    }
    if (grid[i][j] == 1) {
      return true;
    }
    grid[i][j] = 1;
    boolean up = dfs(grid, n, m, i + 1, j);
    boolean down = dfs(grid, n, m, i - 1, j);
    boolean left = dfs(grid, n, m, i, j - 1);
    boolean right = dfs(grid, n, m, i, j + 1);
    return up && down && left && right;
  }
}
