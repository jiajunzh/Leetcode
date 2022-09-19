package problem;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1. Problem 
 * You are given a 2D integer array intervals where intervals[i] = [lefti, righti] represents the inclusive 
 * interval [lefti, righti].
 *
 * You have to divide the intervals into one or more groups such that each interval is in exactly one group, and no 
 * two intervals that are in the same group intersect each other.
 *
 * Return the minimum number of groups you need to make.
 *
 * Two intervals intersect if there is at least one common number between them. For example, the intervals [1, 5] 
 * and [5, 8] intersect.
 *
 * 2. Examples
 * Example 1
 * Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
 * Output: 3
 * Explanation: We can divide the intervals into the following groups:
 * - Group 1: [1, 5], [6, 8].
 * - Group 2: [2, 3], [5, 10].
 * - Group 3: [1, 10].
 * It can be proven that it is not possible to divide the intervals into fewer than 3 groups.
 * 
 * Example 2
 * Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
 * Output: 1
 * Explanation: None of the intervals overlap, so we can put all of them in one group.
 *
 * 3. Constraints
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 106
 */
public class DivideIntervalsIntoMinimumNumberOfGroups {

  /**
   * 1. Approach 
   * Sort + PriorityQueue + Greedy
   * 
   * 2. Complexity 
   * - Time O(NlogN)
   * - Space O(N)
   * 
   * @param intervals
   * @return
   */
  public int minGroups(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
    final PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int[] interval : intervals) {
      pq.offer(interval[1]);
      if (interval[0] > pq.peek()) {
        pq.poll();
      }
    }
    return pq.size();
  }

  /**
   * 1. Approach 
   * Prefix Sum. Count[i] represents the number of overlapping intervals at time i (after performing prefix sum).
   * Thus for an interval [a, b], we could increment count[a] by one and decrease count[b+1] by one. It means that
   * at any time between [a, b], there is one interval occupies this time but starting from b + 1 time, this room 
   * was released by [a, b]
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(MaxTime)
   * 
   * @param intervals
   * @return
   */
  public int minGroups2(int[][] intervals) {
    int maxTime = 0;
    for (int[] interval : intervals) {
      maxTime = Math.max(interval[1], maxTime);
    }
    final int[] count = new int[maxTime + 2];
    for (int[] interval : intervals) {
      count[interval[0]]++;
      count[interval[1] + 1]--;
    }
    int minGroups = 0;
    for (int i = 1; i <= maxTime + 1; i++) {
      count[i] += count[i - 1];
      minGroups = Math.max(count[i], minGroups);
    }
    return minGroups;
  }
}
