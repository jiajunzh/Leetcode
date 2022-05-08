package problem;

/**
 * 1. Problem
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given 
 * target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * 
 * Example 2
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * 
 * Example 3
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 * 3. Constraints
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

  /**
   * 1. Approach 
   * Two Binary Search. This problem could be translated into first finding the start position of the target and then 
   * the end position of the target. 
   * - start position: find the minimum index k such that nums[k] >= target 
   * - end position: find the minimum index k such that nums[k] > target => end position = k - 1
   * 
   * 2. Complexity 
   * - Time O(logN)
   * - Space O(1)
   * 
   * 3. Alternative 
   * Another way to look at this problem is 
   * - start position: if nums[mid - 1] != target and nums[mid] = target => it is start position  
   * - end position: if nums[mid + 1] != target and nums[mid] = target => it is end position 
   * 
   * @param nums
   * @param target
   * @return
   */
  public int[] searchRange(int[] nums, int target) {
    int left = 0, right = nums.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] >= target) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    int start = left;
    left = 0;
    right = nums.length;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] > target) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    int end = left - 1;


    if (end == -1 || nums[start] != target || nums[end] != target) {
      return new int[]{-1, -1};
    }
    return new int[]{start, end};
  }
}
