package problem;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1. Problem 
 * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians). The
 * soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
 *
 * A row i is weaker than a row j if one of the following is true:
 *
 * The number of soldiers in row i is less than the number of soldiers in row j.
 * Both rows have the same number of soldiers and i < j.
 * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
 *
 * 2. Examples
 * Example 1
 * Input: mat = 
 * [[1,1,0,0,0],
 *  [1,1,1,1,0],
 *  [1,0,0,0,0],
 *  [1,1,0,0,0],
 *  [1,1,1,1,1]], 
 * k = 3
 * Output: [2,0,3]
 * Explanation: 
 * The number of soldiers in each row is: 
 * - Row 0: 2 
 * - Row 1: 4 
 * - Row 2: 1 
 * - Row 3: 2 
 * - Row 4: 5 
 * The rows ordered from weakest to strongest are [2,0,3,1,4].
 * 
 * Example 2
 * Input: mat = 
 * [[1,0,0,0],
 *  [1,1,1,1],
 *  [1,0,0,0],
 *  [1,0,0,0]], 
 * k = 2
 * Output: [0,2]
 * Explanation: 
 * The number of soldiers in each row is: 
 * - Row 0: 1 
 * - Row 1: 4 
 * - Row 2: 1 
 * - Row 3: 1 
 * The rows ordered from weakest to strongest are [0,2,3,1].
 *
 * 3. Constraints
 * m == mat.length
 * n == mat[i].length
 * 2 <= n, m <= 100
 * 1 <= k <= m
 * matrix[i][j] is either 0 or 1.
 */
public class TheKWeakestRowsInAMatrix {

  /**
   * 1. Approach 
   * Binary Search + Priority Queue
   * 
   * 2. Complexity 
   * - Time O(NlogM + NlogK)
   * - Space O(N)
   * 
   * 3. Alternative 
   * - Vertical Iteration (Iterate Column By Column) => Iterate each column, whenever meeting zero, we check if the element
   *   to the left of it is 1. If so, that is the first zero in this row. We should add this row to the result set.
   * 
   * @param mat
   * @param k
   * @return
   */
  public int[] kWeakestRows(int[][] mat, int k) {
    final int n = mat.length;
    final int[] soldiers = new int[n];
    final Queue<Integer> pq = new PriorityQueue<>((a, b) -> (soldiers[a] == soldiers[b] ? b - a : soldiers[b] - soldiers[a]));
    for (int i = 0; i < n; i++) {
      soldiers[i] = binarySearch(mat[i]);
      pq.offer(i);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    final int[] result = new int[k];
    int index = k - 1;
    while (!pq.isEmpty()) {
      result[index] = pq.poll();
      index--;
    }
    return result;
  }

  // Find the minimum index k such that row[k] = 0, could be [0: n] 
  private int binarySearch(int[] row) {
    int low = 0, high = row.length;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (row[mid] == 0) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }
}
