package problem;

/**
 * 1. Problem 
 * An integer n is strictly palindromic if, for every base b between 2 and n - 2 (inclusive), the string representation 
 * of the integer n in base b is palindromic.
 *
 * Given an integer n, return true if n is strictly palindromic and false otherwise.
 *
 * A string is palindromic if it reads the same forward and backward.
 *
 * 2. Examples 
 * Example 1
 * Input: n = 9
 * Output: false
 * Explanation: In base 2: 9 = 1001 (base 2), which is palindromic.
 * In base 3: 9 = 100 (base 3), which is not palindromic.
 * Therefore, 9 is not strictly palindromic so we return false.
 * Note that in bases 4, 5, 6, and 7, n = 9 is also not palindromic.
 * 
 * Example 2
 * Input: n = 4
 * Output: false
 * Explanation: We only consider base 2: 4 = 100 (base 2), which is not palindromic.
 * Therefore, we return false.
 *
 * 3. Constraints
 * 4 <= n <= 105
 */
public class StrictlyPalindromicNumber {

  /**
   * 1. Approach 
   * Iterate each base 
   * 
   * 2. Complexity 
   * - Time O(N*logN)
   * - Space O(logN)
   * 
   * 3. Alternative 
   * - Always return false. When base is n - 2, it could be concluded that the number n could be converted to 12, which 
   *   will never be palindromic.
   *   
   * @param n
   * @return
   */
  public boolean isStrictlyPalindromic(int n) {
    for (int i = 2; i <= n - 2; i++) {
      if (!isPalindromic(i, n)) {
        return false;
      }
    }
    return true;
  }

  private boolean isPalindromic(int base, int num) {
    final StringBuilder sb = getStringAtBase(base, num);
    return sb.toString().equals(sb.reverse().toString());
  }

  private StringBuilder getStringAtBase(int base, int num) {
    final StringBuilder sb = new StringBuilder();
    while (num > 0) {
      sb.append(num % base);
      num = num / base;
    }
    return sb;
  }
}
