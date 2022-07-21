package problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1. Problem 
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean 
 * touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where 
 * heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, 
 * and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from 
 * any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell 
 * (ri, ci) to both the Pacific and Atlantic oceans.
 *
 * 2. Examples 
 * Example 1
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 
 * Example 2
 * Input: heights = [[2,1],[1,2]]
 * Output: [[0,0],[0,1],[1,0],[1,1]]
 *
 * 3. Constraints
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 105
 */
public class PacificAtlanticWaterFlow {

  /**
   * 1. Approach 
   * BFS Twice
   * 
   * 2. Complexity 
   * - Time O(N * M)
   * - Space O(N * M)
   */
  private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    final int n = heights.length, m = heights[0].length;
    final Queue<int[]> pacificQueue = new LinkedList<>();
    final Queue<int[]> atlanticQueue = new LinkedList<>();
    final boolean[][] pacificReachable = new boolean[n][m];
    final boolean[][] atlanticReachable = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      pacificQueue.offer(new int[]{i, 0});
      pacificReachable[i][0] = true;
    }
    for (int i = 1; i < m; i++) {
      pacificQueue.offer(new int[]{0, i});
      pacificReachable[0][i] = true;
    }
    for (int i = 0; i < n; i++) {
      atlanticQueue.offer(new int[]{i, m - 1});
      atlanticReachable[i][m - 1] = true;
    }
    for (int i = 0; i < m - 1; i++) {
      atlanticQueue.offer(new int[]{n - 1, i});
      atlanticReachable[n - 1][i] = true;
    }

    bfs(heights, pacificQueue, pacificReachable);
    bfs(heights, atlanticQueue, atlanticReachable);

    final List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (pacificReachable[i][j] && atlanticReachable[i][j]) {
          result.add(List.of(i, j));
        }
      }
    }
    return result;
  }

  private void bfs(final int[][] heights, final Queue<int[]> queue, final boolean[][] reachable) {
    final int n = heights.length, m = heights[0].length;
    while (!queue.isEmpty()) {
      final int[] curr = queue.poll();
      for (int[] dir : DIRS) {
        final int x = dir[0] + curr[0];
        final int y = dir[1] + curr[1];
        if (x < 0 || x >= n || y < 0 || y >= m || reachable[x][y] || heights[x][y] < heights[curr[0]][curr[1]]) {
          continue;
        }
        queue.offer(new int[]{x, y});
        reachable[x][y] = true;
      }
    }
  }
}
