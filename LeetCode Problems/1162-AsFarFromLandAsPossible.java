package problem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem 
 * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell
 * such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in 
 * the grid, return -1.
 *
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is
 * |x0 - x1| + |y0 - y1|.
 *
 * 2. Examples 
 * Example 1
 * Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
 * 
 * Example 2
 * Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 *
 * 3. Constraints
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1
 */
public class AsFarFromLandAsPossible {

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
  public int maxDistance(int[][] grid) {
    final int n = grid.length, m = grid[0].length;
    int maxDistance = -1;
    final boolean[][] visited = new boolean[n][m];
    final Queue<int[]> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1) {
          queue.offer(new int[]{i, j});
          visited[i][j] = true;
        }
      }
    }
    if(queue.size() == 0 || queue.size() == m * n) return -1;
    int distance = 0;
    while (!queue.isEmpty()) {
      final int size = queue.size();
      for (int k = 0; k < size; k++) {
        final int[] curr = queue.poll();
        if (grid[curr[0]][curr[1]] == 0) {
          maxDistance = Math.max(maxDistance, distance);
        }
        for (int[] dir : DIRS) {
          final int x = curr[0] + dir[0];
          final int y = curr[1] + dir[1];
          if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y]) {
            queue.offer(new int[]{x, y});
            visited[x][y] = true;
          }
        }
      }
      distance++;
    }
    return maxDistance;
  }
}
