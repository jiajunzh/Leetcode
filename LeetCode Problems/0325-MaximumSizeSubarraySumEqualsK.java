package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. If there is
 * not one, return 0 instead.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,-1,5,-2,3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 * 
 * Example 2
 * Input: nums = [-2,-1,2,1], k = 1
 * Output: 2
 * Explanation: The subarray [-1, 2] sums to 1 and is the longest.
 *
 * 3. Constraints
 * 1 <= nums.length <= 2 * 105
 * -104 <= nums[i] <= 104
 * -109 <= k <= 109
 */
public class MaximumSizeSubarraySumEqualsK {

  /**
   * 1. Approach 
   * Prefix Sum + HashMap. PrefixSum[i] - PrefixSum[j] = Sum(Aj+1 to Ai).
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param nums
   * @param k
   * @return
   */
  public int maxSubArrayLen(int[] nums, int k) {
    final Map<Integer, Integer> map = new HashMap<>();
    map.put(0, -1);
    int len = 0, sum = 0;

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      len = Math.max(len, i - map.getOrDefault(sum - k, i));
      if (!map.containsKey(sum)) {
        map.put(sum, i);
      }
    }

    return len;
  }
}
