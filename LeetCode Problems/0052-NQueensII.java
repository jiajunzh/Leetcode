package problem;

/**
 * 1. Problem 
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * 2. Examples
 * Example 1
 * Input: n = 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 * 
 * Example 2
 * Input: n = 1
 * Output: 1
 *
 * 3. Constraints
 * 1 <= n <= 9
 */
public class NQueensII {

  /**
   * 1. Approach 
   * Backtracking. Same problem as 51.
   * 2. Complexity
   * Same as problem 51.
   * 
   * @param n
   * @return
   */
  public int totalNQueens(int n) {
    return backtrack(n, 0, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1]);
  }

  private int backtrack(int n, int currIndex, boolean[] col, boolean[] diag, boolean[] antiDiag) {
    if (currIndex == n) {
      return 1;
    }

    int solution = 0;
    for (int i = 0; i < n; i++) {
      if (!col[i] && !diag[n - 1 + currIndex - i] && !antiDiag[currIndex + i]) {
        col[i] = true;
        diag[n - 1 + currIndex - i] = true;
        antiDiag[currIndex + i] = true;
        solution += backtrack(n, currIndex + 1, col, diag, antiDiag);
        col[i] = false;
        diag[n - 1 + currIndex - i] = false;
        antiDiag[currIndex + i] = false;
      }
    }
    return solution;
  }
}
