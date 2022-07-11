package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * You are given an integer array nums. In one move, you can choose one element of nums and change it by any value.
 *
 * Return the minimum difference between the largest and smallest value of nums after performing at most three moves.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [5,3,2,4]
 * Output: 0
 * Explanation: Change the array [5,3,2,4] to [2,2,2,2].
 * The difference between the maximum and minimum is 2-2 = 0.
 * 
 * Example 2
 * Input: nums = [1,5,0,10,14]
 * Output: 1
 * Explanation: Change the array [1,5,0,10,14] to [1,1,0,1,1]. 
 * The difference between the maximum and minimum is 1-0 = 1.
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class MinimumDifferenceBetweenLargestAndSmallestValueInThreeMoves {

  /**
   * 1. Approach 
   * Array Sorting 
   * 
   * 2. Complexity 
   * - Time O(NlogN)
   * - Space O(1)
   * 
   * 3. Improvement 
   * - An optimization we could do for k = 3 is to maintain two arrays of size 4 as we either remove all maximum three or
   *   minimum three in extreme case. Then sort two arrays to iterate each to check whether to remove the current number 
   *   or not. This improve the time from O(NlogN) to O(4log4 + N). 
   *   See https://leetcode.com/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves/discuss/1784546/Java-O(n)-solution-O(1)-space-with-thinking-process.
   *   
   * @param nums
   * @return
   */
  public int minDifference(int[] nums) {
    final int n = nums.length;
    if (n < 5) return 0;
    Arrays.sort(nums);
    int minDiff = Integer.MAX_VALUE;
    for (int left = 0; left <= 3; left++) {
      minDiff = Math.min(nums[n - 4 + left] - nums[left], minDiff);
    }
    return minDiff;
  }
}
