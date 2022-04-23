package problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1. Problem
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *  
 * Example 2
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 3. Constraints
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class PermutationsII {

  /**
   * 1. Approach (Version1)
   * Backtracking. Similar to problem 46 except that there are duplicates in array in this problem. The problem will be 
   * the same as problem 46 as long as we ensure that a specific number could only appear once in specific position i, 
   * meaning other duplicates should be skipped when selecting a number to put in position i.
   * 
   * 2. Complexity 
   * - Time O(N * N!)
   * - Space O(N)
   * 
   * 3. Improvement 
   * - Note the nums[i] could only within the range [-10, 10], meaning that we could have a boolean array keeping track
   *   of whether the num has been used in the current recursion or not instead of using a hashset. It will improve the
   *   time from 6 ms to 1 ms
   * - The other way to approach it is to sort the array and only select the first unused number and skip the other same
   *   ones. It eliminates the needs of having boolean array or hashset.
   * 
   * @param nums
   * @return
   */
  public List<List<Integer>> permuteUnique(int[] nums) {
    final List<List<Integer>> result = new ArrayList<>();
    final boolean[] visited = new boolean[nums.length];
    backtrack(nums, result, new ArrayList<>(), visited);
    return result;
  }

  private void backtrack(int[] nums, List<List<Integer>> result, List<Integer> currList, boolean[] visited) {
    if (nums.length == currList.size()) {
      result.add(new ArrayList<>(currList));
      return;
    }

    final Set<Integer> set = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      if (visited[i] || set.contains(num)) {
        continue;
      }

      set.add(num);
      currList.add(num);
      visited[i] = true;
      backtrack(nums, result, currList, visited);
      visited[i] = false;
      currList.remove(currList.size() - 1);
    }
  }
}
