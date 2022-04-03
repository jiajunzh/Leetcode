package problem;

/**
 * 1. Problem 
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 * 
 * 2. Examples 
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * 
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 * 
 * 3. Constraints 
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 */
public class FindMedianSortedArray {

  /**
   * 1. Approach
   * Binary Search. A very naive approach of this problem is by merging the two arrays together and then finding the
   * middle element, which guarantees the O(M+N) time. However, sorted array gives inspiration of using Binary Search
   * with better time. Consider the two abstract arrays below.
   * 
   * Array M: M1 M2 M3 M4| M5 M6 M7
   * Array N: N1 N2|N3 N4
   * 
   * By the definition of median, it separates one sorted array into two partitions with (1) each having half of the 
   * elements (2) all elements in left partition are less than those in right partition. Translating the two conditions
   * above, having a median means:
   * 
   * 1. The left partition will have (m + n + 1) / 2 elements (for both odd and even cases) 
   * 2. Maximum in M left partition (M4) <= minimum in N right partition (N3)
   *  Otherwise, search left in M array
   * 3. Maximum in N left partition (N2) <= minimum in M right partition (M5)
   *  Otherwise, search right in M array
   * 
   * 2. Complexity 
   * Time: O(Log(Min(M, N)))
   * Space: O(1) 
   * 
   * 3. Mistakes
   * - Generalizing cases by introducing definition of number of elements in each partition. Avoid having separate
   * if-else to cover edge case.
   * 
   * @param nums1
   * @param nums2
   * @return
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // This provides the better time complexity of O(Log(Min(M, N)))
    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1);
    }

    final int m = nums1.length, n = nums2.length;
    int left = 0, right = nums1.length;
    int leftCnt = (m + n + 1) / 2;

    while (left <= right) {
      final int leftM = left + (right - left) / 2;
      final int leftN = leftCnt - leftM;

      final int leftMaxM = leftM == 0 ? Integer.MIN_VALUE : nums1[leftM - 1];
      final int leftMaxN = leftN == 0 ? Integer.MIN_VALUE : nums2[leftN - 1];
      final int rightMinM = leftM == m ? Integer.MAX_VALUE : nums1[leftM];
      final int rightMinN = leftN == n ? Integer.MAX_VALUE : nums2[leftN];

      if (leftMaxM <= rightMinN && leftMaxN <= rightMinM) {
        if ((m + n) % 2 == 0) {
          return (Math.max(leftMaxM, leftMaxN) + Math.min(rightMinM, rightMinN)) / 2.0;
        } else {
          return Math.max(leftMaxM, leftMaxN);
        }
      } else if (leftMaxM <= rightMinN) {
        left = leftM + 1;
      } else {
        right = leftM - 1;
      }
    }

    throw new IllegalArgumentException();
  }
}
