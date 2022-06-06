package problem;

/**
 * 1. Problem 
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the 
 * order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * 
 * Example 2
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * 
 * Example 3
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 * 3. Constraints
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 *
 * 4. Follow up
 * Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence {

  /**
   * 1. Approach
   * Dynamic Programming.
   * 
   * Define dp[i] = longest length of subsequence with ending char at i position.
   * dp[i] = Math.max(dp[j] + 1, dp[i]) for each j in [0, i) and nums[i] > nums[j].
   * 
   * 2. Complexity 
   * - Time O(N^2)
   * - Space O(N)
   * 
   * @param nums
   * @return
   */
  public int lengthOfLIS1(int[] nums) {
    final int n = nums.length;
    final int[] dp = new int[n];
    int len = 0;

    for (int i = 0; i < n; i++) {
      int num = nums[i];
      dp[i] = 1;
      for (int j = 0; j < i; j++) {
        if (num > nums[j]) {
          dp[i] = Math.max(dp[j] + 1, dp[i]);
        }
      }
      len = Math.max(len, dp[i]);
    }

    return len;
  }

  /**
   * 1. Approach
   * Dynamic Programming.
   *
   * Define dp[i] as the "BEST" largest ending number in subsequence with length (i + 1).
   * 
   * How to define BEST?
   * Let's consider [1,2,3,8,5,6] and suppose we are currently examining nums[4] = 5. There are two possible 
   * subsequence [1,2,3,8] and [1,2,3,5] with length 4. Apparently it is always better to choose 5 over 8 as by
   * having smaller number at the end, it allows more potential subsequence with longer length. In this case,
   * we will get [1,2,3,5,6] if we choose 5 but not [1,2,3,8,6].
   * 
   * In this approach, when we examine each position i. We found the first number in dp[j] that will be larger than or 
   * equal to nums[i] and replace the dp[j] with the latest nums[i] to allow more possible solutions.
   * 
   * 2. Complexity 
   * - Time O(N^2)
   * - Space O(N)
   * 
   * @param nums
   * @return
   */
  public int lengthOfLIS2(int[] nums) {
    final int n = nums.length;
    final int[] dp = new int[n];
    int maxLen = 0;

    for (int i = 0; i < n; i++) {
      final int num = nums[i];
      int j = 0;
      while (j < maxLen && dp[j] < num) {
        j++;
      }
      dp[j] = num;
      if (j == maxLen) {
        maxLen++;
      }
    }

    return maxLen;
  }

  /**
   * 1. Approach
   * Dynamic Programming + Binary Search. An observation coming from approach 2 is the dp array will always be 
   * strictly increasing. With that, binary search could be used when we are looking up for the first number that is 
   * greater than or equal to the target.
   * 
   * 2. Complexity 
   * - Time O(NlogN)
   * - Space O(N)
   * 
   * @param nums
   * @return
   */
  public int lengthOfLIS3(int[] nums) {
    final int n = nums.length;
    final int[] dp = new int[n];
    int maxLen = 0;

    for (int i = 0; i < n; i++) {
      final int num = nums[i];
      int j = binarySearch(dp, maxLen - 1, num);
      dp[j] = num;
      if (j == maxLen) maxLen++;
    }

    return maxLen;
  }

  /**
   * Find the minimum k that is greater than or equal to target in [0, endIndex]. Return endIndex + 1 if not found any.
   */
  private int binarySearch(int[] dp, int endIndex, int target) {
    int low = 0, high = endIndex + 1;

    while (low < high) {
      int mid = low + (high - low) / 2;

      if (dp[mid] >= target) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }

    return low;
  }
}
