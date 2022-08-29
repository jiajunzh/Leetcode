package problem;

/**
 * 1. Problem 
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.
 *
 * To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.
 *
 * However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
 *
 * Return the maximum number of points you can achieve.
 *
 * abs(x) is defined as:
 *
 * x for x >= 0.
 * -x for x < 0.
 *
 * 2. Examples 
 * Example 1
 * Input: points = [[1,2,3],[1,5,1],[3,1,1]]
 * Output: 9
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
 * You add 3 + 5 + 3 = 11 to your score.
 * However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
 * Your final score is 11 - 2 = 9.
 * 
 * Example 2
 * Input: points = [[1,5],[2,3],[4,2]]
 * Output: 11
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
 * You add 5 + 3 + 4 = 12 to your score.
 * However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
 * Your final score is 12 - 1 = 11.
 *
 * 3. Constraints
 * m == points.length
 * n == points[r].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * 0 <= points[r][c] <= 105
 */
public class MaximumNumberOfPointsWithCost {

  /**
   * 1. Approach 
   * Dynamic Programming. 
   * 
   * A very naive version of DP that one could come up with is three-for-loop implementation as below
   * for each row i:
   *   for each element j in current row:
   *      for each element k in previous row:
   *         dp[i][j] = Math.max(dp[i - 1][k] - abs(j - k) + points[i][j])
   * 
   * This approach will cause TLE as it takes O(RC^2) ~ 10^10. However, one easy trick to reduce it down to O(RC) is by
   * examining the abs(). We could split abs() into two case:
   * - if k = [0,j] => dp[i][j] = Math.max(dp[i - 1][k] + k) + points[i][j] - j
   * - if k = [j + 1, c - 1] => dp[i][j] = Math.max(dp[i - 1][k] - k) + points[i][j] + j
   * This means we could calculate Math.max(dp[i - 1][k] + k) or Math.max(dp[i - 1][k] - k) while calculating dp[i][j] 
   * in the same for loop.
   * 
   * 2. Complexity 
   * - Time O(RC)
   * - Space O(C)
   * 
   * @param points
   * @return
   */
  public long maxPoints(int[][] points) {
    final int c = points[0].length;
    long[] prev = new long[c];

    for (int[] point : points) {
      long[] curr = new long[c];
      long max = Long.MIN_VALUE;
      for (int j = 0; j < c; j++) {
        max = Math.max(max, prev[j] + j);
        curr[j] = Math.max(curr[j], max + point[j] - j);
      }
      max = Long.MIN_VALUE;
      for (int j = c - 1; j >= 0; j--) {
        max = Math.max(max, prev[j] - j);
        curr[j] = Math.max(curr[j], max + point[j] + j);
      }
      prev = curr;
    }
    long maxPoints = Long.MIN_VALUE;
    for (int i = 0; i < c; i++) {
      maxPoints = Math.max(maxPoints, prev[i]);
    }
    return maxPoints;
  }
}
