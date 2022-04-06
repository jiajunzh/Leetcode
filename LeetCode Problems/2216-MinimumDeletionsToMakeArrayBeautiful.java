package problem;

/**
 * 1. Problem
 * You are given a 0-indexed integer array nums. The array nums is beautiful if:
 *
 * nums.length is even.
 * nums[i] != nums[i + 1] for all i % 2 == 0.
 * Note that an empty array is considered beautiful.
 *
 * You can delete any number of elements from nums. When you delete an element, all the elements to the right of the 
 * deleted element will be shifted one unit to the left to fill the gap created and all the elements to the left of the deleted element will remain unchanged.
 *
 * Return the minimum number of elements to delete from nums to make it beautiful.
 *
 * 2. Examples
 * Example 1:
 * Input: nums = [1,1,2,3,5]
 * Output: 1
 * Explanation: You can delete either nums[0] or nums[1] to make nums = [1,2,3,5] which is beautiful. It can be proven you need at least 1 deletion to make nums beautiful.
 * 
 * Example 2:
 * Input: nums = [1,1,2,2,3,3]
 * Output: 2
 * Explanation: You can delete nums[0] and nums[5] to make nums = [1,2,2,3] which is beautiful. It can be proven you need at least 2 deletions to make nums beautiful.
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 */
public class MinimumDeletionsToMakeArrayBeautiful {

  /**
   * 1. Approach
   * StraightForward. This approach is implemented just based on the logic and it iterates each element in the array.
   * 
   * 2. Complexity
   * Time O(N) - 8ms
   * Space O(1)
   * 
   * 3. Improvement
   * - There are elements not needed to be visited and could be skipped.
   *   - Example 1: if Ai != Ai+1, Ai+1 could be skipped
   *   - Example 2: if Ai == Ai+1, we are deleting Ai, then Ai+1 needs to be visited again.
   * @param nums
   * @return
   */
  public int minDeletion1(int[] nums) {
    int min = 0;

    for (int i = 0; i < nums.length - 1; i++) {

      if (nums[i] == nums[i + 1] && (i - min) % 2 == 0) {
        min++;
      }
    }

    return min + (nums.length - min) % 2;
  }

  /**
   * 1. Approach
   * This approach is optimized based on approach 1. It skips the elements which are not needed to be visited. 
   * For example,
   * 
   * 5   6   7   8   8   8   9   10
   * V   S   V   S   V   V   S   V
   * 
   * V - Visited 
   * S - Skipped
   * 
   * In this example, element at index 4 is visited and it is equals to index 5, so it will be deleted. index 5 
   * is moved to the left so it needs to be re-evaluated again.
   * 
   * 2. Complexity
   * Time O(N) - 4ms
   * Space O(1)
   * 
   * @param nums
   * @return
   */
  public int minDeletion2(int[] nums) {
    int min = 0;

    for (int i = 0; i < nums.length - 1; i += 2) {
      if (nums[i] == nums[i + 1]) {
        min++;
        i--;
      }
    }

    return min + (nums.length - min) % 2;
  }
}
