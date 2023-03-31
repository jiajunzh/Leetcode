package problem;

import java.util.Arrays;

public class ReducingDishes {

    /**
     * 1. Approach
     * Greedy
     *
     * The problem is to select N dishes from the input such that Sum(time[i] * s[i]) is maximized
     *
     * Observation 1 - cooking dish with higher satisfaction at later time will generate higher like-time coefficient.
     * Assume we already have an selected dish list [s1, s2, s3, s4...] with ascending order, it can be proved that
     * cooking s4 later can generate higher objective function value than cooking s4 earlier
     *
     * Observation 2 - Dish with negative satisfaction might still be useful as it can elevate the cooking time for dish
     * with higher satisfaction
     *
     * Observation 3 - For dish with satisfaction s, cooking it at time (t + 1) will generate additional s into
     * coefficient than cooking it at time (t) => s(t + 1) - st = s.
     *
     * Based on observations above, we can get a greedy algorithm as below.
     *  - Maintain a virtual queue for selected dishes and iterate the satisfaction array:
     *  - Select the maximum satisfaction s currently remaining
     *  - Putting s into the selected dishes queue means generating additional coefficient s * 1 + Sum(dishes in queue)
     *  - Dealbreaker is whether the additional coefficient [s * 1 + Sum(dishes in queue)] is greater than 0
     *
     * 2. Complexity
     * - Time O(NlogN)
     * - Space O(logN) to O(N)
     *
     * @param satisfaction
     * @return
     */
    public int maxSatisfaction1(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int max = 0, sum = 0;
        for (int i = satisfaction.length - 1; i >= 0; i--) {
            if (sum + satisfaction[i] > 0) {
                max += sum + satisfaction[i];
            }
            sum += satisfaction[i];
        }
        return max;
    }


    /**
     * 1. Approach
     * DP (Memoization)
     *
     * 2. Complexity
     * - Time O(N^2)
     * - Space O(N^2)
     */
    private Integer[][] memo;

    public int maxSatisfaction2(int[] satisfaction) {
        Arrays.sort(satisfaction);
        this.memo = new Integer[satisfaction.length][satisfaction.length + 1];
        return dp(satisfaction, 0, 1);
    }

    private int dp(int[] satisfaction, int index, int time) {
        if (index == satisfaction.length) {
            return 0;
        }
        if (this.memo[index][time] == null) {
            int dropped = dp(satisfaction, index + 1, time);
            int cooked = dp(satisfaction, index + 1, time + 1) + time * satisfaction[index];
            this.memo[index][time] = Math.max(dropped, cooked);
        }

        return this.memo[index][time];
    }

    /**
     * 1. Approach
     * DP (Tabulation)
     *
     * 2. Complexity
     * - Time O(N^2)
     * - Space O(N^2)
     */
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        final int n = satisfaction.length;
        final int[][] dp = new int[n + 1][n + 2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= i + 1; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1] + j * satisfaction[i]);
            }
        }
        return dp[0][1];
    }
}
