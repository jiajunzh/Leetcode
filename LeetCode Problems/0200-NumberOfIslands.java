package problem;

/**
 * 1. Problem 
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of 
 * islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may
 * assume all four edges of the grid are all surrounded by water.
 *
 * 2. Example
 * Example 1
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * 
 * Example 2
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 * 3. Constraints
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 */
public class NumberOfIslands {

  /**
   * 1. Approach 
   * DFS.
   * 
   * 2. Complexity 
   * - Time O(MxN)
   * - Space O(MxN) in worst case for recursion stack
   * 
   * 3. Improvement 
   * - The initial keep track of a visited array to mark whether the position has been visited. However, an alternative 
   *   is to put it in place in grid array by modifying the grid from land '1' to water '0'
   * - Another alternative is to use BFS, we could store n * i + j into the Queue which will help get the (i,j) index
   *   later. It improves the space complexity to O(Min(M,N)). See analysis post:
   *   https://leetcode.com/problems/number-of-islands/discuss/1585867/Intuitive-Reason-for-Time-Complexity-Analysis-of-BFS-and-DFS-appraoch
   *   
   * @param grid
   * @return
   */
  public int numIslands(char[][] grid) {
    final int n = grid.length, m = grid[0].length;
    int count = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == '1') {
          count++;
          dfs(grid, i, j, n, m);
        }
      }
    }

    return count;
  }

  private void dfs(final char[][] grid, int i, int j, int n, int m) {
    if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == '0') {
      return;
    }

    grid[i][j] = '0';
    dfs(grid, i + 1, j, n, m);
    dfs(grid, i - 1, j, n, m);
    dfs(grid, i, j + 1, n, m);
    dfs(grid, i, j - 1, n, m);
  }

  private static class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count;

    private UnionFind(char[][] grid) {
      int n = grid.length;
      int m = grid[0].length;
      parent = new int[n * m];
      rank = new int[n * m];
      count = 0;

      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (grid[i][j] == '1') {
            parent[i * m + j] = i * m +j;
            count++;
          }
        }
      }
    }

    private int find(int i) {
      if (parent[i] != i) {
        parent[i] = find(parent[i]);
      }
      return parent[i];
    }

    private void union(int i, int j) {
      int parenti = find(i);
      int parentj = find(j);

      if (parenti != parentj) {
        if (rank[parenti] > rank[parentj]) {
          parent[parentj] = parenti;
        } else if (rank[parenti] < rank[parentj]) {
          parent[parenti] = parentj;
        } else {
          parent[parenti] = parentj;
          rank[parenti]++;
        }

        count--;
      }
    }

    private int getCount() {
      return this.count;
    }
  }

  /**
   * 1. Approach 
   * Union Find 
   * 
   * 2. Complexity 
   * - Time O(M * N)
   * - Space O(M * N)
   * 
   * @param grid
   * @return
   */
  public int numIslandsUnionFind(char[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    UnionFind uf = new UnionFind(grid);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == '1') {
          grid[i][j] = '0';
          if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            uf.union((i - 1) * m + j, i * m + j);
          }
          if (i + 1 < n && grid[i + 1][j] == '1') {
            uf.union((i + 1) * m + j, i * m + j);
          }
          if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            uf.union(i* m + j - 1, i * m + j);
          }
          if (j + 1 < m && grid[i][j + 1] == '1') {
            uf.union(i * m + j + 1, i * m + j);
          }
        }
      }
    }

    return uf.getCount();
  }
}
