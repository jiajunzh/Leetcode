package problem;

/**
 * 1. Problem 
 * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m 
 * non-empty continuous subarrays.
 *
 * Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [7,2,5,10,8], m = 2
 * Output: 18
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 * 
 * Example 2
 * Input: nums = [1,2,3,4,5], m = 2
 * Output: 9
 * 
 * Example 3
 * Input: nums = [1,4,4], m = 3
 * Output: 4
 *
 * 3. Constraints
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= m <= min(50, nums.length)
 */
public class SplitArrayLargestSum {

  /**
   * 1. Approach 
   * Dynamic Programming. 
   * 
   * Define dp[i][j] as the minimum largest sum when splitting array of size j into i arrays.
   * dp[i][j] = Min(Max(dp[i - 1][k], Sum[k + 1 : j])) where k in [i - 1 : j - 1]
   * 
   * Base Case when m = 1 (i = 1)
   * dp[i][j] = prefixSum[j]
   *
   * 2. Complexity 
   * - Time O(N * N * M) ~ 5 x 10^7 
   * - Space O(N * M)
   * 
   * @param nums
   * @param m
   * @return
   */
  public int splitArray1(int[] nums, int m) {
    final int n = nums.length;
    final int[] prefixSum = new int[n + 1];
    final int[][] dp = new int[m + 1][n + 1];
    for (int i = 1; i <= n; i++) {
      prefixSum[i] = nums[i - 1] + prefixSum[i - 1];
      dp[1][i] = prefixSum[i];
    }
    for (int i = 2; i <= m; i++) {
      for (int j = i; j <= n; j++) {
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = i - 1; k < j; k++) {
          dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][k], prefixSum[j] - prefixSum[k]));
        }
      }
    }
    return dp[m][n];
  }

  /**
   * 1. Approach 
   * Binary Search. Find the minimum largest sum k such that all subarray sum <= k.
   * 
   * 2. Complexity 
   * - Time O(N * logSum)
   * - Space O(1)
   * 
   * @param nums
   * @param m
   * @return
   */
  public int splitArray2(int[] nums, int m) {
    int sum = 0;
    for (int num : nums) {
      sum += num;
    }
    int low = 0, high = sum;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (splitable(nums, m, mid)) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return high;
  }

  private boolean splitable(int[] nums, int m, int targetSum) {
    int remainingSum = targetSum;
    int count = 1;
    for (int num : nums) {
      if (targetSum < num) {
        return false;
      }
      if (remainingSum >= num) {
        remainingSum -= num;
      } else {
        remainingSum = targetSum - num;
        count++;
      }
    }
    return count <= m;
  }
}
