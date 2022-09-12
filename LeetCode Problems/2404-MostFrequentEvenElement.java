package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * Given an integer array nums, return the most frequent even element.
 *
 * If there is a tie, return the smallest one. If there is no such element, return -1.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [0,1,2,2,4,4,1]
 * Output: 2
 * Explanation:
 * The even elements are 0, 2, and 4. Of these, 2 and 4 appear the most.
 * We return the smallest one, which is 2.
 * 
 * Example 2
 * Input: nums = [4,4,4,9,2,4]
 * Output: 4
 * Explanation: 4 is the even element appears the most.
 * 
 * Example 3
 * Input: nums = [29,47,21,41,13,37,25,7]
 * Output: -1
 * Explanation: There is no even element.
 *
 * 3. Constraints
 * 1 <= nums.length <= 2000
 * 0 <= nums[i] <= 105
 */
public class MostFrequentEvenElement {

  /**
   * 1. Approach 
   * HashMap
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param nums
   * @return
   */
  public int mostFrequentEven(int[] nums) {
    final Map<Integer, Integer> map = new HashMap<>();
    int maxFrequency = 0;
    int mostFrequentEven = -1;
    for (int num : nums) {
      if (num % 2 == 0) {
        int freq = map.getOrDefault(num, 0) + 1;
        map.put(num, freq);
        if (freq > maxFrequency ||((freq == maxFrequency) && mostFrequentEven > num)) {
          mostFrequentEven = num;
          maxFrequency = freq;
        }
      }
    }
    return mostFrequentEven;
  }
}
