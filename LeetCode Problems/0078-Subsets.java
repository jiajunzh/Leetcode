package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 
 * Example 2
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 * 3. Constraints
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 */
public class Subsets {

  /**
   * 1. Approach 
   * Backtrack. 
   * - For each recursion:
   *  1) Record the curr subset
   *  2) Select the number 
   *  3) Recursively call the next recursion 
   *  4) Unselect the number 
   * - End Condition:
   *  nums.length == start 
   * - Parameters:
   *  result List<List<Integer>> / currIndex Integer / nums int[] / List<Integer> currList
   *  
   * 2. Complexity 
   * - Time O(N * 2^N)
   * - Space O(N) Recursion
   * 
   * 3. Alternatives 
   * - Bitmask could also be used in this problem to mark whether to chose number at position i or not 
   *   For example [1, 2, 3] => 111 means choose all three numbers.
   *   
   * @param nums
   * @return
   */
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(nums, result, new ArrayList<>(), 0);
    return result;
  }

  private void backtrack(int[] nums, List<List<Integer>> result, List<Integer> path, int start) {
    result.add(new ArrayList<>(path));

    if (nums.length == start) {
      return;
    }

    for (int i = start; i < nums.length; i++) {
      int num = nums[i];
      path.add(num);
      backtrack(nums, result, path, i + 1);
      path.remove(path.size() - 1);
    }
  }
}
