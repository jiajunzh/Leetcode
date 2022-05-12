package problem;

/**
 * 1. Problem 
 * The Tribonacci sequence Tn is defined as follows: 
 *
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 *
 * Given n, return the value of Tn.
 *
 * 2. Examples
 * Example 1
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * 
 * Example 2
 * Input: n = 25
 * Output: 1389537
 *
 * 3. Constraints
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 */
public class NthTribonacciNumber {

  /**
   * 1. Approach 
   * Dynamic Programming with Constant Space. 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param n
   * @return
   */
  public int tribonacci(int n) {
    if (n <= 1) {
      return n;
    }

    int dp0 = 0, dp1 = 1, dp2 = 1;

    for (int i = 3; i <= n; i++) {
      int sum = dp0 + dp1 + dp2;
      dp0 = dp1;
      dp1 = dp2;
      dp2 = sum;
    }

    return dp2;
  }
}
