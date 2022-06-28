package problem;

import java.util.PriorityQueue;

/**
 * 1. Problem 
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up,
 * down, left, or right from and to an empty cell in one step.
 *
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1)
 * given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 * 2. Examples 
 * Example 1
 * Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
 * Output: 6
 * Explanation: 
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 * 
 * Example 2
 * Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
 * Output: -1
 * Explanation: We need to eliminate at least two obstacles to find such a walk.
 *
 * 3. Constraints
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 40
 * 1 <= k <= m * n
 * grid[i][j] is either 0 or 1.
 * grid[0][0] == grid[m - 1][n - 1] == 0
 */
public class ShortestPathInAGridWithObstaclesElimination {

  private final static int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

  /**
   * 1. Approach 
   * BFS + A* Algorithm.
   *  
   * This problem asks the minimum steps that are needed to reach point(n - 1, m - 1), aka, the shortest path.
   * It is intuitive to think that BFS should be a great candidate approach to solve this problem.
   * 
   * Let's start with pure BFS! For each state, we record three statuses, row, col and number of elimination we have done.
   * We should also keep a visited boolean 3d array to memoization the path that we have explored so that no duplicate
   * path will be explored twice.
   * 
   * On top of the BFS, we could use heuristic approach to optimize the performance by using A* algorithm. In pure BFS,
   * we always traverse the next level once we are done with the previous level. However. A* defines a cost function 
   * f(n) = g(n) + h(n) where g(n) is the cost of reaching step (i, j) from (0, 0) and h(n) is the estimated cost of 
   * reaching (n - 1, m - 1) from (i, j). Intuitively, it prioritizes the downward and right operations over the upward
   * and left operation, which improves the chances of finding the final solution quicker.
   * 
   * The implementation below is using A* algorithm. Simply changing the priority queue to regular queue will get you a 
   * version of pure BFS implementation,
   * 
   * 2. Complexity 
   * 1) Pure BFS
   * - Time O(N * M * k) 
   * - Space O(N * M * k)
   * 2) A*
   * - Time O(N * M * k * log (N * M * k)) 
   * - Space O(N * M * k)
   * 
   * @param grid
   * @param k
   * @return
   */
  public int shortestPath(int[][] grid, int k) {
    final int n = grid.length;
    final int m = grid[0].length;
    final PriorityQueue<StepState> queue = new PriorityQueue<>();
    final boolean[][][] visited = new boolean[n][m][k + 1];
    queue.offer(new StepState(0, 0, 0, grid[0][0], n - 1, m - 1));
    visited[0][0][grid[0][0]] = true;

    while (!queue.isEmpty()) {
      StepState stepState = queue.poll();
      if (stepState.row == n - 1 && stepState.col == m - 1) return stepState.step;
      for (int[] dir : DIRS) {
        int x = stepState.row + dir[0];
        int y = stepState.col + dir[1];
        if (x >= 0 && x < n && y >= 0 && y < m) {
          int z = stepState.elimination + grid[x][y];
          if (z <= k && !visited[x][y][z]) {
            queue.offer(new StepState(x, y, stepState.step + 1, z, n - 1, m - 1));
            visited[x][y][z] = true;
          }
        }
      }
    }

    return -1;
  }

  private static class StepState implements Comparable<StepState> {

    private final int row;
    private final int col;
    private final int step;
    private final int elimination;
    private final int targetRow;
    private final int targetCol;

    private StepState(final int row, final int col, final int step, final int elimination, final int targetRow, final int targetCol) {
      this.row = row;
      this.col = col;
      this.step = step;
      this.elimination = elimination;
      this.targetRow = targetRow;
      this.targetCol = targetCol;
    }

    @Override
    public int compareTo(StepState stepState) {
      return this.getCost() - stepState.getCost();
    }

    private int getCost() {
      return step + Math.abs(row - targetRow) + Math.abs(col - targetCol);
    }
  }
}
