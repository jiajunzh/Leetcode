package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) 
 * such that: 
 * - 0 <= i, j, k, l < n
 * - nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 *
 * 2. Example
 * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 * Output: 2
 * Explanation:
 * The two tuples are:
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
 * Output: 1
 *
 * 3. Constraints:
 * n == nums1.length
 * n == nums2.length
 * n == nums3.length
 * n == nums4.length
 * 1 <= n <= 200
 * -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
 */
public class FourSumII {

  /**
   * 1. Approach
   * HashMap. nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0 could be translated into 
   * nums1[i] + nums2[j] = - (nums3[k] + nums4[l]). This problem could be divided into two parts: Sum(num1, num2)
   * and Sum(num3, num4), then the problem become similar to an easier version Two Sum.
   * 
   * 2. Complexity 
   * Time O(N^N)
   * Space O(N^N)
   * 
   * @param nums1
   * @param nums2
   * @param nums3
   * @param nums4
   * @return
   */
  public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    int fourSumCount = 0;
    final Map<Integer, Integer> twoSumCountMap = new HashMap<>();

    for (int num1 : nums1) {
      for (int num2 : nums2) {
        int sum = num1 + num2;
        twoSumCountMap.put(sum, twoSumCountMap.getOrDefault(sum, 0) + 1);
      }
    }

    for (int num3 : nums3) {
      for (int num4 : nums4) {
        int sum = num3 + num4;
        fourSumCount += twoSumCountMap.getOrDefault(-sum, 0);
      }
    }

    return fourSumCount;
  }
}
