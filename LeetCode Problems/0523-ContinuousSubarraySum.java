package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two 
 * whose elements sum up to a multiple of k, or false otherwise. An integer x is a multiple of k if there exists an 
 * integer n such that x = n * k. 0 is always a multiple of k.
 *
 * 2. Example
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 *
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 *
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 *
 * 3. Constraints
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 */
public class ContinuousSubarraySum {

  /**
   * 1. Approach - Prefix Sum + HashMap
   * 
   * Sum[i] = a0 + a1 + ... + ai = K * Constanti + Ri;
   * Sum[j] = a0 + a1 + ... + aj = K * Constantj + Rj;
   * SubArraySum[i, j] = ai+1 + ai+2 + ... + aj = Sum[j] - Sum[i]
   *
   * SubArraySum[i, j] = K * (Constantj - Constanti) + (Rj - Ri);
   * Which means SubArraySum[i, j] is a multiple of K if Rj = Ri.
   * 
   * 2. Complexity
   * Time O(N).
   * Space O(N).
   * 
   * 3. Mistake
   * - Use the smallest index for Map. Don't overwrite it.
   * - The difference between indexes needs to be greater than 1.
   * - Remember 0 is a multiple of K. Put <0, -1> into the Map.
   * 
   * @param nums
   * @param k
   * @return
   */
  public boolean checkSubarraySum(int[] nums, int k) {
    final Map<Integer, Integer> remainderIndex = new HashMap<>();

    int prefixSum = 0;
    remainderIndex.put(0, -1);
    for (int i = 0; i < nums.length; i++) {
      prefixSum = (prefixSum + nums[i]) % k;

      if (remainderIndex.containsKey(prefixSum)) {
        final int smallestIndex = remainderIndex.get(prefixSum);
        
        if (i - smallestIndex > 1) {
          return true;
        }
      } else {
        remainderIndex.put(prefixSum, i);
      }
    }

    return false;
  }
}
