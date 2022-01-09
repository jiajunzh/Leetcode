package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. Problem
 * Given an integer n, return the least number of perfect square numbers that sum to n. A perfect square is an integer
 * that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 
 * 9, and 16 are perfect squares while 3 and 11 are not.
 * 
 * 2. Examples
 * 
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * 
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 * 
 * 3. Constraints
 * 1 <= n <= 10^4
 */
public class PefectSquare {

  /**
   * Dynamic Programming. NumSquares[n] = min(minNumSquares[n - Si] + 1) Si is any Squares less than n. For example,
   * 12 = 4 + 8 = 4 + 4 + 4.
   * 
   * Mistakes:
   * 1. Array.fill Max Value.
   * The int value is initialized to 0 by Array, which means Math.min will always return 0.
   * 
   * 2. Check if the current Number is greater than perfectSquareNumber in the loop
   * Potential IndexOutOfBound
   * 
   * 3. Put square number (i * i) instead of the number i into the list.
   * 
   * Time: O(N)
   * Space: O(Constant)
   * 
   * @param n
   * @return
   */
  public static int numSquares(int n) {
    final List<Integer> perfectSquareNumbers = constructPerfectSquareNumbers(n);
    final int[] numSquaresArray = new int[n + 1];
    Arrays.fill(numSquaresArray, Integer.MAX_VALUE);
    numSquaresArray[0] = 0;

    /* NumSquares[n] = min(minNumSquares[n - Si] + 1) Si is any Squares less than n */
    for (int i = 1; i <= n; i++) {
      for (final Integer perfectSquareNumber : perfectSquareNumbers) {
        if (perfectSquareNumber <= i) {
          numSquaresArray[i] = Math.min(numSquaresArray[i], numSquaresArray[i - perfectSquareNumber] + 1);
        }
      }
    }

    return numSquaresArray[n];
  }

  private static List<Integer> constructPerfectSquareNumbers(final int n) {
    final List<Integer> perfectSquareNumbers = new ArrayList<>();

    for (int i = 1; i * i <= n; i++) {
      perfectSquareNumbers.add(i * i);
    }

    return perfectSquareNumbers;
  }

//  /**
//   * Cleaner Version
//   * 
//   * @param n
//   * @return
//   */
//  public static int numSquares(int n) {
//    final int[] numSquaresArray = new int[n + 1];
//    numSquaresArray[0] = 0;
//
//    /* NumSquares[n] = min(minNumSquares[n - Si] + 1) Si is any Squares less than n */
//    for (int i = 1; i <= n; i++) {
//      int min = Integer.MAX_VALUE;
//
//      for (int j = 1; j * j <= i; j++) {
//        min = Math.min(min, numSquaresArray[i - j * j] + 1);
//      }
//
//      numSquaresArray[i] = min;
//    }
//
//    return numSquaresArray[n];
//  }

  public static void main(String[] args) {
    assert(numSquares(12) == 3);
    assert(numSquares(13) == 2);
  }
}
