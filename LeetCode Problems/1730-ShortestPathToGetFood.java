package problem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem 
 * You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any
 * food cell.
 *
 * You are given an m x n character matrix, grid, of these different types of cells:
 *
 * '*' is your location. There is exactly one '*' cell.
 * '#' is a food cell. There may be multiple food cells.
 * 'O' is free space, and you can travel through these cells.
 * 'X' is an obstacle, and you cannot travel through these cells.
 * You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
 *
 * Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, 
 * return -1.
 *
 * 2. Examples 
 * Example 1
 * Input: grid = [["X","X","X","X","X","X"],["X","*","O","O","O","X"],["X","O","O","#","O","X"],["X","X","X","X","X","X"]]
 * Output: 3
 * Explanation: It takes 3 steps to reach the food.
 * 
 * Example 2
 * Input: grid = [["X","X","X","X","X"],["X","*","X","O","X"],["X","O","X","#","X"],["X","X","X","X","X"]]
 * Output: -1
 * Explanation: It is not possible to reach the food.
 * 
 * Example 3
 * Input: grid = [["X","X","X","X","X","X","X","X"],["X","*","O","X","O","#","O","X"],["X","O","O","X","O","O","X","X"],["X","O","O","O","O","#","O","X"],["X","X","X","X","X","X","X","X"]]
 * Output: 6
 * Explanation: There can be multiple food cells. It only takes 6 steps to reach the bottom food.
 *
 * 3. Constraints
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[row][col] is '*', 'X', 'O', or '#'.
 * The grid contains exactly one '*'.
 */
public class ShortestPathToGetFood {

  private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  /**
   * 1. Approach 
   * Shortest Path => Graph BFS. 
   * 
   * 2. Complexity
   * - Time O(M*N)
   * - Space O(min(M,N))
   * 
   * @param grid
   * @return
   */
  public int getFood(char[][] grid) {
    final Queue<int[]> queue = new LinkedList<>();
    final int[] startPosition = getStartPosition(grid);
    queue.offer(startPosition);
    int pathLength = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();
      pathLength++;

      for (int i = 0; i < size; i++) {
        int[] curr = queue.poll();
        for (int[] direction : DIRECTIONS) {
          int x = curr[0] + direction[0];
          int y = curr[1] + direction[1];

          if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            if (grid[x][y] == 'O') {
              grid[x][y] = 'X';
              queue.offer(new int[]{x, y});
            }
            if (grid[x][y] == '#') {
              return pathLength;
            }
          }
        }
      }
    }

    return -1;
  }

  private int[] getStartPosition(char[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '*') {
          return new int[]{i, j};
        }
      }
    }

    return null;
  }
}
