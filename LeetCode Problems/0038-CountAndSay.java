package problem;

/**
 * 1. Problem
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 *
 * countAndSay(1) = "1"
 * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
 * To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring contains exactly 
 * one unique digit. Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit.
 * 
 * For example, the saying and conversion for digit string "3322251":
 *
 * Given a positive integer n, return the nth term of the count-and-say sequence.
 * 
 * 2. Examples
 * Example 1
 * Input: n = 1
 * Output: "1"
 * Explanation: This is the base case.
 * 
 * Example 2
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 *
 * 3. Constraints
 * 1 <= n <= 30
 */
public class CountAndSay {

  /**
   * 1. Approach
   * String + Iteration
   * 
   * 2. Complexity 
   * - Time O(4^n/3)
   * - Space O(4^n/3)
   * 
   * @param n
   * @return
   */
  public String countAndSay(int n) {
    String curr = "1";
    for (int i = 2; i <= n; i++) {
      final StringBuilder sb = new StringBuilder();
      char prevChar = curr.charAt(0);
      int count = 0;
      for (char c : curr.toCharArray()) {
        if (c == prevChar) {
          count++;
        } else {
          sb.append(count);
          sb.append(prevChar);
          prevChar = c;
          count = 1;
        }
      }
      sb.append(count);
      sb.append(prevChar);
      curr = sb.toString();
    }
    return curr;
  }
}
