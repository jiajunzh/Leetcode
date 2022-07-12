package problem;

/**
 * 1. Problem 
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents
 * water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, 
 * and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell 
 * is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter 
 * of the island.
 *
 * 2. Examples 
 * Example 1
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image above.
 * 
 * Example 2
 * Input: grid = [[1]]
 * Output: 4
 * 
 * Example 3
 * Input: grid = [[1,0]]
 * Output: 4
 *
 * 3. Constraints
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] is 0 or 1.
 * There is exactly one island in grid.
 */
public class IslandPerimeter {

  /**
   * 1. Approach 
   * DFS 
   * 
   * 2. Complexity 
   * - Time O(M * N)
   * - Space O(M * N)
   * 
   * @param grid
   * @return
   */
  public int islandPerimeter(int[][] grid) {
    final int n = grid.length, m = grid[0].length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1) return dfs(grid, n, m, i, j);
      }
    }
    return -1;
  }

  private int dfs(int[][] grid, int n, int m, int i, int j) {
    if (i < 0 || i >= n || j < 0 || j >= m || grid[i][j] == 0) return 1;
    if (grid[i][j] == 2) return 0;
    grid[i][j] = 2;
    return dfs(grid, n, m, i - 1, j) + dfs(grid, n, m, i + 1, j) + dfs(grid, n, m, i, j - 1) + dfs(grid, n, m, i, j + 1);
  }
}
