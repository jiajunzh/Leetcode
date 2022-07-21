package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 *
 * 2. Examples
 * Example 1
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * Example 2
 * Input: numRows = 1
 * Output: [[1]]
 *
 * 3. Constraints
 * 1 <= numRows <= 30
 */
public class PascalsTriangle {

  /**
   * 1. Approach 
   * DP
   * 
   * 2. Complexity
   * - Time O(R ^ R)
   * - Space O(R ^ R)
   * 
   * @param numRows
   * @return
   */
  public List<List<Integer>> generate(int numRows) {
    final List<List<Integer>> result = new ArrayList<>();
    final List<Integer> firstRow = new ArrayList<>();
    firstRow.add(1);
    result.add(firstRow);
    for (int i = 1; i < numRows; i++) {
      final List<Integer> curr = new ArrayList<>();
      final List<Integer> last = result.get(i - 1);
      curr.add(1);
      for (int j = 1; j < last.size(); j++) {
        curr.add(last.get(j - 1) + last.get(j));
      }
      curr.add(1);
      result.add(curr);
    }
    return result;
  }
}
