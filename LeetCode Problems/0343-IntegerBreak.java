package problem;

/**
 * 1. Problem
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 *
 * Return the maximum product you can get.
 *
 * 2. Examples
 * Example 1
 * Input: n = 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 *
 * Example 2
 * Input: n = 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 *
 * 3. Constraints
 * 2 <= n <= 58
 */
public class IntegerBreak {

    private int maxProduct;

    /**
     * 1. Approach
     * Backtracking
     *
     * - Input: n (Integer), currProduct(Integer), k (Integer), start(Integer)
     * - Output: there is no output
     * - Global variable: maxProduct
     * - Remove duplicate => start parameter
     *
     * 2. Complexity
     * - Time O(N^N)
     * - Space O(N)
     */
    public int integerBreak(int n) {
        backtrack(n, 1, 0, 1);
        return this.maxProduct;
    }

    private void backtrack(int n, int currProduct, int k, int start) {
        if (n < 1) {
            if (k > 1) {
                this.maxProduct = Math.max(this.maxProduct, currProduct);
            }
            return;
        }
        // 1 + 2 + 1 => 1 2 1 | 2 1 1 |
        for (int i = start; i <= n; i++) {
            backtrack(n - i, currProduct * i, k + 1, i);
        }
    }

    /**
     * 1. Approach
     * Math
     *
     *  //    n       broken into       product         answer              ///
     *  //    7       3 + 4             3 * 4            12                 ///
     *  //    8       3 + 3 + 2         3 * 3 * 2        18                 ///
     *  //    9       3 + 3 + 3         3 * 3 * 3        27                 ///
     *  //    10      3 + 3 + 4         3 * 3 * 4        36                 ///
     *  //    11      3 + 3 + 3 + 2     3 * 3 * 3 * 2    54                 ///
     *
     * 2. Complexity
     * - Time O(N/3)
     * - Space O(1)
     *
     * @param n
     * @return
     */
    public int integerBreak2(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;

        int maxProduct = 1;
        while (n > 4) {
            n -= 3;
            maxProduct *= 3;
        }
        return maxProduct * n;
    }

    /**
     * 1. Approach
     * DP
     *
     * 2. Complexity
     * - Time O(N^2)
     * - Space O(N)
     * 
     * @param n
     * @return
     */
    public int integerBreak3(int n) {
        final int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(j * (i - j), dp[i]);
                dp[i] = Math.max(dp[j] * (i - j), dp[i]);
            }
        }
        return dp[n];
    }
}
