package problem;

import java.util.Arrays;

/**
 * 1. Problem
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and 
 * then concatenate all the integers.
 *
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the 
 * expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 *
 * Example 2
 * Input: nums = [1], target = 1
 * Output: 1
 *
 * 3. Constraints
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class TargetSum {
  
  private int[][] memo;
  private int total = 0;

  /**
   * 1. Approach 
   * Recursion + Memoization => also could be solved by DP
   * 
   * 2. Complexity 
   * - Time O(T * N)
   * - Space O(T * N)
   * 
   * @param nums
   * @param target
   * @return
   */
  public int findTargetSumWays(int[] nums, int target) {
    for (int num : nums) total += num;
    this.memo = new int[nums.length][2 * total + 1];
    for (int[] row : memo) {
      Arrays.fill(row, Integer.MIN_VALUE);
    }
    return findTargetSumWays(nums, 0, 0, target);
  }

  public int findTargetSumWays(int[] nums, int index, int sum, int target) {
    if (index == nums.length) {
      return target == sum ? 1 : 0;
    }
    if (this.memo[index][sum + total] != Integer.MIN_VALUE) {
      return this.memo[index][sum + total];
    }
    int count = 0;
    count += findTargetSumWays(nums, index + 1, sum + nums[index], target);
    count += findTargetSumWays(nums, index + 1, sum - nums[index], target);
    this.memo[index][sum + total] = count;
    return count;
  }
}
