package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must
 * appear as many times as it shows in both arrays and you may return the result in any order.
 *
 * 2. Examples 
 * Example 1
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * 
 * Example 2
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 *
 * 3. Constraints
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 */
public class IntersectionOfTwoArraysII {

  /**
   * 1. Approach 
   * HashMap 
   * 
   * 2. Complexity 
   * - Time O(N + M)
   * - Space O(min(N, M))
   * 
   * 3. Follow up
   * 1) What if the given array is already sorted? How would you optimize your algorithm?
   * Given the array sorted, another approach that we could take is to have two pointers iterating through 
   * two arrays
   * 2) What if nums1's size is small compared to nums2's size? Which algorithm is better?
   * Use smaller array to build up hash map
   * 3) What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements
   * into the memory at once?
   * If nums1 fits into memory, then we could use the approach below to build up the hashmap.
   * If both not, we could still build up the occurrence count hash map by iterating each range in nums1
   * 
   * @param nums1
   * @param nums2
   * @return
   */
  public int[] intersect(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) return intersect(nums2, nums1);
    final Map<Integer, Integer> map = new HashMap<>();
    final ArrayList<Integer> resultList = new ArrayList<>();
    for (int num : nums1) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    for (int num : nums2) {
      if (map.getOrDefault(num, 0) > 0) {
        resultList.add(num);
        map.put(num, map.getOrDefault(num, 0) - 1);
      }
    }
    final int[] result = new int[resultList.size()];
    for (int i = 0; i < result.length; i++) {
      result[i] = resultList.get(i);
    }
    return result;
  }
}
