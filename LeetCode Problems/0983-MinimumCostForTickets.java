package problem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1. Problem 
 * You have planned some train traveling one year in advance. The days of the year in which you will travel are given 
 * as an integer array days. Each day is an integer from 1 to 365.
 *
 * Train tickets are sold in three different ways:
 *
 * a 1-day pass is sold for costs[0] dollars,
 * a 7-day pass is sold for costs[1] dollars, and
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 *
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 * 
 * 2. Examples 
 * Example 1
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total, you spent $11 and covered all the days of your travel.
 * 
 * Example 2
 * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * Output: 17
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 * In total, you spent $17 and covered all the days of your travel.
 *
 * 3. Constraints
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days is in strictly increasing order.
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 */
public class MinimumCostForTickets {

  /**
   * 1. Approach 
   * Dynamic Programming. 
   * 
   * Define dp[i] as the minimum cost of tickets when travelling from day i to the end.
   * dp[i] = Min(dp[i + 1] + costs[0], dp[i + 2] + costs[1], dp[i + 3] + costs[2])
   * 
   * One thing to note here is if day i does not exist in days array, then day[i] = day[i + 1].
   * For example, we have days = [11, 20]
   * 
   * Then dp[19] should be equal to dp[20] as the problem does not ask us to cover for it.
   * 
   * 2. Complexity 
   * - Time O(W) W = maximum day - minimum day
   * - Space O(W) W = maximum day - minimum day
   * 
   * 3. Mistakes 
   * - Base case is dp[m] = Min(costs[0], costs[1], costs[2]) OR dp[m + 1] = 0. One wrong assumption is costs[0] will 
   *   always be smaller than costs[1], then we could have dp[m] as costs[0]. Edge case costs = [7,2,15]
   * 
   * @param days
   * @param costs
   * @return
   */
  public int mincostTickets(int[] days, int[] costs) {
    final Set<Integer> set = new HashSet<>();
    final int n = days.length;
    final int m = days[n - 1];
    final int start = days[0];
    for (int day : days) {
      set.add(day);
    }

    final int[] dp = new int[m + 2];

    for (int i = m; i >= start; i--) {
      if (!set.contains(i)) {
        dp[i] = dp[i + 1];
      } else {
        int oneDayPass = dp[i + 1] + costs[0];
        int sevenDayPass = (i + 7 > m ? 0 : dp[i + 7]) + costs[1];
        int thirtyDayPass = (i + 30 > m ? 0 : dp[i + 30]) + costs[2];
        dp[i] = Math.min(oneDayPass, Math.min(sevenDayPass, thirtyDayPass));
      }
    }

    return dp[start];
  }

  /**
   * 1. Approach 
   * Dynamic Programming on top of approach 1. 
   * 
   * One observation from approach 1 is that if the day does not appear in the array, then bypass it by setting it as 
   * dp[i + 1]. So instead of going thru each day from minimum day to maximum day, we could initialize an array dp[n] 
   * that will consider days appearing in the days array.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   */
  private final static int[] DURATIONS = new int[]{1, 7, 30};
  
  public int mincostTickets2(int[] days, int[] costs) {
    final int n = days.length;
    final int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[n] = 0;

    for (int i = n - 1; i >= 0; i--) {
      for (int k = 0; k <= 2; k++) {
        int j = i + 1;
        while (j < n && days[j] < DURATIONS[k] + days[i]) {
          j++;
        }
        dp[i] = Math.min(dp[i], dp[j] + costs[k]);
      }
    }

    return dp[0];
  }
}
