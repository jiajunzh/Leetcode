package problem;

/**
 * 1. Problem
 * You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays 
 * are 1-indexed.
 *
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
 *
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Remove x from the array nums.
 * Return the maximum score after performing m operations.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 * 
 * Example 2
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score. 
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 *
 * 3. Constraints
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 103
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 */
public class MaximumScoreFromPerformingMultiplicationOperations {

  /**
   * 1. Approach 
   * Memoization/Top Down. One inspiration for memoization is to notice that there is recalculation in brutal force 
   * approach. For example, in brutal force, if we have nums = [A1, A2, A3, A4] and multipliers = [M1, M2, M3]
   * Then there will be 2^m = 2^3 = 8 possibilities.
   * A1 * M1 + maximumScore([A2, A3, A4], [M2, M3]) 
   *    => A1 * M1 + A2 * M2 + maximumScore([A3, A4], [M3]) 
   *    => OR A1 * M1 + A4 * M2 + maximumScore([A2, A3], [M3])
   *    
   * A4 * M1 + maximumScore([A1, A2, A3], [M2, M3])
   *    => A4 * M1 + A1 * M2 + maximumScore([A2, A3], [M3]) 
   *    => OR A4 * M1 + A3 * M2 + maximumScore([A1, A2], [M3]) 
   * 
   * Notice in here maximumScore([A2, A3], [M3]) was calculated twice, which is redundant. Thus, memoization will keep
   * track of results has been calculated and reduce the time complexity.
   * 
   * Define memo[i][j] as the maximum score of array starting from i to nums.length - 1 - j with multipliers[i + j + 1, m]
   * 
   * 2. Complexity
   * - Time O(M^2)
   * - Space O(M^2)
   * 
   * 3. Improvement
   * - As the sum of the number of elements taken from the left and elements taken from the right will be exactly equal
   *   to the number of multipliers used. There is a relationship between start, end and index. The dp array could be 
   *   modified to index on start and index instead of start and end.
   * 
   * @param nums
   * @param multipliers
   * @return
   */
  public int maximumScore1(int[] nums, int[] multipliers) {
    final Integer[][] memo = new Integer[multipliers.length][multipliers.length];
    return maximumScore(nums, 0, nums.length - 1, multipliers, 0, memo);
  }

  private int maximumScore(int[] nums, int start, int end, int[] multipliers, int index, Integer[][] memo) {
    if (index == multipliers.length) {
      return 0;
    }

    if (memo[start][nums.length - 1 - end] != null) {
      return memo[start][nums.length - 1 - end];
    }

    int leftTakenScore = nums[start] * multipliers[index] + maximumScore(nums, start + 1, end, multipliers, index + 1, memo);
    int rightTakenScore = nums[end] * multipliers[index] + maximumScore(nums, start, end - 1, multipliers, index + 1, memo);

    memo[start][nums.length - 1 - end] = Math.max(leftTakenScore, rightTakenScore);

    return memo[start][nums.length - 1 - end];
  }

  /**
   * 1. Approach 
   * Dynamic Programming.
   * Define dp[i][j] as the maximum score with i elements taken to the left and j elements taken to the right.
   * i and j could be anything in [0, m]. The current index of multipliers will be i + j - 1.
   * 
   * dp[i][j] = Max(dp[i - 1][j] + multipliers[i + j - 1] * nums[i - 1], dp[i][j - 1] + multipliers[i + j - 1] * nums[n - j])
   * 
   * 2. Complexity 
   * - Time O(M^2)
   * - Space O(M^2)
   * 
   * 3. Improvement
   * - Remember when i = 0 or j = 0 meaning 0 element is taken to the left or right. In this case, there will be no 
   *   element could be used to calculate the score on its side, then this side should be ignored (Integer.MIN_VALUE)
   * 
   * @param nums
   * @param multipliers
   * @return
   */
  public int maximumScore(int[] nums, int[] multipliers) {
    int n = nums.length;
    int m = multipliers.length;
    int[][] dp = new int[m + 1][m + 1];
    int ans = Integer.MIN_VALUE;

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= m - i; j++) {
        if (i + j > 0) {
          int left = i > 0 ? dp[i - 1][j] + nums[i - 1] * multipliers[i + j - 1] : Integer.MIN_VALUE;
          int right = j > 0 ? dp[i][j - 1] + nums[n - j] * multipliers[i + j - 1] : Integer.MIN_VALUE;

          dp[i][j] = Math.max(left, right);
        }

        if (i + j == m) {
          ans = Math.max(ans, dp[i][j]);
        }
      }
    }

    return ans;
  }
}
