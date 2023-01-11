package problem;

/**
 * 1. Problem
 * Given an integer n, return the number of ways you can write n as the sum of consecutive positive integers.
 *
 * 2. Examples
 * Example 1
 * Input: n = 5
 * Output: 2
 * Explanation: 5 = 2 + 3
 *
 * Example 2
 * Input: n = 9
 * Output: 3
 * Explanation: 9 = 4 + 5 = 2 + 3 + 4
 *
 * Example 3
 * Input: n = 15
 * Output: 4
 * Explanation: 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *
 * 3. Constraints
 * 1 <= n <= 109
 */
public class ConsecutiveNumbersSum {

    /**
     * 1. Approach
     * Sliding Window
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1)
     *
     * @param n
     * @return
     */
    public int consecutiveNumbersSum(int n) {
        int count = 0, sum = 0, start = 0, end = 0;
        while (end <= n) {
            if (sum == n) {
                count++;
                end++;
                sum += end;
            } else if (sum < n) {
                end++;
                sum += end;
            } else {
                sum -= start;
                start++;
            }
        }
        return count;
    }

    /**
     * 1. Approach
     * Math => See https://leetcode.com/problems/consecutive-numbers-sum/solutions/653742/consecutive-numbers-sum/
     *
     * 2. Complexity
     * - Time O(Sqrt(N))
     * - Space O(1)
     *
     * @param n
     * @return
     */
    public int consecutiveNumbersSum2(int n) {
        int count = 0, kSum = 0;
        int upperBound = (int)(Math.sqrt(2 * n + 0.25) - 0.5);
        for (int k = 1; k <= upperBound; k++) {
            kSum += k;
            if ((n - kSum) % k == 0) count++;
        }
        return count;
    }
}
