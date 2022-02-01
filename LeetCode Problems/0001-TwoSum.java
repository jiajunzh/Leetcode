import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to 
 * target. You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * 2. Example
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * 3. Constraints:
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 */
public class TwoSum {
  /**
   * 1. Approach
   * HashMap
   * 
   * 2. Complexity 
   * Time O(N)
   * Space O(N)
   */
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      if (map.containsKey(nums[i])) {
        return new int[]{map.get(nums[i]), i};
      }
      map.put(target - nums[i], i);
    }
    return new int[0];
  }

  public static void main(String[] args) {
    TwoSum twoSum = new TwoSum();
    int[] result = twoSum.twoSum(new int[]{2, 7, 11, 15}, 9);
    System.out.println("[" + result[0] + ", " + result[1] + "]");
  }
}
