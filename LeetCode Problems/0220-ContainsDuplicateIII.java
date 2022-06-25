package problem;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 1. Problem 
 * Given an integer array nums and two integers k and t, return true if there are two distinct indices i and j in the
 * array such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * 
 * Example 2
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * 
 * Example 3
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 *
 * 3. Constraints
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 104
 * 0 <= t <= 231 - 1
 */
public class ContainsDuplicateIII {

  /**
   * 1. Approach 
   * TreeSet (Search Tree). 
   * 
   * Keep a set/window with size k where all elements in it will have distance <= k to nums[i]. Then we could only focus
   * on the constraint that abs(nums[i] - nums[j]) <= t.
   * 
   * Note that in the actual code, it removes nums[i - k] if size of set > k. One might think what if there is a 
   * duplicate post nums[i - k] in the current set as it will remove that number as well. It is actually okay to do so
   * because if there are nums[i - k] = nums[j] where j > i - k, it is guaranteed the final will be true. Because you 
   * have two numbers whose indexes have distance <= k with values having distance == 0 (<= any t)
   * 
   * 2. Complexity 
   * - Time O(Nlog(min(N, k))
   * - Space O(min(N, k))
   * 
   * @param nums
   * @param k
   * @param t
   * @return
   */
  public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
    final TreeSet<Integer> orderedSet = new TreeSet<>();
    for (int i = 0; i < nums.length; i++) {
      final int num = nums[i];
      final Integer ceiling = orderedSet.ceiling(num);
      if (ceiling != null && ceiling <= ((long) num + t)) {
        return true;
      }
      final Integer floor = orderedSet.floor(num);
      if (floor != null && num <= ((long) floor + t)) {
        return true;
      }
      orderedSet.add(num);
      if (orderedSet.size() > k) {
        orderedSet.remove(nums[i - k]);
      }
    }
    return false;
  }

  /**
   * 1. Approach 
   * Bucket Sorting.
   * 
   * 1) Keep a bucket for a range of values. For example, number 1 should be within the bucket [0, t] (t + 1 values in 
   * total).
   * 2) For each number, if there exist a value in its bucket or there is a value within required distance in its two
   * adjacent buckets
   * 3) We always keep only k elements in bucket => remove element that is too old
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(min(n, k))
   * 
   * @param nums
   * @param k
   * @param t
   * @return
   */
  public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
    final Map<Long, Long> buckets = new HashMap<>();
    long w = (long) t + 1;
    for (int i = 0; i < nums.length; i++) {
      long bucketId = getBucketId(nums[i], w);
      if (buckets.containsKey(bucketId)) {
        return true;
      }
      if (buckets.containsKey(bucketId - 1) && Math.abs(buckets.get(bucketId - 1) - nums[i]) <= t) {
        return true;
      }
      if (buckets.containsKey(bucketId + 1) && Math.abs(buckets.get(bucketId + 1) - nums[i]) <= t) {
        return true;
      }
      buckets.put(bucketId, (long) nums[i]);
      if (i >= k) {
        buckets.remove(getBucketId(nums[i - k], w));
      }
    }
    return false;
  }

  private long getBucketId(int num, long w) {
    return num < 0 ? (num + 1) / w - 1 : num / w;
  }
}
