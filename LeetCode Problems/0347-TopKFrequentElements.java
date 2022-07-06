package problem;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1. Problem 
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in
 * any order.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 * Example 2
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 * 4. Follow up
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {

  /**
   * 1. Approach 
   * HashMap + PriorityQueue
   * 
   * 2. Complexity 
   * - Time O(NLogK)
   * - Space O(N + k)
   * 
   * @param nums
   * @param k
   * @return
   */
  public int[] topKFrequent(int[] nums, int k) {
    final Map<Integer, Integer> count = new HashMap<>();
    for (int num : nums) {
      count.put(num, count.getOrDefault(num, 0) + 1);
    }
    final PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (count.get(a) - count.get(b)));
    for (int num : count.keySet()) {
      pq.offer(num);
      while (pq.size() > k) {
        pq.poll();
      }
    }
    final int[] result = new int[k];
    for (int i = 0; i < k; i++) {
      result[i] = pq.poll();
    }
    return result;
  }

  /**
   * 1. Approach 
   * Quick Select 
   * 
   * 2. Complexity 
   * - Time O(N) in average case but O(N^2) in worst case
   * - Space O(N)
   * 
   * @param nums
   * @param k
   * @return
   */
  public int[] topKFrequent2(int[] nums, int k) {
    final Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    int n = map.size();
    final int[] unique = new int[n];
    int j = 0;
    for (int num: map.keySet()) {
      unique[j] = num;
      j++;
    }

    int left = 0, right = n - 1;
    while (left < right) {
      int pivot = left + (right - left) / 2;
      int index = partition(left, right, pivot, unique, map);
      if (index == n - k) {
        break;
      } else if (index > n - k) {
        right = index - 1;
      } else {
        left = index + 1;
      }
    }
    final int[] result = new int[k];
    for (int i = 0; i < k; i++) {
      result[i] = unique[n - 1 - i];
    }
    return result;
  }

  private int partition(int left, int right, int pivot, int[] nums, Map<Integer, Integer> count) {
    int target = count.get(nums[pivot]);
    swap(right, pivot, nums);
    int p = left;
    for (int i = left; i < right; i++) {
      if (target > count.get(nums[i])) {
        swap(i, p, nums);
        p++;
      }
    }
    swap(p, right, nums);
    return p;
  }

  private void swap(int a, int b, int[] nums) {
    int tmp = nums[a];
    nums[a] = nums[b];
    nums[b] = tmp;
  }
}
