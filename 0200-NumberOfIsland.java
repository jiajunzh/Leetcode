public class NumberOfIsland {
  
  /**
   * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
   * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
   * You may assume all four edges of the grid are all surrounded by water.
   * 
   * @param grid
   * @return
   */
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      throw new IllegalArgumentException("Wrong grid parameter");
    }

    int m = grid.length;
    int n = grid[0].length;
    int cnt = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          cnt++;
          dfs(grid, m, n, i, j);
        }
      }
    }

    return cnt;
  }

  private void dfs(char[][] grid, int m, int n, int i, int j) {
    if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') {
      return;
    }

    grid[i][j] = '-';
    dfs(grid, m, n, i + 1, j);
    dfs(grid, m, n, i, j + 1);
    dfs(grid, m, n, i - 1 , j);
    dfs(grid, m, n, i, j - 1);
  }

  public static void main(String[] args) {
    NumberOfIsland noi = new NumberOfIsland();
    char[][] grid = new char[][]{{'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}};
    int cnt = noi.numIslands(grid);
    System.out.println(cnt);
  }
}
