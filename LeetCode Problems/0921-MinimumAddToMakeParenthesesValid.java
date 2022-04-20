package problem;

/**
 * 1. Problem
 * A parentheses string is valid if and only if:
 *
 * It is the empty string,
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
 *
 * For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be
 * "())))". Return the minimum number of moves required to make s valid.
 *
 * 2. Examples
 * Example 1
 * Input: s = "())"
 * Output: 1
 * 
 * Example 2
 * Input: s = "((("
 * Output: 3
 *
 * 3. Constraints
 * 1 <= s.length <= 1000
 * s[i] is either '(' or ')'.
 */
public class MinimumAddToMakeParenthesesValid {

  /**
   * 1. Approach
   * Balance Count. Use a variable to keep track of the '(' already encountered so far, if the balance is zero and it 
   * encounters ')', that means there is no remaining '(' to match with ')' to become a closing parenthesis, meaning
   * one more add operation is needed for this. This problem is an easier version of problem 1249.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   *
   * @param s
   * @return
   */
  public int minAddToMakeValid(String s) {
    int balance = 0;
    int minAdd = 0;
    char[] charArray = s.toCharArray();

    for (char c: charArray) {
      if (c == ')'){
        if (balance == 0) {
          minAdd++;
        } else {
          balance--;
        }
      } else if (c == '(') {
        balance++;
      }
    }

    return minAdd + balance;
  }
}
