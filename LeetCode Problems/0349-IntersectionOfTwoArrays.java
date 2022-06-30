package problem;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. Problem
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be 
 * unique and you may return the result in any order.
 *
 *
 * 2. Examples
 * Example 1
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * 
 * Example 2
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Explanation: [4,9] is also accepted.
 *
 * 3. Constraints
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 */
public class IntersectionOfTwoArrays {

  /**
   * 1. Approach 
   * HashSet 
   * 
   * 2. Complexity 
   * - Time O(N1 + N2)
   * - Space O(min(N1, N2))
   * 
   * @param nums1
   * @param nums2
   * @return
   */
  public int[] intersection(int[] nums1, int[] nums2) {
    if (nums2.length < nums1.length) return intersection(nums2, nums1);
    final Set<Integer> numSet1 = new HashSet<>();
    for (int num : nums1) {
      numSet1.add(num);
    }
    final Set<Integer> intersectionSet = new HashSet<>();
    for (int num : nums2) {
      if (numSet1.contains(num)) {
        intersectionSet.add(num);
      }
    }
    final int[] intersection = new int[intersectionSet.size()];
    int i = 0;
    for (int num : intersectionSet) {
      intersection[i] = num;
      i++;
    }
    return intersection;
  }
}
