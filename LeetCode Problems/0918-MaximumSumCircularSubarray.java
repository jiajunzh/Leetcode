package problem;

/**
 * 1. Problem 
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 *
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of 
 * nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 *
 * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], 
 * nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3.
 * 
 * Example 2
 * Input: nums = [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
 * 
 * Example 3
 * Input: nums = [-3,-2,-3]
 * Output: -2
 * Explanation: Subarray [-2] has maximum sum -2.
 *
 * 3. Constraints
 * n == nums.length
 * 1 <= n <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104
 */
public class MaximumSumCircularSubarray {

  /**
   * 1. Approach 
   * Dynamic Programming (Kadane's Algorithm). Kadane's algorithm is used to calculate the maximum subarray sum, but 
   * also the minimum subarray. Imagine we have the result subarray, it could be either one case of the below two cases:
   * 1) The subarray starts from i and end at j where i <= j.
   * 2) The subarray starts from i and end at j where i > j, then the subarray will be [0,...,j,...,i,...N-1]
   * In this case the Max(SubarraySum) = Sum(nums) - Min(Sum(j+1, i-1)) = Sum(nums) - Min(SubarraySum) 
   *
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * 3. Mistakes 
   * - One mistake is that the result subarray could not be an empty array, where sum will be equal to the minimum value 
   *   of subarray.
   * 
   * @param nums
   * @return
   */
  public int maxSubarraySumCircular(int[] nums) {
    int sum = 0, currMax = 0, currMin = 0;
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    for (int num : nums) {
      sum += num;
      currMax = Math.max(currMax, 0) + num;
      max = Math.max(currMax, max);
      currMin = Math.min(currMin, 0) + num;
      min = Math.min(currMin, min);
    }

    if (min == sum) {
      return max;
    }
    return Math.max(max, sum - min);
  }
}
