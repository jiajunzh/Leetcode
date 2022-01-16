package problem;

/**
 * 1. Problem
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return the nth ugly number.
 *
 * 2. Example
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 *
 * Input: n = 1
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 *
 * 3. Constraints:
 * 1 <= n <= 1690
 */
public class UglyNumberII {

  /**
   * Dynamic Programming with Three Pointers. Have three pointers keep track of the last position for each
   * factor. dp[i] = Min(2 * dp[p2], 3 * dp[p3], 5 * [p5]).
   * 
   * Time O(N)
   * Space O(N)
   * 
   * Mistake:
   * 1. Move pointers for all factors
   * For example, 6 = 2 x 3, meaning both v2 and v3 could have the same value. If only one of the pointer
   * get moved, the next iteration will cause duplicate value.
   * 
   * @param n
   * @return
   */
  public int nthUglyNumber(int n) {
    final int[] dp = new int[n + 1];

    dp[1] = 1;
    int p2 = 1;
    int p3 = 1;
    int p5 = 1;

    for (int i = 2; i <= n; i++) {
      int v2 = 2 * dp[p2];
      int v3 = 3 * dp[p3];
      int v5 = 5 * dp[p5];

      dp[i] = Math.min(v2, Math.min(v3, v5));

      if (dp[i] == v2) p2++;
      if (dp[i] == v3) p3++;
      if (dp[i] == v5) p5++;
    }

    return dp[n];
  }
}
