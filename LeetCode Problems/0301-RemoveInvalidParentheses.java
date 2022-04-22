package problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1. Problem
 * Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the 
 * input string valid.
 *
 * Return all the possible results. You may return the answer in any order.
 *
 * 2. Examples
 * Example 1
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 * 
 * Example 2
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 * 
 * Example 3
 * Input: s = ")("
 * Output: [""]
 *
 * 3. Constraints
 * 1 <= s.length <= 25
 * s consists of lowercase English letters and parentheses '(' and ')'.
 * There will be at most 20 parentheses in s.
 */
public class RemoveInvalidParentheses {
  
  private int minRemoval = Integer.MAX_VALUE;

  /**
   * 1. Approach (Version1)
   * Pure Backtracking. Looking at the constraints, a naive solution to the problem is pure backtracking. Recursively 
   * traverse each letter in the string. If the letter is parenthese, either remove it or keep it. When it reaches to 
   * the last letter of the string, check if the generated string is valid or not. 
   *
   * A few things to note include:
   * - There are multiple removal possibilities that could reach the same answer. For example, if the s = "(a))". The 
   *   result will be the same whether you remove the 2th or 3th char in the string. Use Set here to avoid duplicate
   *   answers.
   * - Only keep track of the answers with minimum number of removal. Otherwise, "()" will also be an answer for string
   *   "(((())))". Clear the set if encountering a string with fewer number of removal. 
   *
   * 2. Complexity 
   * - Time O(N * 2^N) - 771 ms
   * - Space O(2^N) if considering the space to store answers | O(N) for stack depth otherwise
   *
   * 3. Alternative 
   * - BFS. This approach uses the DFS which search to the end of the string. However, it could also be done by 
   *   removing 1, 2, 3 .... N letters for each iteration, essentially BFS.
   *   
   * @param s
   * @return
   */
  public List<String> removeInvalidParenthesesVersion1(String s) {
    final Set<String> result = new HashSet<>();
    backtrackingVersion1(s, result, 0, new StringBuilder());
    return new ArrayList<>(result);
  }

  private void backtrackingVersion1(String s, Set<String> result, int currIndex, StringBuilder sb) {
    if (currIndex == s.length()) {
      final String candidate = sb.toString();
      final int removeCnt = s.length() - sb.length();

      if (minRemoval >= removeCnt && isValidParentheses(candidate)) {
        if (minRemoval > removeCnt) {
          result.clear();
          minRemoval = removeCnt;
        }
        result.add(candidate);
      }
    } else {
      char c = s.charAt(currIndex);

      if (c == '(' || c == ')') {
        backtrackingVersion1(s, result, currIndex + 1, sb);
      }

      sb.append(c);
      backtrackingVersion1(s, result, currIndex + 1, sb);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  private boolean isValidParentheses(String s) {
    int balance = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        balance++;
      } else if (c == ')') {
        if (balance == 0) return false;
        balance--;
      }
    }

    return balance == 0;
  }

  /**
   * 1. Approach (Version 2)
   * A slight pruning/optimization on top of version 1 is to remove the check "isValidParentheses" for each combination.
   * One thing to notice here is version 1 just iterates from start to end of the string which could be done easily 
   * along with the recursion itself. This will help exit the recursion earlier.
   *
   * 2. Complexity 
   * - Time O(N * 2^N) - 313 ms Note StringBuilder.toString() takes O(N)
   * - Space O(2^N) if considering the space to store answers | O(N) for stack depth otherwise
   *
   * @param s
   * @return
   */
  public List<String> removeInvalidParenthesesVersion2(String s) {
    final Set<String> result = new HashSet<>();
    backtrackingVersion2(s, result, 0, new StringBuilder(), 0);
    return new ArrayList<>(result);
  }

