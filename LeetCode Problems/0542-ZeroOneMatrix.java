package problem;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem 
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 * 2. Examples
 * Example 1
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 * 
 * Example 2
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 *
 * 3. Constraints
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * There is at least one 0 in mat.
 */
public class ZeroOneMatrix {
  
  private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  /**
   * 1. Approach 
   * BFS
   * 
   * 2. Complexity
   * - Time O(R * C)
   * - Space O(R * C)
   * 
   * @param mat
   * @return
   */
  public int[][] updateMatrix1(int[][] mat) {
    final int n = mat.length, m = mat[0].length;
    final Queue<int[]> queue = new LinkedList<>();
    final int[][] distances = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (mat[i][j] == 0) queue.offer(new int[]{i, j});
      }
    }
    int distance = 1;
    while (!queue.isEmpty()) {
      final int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] curr = queue.poll();
        for (int[] dir : DIRS) {
          int x = curr[0] + dir[0];
          int y = curr[1] + dir[1];
          if (x < 0 || x >= n || y < 0 || y >= m || mat[x][y] == 0 || distances[x][y] != 0) {
            continue;
          }
          distances[x][y] = distance;
          queue.offer(new int[]{x, y});
        }
      }
      distance++;
    }
    return distances;
  }

  /**
   * 1. Approach 
   * DP with traversal from top-left once and then bottom-right again.
   * 
   * There are four parts to consider:
   * - top-left: considered during top-left traversal
   * - top-right: considered during both top-left and bottom-right traversal
   * - bottom-left: considered during both top-left and bottom-right traversal
   * - bottom-right: considered during bottom-right traversal
   * 
   * 2. Complexity
   * - Time O(R * C)
   * - Space O(1)
   * 
   * @param mat
   * @return
   */
  public int[][] updateMatrix2(int[][] mat) {
    final int n = mat.length, m = mat[0].length;
    final int[][] distances = new int[n][m];
    for (int[] distance : distances) {
      Arrays.fill(distance, 100_000);
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (mat[i][j] == 0) {
          distances[i][j] = 0;
        } else {
          if (i > 0) distances[i][j] = Math.min(distances[i][j], distances[i - 1][j] + 1);
          if (j > 0) distances[i][j] = Math.min(distances[i][j], distances[i][j - 1] + 1);
        }
      }
    }
    for (int i = n - 1; i >= 0; i--) {
      for (int j = m - 1; j >= 0; j--) {
        if (mat[i][j] == 0) {
          distances[i][j] = 0;
        } else {
          if (i < n - 1) distances[i][j] = Math.min(distances[i][j], distances[i + 1][j] + 1);
          if (j < m - 1) distances[i][j] = Math.min(distances[i][j], distances[i][j + 1] + 1);
        }
      }
    }
    return distances;
  }
}
