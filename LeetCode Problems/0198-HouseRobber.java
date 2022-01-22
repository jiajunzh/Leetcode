package problem;

/**
 * 1. Problem 
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money 
 * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems 
 * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you 
 * can rob tonight without alerting the police.
 *
 * 2. Example
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 * 3. Constraints
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class HouseRobber {
  
  /**
   * 1. Problem 
   * Dynamic Programming 
   * dp[i] defines the maximum amount of money you can rob without alerting the police.
   * dp[i] = Max(dp[i - 2] + nums[i], dp[i - 1]);
   * 
   * 2. Complexity 
   * Time O(N)
   * Space O(C)
   *
   * 3. Mistake/Improvement 
   * - Space Optimization: use two variable instead of having a dp array
   */
  public int rob(int[] nums) {
    final int n = nums.length;

    if (n == 1) {
      return nums[0];
    }

    int dp1 = nums[0];
    int dp2 = Math.max(nums[1], nums[0]);

    for (int i = 2; i < n; i++) {
      int temp = dp2;
      dp2 = Math.max(dp1 + nums[i], dp2);
      dp1 = temp;
    }

    return dp2;
  }
}
