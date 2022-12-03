package problem;

/**
 * 1. Problem 
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at 
 * nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 *
 * 2. Examples
 * Example 1
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * Example 2
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 * 3. Constraints
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 */
public class JumpGameII {

  /**
   * 1. Approach 
   * Greedy. This approach considers a group of indexes that could be reached with same amount of steps as one. 
   * For example, if we have 2,3,3,1,1,4,5, this array could be separated into four groups 
   * 2 | 3,3 | 1,1,4 | 5
   * 
   * - group 1: start point 
   * - group 2: could be reached from start within 1 step
   * - group 3: could be reached with an extra step on top of group 1. In total, 2 steps from start
   * - group 4: could be reached with an extra step on top of group 2. In total, 3 steps from start
   * 
   * Based on this we could keep track of three things altogether:
   * - currMax: the maximum index that could be reached with an extra step on top of any index in the current group
   * - nextIndex: The next upper bound on index that could be reached with an additional step
   * - steps: number of steps taken so far.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param nums
   * @return
   */
  public int jump(int[] nums) {
    int currMax = 0, nextIndex = 0, steps = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      currMax = Math.max(currMax, nums[i] + i);
      if (i == nextIndex) {
        steps++;
        nextIndex = currMax;
      }
    }
    return steps;
  }
}
