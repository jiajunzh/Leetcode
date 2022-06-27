package problem;

/**
 * 1. Problem 
 * You are given two 0-indexed integer arrays nums1 and nums2, both of length n.
 *
 * You can choose two integers left and right where 0 <= left <= right < n and swap the subarray nums1[left...right] 
 * with the subarray nums2[left...right].
 *
 * For example, if nums1 = [1,2,3,4,5] and nums2 = [11,12,13,14,15] and you choose left = 1 and right = 2, nums1 becomes 
 * [1,12,13,4,5] and nums2 becomes [11,2,3,14,15].
 * You may choose to apply the mentioned operation once or not do anything.
 *
 * The score of the arrays is the maximum of sum(nums1) and sum(nums2), where sum(arr) is the sum of all the elements 
 * in the array arr.
 *
 * Return the maximum possible score.
 *
 * A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains 
 * the elements of nums between indices left and right (inclusive).
 *
 * 2. Examples
 * Example 1
 * Input: nums1 = [60,60,60], nums2 = [10,90,10]
 * Output: 210
 * Explanation: Choosing left = 1 and right = 1, we have nums1 = [60,90,60] and nums2 = [10,60,10].
 * The score is max(sum(nums1), sum(nums2)) = max(210, 80) = 210.
 * 
 * Example 2
 * Input: nums1 = [20,40,20,70,30], nums2 = [50,20,50,40,20]
 * Output: 220
 * Explanation: Choosing left = 3, right = 4, we have nums1 = [20,40,20,40,20] and nums2 = [50,20,50,70,30].
 * The score is max(sum(nums1), sum(nums2)) = max(140, 220) = 220.
 * 
 * Example 3
 * Input: nums1 = [7,11,13], nums2 = [1,1,1]
 * Output: 31
 * Explanation: We choose not to swap any subarray.
 * The score is max(sum(nums1), sum(nums2)) = max(31, 3) = 31.
 *
 * 3. Constraints
 * n == nums1.length == nums2.length
 * 1 <= n <= 105
 * 1 <= nums1[i], nums2[i] <= 104
 */
public class MaximumScoreOfSplicedArray {

  /**
   * 1. Approach 
   * DP/Kadane's Algorithm.
   * 
   * Suppose we want to swap the subarray [left:right], SubSum1 = Sum of Array1[left:right], SubSum2 = Sum of Array2[left:right]
   * Then the sum of each array after swapping is:
   * 
   * NewSum1 = OldSum1 - SubSum1 + SubSum2 => To maximize NewSum1, we need to minimize SubSum1 - SubSum2
   * NewSum2 = OldSum2 - SubSum2 + SubSum1 => To maximize NewSum2, we need to maximize SubSum1 - SubSum2
   * 
   * Now if we have an array where array[i] = nums1[i] - nums2[i]. Maximizing/Minimizing (SubSum1 - SubSum2) becomes
   * solving the maximum subarray sum using Kadane's algorithm.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param nums1
   * @param nums2
   * @return
   */
  public int maximumsSplicedArray(int[] nums1, int[] nums2) {
    int sum1 = 0, sum2 = 0, n = nums1.length;
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    int currMaxSum = 0;
    int currMinSum = 0;
    for (int i = 0; i < n; i++) {
      sum1 += nums1[i];
      sum2 += nums2[i];
      int diff = nums1[i] - nums2[i];
      currMaxSum = Math.max(currMaxSum, 0) + diff;
      max = Math.max(max, currMaxSum);
      currMinSum = Math.min(currMinSum, 0) + diff;
      min = Math.min(min, currMinSum);
    }
    return Math.max(sum2 + max, sum1 - min);
  }
}
