package problem;

/**
 * 1. Problem 
 * Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,0,1,1,0]
 * Output: 4
 * Explanation: 
 * - If we flip the first zero, nums becomes [1,1,1,1,0] and we have 4 consecutive ones.
 * - If we flip the second zero, nums becomes [1,0,1,1,1] and we have 3 consecutive ones.
 * The max number of consecutive ones is 4.
 * 
 * Example 2
 * Input: nums = [1,0,1,1,0,1]
 * Output: 4
 * Explanation: 
 * - If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4 consecutive ones.
 * - If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4 consecutive ones.
 * The max number of consecutive ones is 4.
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 *
 * 4. Follow up
 * What if the input numbers come in one by one as an infinite stream? In other words, you can't store all
 * numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
 */
public class MaxConsecutiveOnesII {

  /**
   * 1. Approach 
   * Sliding Window
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param nums
   * @return
   */
  public int findMaxConsecutiveOnes(int[] nums) {
    int count = 0, left = 0, right = 0, zeroCount = 0;
    while (right < nums.length) {
      if (nums[right] == 0) zeroCount++;
      while (zeroCount == 2) {
        if (nums[left] == 0) zeroCount--;
        left++;
      }
      count = Math.max(count, right - left + 1);
      right++;
    }
    return count;
  }
}
