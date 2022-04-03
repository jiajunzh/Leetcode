package problem;

/**
 * 1. Problem 
 * Given an integer x, return true if x is palindrome integer. An integer is a palindrome when it reads the same 
 * backward as forward. For example, 121 is a palindrome while 123 is not.
 *
 * 2. Examples
 * Example 1:
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * 
 * Example 2:
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * 
 * Example 3:
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 *
 * 3. Constraints:
 * -2^31 <= x <= 2^31 - 1
 */
public class PalindromeNumber {

  /**
   * 1 Approach
   * Revert Number from end to start. The number should be the same as the original one for palindrom number.
   * 
   * 2. Complexity
   * Time O(Digit)
   * Space O(1)
   * 
   * 3. Improvement
   * One improvement is only revert the number at the middle of the number. But one edge case to notice is 10 as it 
   * misses the leading zeros when reverting back.
   * 
   * @param x
   * @return
   */
  public boolean isPalindrome(int x) {
    int revertedNumber = 0;
    int inputNumber = x;

    while (inputNumber > 0) {
      revertedNumber = revertedNumber * 10 + inputNumber % 10;
      inputNumber = inputNumber / 10;
    }

    return x == revertedNumber;
  }
}
