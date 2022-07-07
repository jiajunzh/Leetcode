package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * 2. Examples 
 * Example 1
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * 
 * Example 2
 * Input: n = 1
 * Output: ["()"]
 *
 * 3. Constraints
 * 1 <= n <= 8
 */
public class GenerateParentheses {

  private final List<String>[] memo = new List[10];

  /**
   * 1. Approach 
   * Top-Down Memoization 
   * 
   * 2. Complexity
   * - Time O(N * 2 ^ N)
   * - Space O(N * 2 ^ N)
   * 
   * @param n
   * @return
   */
  public List<String> generateParenthesis1(int n) {
    if (memo[n] != null) {
      return memo[n];
    }
    memo[n] = new ArrayList<>();
    if (n == 0) memo[0].add("");
    for (int i = 0; i < n; i++) {
      for (final String left : generateParenthesis1(i)) {
        for (final String right : generateParenthesis1(n - 1 - i)) {
          memo[n].add("(" + left + ")" + right);
        }
      }
    }

    return memo[n];
  }

  /**
   * 1. Approach 
   * Backtracking 
   * 
   * 2. Complexity 
   * - Time O(N * 2^2N) where O(N) is for StringBuilder toString()
   * - Space O(N * 2^N) considering the space to store results
   * 
   * @param n
   * @return
   */
  public List<String> generateParenthesis(int n) {
    final List<String> result = new ArrayList<>();
    generateParenthesis(result, n, n, new StringBuilder());
    return result;
  }

  private void generateParenthesis(final List<String> result, final int open, final int close, final StringBuilder sb) {
    if (open < 0 || close < open) {
      return;
    }
    if (open + close == 0) {
      result.add(sb.toString());
      return;
    }
    sb.append("(");
    generateParenthesis(result, open - 1, close, sb);
    sb.deleteCharAt(sb.length() - 1);
    sb.append(")");
    generateParenthesis(result, open, close - 1, sb);
    sb.deleteCharAt(sb.length() - 1);
  }
}
