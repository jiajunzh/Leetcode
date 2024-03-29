package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the
 * range [1, n] that do not appear in nums.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 * 
 * Example 2
 * Input: nums = [1,1]
 * Output: [2]
 *
 * 3. Constraints
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 *
 * Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count
 * as extra space.
 */
public class FindAllNumbersDisappearedInAnArray {

  /**
   * 1. Approach 
   * In Place Mark Negative.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param nums
   * @return
   */
  public List<Integer> findDisappearedNumbers(int[] nums) {
    final List<Integer> result = new ArrayList<>();

    for (int i = 0; i < nums.length; i++) {
      int index = Math.abs(nums[i]);
      nums[index - 1] = - Math.abs(nums[index - 1]);
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > 0) {
        result.add(i + 1);
      }
    }

    return result;
  }
}
