package problem;

/**
 * 1. Problem 
 * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
 *
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger). 
 * You must also not convert the inputs to integers directly.
 *
 * 2. Examples
 * Example 1
 * Input: num1 = "11", num2 = "123"
 * Output: "134"
 * 
 * Example 2
 * Input: num1 = "456", num2 = "77"
 * Output: "533"
 * 
 * Example 3
 * Input: num1 = "0", num2 = "0"
 * Output: "0"
 * 
 * 3. Constraints
 * 1 <= num1.length, num2.length <= 104
 * num1 and num2 consist of only digits.
 * num1 and num2 don't have any leading zeros except for the zero itself.
 */
public class AddStrings {

  /**
   * 1. Approach
   * Two pointers
   * 
   * 2. Complexity 
   * - Time O(Max(N, M))
   * - Space O(Max(N,M))
   * 
   * 3. Mistake
   * - sb.insert(0, sum % 10 + '0'); is wrong as sum % 10 + '0' will return an integer representation of the character,
   *   it should be converted to char first.
   *   
   * @param num1
   * @param num2
   * @return
   */
  public String addStrings(String num1, String num2) {
    int i = num1.length() - 1, j = num2.length() - 1;
    final StringBuilder sb = new StringBuilder();
    int carryOut = 0;

    while (i >= 0 || j >= 0) {
      char c1 = i < 0 ? '0' : num1.charAt(i);
      char c2 = j < 0 ? '0' : num2.charAt(j);

      int sum = (c1 - '0') + (c2 - '0') + carryOut;
      carryOut = sum / 10;

      char addedChar = (char) (sum % 10 + '0');

      sb.insert(0, addedChar);
      i--;
      j--;
    }

    if (carryOut == 1) {
      sb.insert(0, carryOut);
    }

    return sb.toString();
  }
}
