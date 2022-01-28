package problem;

/**
 * 1. Problem
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, 
 * return the index where it would be if it were inserted in order. You must write an algorithm with O(log n) runtime 
 * complexity.
 *
 * 2. Example
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 *
 * 3. Constraints
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums contains distinct values sorted in ascending order.
 * -10^4 <= target <= 10^4
 */
public class SearchInsertPosition {

  /**
   * 1. Approach.
   * This problem could be translated to finding the minimum k such that nums[k] >= target.
   * 
   * 2. Problem 
   * Time O(logN)
   * Space O(1)
   * 
   * 3. Mistake
   * 1) Initialize right as nums.length instead of nums.length - 1;
   * One edge case is that the target could be put at the end of the array.
   * 
   * @param nums
   * @param target
   * @return
   */
  public int searchInsert(int[] nums, int target) {
    int left = 0, right = nums.length;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] >= target) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    return left;
  }
}
