package problem;

/**
 * 1. Problem
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by
 * the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or
 * dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the
 * following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population.
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births
 * and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 *
 * 2. Examples
 * Example 1
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 *
 * Example 2
 * Input: board = [[1,1],[1,0]]
 * Output: [[1,1],[1,1]]
 *
 * 3. Constraints
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j] is 0 or 1.
 *
 * 4. Follow up
 * - Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some
 * cells first and then use their updated values to update other cells.
 * - In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause
 * problems when the active area encroaches upon the border of the array (i.e., live cells reach the border). How
 * would you address these problems?
 */
public class GameOfLife {
    private static final int[][] DIRS = new int[][]{
        {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };

    /**
     * 1. Approach
     * In Place with Signification.
     *
     * 2. Complexity
     * - Time O(M * N)
     * - Space O(1)
     *
     * 3. Alternative
     * - Another way that is similar to this but easier to understand is we could use signification plus value to represent
     *   the only 4 states we might have:
     *   - Previously dead but now live => 2
     *   - Previously dead and now dead => -2
     *   - Previously live but now dead => -1
     *   - Previously live and now live => 1
     *
     * 4. Follow-up
     * The second follow-up brings up the scalability problem with specifically:
     * - Computationally impossible to iterate a matrix
     * - Memory insufficient to load the whole matrix
     *  - Load only three rows each time from file into memory and discard the first row each time loading a new row
     * - Memory waste if the matrix is sparse (with only a few live cells)
     *  - Instead of iterating each cell and determine its next state by looking at its neighbors, just store the location
     *    of cells that are alive and update its neighbor with four rules.
     *
     * @param board
     */
    public void gameOfLife(int[][] board) {
        final int n = board.length, m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0) {
                    for (int[] dir : DIRS) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && x < n && y >= 0 && y < m) {
                            if (board[x][y] > 0) board[x][y]++;
                            else board[x][y]--;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = board[i][j] == 3 || board[i][j] == 4 ? 1 : 0;
                } else {
                    board[i][j] = board[i][j] == -3 ? 1 : 0;
                }
            }
        }
    }
}
