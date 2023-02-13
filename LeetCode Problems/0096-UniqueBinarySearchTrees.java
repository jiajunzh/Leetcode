package problem;

/**
 * 1. Problem
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes
 * of unique values from 1 to n.
 *
 * 2. Examples
 * Example 1
 * Input: n = 3
 * Output: 5
 *
 * Example 2
 * Input: n = 1
 * Output: 1
 *
 * 3. Constraints
 * 1 <= n <= 19
 */
public class UniqueBinarySearchTrees {

    /**
     * 1. Approach
     * Recursion
     * 
     * numTrees(int n) -> int
     * Suppose we have number 1 to n and we break at k within [1, n] such that k is the root, [1,k) is at left subtree and
     * (k, n] is right subtree, k belongs to [1, n]
     *
     * 2. Complexity
     * - Time O(N!)
     * - Space O(N)
     */
    public int numTrees(int n) {
        if (n <= 1) return 1;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += numTrees(i - 1) * numTrees(n - i);
        }
        return count;
    }

    private Integer[] memo;
    /**
     * 1. Approach
     * Memoization
     *
     * 2. Complexity
     * - Time O(N^2)
     * - Space O(N)
     *
     * @param n
     * @return
     */
    public int numTrees2(int n) {
        this.memo = new Integer[n + 1];
        return numTreesHelper(n);
    }

    public int numTreesHelper(int n) {
        if (n <= 1) return 1;
        if (this.memo[n] != null) return this.memo[n];
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += numTreesHelper(i - 1) * numTreesHelper(n - i);
        }
        this.memo[n] = count;
        return count;
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
    public int numTrees3(int n) {
        final int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= i; k++) {
                dp[i] += dp[k - 1] * dp[i - k];
            }
        }
        return dp[n];
    }
}
