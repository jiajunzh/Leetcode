package problem;

/**
 * 1. Problem 
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, 
 * return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * 
 * Example 2:
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the 
 * peak element is 6.
 *
 * 3. Constraints
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * nums[i] != nums[i + 1] for all valid i.
 */
public class FindPeakElement {

  /**
   * 1. Approach 
   * Binary Search. Translating this problem into another word, it is basically saying keeping looking up until it 
   * hits the start or end of the array OR there is a peak found.
   * 
   * Highlights:
   * - If the array contains multiple peaks, return the index to any of the peaks => Local optimal 
   * - nums[-1] = nums[n] = -∞ => Peak element is guaranteed in the array 
   * - nums[i] != nums[i + 1] for all valid i.
   * 
   * 2. Complexity 
   * - Time O(logN)
   * - Space O(1)
   * 
   * @param nums
   * @return
   */
  public int findPeakElement(int[] nums) {
    int left = 0, right = nums.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] > nums[mid + 1]) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    return right;
  }
}
