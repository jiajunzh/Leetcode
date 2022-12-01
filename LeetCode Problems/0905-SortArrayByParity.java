package problem;

/**
 * 1. Problem 
 * Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
 *
 * Return any array that satisfies this condition.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [3,1,2,4]
 * Output: [2,4,3,1]
 * Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 * 
 * Example 2
 * Input: nums = [0]
 * Output: [0]
 *
 * 3. Constraints
 * 1 <= nums.length <= 5000
 * 0 <= nums[i] <= 5000
 */
public class SortArrayByParity {

  /**
   * 1. Approach 
   * Swap
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param nums
   * @return
   */
  public int[] sortArrayByParity(int[] nums) {
    int start = 0, end = nums.length - 1;
    while (start < end) {
      while (start < end && nums[start] % 2 == 0) start++;
      while (start < end && nums[end] % 2 == 1) end--;
      swap(nums, start, end);
      start++;
      end--;
    }
    return nums;
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
