package problem;

/**
 * 1. Problem 
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 *
 * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it 
 * modulo 109 + 7.
 *
 * In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 
 * 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
 *
 * 2. Examples 
 * Example 1
 * Input: n = 3
 * Output: 5
 * Explanation: The five different ways are show above.
 * 
 * Example 2
 * Input: n = 1
 * Output: 1
 *
 * 3. Constraints
 * 1 <= n <= 1000
 */
public class DominoAndTrominoTiling {

  private static final int MOD = 1000000007;

  /**
   * 1. Approach 
   * Dynamic Programming. There are four types of tiles we could put each time:
   * 1) Two grids aligned horizontally 
   *     * *
   * 2) Two grids aligned vertically 
   *     *
   *     *
   * 3) Three grids with the right grid partially covered 
   *     *              * *
   *     * *     OR     * 
   * 4) Three grids with the left grid partially covered 
   *     *          * *
   *   * *     OR     * 
   * 
   * Now we define two state variables below
   * f[i] - number of tilings used when the board are fully covered.
   * p[i] - number of tilings used when the board are partially covered (to the right)
   * 
   * f[i] = f[i - 1] + f[i - 2] + p[i - 1]
   * f[i - 1] => when ith grid uses type 2) tile
   * f[i - 2] => when ith grid uses type two 1) tiles 
   * p[i - 1] => when ith grid uses type 4) tiles 
   * 
   * 
   * p[i] = p[i - 1] + f[i - 2] * 2
   * p[i - 1] => when ith grid uses 4) tile
   * f[i - 2] => when ith grid uses 3) tile
   *
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * 3. Mistakes 
   * - One combination that could be ignored is as below
   *   * * o o
   *   * x x o
   *   
   * @param n
   * @return
   */
  public int numTilings(int n) {
    if (n <= 2) return n;

    long fPrevious = 1, fCurrent = 2;
    long pCurrent = 2;

    for (int i = 3; i <= n; i++) {
      long tmp = fCurrent;
      fCurrent = (fCurrent + fPrevious + pCurrent) % MOD;
      pCurrent = (pCurrent + fPrevious * 2) % MOD;
      fPrevious = tmp;
    }

    return (int)fCurrent;
  }
}
