package problem;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. Problem 
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if 
 * every element is distinct.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [1,2,3,1]
 * Output: true
 * 
 * Example 2
 * Input: nums = [1,2,3,4]
 * Output: false
 * 
 * Example 3
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class ContainsDuplicate {

  /**
   * 1. Approach 
   * HashSet 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param nums
   * @return
   */
  public boolean containsDuplicate(int[] nums) {
    final Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      if (set.contains(num)) {
        return true;
      }
      set.add(num);
    }
    return false;
  }
}
