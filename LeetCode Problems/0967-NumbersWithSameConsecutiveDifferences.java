package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.
 *
 * Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.
 *
 * You may return the answer in any order.
 *
 * 2. Examples
 * Example 1
 * Input: n = 3, k = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 * 
 * Example 2
 * Input: n = 2, k = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 * 3. Constraints
 * 2 <= n <= 9
 * 0 <= k <= 9
 */
public class NumbersWithSameConsecutiveDifferences {
  
  /**
   * 1. Approach 
   * Backtracking/DFS
   * 
   * 2. Complexity 
   * - Time O(9 * 2^N)
   * - Space O(N)
   * 
   * 3. Edge Case
   * - Be careful with edge case where k = 0 as you only need to recurse once in this case
   * 
   * 4. Alternative
   * - This could also be achieved by BFS
   * 
   * @param n
   * @param k
   * @return
   */
  public int[] numsSameConsecDiff(int n, int k) {
    final List<Integer> numsSameConsecDiff = new ArrayList<>();
    for (int i = 1; i <= 9; i++) {
      numsSameConsecDiff(numsSameConsecDiff, i, n - 1, k);
    }
    final int[] result = new int[numsSameConsecDiff.size()];
    for (int i = 0; i < numsSameConsecDiff.size(); i++) {
      result[i] = numsSameConsecDiff.get(i);
    }
    return result;
  }

  public void numsSameConsecDiff(List<Integer> numsSameConsecDiff, int currNum, int n, int k) {
    if (n == 0) {
      numsSameConsecDiff.add(currNum);
      return;
    }
    List<Integer> nextDigits = new ArrayList<>();
    int lastDigit = currNum % 10;
    nextDigits.add(lastDigit + k);
    if (k != 0) nextDigits.add(lastDigit - k);
    for (final int nextDigit : nextDigits) {
      if (nextDigit >= 0 && nextDigit <= 9) {
        numsSameConsecDiff(numsSameConsecDiff, currNum * 10 + nextDigit, n - 1, k);
      }
    }
  }
}
