package problem;

/**
 * 1. Problem 
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 * 2. Examples
 * Example 1
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * 
 * Example 2
 * Input: x = 2.10000, n = 3
 * Output: 9.2610
 * 
 * Example 3
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 * 3. Constraints
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * n is an integer.
 * -104 <= xn <= 104
 */
public class PowXN {

  /**
   * 1. Approach 
   * Recursion 
   * 
   * 2. Complexity 
   * - Time O(logN)
   * - Space O(logN)
   * 
   * @param x
   * @param n
   * @return
   */
  public double myPow(double x, int n) {
    long N = n;
    if (N == 0) return 1;
    if (N < 0) {
      N = -N;
      x = 1 / x;
    }
    return myPowHelper(x, N);
  }

  private double myPowHelper(double x, long n) {
    if (n == 0) return 1.0;
    final double half = myPowHelper(x, n / 2);
    return half * half * (n % 2 == 0 ? 1 : x);
  }
}
