package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 
 * Example 2
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * 
 * Example 3
 * Input: nums = [1]
 * Output: [[1]]
 *
 * 3. Constraints
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
public class Permutations {

  /**
   * 1. Approach
   * Backtrack. Try out each possible permutation in the problem. Keep track of whether each individual number is used 
   * or not by having a visited boolean array.
   * 
   * 2. Complexity 
   * - Time: O(N * N!). Number of permutations = P(N,N) = N!. Each permutation takes O(N) to construct.
   *   The total number of nodes during the backtracking is
   *   1 + n + n*(n-1) + n*(n-1)*(n-2) + ... + n*(n-1)*(n-2)*...*2 + n*(n-1)*(n-2)*...*2*1
   *   e.g.                                [1,2,3]                                          1
   *             [1]                        [2]                      [3]                    3
   *   [1,2]           [1,3]       [2,1]         [2,3]      [3,1]          [3,2]          3 x 2
   *   [1,2,3]        [1,3,2]     [2,1,3]        [2,3,1]    [3,1,2]        [3,2,1]        3 x 2 x 1
   *   
   *   1 + n + n*(n-1) + n*(n-1)*(n-2) + ... + n*(n-1)*(n-2)*...*2 + n*(n-1)*(n-2)*...*2*1
   *   = n*(n-1)*(n-2)*...*2*1 + n*(n-1)*(n-2)*...*2 + ... +  n*(n-1)*(n-2) + n*(n-1) + n + 1
   *   = n! + n!/1! + n!/2! + ... + n!/(n-1)! + n!/n!
   *   = n! * (1 + 1/1! + 1/2! + ... + 1/(n-1)! + 1/n!)
   *   = n! * e
   *   
   *   For each node, it takes N iterations, so the overall time is N * N! * e => O(N * N!)
   * - Space O(N) if not consider the space used for storing the answer. (Recursion stack + visited array)
   * 
   * 3. Mistakes
   * - Remember to have a new copy of arraylist instead of using the existing one.
   * 
   * @param nums
   * @return
   */
  public List<List<Integer>> permute(int[] nums) {
    final List<List<Integer>> result = new ArrayList<>();
    boolean[] visited = new boolean[nums.length];
    backtrack(result, new ArrayList<>(), visited, nums);
    return result;
  }

  private void backtrack(List<List<Integer>> result, List<Integer> currList, boolean[] visited, int[] nums) {
    if (visited.length == currList.size()) {
      result.add(new ArrayList<>(currList));
    }

    for (int i = 0; i < nums.length; i++) {
      if (visited[i]) {
        continue;
      }

      visited[i] = true;
      currList.add(nums[i]);
      backtrack(result, currList, visited, nums);
      currList.remove(currList.size() - 1);
      visited[i] = false;
    }
  }
}
