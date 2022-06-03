package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish 
 * all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each 
 * day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 *
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 *
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 * 2. Examples 
 * Example 1
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7 
 * 
 * Example 2
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 * 
 * Example 3
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 * 3. Constraints
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 */
public class MinimumDifficultyOfAJobSchedule {

  /**
   * 1. Approach 
   * Dynamic Programming. 
   * 
   * 1) State Variable 
   * Define dp[i][k] as the minimum sum of job difficulty when doing i jobs (1 based) within k days.
   * 
   * 2) Recurrence Relation
   * [JB1 JB2 ..... JBj | JBj+1 ..... JBi]
   *    k - 1 days    <-->    kth day
   * dp[i][k] = Min(dp[j][k - 1] + Max(JB from j+1 to i))
   *
   * 3) Base Case 
   * dp[0][0] = 0 => This will make sure when calculating the first day, the maximum from JB1 to JBi all considered.
   * 
   * 2. Complexity 
   * - Time O(D * N ^ 2)
   * - Space O(D * N)
   * 
   * 3. Improvement
   * - See approach 2 below. Space optimization.
   * 
   * @param jobDifficulty
   * @param d
   * @return
   */
  public int minDifficulty1(int[] jobDifficulty, int d) {
    final int n = jobDifficulty.length;
    if (d > n) return -1;
    final int[][] dp = new int[n + 1][d + 1];
    Arrays.fill(dp[0], Integer.MAX_VALUE / 2);
    dp[0][0] = 0;

    for (int i = 1; i <= n; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
      for (int k = 1; k <= d; k++) {
        int maxDifficulty = 0;
        for (int j = i - 1; j >= (k - 1); j--) {
          maxDifficulty = Math.max(maxDifficulty, jobDifficulty[j]);
          dp[i][k] = Math.min(dp[i][k], dp[j][k - 1] + maxDifficulty);
        }
      }
    }

    return dp[n][d];
  }

  public int minDifficulty(int[] jobDifficulty, int d) {
    final int n = jobDifficulty.length;
    if (d > n) return -1;
    final int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE / 2);
    dp[0] = 0;

    for (int k = 1; k <= d; k++) {
      for (int i = n; i >= k; i--) {
        dp[i] = Integer.MAX_VALUE;
        int maxDifficulty = 0;
        for (int j = i - 1; j >= (k - 1); j--) {
          maxDifficulty = Math.max(maxDifficulty, jobDifficulty[j]);
          dp[i] = Math.min(dp[i], dp[j] + maxDifficulty);
        }
      }
    }

    return dp[n];
  }
}
