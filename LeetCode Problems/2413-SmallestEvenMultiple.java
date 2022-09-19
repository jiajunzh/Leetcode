package problem;

/**
 * 1. Problem 
 * Given a positive integer n, return the smallest positive integer that is a multiple of both 2 and n.
 * 
 * 2. Examples
 * Example 1
 * Input: n = 5
 * Output: 10
 * Explanation: The smallest multiple of both 5 and 2 is 10.
 * 
 * Example 2
 * Input: n = 6
 * Output: 6
 * Explanation: The smallest multiple of both 6 and 2 is 6. Note that a number is a multiple of itself.
 *
 * 3. Constraints
 * 1 <= n <= 150
 */
public class SmallestEvenMultiple {

  /**
   * 1. Approach
   * Math
   * 
   * 2. Complexity 
   * - Time O(1)
   * - Space O(1)
   * 
   * @param n
   * @return
   */
  public int smallestEvenMultiple(int n) {
    if (n % 2 == 0) {
      return n;
    } else {
      return 2 * n;
    }
  }

  /**
   * 1. Approach 
   * Bit shifting 
   * 
   * @param n
   * @return
   */
  public int smallestEvenMultiple2(int n) {
    return n << (n % 2);
  }
}
