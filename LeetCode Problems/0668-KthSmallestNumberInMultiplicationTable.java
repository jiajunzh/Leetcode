package problem;

/**
 * 1. Problem
 * Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat 
 * where mat[i][j] == i * j (1-indexed). Given three integers m, n, and k, return the kth smallest element in the m x n
 * multiplication table.
 *
 * 2. Examples
 * Input: m = 3, n = 3, k = 5
 * Output: 3
 * Explanation: The 5th smallest number is 3.
 *
 * Input: m = 2, n = 3, k = 6
 * Output: 6
 * Explanation: The 6th smallest number is 6.
 *
 * 3. Constraints
 * 1 <= m, n <= 3 * 104
 * 1 <= k <= m * n
 */
public class KthSmallestNumberInMultiplicationTable {
  
  /**
   * 1. Approach  
   * Binary Search. Find minimum Multiplication such that at least k elements are less or equals to Multiplication.
   * 
   * 2. Complexity 
   * Time O(M * Log(M * N))
   * Space O(1)
   * 
   * 3. Mistake
   * 1) Reduce one loop
   * The original solution loops through both m and n, which would trigger TimeLimitExceeded in case where m = 9895,
   * n = 28405, k = 100787757. However, it could reduce one for loop simply by using division calculation. For each 
   * row, the number of elements that are less than or equals to Multiplication is Math.min(n, target / i)
   */
  public int findKthNumber(int m, int n, int k) {
    int low = 1, high = m * n;

    while (low < high) {
      final int mid = low + (high - low) / 2;

      if (countNumbersLessThanOrEqualsToTarget(m, n, mid) >= k) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }

    return low;
  }

  private int countNumbersLessThanOrEqualsToTarget(int m, int n, int target) {
    int count = 0;

    for (int i = 1; i <= m; i++) {
      count += Math.min(n, target / i);
    }

    return count;
  }
}
