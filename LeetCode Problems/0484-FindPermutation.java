package problem;

import java.util.Stack;

/**
 * 1. Problem 
 * A permutation perm of n integers of all the integers in the range [1, n] can be represented as a string s of length 
 * n - 1 where:
 *
 * s[i] == 'I' if perm[i] < perm[i + 1], and
 * s[i] == 'D' if perm[i] > perm[i + 1].
 * Given a string s, reconstruct the lexicographically smallest permutation perm and return it.
 *
 * 2. Examples 
 * Example 1
 * Input: s = "I"
 * Output: [1,2]
 * Explanation: [1,2] is the only legal permutation that can represented by s, where the number 1 and 2 construct an increasing relationship.
 * 
 * Example 2
 * Input: s = "DI"
 * Output: [2,1,3]
 * Explanation: Both [2,1,3] and [3,1,2] can be represented as "DI", but since we want to find the smallest lexicographical permutation, you should return [2,1,3]
 *
 * 3. Constraints
 * 1 <= s.length <= 105
 * s[i] is either 'I' or 'D'.
 */
public class FindPermutation {

  /**
   * 1. Approach 
   * Stack.
   * 
   * Consider case 'DDIIIID'. For the first two 'DD', we know it is decreasing and we have multiple choices 
   * such as '321', '432', '543' ... However, the '321' is the choice generating the smallest lexi order permutation by 
   * always selecting the smallest number so far for decreasing sequence. For increasing sequence, we just put
   * the current unused smallest number, for example, '321''4567'.
   * 
   * The rule is:
   * - 'I' => place the current smallest unused number
   * - 'D' => reverse the subarray in increasing order to decreasing order. E.g., if we have 'DD' and 123, we want to 
   *          reverse 123 to 321
   * 
   * With the rule, we could keep a stack to reverse the subarray.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param s
   * @return
   */
  public int[] findPermutation1(String s) {
    final int n = s.length() + 1;
    final int[] result = new int[n];
    final Stack<Integer> stack = new Stack<>();
    int j = 0;
    for (int i = 1; i <= n; i++) {
      final char c = i == n ? 'I' : s.charAt(i - 1);
      if (c == 'I') {
        stack.push(i);
        while (!stack.isEmpty()) {
          result[j] = stack.pop();
          j++;
        }
      } else {
        stack.push(i);
      }
    }
    return result;
  }
  
  /**
   * 1. Approach 
   * Two Pointers 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param s
   * @return
   */
  public int[] findPermutation2(String s) {
    final int n = s.length() + 1;
    final int[] result = new int[n];
    int i = 0, j = 0;
    while (j <= s.length()) {
      final char c = j == s.length() ? 'I' : s.charAt(j);
      if (c == 'D') {
        j++;
      } else {
        int k = j + 1;
        while (i <= j) {
          result[i] = k;
          i++;
          k--;
        }
        j++;
      }
    }
    return result;
  }
}
