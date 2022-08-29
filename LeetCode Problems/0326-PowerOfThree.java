package problem;

/**
 * 1. Problem 
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 *
 * 2. Examples 
 * Example 1
 * Input: n = 27
 * Output: true
 * 
 * Example 2
 * Input: n = 0
 * Output: false
 * 
 * Example 3
 * Input: n = 9
 * Output: true
 *
 * 3. Constraints
 * -231 <= n <= 231 - 1
 *
 * 4. Follow up
 * Could you solve it without loops/recursion?
 */
public class PowerOfThree {

  /**
   * 1. Approach
   * Recursion 
   * 
   * 2. Complexity
   * - Time O(logb(n))
   * - Space O(logb(n))
   * 
   * 3. Other Approaches 
   * (1) Base Conversion 
   * For any Base B, the number could be represented by 1 followed by a series of 0s (e.g. 100..000). For example, 
   * (10)2 for 2, (100)2 for 4 and etc. 
   * return Integer.toString(n, 3).matches("^10*$");
   * 
   * (2) Integer Limitations
   * The maximum value of Integer is 2147483647 and we could know that the maximum integer number that is power of 3 is 
   * 1162261467 = 3^19. Then if 1162261467 % n == 0, then it should be a power of three
   * 
   * @param n
   * @return
   */
  public boolean isPowerOfThree(int n) {
    if (n == 1) return true;
    if (n == 0 || n % 3 != 0) return false;
    return isPowerOfThree(n / 3);
  }
}
