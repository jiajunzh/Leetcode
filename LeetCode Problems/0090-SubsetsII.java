package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. Problem 
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 
 * Example 2
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * 3. Constraints
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class SubsetsII {

  /**
   * 1. Approach 
   * Backtracking + Array Sorting. 
   * - For each recursion:
   *  1. Iterate from start to the end
   *  2. Select the number 
   *  3. Backtrack(start + 1)
   *  4. Unselect
   *  5. The next iterated number should not be equal to the current one
   *  
   * 2. Complexity 
   * - Time O(N * 2^N)
   * - Space O(N)
   * 
   * @param nums
   * @return
   */
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(nums, new ArrayList<>(), result, 0);
    return result;
  }

  private void backtrack(int[] nums, List<Integer> path, List<List<Integer>> result, int start) {
    result.add(new ArrayList<>(path));

    if (nums.length == start) {
      return;
    }

    for (int i = start; i < nums.length; i++) {
      if (i > start && nums[i] == nums[i - 1]) {
        continue;
      }
      path.add(nums[i]);
      backtrack(nums, path, result, i + 1);
      path.remove(path.size() - 1);
    }
  }
}
