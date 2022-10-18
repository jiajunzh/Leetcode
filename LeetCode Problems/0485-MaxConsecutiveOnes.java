package problem;

/**
 * 1. Problem
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 * 
 * Example 2
 * Input: nums = [1,0,1,1,0,1]
 * Output: 2
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */
public class MaxConsecutiveOnes {

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
    int start = 0, maxLength = 0;
    while (start < nums.length) {
      int end = start;
      while (end < nums.length && nums[end] == 1) {
        end++;
      }
      maxLength = Math.max(end - start, maxLength);
      end++;
      start = end;
    }
    return maxLength;
  }
}
