package problem;

/**
 * 1. Problem
 * You are given a m x n matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step,
 * you can only move right or down in the matrix.
 *
 * Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (m - 1, n - 1),
 * find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid
 * cells visited along the path.
 *
 * Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative, return -1.
 *
 * Notice that the modulo is performed after getting the maximum product.
 *
 * 2. Examples
 * Example 1
 * Input: grid = [[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]
 * Output: -1
 * Explanation: It is not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
 *
 * Example 2
 * Input: grid = [[1,-2,1],[1,-2,1],[3,-4,1]]
 * Output: 8
 * Explanation: Maximum non-negative product is shown (1 * 1 * -2 * -4 * 1 = 8).
 *
 * Example 3
 * Input: grid = [[1,3],[0,-4]]
 * Output: 0
 * Explanation: Maximum non-negative product is shown (1 * 0 * -4 = 0).
 *
 * 3. Constraints
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 15
 * -4 <= grid[i][j] <= 4
 */
public class MaximumNonNegativeProductInAMatrix {

    private static final int MOD = 1_000_000_007;

    /**
     * 1. Approach
     * DP
     *
     * 2. Complexity
     * - Time O(MN)
     * - Space O(MN)
     *
     * @param grid
     * @return
     */
    public int maxProductPath(int[][] grid) {
        final int n = grid.length, m = grid[0].length;
        final long[][][] dp = new long[n][m][2];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int curr = grid[i][j];
                if (i > 0 && j > 0) {
                    long a = curr * Math.max(dp[i][j - 1][0], dp[i - 1][j][0]);
                    long b = curr * Math.min(dp[i][j - 1][1], dp[i - 1][j][1]);
                    dp[i][j][0] = Math.max(a, b);
                    dp[i][j][1] = Math.min(a, b);
                } else {
                    if (i == 0 && j == 0) {
                        dp[i][j][0] = curr;
                    } else if (i == 0) {
                        dp[i][j][0] = dp[i][j - 1][0] * curr;
                    } else {
                        dp[i][j][0] = dp[i - 1][j][0] * curr;
                    }
                    dp[i][j][1] = dp[i][j][0];
                }
            }
        }
        return dp[n - 1][m - 1][0] < 0 ? -1 : (int) (dp[n - 1][m - 1][0] % MOD);
    }
}