  private void backtrackingVersion2(String s, Set<String> result, int currIndex, StringBuilder sb, int balance) {
    if (balance < 0) {
      return;
    }

    if (currIndex == s.length()) {
      final String candidate = sb.toString();
      final int removeCnt = s.length() - sb.length();

      if (minRemoval >= removeCnt && balance == 0) {
        if (minRemoval > removeCnt) {
          result.clear();
          minRemoval = removeCnt;
        }
        result.add(candidate);
      }
    } else {
      char c = s.charAt(currIndex);

      if (c == '(') {
        backtrackingVersion2(s, result, currIndex + 1, sb, balance);
        balance++;
      }
      if (c == ')') {
        backtrackingVersion2(s, result, currIndex + 1, sb, balance);
        balance--;
      }

      sb.append(c);
      backtrackingVersion2(s, result, currIndex + 1, sb, balance);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  /**
   * 1. Approach (Version3)
   * Limited Backtracking with pre-calculated count of removed parentheses.
   * 
   * 2. Complexity 
   * - Time O(N * 2^N) - 163 ms Note StringBuilder.toString() takes O(N)
   * - Space O(2^N) if considering the space to store answers | O(N) for stack depth otherwise
   * 
   * @param s 
   * @return
   */
  public List<String> removeInvalidParenthesesVersion3(String s) {
    final Set<String> result = new HashSet<>();
    final int[] removedCnt = calculateRemovedParenthese(s);
    backtrackingVersion3(s, result, 0, new StringBuilder(), 0, removedCnt[0], removedCnt[1]);
    return new ArrayList<>(result);
  }

  private void backtrackingVersion3(String s, Set<String> result, int currIndex, StringBuilder sb, int balance, int removedOpen, int removedClose) {
    if (balance < 0 || removedOpen < 0 || removedClose < 0) {
      return;
    }

    if (currIndex == s.length()) {
      if (removedOpen == 0 && removedClose == 0) {
        result.add(sb.toString());
      }
    } else {
      char c = s.charAt(currIndex);

      if (c == '(') {
        backtrackingVersion3(s, result, currIndex + 1, sb, balance, removedOpen - 1, removedClose);
        balance++;
      }
      if (c == ')') {
        backtrackingVersion3(s, result, currIndex + 1, sb, balance, removedOpen, removedClose - 1);
        balance--;
      }

      sb.append(c);
      backtrackingVersion3(s, result, currIndex + 1, sb, balance, removedOpen, removedClose);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  private int[] calculateRemovedParenthese(String s) {
    int removedOpen = 0, removedClose = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        removedOpen++;
      } else if (c == ')') {
        if (removedOpen == 0) {
          removedClose++;
        } else {
          removedOpen--;
        }
      }
    }

    return new int[]{removedOpen, removedClose};
  }

  /**
   * 1. Approach (Version4)
   * DFS (Recursion). This approach detects what is the recent invalid closure parentheses needs to be removed and then 
   * remove the first closure in a consecutive closure substring to avoid duplicates and recurse downwards.
   * 
   * Highlights:
   * - Duplicate answer. It avoids duplicates by always removing the first closure in a consecutive substring. E.g. 
   *   (((a)))a), the answer will be the same if we remove closure at 4th, 5th, 6th letter in the string, so we only 
   *   need to remove the first one 4th.
   * - How to handle removal of open parentheses? The simplest solution is to reverse the string and reuse the code.
   * - Keep track of the last removal position. Because if we start all over from the beginning, we might remove two 
   *   closure in different orders, generating duplicates. For example, (()())a)), remove 2rd and 4th in different 
   *   orders. By starting only from the last removal position, we guarantee that anything before this position could
   *   not be removed so it will cause duplicate.
   *   remove 2rd first => ((())a)) => remove 4th (original index) => ((()a))
   *   remove 4th first => (()()a)) => starting from 4th only => 2rd will be guaranteed to be kept
   * 
   * Reference: https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
   * 
   * 2. Complexity (Rough Estimation)
   * - Time O(K * I * J * N (subString)) ~ O(K * N^3) The program only generates valid answers. Every path in the search 
   *   generates one valid answer. The whole search space is a tree with K leaves. Suppose the length of the string s is
   *   N and the length of the answer is M, then number of removal is (N - M)
   * - Space O(N)
   *   
   * @param s
   * @return
   */
  public List<String> removeInvalidParenthesesVersion4(String s) {
    final List<String> result = new ArrayList<>();
    dfs(s, result, 0, 0, new char[]{'(', ')'});
    return result;
  }

  private void dfs(String s, List<String> result, int starti, int startj, char[] parentheses) {
    int balance = 0;

    for (int i = starti; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == parentheses[0]) balance++;
      if (c == parentheses[1]) balance--;
      if (balance >= 0) continue;

      for (int j = startj; j <= i; j++) {
        if (s.charAt(j) == parentheses[1] && (j == startj || s.charAt(j - 1) != parentheses[1])) {
          dfs(s.substring(0, j) + s.substring(j + 1, s.length()), result, i, j, parentheses);
        }
      }

      return;
    }

    s = new StringBuilder(s).reverse().toString();

    if (parentheses[0] == '(') {
      dfs(s, result, 0, 0, new char[]{')', '('});
    } else {
      result.add(s);
    }
  }
}
