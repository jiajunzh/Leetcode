package problem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem 
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no 
 * clear path, return -1.
 *
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell
 * (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge 
 * or a corner).
 * The length of a clear path is the number of visited cells of this path.
 *
 * 2. Examples 
 * Example 1
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 * 
 * Example 2
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 * 
 * Example 3
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 *
 * 3. Constraints
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1
 */
public class ShortestPathInBinaryMatrix {
  
  private final static int[][] DIRECTIONS = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

  /**
   * 1. Approach 
   * BFS 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param grid
   * @return
   */
  public int shortestPathBinaryMatrix(int[][] grid) {
    if (grid[0][0] == 1) return -1;
    int length = 0;
    int n = grid.length;
    final Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{0, 0});
    grid[0][0] = 1;
    while (!queue.isEmpty()) {
      final int size = queue.size();
      length++;
      for (int i = 0; i < size; i++) {
        final int[] curr = queue.poll();
        final int currX = curr[0];
        final int currY = curr[1];
        if (currX == n - 1 && currY == n - 1) {
          return length;
        }
        for (int[] direction : DIRECTIONS) {
          int x = curr[0] + direction[0];
          int y = curr[1] + direction[1];

          if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
            queue.add(new int[]{x, y});
            grid[x][y] = 1;
          }
        }
      }
    }

    return -1;
  }
}
