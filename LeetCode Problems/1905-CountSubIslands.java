package problem;

/**
 * 1. Problem 
 * You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's 
 * (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells 
 * outside of the grid are considered water cells.
 *
 * An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make 
 * up this island in grid2.
 *
 * Return the number of islands in grid2 that are considered sub-islands.
 * 
 * 2. Examples 
 * Example 1
 * Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 * Output: 3
 * Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
 * The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
 * 
 * Example 2
 * Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 * Output: 2 
 * Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
 * The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
 *
 * 3. Constraints
 * m == grid1.length == grid2.length
 * n == grid1[i].length == grid2[i].length
 * 1 <= m, n <= 500
 * grid1[i][j] and grid2[i][j] are either 0 or 1. 
 */
public class CountSubIslands {

  /**
   * 1. Approach 
   * DFS 
   * 
   * 2. Complexity 
   * - Time O(M * N)
   * - Space O(M * N)
   * 
   * @param grid1
   * @param grid2
   * @return
   */
  public int countSubIslands(int[][] grid1, int[][] grid2) {
    final int n = grid1.length, m = grid1[0].length;
    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid2[i][j] == 1 && dfs(grid1, grid2, n, m, i, j)) count++;
      }
    }
    return count;
  }

  private boolean dfs(int[][] grid1, int[][] grid2, int n, int m, int i, int j) {
    if (i < 0 || i >= n || j < 0 || j >= m) return true;
    if (grid2[i][j] == 0) return true;
    grid2[i][j] = 0;
    final boolean up = dfs(grid1, grid2, n, m, i - 1, j);
    final boolean down = dfs(grid1, grid2, n, m, i + 1, j);
    final boolean left = dfs(grid1, grid2, n, m, i, j - 1);
    final boolean right = dfs(grid1, grid2, n, m, i, j + 1);
    return grid1[i][j] == 1 && up && down && left && right;
  }
}
