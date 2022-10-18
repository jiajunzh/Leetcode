package problem;

/**
 * 1. Problem 
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous 
 * subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no 
 * such subarray, return 0 instead.
 *
 * 2. Examples 
 * Example 1
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * 
 * Example 2
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * 
 * Example 3
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 * 3. Constraints
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 * 4. Follow up
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */
public class MinimumSizeSubarraySum {

  /**
   * 1. Approach 
   * Sliding Window + Two Pointers
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param target
   * @param nums
   * @return
   */
  public int minSubArrayLen(int target, int[] nums) {
    int windowSum = 0, minLength = Integer.MAX_VALUE;
    int start = 0, end = 0;
    while (end < nums.length) {
      while (end < nums.length && windowSum < target) {
        windowSum += nums[end];
        end++;
      }
      while (windowSum >= target) {
        minLength = Math.min(minLength, end - start);
        windowSum -= nums[start];
        start++;
      }
    }
    return minLength == Integer.MAX_VALUE ? 0 : minLength;
  }
}
