package problem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem 
 * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 *
 * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
 *
 * You may change 0's to 1's to connect the two islands to form one island.
 *
 * Return the smallest number of 0's you must flip to connect the two islands.
 *
 * 2. Examples
 * Example 1
 * Input: grid = [[0,1],[1,0]]
 * Output: 1
 * 
 * Example 2
 * Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * 
 * Example 3
 * Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 * 
 * 3. Constraints
 * n == grid.length == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] is either 0 or 1.
 * There are exactly two islands in grid.
 */
public class ShortestBridge {
  
  private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  /**
   * 1. Approach
   * BFS
   * 
   * 2. Complexity 
   * - Time O(N * M)
   * - Space O(N * M)
   * 
   * @param grid
   * @return
   */
  public int shortestBridge(int[][] grid) {
    final int n = grid.length, m = grid[0].length;
    final Queue<int[]> queue1 = new LinkedList<>();
    final Queue<int[]> queue2 = new LinkedList<>();
    final int[] randomLand = findRandomLand(grid, n, m);
    queue1.offer(randomLand);
    grid[randomLand[0]][randomLand[1]] = 0;
    while (!queue1.isEmpty()) {
      final int[] curr = queue1.poll();
      queue2.offer(curr);
      for (int[] dir : DIRS) {
        final int x = dir[0] + curr[0];
        final int y = dir[1] + curr[1];
        if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] != 1) {
          continue;
        }
        queue1.offer(new int[]{x, y});
        grid[x][y] = -1;
      }
    }

    int shortestBridge = 0;
    while (!queue2.isEmpty()) {
      final int size = queue2.size();
      for (int i = 0; i < size; i++) {
        final int[] curr = queue2.poll();
        for (int[] dir : DIRS) {
          final int x = dir[0] + curr[0];
          final int y = dir[1] + curr[1];
          if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] == -1) {
            continue;
          }
          if (grid[x][y] == 1) {
            return shortestBridge;
          }
          queue2.offer(new int[]{x, y});
          grid[x][y] = -1;
        }
      }
      shortestBridge++;
    }
    return shortestBridge;
  }

  private int[] findRandomLand(int[][] grid, int n, int m) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1) {
          return new int[]{i, j};
        }
      }
    }
    return null;
  }
}
