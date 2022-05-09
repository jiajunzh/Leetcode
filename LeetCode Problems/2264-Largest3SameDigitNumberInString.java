package problem;

/**
 * 1. Problem 
 * You are given a string num representing a large integer. An integer is good if it meets the following conditions:
 *
 * It is a substring of num with length 3.
 * It consists of only one unique digit.
 * Return the maximum good integer as a string or an empty string "" if no such integer exists.
 *
 * Note:
 *
 * A substring is a contiguous sequence of characters within a string.
 * There may be leading zeroes in num or a good integer.
 *
 * 2. Examples
 * Example 1
 * Input: num = "6777133339"
 * Output: "777"
 * Explanation: There are two distinct good integers: "777" and "333".
 * "777" is the largest, so we return "777".
 * 
 * Example 2
 * Input: num = "2300019"
 * Output: "000"
 * Explanation: "000" is the only good integer.
 * 
 * Example 3
 * Input: num = "42352338"
 * Output: ""
 * Explanation: No substring of length 3 consists of only one unique digit. Therefore, there are no good integers.
 *
 * 3. Constraints
 * 3 <= num.length <= 1000
 * num only consists of digits.
 */
public class Largest3SameDigitNumberInString {

  /**
   * 1. Approach 
   * Array (Brutal Force)
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param num
   * @return
   */
  public String largestGoodInteger(String num) {
    final int n = num.length();
    int max = -1;

    for (int i = 0; i < n - 2; i++) {
      if (num.charAt(i) == num.charAt(i + 1) && num.charAt(i) == num.charAt(i + 2)) {
        int integer = num.charAt(i) - '0';
        max = Math.max(integer, max);
      }
    }

    return max == -1 ? "" : constructGoodInteger(max);
  }

  private String constructGoodInteger(int num) {
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 3; i++) sb.append(num);
    return sb.toString();
  }
}
