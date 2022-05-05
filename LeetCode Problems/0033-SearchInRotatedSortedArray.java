package problem;

/**
 * 1. Problem 
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) 
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). 
 * For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, 
 * or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * 
 * Example 2
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * 
 * Example 3
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * 3. Constraints
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -104 <= target <= 104
 */
public class SearchInRotatedSortedArray {

  /**
   * 1. Approach
   * Binary Search. One pass binary search could be achieved by adding additional check. A common pattern for a sorted
   * array that is rotated is as below. We could mark the start and end of the array element value.
   *    
   *         --
   *       --
   *     --
   *   --
   * --
   *                --
   *             -- 
   *           --
   * By comparing the target, nums[mid] and nums[start], we will be sure whether to search to the left or to the right.
   * 
   * 2. Complexity 
   * - Time O(logN)
   * - Space O(1)
   * 
   * @param nums
   * @param target
   * @return
   */
  public int search(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    int start = nums[0], end = nums[high];

    while (low <= high) {
      int mid = low + (high - low) / 2;
      int num = nums[mid];

      if (num == target) {
        return mid;
      }

      if (target >= start) {
        if (num < start || num >= target) {
          high = mid - 1;
        } else if (num < target) {
          low = mid + 1;
        }
      } else {
        if (num >= start || num <= target) {
          low = mid + 1;
        } else if (num > target) {
          high = mid - 1;
        }
      }
    }

    return -1;
  }
}
