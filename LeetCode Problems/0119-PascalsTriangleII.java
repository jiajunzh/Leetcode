package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * 2. Examples
 * Example 1
 * Input: rowIndex = 3
 * Output: [1,3,3,1]
 * 
 * Example 2
 * Input: rowIndex = 0
 * Output: [1]
 * 
 * Example 3
 * Input: rowIndex = 1
 * Output: [1,1]
 *
 * 3. Constraints
 * 0 <= rowIndex <= 33
 *
 * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?
 */
public class PascalsTriangleII {

  /**
   * 1. Approach 
   * Recursion with Memory Optimized. Each iteration will override the element in the list obtained from its subproblem
   * or previous iteration.
   * 
   * 2. Complexity 
   * - Time O(N * N)
   * - Space O(N)
   * 
   * 3. Alternative 
   * - Obviously this could also be modified to use iteration and Dynamic Programming. However, idea is similar to here.
   *   pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j]
   * 
   * @param rowIndex
   * @return
   */
  public List<Integer> getRow(int rowIndex) {
    if (rowIndex <= 1) {
      final List<Integer> result = new ArrayList<>();
      result.add(1);
      if (rowIndex == 1) result.add(1);
      return result;
    }

    final List<Integer> row = getRow(rowIndex - 1);
    row.add(0, 1);
    for (int i = 2; i < row.size(); i++) {
      int prev = row.get(i - 1);
      int curr = row.get(i);
      row.set(i - 1, prev + curr);
    }
    return row;
  }
}
