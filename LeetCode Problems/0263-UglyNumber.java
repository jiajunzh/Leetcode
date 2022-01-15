package problem;

/**
 * 1. Problem 
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return true if n is an ugly number.
 *
 * 2. Example
 * Input: n = 6
 * Output: true
 * Explanation: 6 = 2 × 3
 *
 * Input: n = 1
 * Output: true
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 *
 * Input: n = 14
 * Output: false
 * Explanation: 14 is not ugly since it includes the prime factor 7.
 *
 * 3. Constraints:
 *
 * -231 <= n <= 231 - 1
 */
public class UglyNumber {

  /**
   * Time O(C) C is number of prime factors.
   * Space O(C)
   * 
   * Mistakes:
   * 1. Forget to check positivity 
   * One of the condition is positive number.
   * 
   * @param n
   * @return
   */
  public boolean isUgly(int n) {
    if (n <= 0) {
      return false;
    }

    final int[] primeFactors = new int[]{2, 3, 5};

    for (final int primeFactor : primeFactors) {
      while (n % primeFactor == 0) {
        n = n / primeFactor;
      }
    }

    return n == 1;
  }
}
