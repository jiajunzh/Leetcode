package problem;

import java.util.Arrays;

/**
 * 1. Problem
 * You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.
 *
 * You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break,
 * and any egg dropped at or below floor f will not break.
 *
 * Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can
 * no longer use it. However, if the egg does not break, you may reuse it in future moves.
 *
 * Return the minimum number of moves that you need to determine with certainty what the value of f is.
 *
 * 2. Examples
 * Example 1
 * Input: k = 1, n = 2
 * Output: 2
 * Explanation:
 * Drop the egg from floor 1. If it breaks, we know that f = 0.
 * Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
 * If it does not break, then we know f = 2.
 * Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
 *
 * Example 2
 * Input: k = 2, n = 6
 * Output: 3
 *
 * Example 3
 * Input: k = 3, n = 14
 * Output: 4
 *
 * 3. Constraints
 * 1 <= k <= 100
 * 1 <= n <= 104
 */
public class SuperEggDrop {

    /**
     * 1. Approach
     * DP + Binary Search (Optimization)
     *
     * dp[k,n] = 1 + Min(Max(dp[k - 1, i - 1], dp[k, n - i])) for any i in [1,n]
     *
     * https://www.youtube.com/watch?v=aPY6sps_Q44
     *
     * 2. Complexity
     * - Time O(KNlogN)
     * - Space O(KN)
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop(int k, int n) {
        final int[][] memo = new int[k + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        return dp(memo, k, n);
    }

    private int dp(int[][] memo, int k, int n) {
        if (n <= 1 || k == 1) return n;
        if (memo[k][n] != Integer.MAX_VALUE) {
            return memo[k][n];
        }

        int left = 1, right = n + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int broken = dp(memo, k - 1, mid - 1);
            int notBroken = dp(memo, k, n - mid);
            if (broken >= notBroken) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        memo[k][n] = Math.min(memo[k][n], 1 + Math.max(dp(memo, k - 1, right - 1), dp(memo, k, n - right)));
        return memo[k][n];
    }

    /**
     * 1. Approach
     * Optimized DP by tracking monotonically increasing x0.
     *
     * See https://leetcode.com/problems/super-egg-drop/solutions/158936/super-egg-drop/
     *
     * 2. Complexity
     * - Time O(KN)
     * - Space O(KN)
     *
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop2(int k, int n) {
        int[] dp = new int[n + 1];
        for (int j = 1; j <= n; j++) {
            dp[j] = j;
        }

        for (int i = 2; i <= k; i++) {
            int[] tmp = new int[n + 1];
            int x = 1;
            for (int j = 1; j <= n; j++) {
                while (x < j && Math.max(dp[x - 1], tmp[j - x]) >= Math.max(dp[x], tmp[j - x - 1])) {
                    x++;
                }
                tmp[j] = 1 + Math.max(dp[x - 1], tmp[j - x]);
            }
            dp = tmp;
        }
        return dp[n];
    }
}
