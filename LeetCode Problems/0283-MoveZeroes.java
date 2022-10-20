package problem;

/**
 * 1. Approach
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * 
 * Example 2
 * Input: nums = [0]
 * Output: [0]
 * 
 * 3. Constraints
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * Follow up: Could you minimize the total number of operations done?
 */
public class MoveZeroes {

  /**
   * 1. Approach 
   * Two Pointers + Swap
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param nums
   */
  public void moveZeroes(int[] nums) {
    int k = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        int tmp = nums[k];
        nums[k] = nums[i];
        nums[i] = tmp;
        k++;
      }
    }
  }
}
