package problem;

/**
 * 1. Problem 
 * You are given a 2D integer array grid of size m x n, where each cell contains a positive integer.
 *
 * A cornered path is defined as a set of adjacent cells with at most one turn. More specifically, 
 * the path should exclusively move either horizontally or vertically up to the turn (if there is one), 
 * without returning to a previously visited cell. After the turn, the path will then move exclusively in the alternate
 * direction: move vertically if it moved horizontally, and vice versa, also without returning to a previously visited 
 * cell.
 *
 * The product of a path is defined as the product of all the values in the path.
 *
 * Return the maximum number of trailing zeros in the product of a cornered path found in grid.
 *
 * Note:
 * Horizontal movement means moving in either the left or right direction.
 * Vertical movement means moving in either the up or down direction.
 *
 * 2. Examples
 * Example 1
 * Input: grid = [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
 * Output: 3
 * Explanation: The grid on the left shows a valid cornered path.
 * It has a product of 15 * 20 * 6 * 1 * 10 = 18000 which has 3 trailing zeros.
 * It can be shown that this is the maximum trailing zeros in the product of a cornered path.
 *
 * The grid in the middle is not a cornered path as it has more than one turn.
 * The grid on the right is not a cornered path as it requires a return to a previously visited cell.
 * 
 * Example 2
 * Input: grid = [[4,3,2],[7,6,1],[8,8,8]]
 * Output: 0
 * Explanation: The grid is shown in the figure above.
 * There are no cornered paths in the grid that result in a product with a trailing zero.
 *
 * 3. Constraints
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 1 <= grid[i][j] <= 1000
 */
public class MaximumTrailingZerosInACorneredPath {

  /**
   * 1. Approach 
   * Prefix Sum.
   * 
   * 2. Complexity
   * - Time O(N*M)
   * - Space O(N*M)
   * 
   * 3. Mistakes & Improvement
   * - It should not really calculate the product as the complexity for the product of a row could be (10^3)^10^5, 
   *   which is huge. Instead the trailingZeros = Math.max(cnt2, cnt5) + cnt10;
   * - A previous mistakes is to calculate all trailing zeros for left, right, up, down individually and get the
   *   maximum. However, it ignores the case where there is a 5 is left path and a 2 in down path (then there will
   *   be one more trailing zeros).
   *   
   * @param grid
   * @return
   */
  public int maxTrailingZeros(int[][] grid) {
    final int n = grid.length;
    final int m = grid[0].length;
    final int[][][] horizontalMemo = new int[n + 1][m + 1][3];
    final int[][][] verticalMemo = new int[n + 1][m + 1][3];

    buildMemo(grid, horizontalMemo, verticalMemo, n, m);

    int maximumTrailingZeros = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int[] leftZeroFactors = getZeroFactors(horizontalMemo[i + 1][j], horizontalMemo[0][0]);
        int[] rightZeroFactors = getZeroFactors(horizontalMemo[i + 1][m], horizontalMemo[i + 1][j + 1]);
        int[] upZeroFactors = getZeroFactors(verticalMemo[i + 1][j + 1], verticalMemo[0][j + 1]);
        int[] downZeroFactors = getZeroFactors(verticalMemo[n][j + 1], verticalMemo[i][j + 1]);

        maximumTrailingZeros = Math.max(maximumTrailingZeros, leftZeroFactors[0] + upZeroFactors[0] + Math.min(leftZeroFactors[1] + upZeroFactors[1], leftZeroFactors[2] + upZeroFactors[2]));
        maximumTrailingZeros = Math.max(maximumTrailingZeros, leftZeroFactors[0] + downZeroFactors[0] + Math.min(leftZeroFactors[1] + downZeroFactors[1], leftZeroFactors[2] + downZeroFactors[2]));
        maximumTrailingZeros = Math.max(maximumTrailingZeros, rightZeroFactors[0] + upZeroFactors[0] + Math.min(rightZeroFactors[1] + upZeroFactors[1], rightZeroFactors[2] + upZeroFactors[2]));
        maximumTrailingZeros = Math.max(maximumTrailingZeros, rightZeroFactors[0] + downZeroFactors[0] + Math.min(rightZeroFactors[1] + downZeroFactors[1], rightZeroFactors[2] + downZeroFactors[2]));
      }
    }

    return maximumTrailingZeros;
  }

  private void buildMemo(int[][] grid, int[][][] horizontalMemo, int[][][] verticalMemo, int n, int m) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int value = grid[i][j];

        horizontalMemo[i + 1][j + 1][0] = horizontalMemo[i + 1][j][0];
        horizontalMemo[i + 1][j + 1][1] = horizontalMemo[i + 1][j][1];
        horizontalMemo[i + 1][j + 1][2] = horizontalMemo[i + 1][j][2];
        verticalMemo[i + 1][j + 1][0] = verticalMemo[i][j + 1][0];
        verticalMemo[i + 1][j + 1][1] = verticalMemo[i][j + 1][1];
        verticalMemo[i + 1][j + 1][2] = verticalMemo[i][j + 1][2];

        while (value % 10 == 0) {
          horizontalMemo[i + 1][j + 1][0]++;
          verticalMemo[i + 1][j + 1][0]++;
          value = value / 10;
        }

        while (value % 5 == 0) {
          horizontalMemo[i + 1][j + 1][1]++;
          verticalMemo[i + 1][j + 1][1]++;
          value = value / 5;
        }

        while (value % 2 == 0) {
          horizontalMemo[i + 1][j + 1][2]++;
          verticalMemo[i + 1][j + 1][2]++;
          value = value / 2;
        }
      }
    }
  }

  private int[] getZeroFactors(int[] memo1, int[] memo2) {
    return new int[]{(memo1[0] - memo2[0]), (memo1[1] - memo2[1]), (memo1[2] - memo2[2])};
  }
}
