package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following 
 * rules:
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *
 * 2. Examples 
 * Example 1
 * Input: board = 
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 * 
 * Example 2
 * Input: board = 
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's 
 * in the top left 3x3 sub-box, it is invalid.
 *
 * 3. Constraints
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 */
public class ValidSudoku {

  /**
   * 1. Approach 
   * Array + boolean map
   * 
   * 2. Complexity 
   * - Time O(N^2)
   * - Space O(N)
   * 
   * @param board
   * @return
   */
  public boolean isValidSudoku(char[][] board) {
    final boolean[] visited = new boolean[9];
    for (int i = 0; i < 9; i++) {
      Arrays.fill(visited, false);
      for (int j = 0; j < 9; j++) {
        final char num = board[i][j];
        if (num == '.') continue;
        if (visited[num - '1']) return false;
        visited[num - '1'] = true;
      }
    }
    for (int i = 0; i < 9; i++) {
      Arrays.fill(visited, false);
      for (int j = 0; j < 9; j++) {
        final char num = board[j][i];
        if (num == '.') continue;
        if (visited[num - '1']) return false;
        visited[num - '1'] = true;
      }
    }
    for (int i = 0; i < 9; i += 3) {
      for (int j = 0; j < 9; j += 3) {
        Arrays.fill(visited, false);
        for (int ii = i; ii < i + 3; ii++) {
          for (int jj = j; jj < j + 3; jj++) {
            final char num = board[ii][jj];
            if (num == '.') continue;
            if (visited[num - '1']) return false;
            visited[num - '1'] = true;
          }
        }
      }
    }
    return true;
  }
}
