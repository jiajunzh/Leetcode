package problem;

/**
 * 1. Problem 
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 *
 * 2. Examples
 * Example 1
 * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * Explanation: The input board is shown above and the only valid solution is shown below:
 *
 * 3. Constraints
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 */
public class SudokuSolver {

  private static final int N = 9;
  private final boolean[][] rows = new boolean[N][N + 1];
  private final boolean[][] cols = new boolean[N][N + 1];
  private final boolean[][] boxes = new boolean[N][N + 1];

  /**
   * 1. Approach 
   * Backtracking 
   * 
   * 2. Complexity
   * - Time O((N!)^N)
   * - Space O(N^2)
   * 
   * @param board
   */
  public void solveSudoku(char[][] board) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (board[i][j] != '.') {
          int num = board[i][j] - '0';
          placeNumber(board, num, i, j);
        }
      }
    }
    solveSudokuHelper(board, 0, 0);
  }

  private boolean solveSudokuHelper(char[][] board, int i, int j) {
    if (i == N) return true;
    int nextIndex = i * N + j + 1;
    if (board[i][j] == '.') {
      for (int num = 1; num <= N; num++) {
        int boxId = (i / 3) * 3 + (j / 3);
        if (!rows[i][num] && !cols[j][num] && !boxes[boxId][num]) {
          placeNumber(board, num, i, j);
          if (solveSudokuHelper(board, nextIndex / N, nextIndex % N)) {
            return true;
          }
          removeNumber(board, num, i, j);
        }
      }
    } else {
      if (solveSudokuHelper(board, nextIndex / N, nextIndex % N)) {
        return true;
      }
    }
    return false;
  }

  private void placeNumber(char[][] board, int num, int i, int j) {
    int boxId = (i / 3) * 3 + (j / 3);
    rows[i][num] = true;
    cols[j][num] = true;
    boxes[boxId][num] = true;
    board[i][j] = (char) ('0' + num);
  }

  private void removeNumber(char[][] board, int num, int i, int j) {
    int boxId = (i / 3) * 3 + (j / 3);
    board[i][j] = '.';
    rows[i][num] = false;
    cols[j][num] = false;
    boxes[boxId][num] = false;
  }
}
