package problem;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 1. Problem
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting 
 * parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 * 2. Examples
 * Example 1:
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * 
 * Example 2:
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * 
 * Example 3:
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 * 3. Constraints
 * 1 <= s.length <= 105
 * s[i] is either'(' , ')', or lowercase English letter.
 */
public class MinimumRemoveToMakeValidParentheses {

  /**
   * 1. Approach
   *  Stack with StringBuilder. A naive way to approach parenthese related problem is to use Stack. For each ')' 
   *  encountered, it is valid only when there is a matched '(' prior to it, vice versa. This approach takes two 
   *  pass: first find all indices of invalid ')' and '(' to be removed and then remove them.
   *  
   *  2. Complexity
   *  Time O(N)
   *  Space O(N)
   *  
   * 3. Improvement
   * - Instead of using Stack, a balance variable could be used to achieve the same. See approach 2.
   * @param s
   * @return
   */
  public String minRemoveToMakeValid1(String s) {
    final Stack<Integer> parentheseStack = new Stack<>();
    final Set<Integer> removedSet = new HashSet<>();
    final StringBuilder sb = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '(') {
        parentheseStack.push(i);
      }

      if (c == ')') {
        if (parentheseStack.isEmpty()) {
          removedSet.add(i);
        } else {
          parentheseStack.pop();
        }
      }
    }

    while (!parentheseStack.isEmpty()) {
      removedSet.add(parentheseStack.pop());
    }

    for (int i = 0; i < s.length(); i++) {
      if (!removedSet.contains(i)) {
        sb.append(s.charAt(i));
      }
    }

    return sb.toString();
  }

  /**
   * 1. Approach
   * Balance Variable and Reverse String. Taking a deeper loop, what we care to determine whether ')' is valid is that 
   * whether there are comparative number (more or equals) of '(' prior to it, meaning we only need the cnt of the 
   * parenthese instead of the whole stack. However, this will only help remove ')' but not '('. So a trick here 
   * is to reverse the String and perform the same operation and then extra '(' will be cleaned up.
   * 
   * Example 
   * Char        L  (  e  e  )  (  t  (  (  )  c  o  )  d  )  e    )
   * Balance     0  1  0  0  0  1  0  2  3  2  0  0  1  0  0  0  -1 (Invalid)
   * 
   * 2. Complexity 
   * Time O(N)
   * Space O(N) => Only for StringBuilder
   * 
   * 3. Improvement
   * - This approach reverse the string to remove '('. But we could count the total number of '(' seen to calculate
   *   how many extra '(' we need to remove. See Approach 3.
   * @param s
   * @return
   */
  public String minRemoveToMakeValid2(String s) {
    final StringBuilder strWithRemovedClosingParenthese = removeInvalidClosing(s, '(', ')');
    final StringBuilder strWithRemovedOpenParenthese = removeInvalidClosing(
      strWithRemovedClosingParenthese.reverse().toString(), ')', '('
    );

    return strWithRemovedOpenParenthese.reverse().toString();
  }

  private StringBuilder removeInvalidClosing(String s, char openChar, char closeChar) {
    int balance = 0;
    final StringBuilder sb = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == openChar) {
        balance++;
      }

      if (c == closeChar) {
        if (balance == 0) continue;
        balance--;
      }

      sb.append(c);
    }

    return sb;
  }

  /**
   * 1. Approach 
   * This approach is an optimized version on top of approach 2. The main idea is that for extra '(' we could calculate
   * how many extra we have. If we always remove '(' on the most right, then we are guaranteed that we will have valid
   * parenthese string.
   * 
   * 2. Complexity 
   * Time O(N)
   * Space O(N) => Only for StringBuilder
   *
   * @param s
   * @return
   */
  public String minRemoveToMakeValid3(String s) {
    final StringBuilder sb = new StringBuilder();
    int balance = 0, openSeen = 0;

    for (int i = 0 ; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '(') {
        openSeen++;
        balance++;
      }

      if (c == ')') {
        if (balance == 0) continue;
        balance--;
      }

      sb.append(c);
    }

    final StringBuilder result = new StringBuilder();
    int openKeep = openSeen - balance;

    for (int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);

      if (c == '(') {
        if (openKeep <= 0) continue;
        openKeep--;
      }

      result.append(c);
    }

    return result.toString();
  }
}
