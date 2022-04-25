package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. Problem 
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in 
 * candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 * 2. Examples
 * Example 1
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output: 
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 
 * Example 2
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output: 
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * 3. Constraints
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class CombinationSumII {

  /**
   * 1. Approach
   * Backtrack + Variations of problem 39. The only difference is it escapes the subsequent duplicate items when 
   * selecting the item at position ith, which helps with eliminating duplicate answers.
   * 
   * 2. Complexity 
   * - Time O(2^N)
   * - Space O(N)
   *
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    final List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);
    backtrack(candidates, result, target, 0, new ArrayList<>());
    return result;
  }

  private void backtrack(int[] candidates, List<List<Integer>> result, int target, int start, List<Integer> curr) {
    if (target == 0) {
      result.add(new ArrayList<>(curr));
    }

    for (int i = start; i < candidates.length; i++) {
      if (i > start && candidates[i] == candidates[i - 1]) {
        continue;
      }
      int candidate = candidates[i];
      if (candidate > target) {
        break;
      }
      curr.add(candidate);
      backtrack(candidates, result, target - candidate, i + 1, curr);
      curr.remove(curr.size() - 1);
    }
  }
}
