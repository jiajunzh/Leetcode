package problem;

/**
 * 1. Problem 
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 *
 * 2. Examples 
 * Example 1
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 * 
 * Example 2
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 *
 * 3. Constraints
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 */
public class MaximumLengthOfRepeatedSubarray {

  /**
   * 1. Approach 
   * Dynamic Programming.
   * 
   * Define dp[i][j] as the maximum length of repeated subarray ending at i in nums1 and j in nums2.
   * if nums[i] != nums[j] => dp[i][j] = 0
   * if nums[i] == nums[j] => dp[i][j] = dp[i - 1][j - 1] + 1
   * 
   * 2. Complexity
   * - Time O(M * N)
   * - Space O(M * N) 
   * 
   * 3. Alternatives 
   * - Sliding Window: https://leetcode.com/problems/maximum-length-of-repeated-subarray/discuss/109059/O(mn)-time-O(1)-space-solution
   *   [1,2,3,2,1]   --> 
   *       <--    [3,2,1,4,7]  
   *       
   *   [1,2,3,2,1]   --> 
   *     <--   [3,2,1,4,7]  
   *
   *   [1,2,3,2,1]   --> 
   *   <--   [3,2,1,4,7]   
   *
   *   [1,2,3,2,1]   --> 
   * <--   [3,2,1,4,7]   
   * 
   * @param nums1
   * @param nums2
   * @return
   */
  public int findLength(int[] nums1, int[] nums2) {
    final int n = nums1.length, m = nums2.length;
    final int[][] dp = new int[n + 1][m + 1];
    int maxLength = 0;
    
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (nums1[i - 1] == nums2[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
          maxLength = Math.max(maxLength, dp[i][j]);
        }
      }
    }
    
    return maxLength;
  }
}
