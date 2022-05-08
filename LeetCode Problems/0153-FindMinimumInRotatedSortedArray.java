package problem;

/**
 * 1. Problem 
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array 
 * nums = [0,1,2,4,5,6,7] might become:
 *
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the 
 * array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * 
 * Example 2
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * 
 * Example 3
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times. 
 *
 * 3. Constraints
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * All the integers of nums are unique.
 * nums is sorted and rotated between 1 and n times.
 */
public class FindMinimumInRotatedSortedArray {

  /**
   * 1. Approach 
   * Binary Search. It is to find the minimum element k such that is smaller than nums[0]. Think about the below example.
   * Consider the array is rotated for either [1,n-1] times 
   * [3,4,5,1,2] => The inflection point is index 3 nums[3] = 1. 
   * With index 3:
   * - All elements to its left is greater than or equal to nums[0]
   * - All elements to its right is smaller than nums[0]
   * 
   * 2. Complexity 
   * - Time O(logN)
   * - Space O(1)
   * 
   * 3. Mistakes
   * - The binary search only applies to the case where the array is rotated between [1, n-1] times. It does not apply 
   *   if the array is not rotated at all (i.e. ascending sorted)
   * 
   * @param nums
   * @return
   */
  public int findMin(int[] nums) {
    final int n = nums.length;
    int low = 0, high = n - 1;
    if (nums[0] <= nums[high]) {
      return nums[0];
    }

    while (low < high) {
      int mid = low + (high - low) / 2;

      if (nums[mid] >= nums[0]) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }

    return nums[low];
  }
}
