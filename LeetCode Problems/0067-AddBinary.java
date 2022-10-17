package problem;

/**
 * 1. Problem 
 * Given two binary strings a and b, return their sum as a binary string.
 *
 * 2. Examples
 * Example 1
 * Input: a = "11", b = "1"
 * Output: "100"
 * 
 * Example 2
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 * 3. Constraints
 * 1 <= a.length, b.length <= 104
 * a and b consist only of '0' or '1' characters.
 * Each string does not contain leading zeros except for the zero itself.
 */
public class AddBinary {
  
  /**
   * 1. Approach 
   * StringBuilder
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(N)
   */
  public String addBinary(String a, String b) {
    int i = a.length() - 1, j = b.length() - 1;
    int carryout = 0;
    final StringBuilder sb = new StringBuilder();
    while (i >= 0 || j >= 0) {
      final int  ca = i < 0 ? 0 : a.charAt(i) - '0';
      final int cb = j < 0 ? 0 : b.charAt(j) - '0';
      int sum = carryout + ca + cb;
      carryout = sum / 2;
      sum = sum % 2;
      sb.append(sum);
      i--;
      j--;
    }
    if (carryout > 0) sb.append(carryout);
    return sb.reverse().toString();
  }
}
