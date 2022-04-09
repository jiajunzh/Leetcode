package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem
 * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
 *
 * 2. Examples
 * Example 1:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * 
 * Example 2:
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 * 3. Constraints:
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class SubarraySumEqualsK {

  /**
   * 1. Approach
   * Cumulative sum + Iterate all subarray. 
   * 
   * 2. Complexity
   * - Time O(N*N)
   * - Space O(1)
   *
   * @param nums
   * @param k
   * @return
   */
  public int subarraySum1(int[] nums, int k) {
    int cnt = 0;

    for (int i = 0; i < nums.length; i++) {
      int sum = 0;
      for (int j = i; j < nums.length; j++) {
        sum += nums[j];
        if (sum == k) {
          cnt++;
        }
      }
    }

    return cnt;
  }

  /**
   * 1. Approach
   * HashMap. One key thing to note in this problem is subarraySum[i, j] = preSum[j] - preSum[i]. The subarray starts 
   * from nums[i + 1] to nums[j]. That is to say, once we have the preSum at index j, we will know if there is any 
   * subarray with sum == k exist up till index j by checking the condition where 
   * 
   * preSum[i] = preSum[j] - subarraySum[i, j] (i.e. k) = preSum[j] - k
   * 
   * The number of subarray with sum == k up till index j could be translated into the number of preSum[i] occurred 
   * up till index j.
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(N)
   * 
   * 3. Mistakes
   * - Initialize 0 with 1 occurrence. One edge case this will cover is nums = [1], k = 1
   * 
   * @param nums
   * @param k
   * @return
   */
  public int subarraySum2(int[] nums, int k) {
    final Map<Integer, Integer> sumOccurrences = new HashMap<>();
    int cnt = 0;
    int sum = 0;
    sumOccurrences.put(0, 1);

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      int anotherSum = sum - k;
      if (sumOccurrences.containsKey(anotherSum)) {
        cnt += sumOccurrences.get(anotherSum);
      }

      sumOccurrences.put(sum, sumOccurrences.getOrDefault(sum, 0) + 1);
    }

    return cnt;
  }
}
