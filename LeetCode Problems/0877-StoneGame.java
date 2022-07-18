package problem;

/**
 * 1. Problem 
 * Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile 
 * has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, 
 * so there are no ties.
 *
 * Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from
 * the beginning or from the end of the row. This continues until there are no more piles left, at which point the 
 * person with the most stones wins.
 *
 * Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.
 *
 * 2. Examples 
 * Example 1
 * Input: piles = [5,3,4,5]
 * Output: true
 * Explanation: 
 * Alice starts first, and can only take the first 5 or the last 5.
 * Say she takes the first 5, so that the row becomes [3, 4, 5].
 * If Bob takes 3, then the board is [4, 5], and Alice takes 5 to win with 10 points.
 * If Bob takes the last 5, then the board is [3, 4], and Alice takes 4 to win with 9 points.
 * This demonstrated that taking the first 5 was a winning move for Alice, so we return true.
 * 
 * Example 2
 * Input: piles = [3,7,2,3]
 * Output: true
 *
 * 3. Constraints
 * 2 <= piles.length <= 500
 * piles.length is even.
 * 1 <= piles[i] <= 500
 * sum(piles[i]) is odd.
 */
public class StoneGame {
  
  private int[][] memo;

  /**
   * 1. Approach 
   * Memoization + MinMax.
   * 
   * Define a relative score which will increase by piles[i] if it is Alice turn otherwise decrease by piles[i]. 
   * Then score(i, j) = max(score(i + 1, j) + piles[i], score(i, j - 1) + piles[j])
   * 
   * 2. Complexity
   * - Time O(N^2)
   * - Space O(N^2)
   * 
   * @param piles
   * @return
   */
  public boolean stoneGame1(int[] piles) {
    memo = new int[piles.length][piles.length];
    return score(piles, 0, piles.length - 1) > 0;
  }

  private int score(int[] piles, int start, int end) {
    if (start > end) return 0;
    if (start == end) return piles[start];
    if (memo[start][end] != 0) return memo[start][end];
    memo[start][end] = Math.max(piles[start] - score(piles, start + 1, end), piles[end] - score(piles, start, end - 1));
    return memo[start][end];
  }

  /**
   * 1. Approach 
   * 2D Dynamic Programming + MinMax
   * 
   * 2. Complexity 
   * - Time O(N^2)
   * - Space O(N^2)
   * 
   * @param piles
   * @return
   */
  public boolean stoneGame2(int[] piles) {
    final int n = piles.length;
    final int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++) {
      dp[i][i] = piles[i];
    }
    for (int size = 2; size <= n; size++) {
      for (int i = 0; i + size <= n; i++) {
        int j = i + size - 1;
        dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
      }
    }
    return dp[0][n - 1] > 0;
  }

  /**
   * 1. Approach 
   * 1D Dynamic Programming + MinMax
   *
   * 2. Complexity 
   * - Time O(N^2)
   * - Space O(N)
   *
   * @param piles
   * @return
   */
  public boolean stoneGame3(int[] piles) {
    final int n = piles.length;
    final int[] dp = new int[n];
    for (int i = 0; i < n; i++) {
      dp[i] = piles[i];
    }
    for (int size = 2; size <= n; size++) {
      for (int i = 0; i + size <= n; i++) {
        dp[i] = Math.max(piles[i] - dp[i + 1], piles[i + size - 1] - dp[i]);
      }
    }
    return dp[0] > 0;
  }
}
