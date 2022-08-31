package problem;

/**
 * 1. Problem 
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * 2. Examples
 * Example 1
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * 
 * Example 2
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * 
 * Example 3
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 * 3. Constraints
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 *
 * 4. Follow up
 * Could you use search pruning to make your solution faster with a larger board?
 */
public class WordSearch {

  private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  /**
   * 1. Approach 
   * Backtracking 
   * 
   * 2. Complexity 
   * - Time O(M * N * 3^S) Initially we have 4 directions to explore but later only 3 for each element
   * - Space O(S)
   * 
   * @param board
   * @param word
   * @return
   */
  public boolean exist(char[][] board, String word) {
    final int n = board.length, m = board[0].length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (exist(board, n, m, word, 0, i, j)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean exist(char[][] board, int n, int m, String word, int index, int i, int j) {
    if (index == word.length()) {
      return true;
    }
    if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] != word.charAt(index)) {
      return false;
    }
    final char c = board[i][j];
    board[i][j] = '-';
    for (int[] dir : DIRS) {
      if (exist(board, n, m, word, index + 1, i + dir[0], j + dir[1])) {
        return true;
      }
    }
    board[i][j] = c;
    return false;
  }
}
