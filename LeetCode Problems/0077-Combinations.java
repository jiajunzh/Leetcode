package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 *
 * You may return the answer in any order.
 *
 * 2. Examples
 * Example 1
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 * 
 * Example 2
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 *
 * 3. Constraints
 * 1 <= n <= 20
 * 1 <= k <= n
 * 
 */
public class Combinations {

  /**
   * 1. Approach 
   * Backtracking 
   * 
   * 2. Complexity 
   * - Time O(C(N, k) * k)
   * - Space O(k)
   * 
   * @param n
   * @param k
   * @return
   */
  public List<List<Integer>> combine(int n, int k) {
    final List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), k, 1, n);
    return result;
  }

  private void backtrack(final List<List<Integer>> result, final List<Integer> curr, final int k, final int start, final int n) {
    if (curr.size() == k) {
      result.add(new ArrayList<>(curr));
      return;
    }
    for (int i = start; i <= n; i++) {
      curr.add(i);
      backtrack(result, curr, k, i + 1, n);
      curr.remove(curr.size() - 1);
    }
  }
}
