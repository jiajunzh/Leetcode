package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. Problem 
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations 
 * of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
 * frequency of at least one of the chosen numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the 
 * given input.
 *
 * 2. Examples
 * 
 * Example 1
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * 
 * Example 2
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * 
 * Example 3
 * Input: candidates = [2], target = 1
 * Output: []
 *
 * 3. Constraints
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * All elements of candidates are distinct.
 * 1 <= target <= 500
 */
public class CombinationSum {
  /**
   * 1. Approach 
   * Backtracking. Highlights below:
   * - candidate[i] and target is always be greater than 0, meaning that the target will be decreasing for each recurison 
   * - each candidate could be picked up for any times
   * - unique combination are required. So [2,2,3] and [2,3,2] are duplicates. This could be resolved by always starting
   *   iterating from the last position in the last recursion, it guarantees two things:
   *   - The same element could be selected for multiple times.
   *   - The previous element won't be selected again to cause duplicates.
   * 
   * 2. Complexity 
   * - Time O(N^(T/M) + 1) T is the target number and M is the minimum value in candidates.
   * - Space O(T/M)
   * 
   * 3. Improvement
   * - Since the time complexity for backtracking is N^(T/M), array sorting with complexity only O(NlogN) could help 
   *   with pruning the backtracking by exiting once the number is greater than target (as there is no need to iterate
   *   further).
   * 
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    final List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);
    backtrack(candidates, target, new ArrayList<>(), result, 0);
    return result;
  }
  
  private void backtrack(int[] candidates, int target, List<Integer> curr, List<List<Integer>> result, int start) {
    if (target == 0) {
      result.add(new ArrayList<>(curr));
      return;
    }


    for (int i = start; i < candidates.length; i++) {
      int candidate = candidates[i];
      if (candidate > target) {
        break;
      }
      curr.add(candidate);
      backtrack(candidates, target - candidate, curr, result, i);
      curr.remove(curr.size() - 1);
    }
  }
}
