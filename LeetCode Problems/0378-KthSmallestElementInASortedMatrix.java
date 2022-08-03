package problem;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1. Problem 
 * Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * You must find a solution with a memory complexity better than O(n2).
 *
 * 2. Examples 
 * Example 1
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
 * 
 * Example 2
 * Input: matrix = [[-5]], k = 1
 * Output: -5
 *
 * 3. Constraints
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 300
 * -109 <= matrix[i][j] <= 109
 * All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
 * 1 <= k <= n2
 *
 * 4. Follow up
 * Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
 * Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
 */
public class KthSmallestElementInASortedMatrix {

  /**
   * 1. Approach 
   * PriorityQueue/Heap. This problem could be converted to a problem that find the kth smallest element among N sorted 
   * lists. Then we just need a priority queue to keep track of a pointer of each sorted list
   * 
   * 2. Complexity 
   * - Time O(Min(N, K) + K*logMin(N, K))
   * - Space O(Min(N, K))
   * 
   * @param matrix
   * @param k
   * @return
   */
  public int kthSmallest1(int[][] matrix, int k) {
    final int n = matrix.length;
    final PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> matrix[a[0]][a[1]]));
    for (int i = 0; i < Math.min(n, k); i++) {
      pq.offer(new int[]{i, 0});
    }
    while (k > 0) {
      final int[] curr = pq.poll();
      final int row = curr[0];
      final int col = curr[1];
      if (k == 1) {
        return matrix[row][col];
      }
      if (col + 1 < n) {
        pq.offer(new int[]{row, col + 1});
      }
      k--;
    }
    return -1;
  }

  /**
   * 1. Approach 
   * Binary Search + Search Target In 2D Matrix
   * 
   * Search Space: [minElement, maxElement]
   * Condition: there are k elements smaller than or equal to target
   * Binary Search: Find the minimum element x such that there are k elements smaller than or equal to target
   * 
   * 2. Complexity 
   * - Time O(N * log (MAX - MIN))
   * - Space O(1)
   * 
   * @param matrix
   * @param k
   * @return
   */
  public int kthSmallest2(int[][] matrix, int k) {
    final int n = matrix.length;
    int low = matrix[0][0], high = matrix[n - 1][n - 1];
    while (low < high) {
      int mid = low + (high - low) / 2;
      final int count = countElementsSmallerThanEqualToTarget(matrix, mid);
      if (count < k) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return high;
  }

  private int countElementsSmallerThanEqualToTarget(final int[][] matrix, final int target) {
    final int n = matrix.length;
    int row = n - 1, col = 0;
    int count = 0;
    while (row >= 0 && col < n) {
      if (matrix[row][col] > target) {
        row--;
      } else {
        count += row + 1;
        col++;
      }
    }
    return count;
  }
}
