package problem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem
 * You are given an m x n grid rooms initialized with these three possible values.
 *
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 * 2. Examples
 * Example 1
 * Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 * 
 * Example 2
 * Input: rooms = [[-1]]
 * Output: [[-1]]
 *
 * 3. Constraints
 * m == rooms.length
 * n == rooms[i].length
 * 1 <= m, n <= 250
 * rooms[i][j] is -1, 0, or 231 - 1.
 */
public class WallsAndGates {
  
  private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  /**
   * 1. Approach 
   * BFS
   * 
   * 2. Complexity
   * - Time O(M * N)
   * - Space O(M * N)
   *
   * @param rooms
   */
  public void wallsAndGates(int[][] rooms) {
    final int m = rooms.length, n = rooms[0].length;
    final Queue<int[]> queue = new LinkedList<>();
    final boolean[][] visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (rooms[i][j] == 0) {
          queue.offer(new int[]{i, j});
          visited[i][j] = true;
        }
      }
    }
    int dist = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int[] curr = queue.poll();
        if (rooms[curr[0]][curr[1]] != 0) {
          rooms[curr[0]][curr[1]] = dist;
        }
        for (int[] dir : DIRS) {
          int x = dir[0] + curr[0];
          int y = dir[1] + curr[1];
          if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && rooms[x][y] > 0) {
            queue.offer(new int[]{x, y});
            visited[x][y] = true;
          }
        }
      }
      dist++;
    }
  }
}
