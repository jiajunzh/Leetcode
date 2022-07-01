package problem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem 
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through
 * the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball 
 * stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and 
 * destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise
 * return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 * 2. Examples 
 * Example 1
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * 
 * Example 2
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the 
 * destination but you cannot stop there.
 * 
 * Example 3
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
 * Output: false
 *
 * 3. Constraints
 * m == maze.length
 * n == maze[i].length
 * 1 <= m, n <= 100
 * maze[i][j] is 0 or 1.
 * start.length == 2
 * destination.length == 2
 * 0 <= startrow, destinationrow <= m
 * 0 <= startcol, destinationcol <= n
 * Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
 * The maze contains at least 2 empty spaces.
 */
public class TheMaze {

  private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  /**
   * 1. Approach 
   * BFS.
   * 
   * 2. Complexity
   * - Time O(N * M)
   * - Space O(N * M)
   * 
   * @param maze
   * @param start
   * @param destination
   * @return
   */
  public boolean hasPathBfs(int[][] maze, int[] start, int[] destination) {
    final int n = maze.length;
    final int m = maze[0].length;
    final boolean[][] visited = new boolean[n][m];
    final Queue<int[]> queue = new LinkedList<>();
    queue.offer(start);
    visited[start[0]][start[1]] = true;
    while(!queue.isEmpty()) {
      final int[] curr = queue.poll();
      if (curr[0] == destination[0] && curr[1] == destination[1]) {
        return true;
      }
      for (int[] dir : DIRS) {
        int x = dir[0] + curr[0];
        int y = dir[1] + curr[1];
        while (x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 0) {
          x += dir[0];
          y += dir[1];
        }
        if (!visited[x - dir[0]][y - dir[1]]) {
          queue.offer(new int[]{x - dir[0], y - dir[1]});
          visited[x - dir[0]][y - dir[1]] = true;
        }
      }
    }
    return false;
  }

  /**
   * 1. Approach 
   * DFS
   *
   * 2. Complexity
   * - Time O(N * M)
   * - Space O(N * M)
   *
   * @param maze
   * @param start
   * @param destination
   * @return
   */
  public boolean hasPathDfs(int[][] maze, int[] start, int[] destination) {
    final int n = maze.length;
    final int m = maze[0].length;
    final boolean[][] visited = new boolean[n][m];
    return hasPath(maze, start, destination, visited, n, m);
  }

  private boolean hasPath(int[][] maze, int[] start, int[] destination, boolean[][] visited, int n, int m) {
    if (start[0] == destination[0] && start[1] == destination[1]) {
      return true;
    }
    visited[start[0]][start[1]] = true;
    for (int[] dir : DIRS) {
      int x = dir[0] + start[0];
      int y = dir[1] + start[1];
      while (x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 0) {
        x += dir[0];
        y += dir[1];
      }
      if (!visited[x - dir[0]][y - dir[1]] && hasPath(maze, new int[]{x - dir[0], y - dir[1]}, destination, visited, n, m)) {
        return true;
      }
    }
    return false;
  }
}
