package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem
 * Given a 0-indexed n x n integer matrix grid, return the number of pairs (Ri, Cj) such that row Ri and column Cj are equal.
 *
 * A row and column pair is considered equal if they contain the same elements in the same order (i.e. an equal array).
 *
 * 2. Examples
 * Example 1
 * Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
 * Output: 1
 * Explanation: There is 1 equal row and column pair:
 * - (Row 2, Column 1): [2,7,7]
 * 
 * Example 2
 * Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * Output: 3
 * Explanation: There are 3 equal row and column pairs:
 * - (Row 0, Column 0): [3,1,2,2]
 * - (Row 2, Column 2): [2,4,2,2]
 * - (Row 3, Column 2): [2,4,2,2]
 *
 * 3. Constraints
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * 1 <= grid[i][j] <= 105
 */
public class EqualRowAndColumnPairs {

  /**
   * 1. Approach 
   * StringBuilder + HashMap
   * 
   * 2. Complexity 
   * - Time O(N^2)
   * - Space O(N^2)
   * 
   * @param grid
   * @return
   */
  public int equalPairs(int[][] grid) {
    final int n = grid.length, m = grid[0].length;
    final Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      final StringBuilder sb = new StringBuilder();
      for (int j = 0; j < m; j++) {
        sb.append(grid[i][j]);
        sb.append("-");
      }
      final String rowKey = sb.toString();
      map.put(rowKey, map.getOrDefault(rowKey, 0) + 1);
    }
    int count = 0;
    for (int i = 0; i < n; i++) {
      final StringBuilder sb = new StringBuilder();
      for (int j = 0; j < m; j++) {
        sb.append(grid[j][i]);
        sb.append("-");
      }
      final String colKey = sb.toString();
      count += map.getOrDefault(colKey, 0);
    }
    return count;
  }
}
