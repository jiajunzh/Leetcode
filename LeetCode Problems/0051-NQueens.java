package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a 
 * queen and an empty space, respectively.
 *
 * 2. Examples
 * Example 1
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * 
 * Example 2
 * Input: n = 1
 * Output: [["Q"]]
 *
 * 3. Constraints:
 * 1 <= n <= 9
 */
public class NQueens {

  /**
   * 1. Approach 
   * Backtracking. It means that no two queen could exist in the same row, column and diagonal and anti-diagonal by 
   * saying no two queens attack each other. That is to say, there could be only one queen in each row. We could 
   * recursively place queen row by row and try out each position in the row in each recursion.
   * 
   * 2. Complexity
   * - Time O(N!) 
   * - Space O(N)
   * 
   * 3. Improvement
   * - The initial version check each row, col, diagonal for each recursion from start to the current position to see if
   *   there exist any duplicate. However, this could be tracked by three arrays and there is no need to check from 
   *   start position.
   * 
   * @param n
   * @return
   */
  public List<List<String>> solveNQueens(int n) {
    final List<List<String>> result = new ArrayList<>();
    char[][] chessBoard = new char[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        chessBoard[i][j] = '.';
      }
    }
    boolean[] col = new boolean[n];
    boolean[] diag = new boolean[2 * n - 1];
    boolean[] antiDiag = new boolean[2 * n - 1];
    backtrack(chessBoard, 0, result, col, diag, antiDiag, n);
    return result;
  }

  private void backtrack(char[][] chessBoard, int currPos, List<List<String>> result, boolean[] col, boolean[] diag, boolean[] antiDiag, int n) {
    if (currPos == chessBoard.length) {
      final List<String> solution = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        solution.add(String.valueOf(chessBoard[i]));
      }
      result.add(solution);
      return;
    }

    for (int i = 0; i < chessBoard.length; i++) {
      if (!col[i] && !diag[currPos - i + n - 1] && !antiDiag[currPos + i]) {
        col[i] = true;
        diag[currPos - i + n - 1] = true;
        antiDiag[currPos + i] = true;
        chessBoard[currPos][i] = 'Q';

        backtrack(chessBoard, currPos + 1, result, col, diag, antiDiag, n);

        chessBoard[currPos][i] = '.';
        diag[currPos - i + n - 1] = false;
        antiDiag[currPos + i] = false;
        col[i] = false;
      }
    }
  }
}
