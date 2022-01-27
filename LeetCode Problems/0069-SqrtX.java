package problem;

/**
 * 1. Problem
 * Given a non-negative integer x, compute and return the square root of x. Since the return type is an integer, the 
 * decimal digits are truncated, and only the integer part of the result is returned.
 *
 * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
 *
 * 2. Example
 * Input: x = 4
 * Output: 2
 *
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 *
 * 3. Constraints:
 * 0 <= x <= 2^31 - 1
 */
public class SqrtX {

  /**
   * 1. Approach
   * Binary Search. This problem could be generalized as finding the maximum value k satisfying condition k * k <= x.
   * It could be further transformed to finding the minimum value k such that satisfying condition
   * (k + 1) * (k + 1) > x.
   * 
   * 2. Complexity 
   * Time O(logN)
   * Space O(1)
   * 
   * 3. Mistakes
   * 1) Use long instead of integer. Error case: 2147395599
   * The x passed into the method could be Integer.MAX_VALUE. Two places that could cause overflow include 
   * x + 1 and x * x.
   * 
   * @param x
   * @return
   */
  public int mySqrt(int x) {
    long left = 0, right = x;

    while (left < right) {
      long mid = left + (right - left) / 2;

      if ((mid + 1) * (mid + 1) > x) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    return (int)left;
  }
}
