package problem;

/**
 * 1. Problem
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money 
 * stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last 
 * one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if 
 * two adjacent houses were broken into on the same night. Given an integer array nums representing the amount of money
 * of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * 2. Example
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Input: nums = [1,2,3]
 * Output: 3
 *
 * 3. Constraints
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class HouseRobberII {

  /**
   * 1. Approach
   * Dynamic Programming. As the houses are rotated, the answer could be resolved by diving the array into two cases.
   * One is to rob the house 0 (meaning house n - 1 could not be robbed) => RobHousesWithoutRotation(0, n - 2).
   * The other is to rob the house n - 1 (meaning house 0 could not be robbed) => RobHousesWithoutRotation(1, n - 1).
   * 
   * 2. Complexity
   * Time O(N)
   * Space O(C)
   * 
   * @param nums
   * @return
   */
  public int rob(int[] nums) {
    final int n = nums.length;

    if (n == 1) {
      return nums[0];
    }

    return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
  }

  private int rob(int[] nums, int start, int end) {
    int length = end - start + 1;

    if (length < 1) {
      return 0;
    }

    if (length == 1) {
      return nums[start];
    }

    int dp1 = nums[start];
    int dp2 = Math.max(nums[start], nums[start + 1]);

    for (int i = start + 2; i <= end; i++) {
      int temp = dp2;
      dp2 = Math.max(dp2, dp1 + nums[i]);
      dp1 = temp;
    }

    return dp2;
  }
}
