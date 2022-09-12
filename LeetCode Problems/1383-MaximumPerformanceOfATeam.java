package problem;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1. Problem 
 * You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n 
 * engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer 
 * respectively.
 *
 * Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
 *
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their
 * engineers.
 *
 * Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.
 *
 * 2. Examples
 * Example 1
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation: 
 * We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 
 * 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
 * 
 * Example 2
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 * 
 * Example 3
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 *
 * 3. Constraints
 * 1 <= k <= n <= 105
 * speed.length == n
 * efficiency.length == n
 * 1 <= speed[i] <= 105
 * 1 <= efficiency[i] <= 108
 */
public class MaximumPerformanceOfATeam {

  private static final int MOD = 1_000_000_007;

  /**
   * 1. Approach 
   * Greedy + Sort + PriorityQueue. The problem suggests the performance is related to two things:
   * (1) sum of speed
   * (2) minimum efficiency 
   * With it, we could handle one order at a time. Sort the array by engineers' efficiency from largest to smallest and
   * then iterate through each engineer. As the order of efficiency is descending, we know the minimum efficiency when
   * picking up engineer i is the efficiency of engineer i. If the number of selected engineers exceeds k, then we just 
   * remove the one with minimum speed.
   * 
   * 2. Complexity 
   * - Time O(N(logN + logK))
   * - Space O(N + K)
   * 
   * @param n
   * @param speed
   * @param efficiency
   * @param k
   * @return
   */
  public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
    final Engineer[] engineers = new Engineer[n];
    for (int i = 0; i < n; i++) {
      engineers[i] = new Engineer(speed[i], efficiency[i]);
    }
    Arrays.sort(engineers, (a, b) -> (b.efficiency - a.efficiency));
    final PriorityQueue<Engineer> pq = new PriorityQueue<>((a, b) -> (a.speed - b.speed));
    long maxPerformance = 0;
    long sum = 0;
    for (final Engineer engineer: engineers) {
      if (pq.size() == k) {
        sum -= pq.poll().speed;
      }
      pq.offer(engineer);
      sum += engineer.speed;
      maxPerformance = Math.max(maxPerformance, sum * engineer.efficiency);
    }
    maxPerformance = maxPerformance % MOD;
    return (int) maxPerformance;
  }

  private static class Engineer {
    private final int speed;
    private final int efficiency;

    private Engineer(final int speed, final int efficiency) {
      this.speed = speed;
      this.efficiency = efficiency;
    }
  }
}
