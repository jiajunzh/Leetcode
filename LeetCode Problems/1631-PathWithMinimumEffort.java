package problem;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1. Problem 
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where 
 * heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you 
 * hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, 
 * and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 * 2. Examples
 * Example 1
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * 
 * Example 2
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 * 
 * Example 3
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 * 3. Constraints
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 */
public class PathWithMinimumEffort {
  
  private static final int[][] DIRS = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

  /**
   * 1. Approach 
   * Modified Dijkstra's Algorithm
   * 
   * 2. Complexity 
   * - Time O(mnlog(mn))
   * - Space O(mn)
   * 
   * 3. Alternative 
   * - Union Find + Sorting Edges: Sort the edge by effort from smallest to largest and iterate through each edge to 
   *   connect each cell. Once the whole graph is connected, the current effort in current edge is the answer
   * - Binary Search + BFS/DFS: Search space is from 0 to 10^6 => Binary search the whole space and try if the current
   *   effort is possible for you to get the path from src to dest. 
   * 
   * @param heights
   * @return
   */
  public int minimumEffortPath(int[][] heights) {
    final int row = heights.length, col = heights[0].length;
    final Queue<int[]> queue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
    final int[][] effortTo = new int[row][col];
    for (int i = 0; i < row; i++) {
      Arrays.fill(effortTo[i], Integer.MAX_VALUE);
    }
    queue.offer(new int[]{0, 0, 0});
    effortTo[0][0] = 0;
    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      for (int[] dir : DIRS) {
        int x = curr[0] + dir[0];
        int y = curr[1] + dir[1];
        if (x >= 0 && x < row && y >= 0 && y < col) {
          if (Math.max(effortTo[curr[0]][curr[1]], Math.abs(heights[curr[0]][curr[1]] - heights[x][y])) < effortTo[x][y]) {
            effortTo[x][y] = Math.max(effortTo[curr[0]][curr[1]], Math.abs(heights[curr[0]][curr[1]] - heights[x][y]));
            queue.offer(new int[]{x, y, effortTo[x][y]});
          }
        }
      }
    }
    return effortTo[row - 1][col - 1];
  }
}
