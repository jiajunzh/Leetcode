package problem;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 1. Problem 
 * Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [3,2,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2.
 * The third distinct maximum is 1.
 * 
 * Example 2
 * Input: nums = [1,2]
 * Output: 2
 * Explanation:
 * The first distinct maximum is 2.
 * The second distinct maximum is 1.
 * The third distinct maximum does not exist, so the maximum (2) is returned instead.
 * 
 * Example 3
 * Input: nums = [2,2,3,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2 (both 2's are counted together since they have the same value).
 * The third distinct maximum is 1.
 *
 * 3. Constraints
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 * 4. Follow up
 * Can you find an O(n) solution?
 */
public class ThirdMaximumNumber {

  /**
   * 1. Approach 
   * PriorityQueue
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   *  
   * 3. Alternative 
   * - Ordered set such TreeSet 
   * 
   * @param nums
   * @return
   */
  public int thirdMax(int[] nums) {
    final PriorityQueue<Integer> pq = new PriorityQueue<>();
    final Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      if (set.contains(num)) continue;
      set.add(num);
      pq.offer(num);
      if (pq.size() > 3) pq.poll();
    }

    if (pq.size() < 3) {
      while (pq.size() > 1) pq.poll();
    }

    return pq.peek();
  }

  /**
   * 1. Approach 
   * Three Pointer => Hard to extend for fourth, fifth and etc.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param nums
   * @return
   */
  public int thirdMax2(int[] nums) {
    long firstMax = Long.MIN_VALUE;
    long secondMax = Long.MIN_VALUE;
    long thirdMax = Long.MIN_VALUE;

    for (int num : nums) {
      if (num == firstMax || num == secondMax || num == thirdMax) continue;
      if (num > firstMax) {
        thirdMax = secondMax;
        secondMax = firstMax;
        firstMax = num;
      } else if (num > secondMax) {
        thirdMax = secondMax;
        secondMax = num;
      } else if (num > thirdMax) {
        thirdMax = num;
      }
    }
    if (thirdMax == Long.MIN_VALUE) return (int) firstMax;
    return (int) thirdMax;
  }
}
