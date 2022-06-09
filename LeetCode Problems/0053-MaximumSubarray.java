package problem;

/**
 * 1. Problem
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum 
 * and return its sum.
 *
 * A subarray is a contiguous part of an array.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * 
 * Example 2
 * Input: nums = [1]
 * Output: 1
 * 
 * Example 3
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {

  /**
   * 1. Approach 
   * Dynamic Programming - Kadane's algorithm.
   * 
   * Define dp[i] as the maximum subarray ending at position i.
   * For each position i
   * 1) Previous sum is less than 0, meaning that the previous subarray could be dropped. The new subarray could be 
   * sumed and refreshed to 0 and start from position i again.
   * 2) Previous sum is equal to or greater than 0, meaning the previous subarray could be combined with the current 
   * number together to build a possibly larger sum.
   *
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param nums
   * @return
   */
  public int maxSubArrayDp(int[] nums) {
    int current = nums[0];
    int max = nums[0];

    for (int i = 1; i < nums.length; i++) {
      if (current < 0) {
        current = 0;
      }
      current += nums[i];
      max = Math.max(current, max);
    }

    return max;
  }

  /**
   * 1. Approach 
   * Divide and Conquer. 
   * 
   * The whole array could be separated in the middle into two subarrays as below.
   * => leftSubarray | middlePoint | rightSubarray
   * 
   * The maximum subarray sum will be either:
   * 1) max sum of left subarray that ends at middlePoint - 1 + middlePoint + max sum of right subarray that starts at middlePoint + 1
   * 2) max sum of left subarray (no need to end at middlePoint - 1)
   * 3) max sum of right subarray (no need to start at middlePoint + 1)
   * 
   * 2. Complexity 
   * - Time O(NlogN)
   * - Space O(logN)
   * 
   * @param nums
   * @return
   */
  public int maxSubArrayDivideConquer(int[] nums) {
    return maxSubArray(nums, 0, nums.length - 1);
  }

  private int maxSubArray(int[] nums, int start, int end) {
    if (end < start) {
      return Integer.MIN_VALUE;
    }
    int mid = start + (end - start) / 2;

    int sum = 0;
    int leftMaxSum = 0;
    for (int i = mid - 1; i >= start; i--) {
      sum += nums[i];
      leftMaxSum = Math.max(leftMaxSum, sum);
    }

    sum = 0;
    int rightMaxSum = 0;
    for (int i = mid + 1; i <= end; i++) {
      sum += nums[i];
      rightMaxSum = Math.max(rightMaxSum, sum);
    }

    int midMax = nums[mid] + leftMaxSum + rightMaxSum;
    int leftMax = maxSubArray(nums, start, mid - 1);
    int rightMax = maxSubArray(nums, mid + 1, end);

    return Math.max(midMax, Math.max(leftMax, rightMax));
  }
}
