package problem;

/**
 * 1. Problem 
 * Given an array of integers nums, sort the array in ascending order and return it.
 *
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest 
 * space complexity possible.
 * 
 * 2. Examples
 * Example 1
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while 
 * the positions of other numbers are changed (for example, 1 and 5).
 * 
 * Example 2
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 * Explanation: Note that the values of nums are not necessairly unique.
 *
 * 3. Constraints
 * 1 <= nums.length <= 5 * 104
 * -5 * 104 <= nums[i] <= 5 * 104 
 */
public class SortAnArray {

  /**
   * 1. Approach
   * Merge Sort.
   * https://leetcode.com/problems/sort-an-array/discuss/492042/7-Sorting-Algorithms-(quick-sort-top-downbottom-up-merge-sort-heap-sort-etc.)
   * 
   * 2. Complexity 
   * - Time O(NlogN)
   * - Space O(N)
   * 
   * @param nums
   * @return
   */
  public int[] sortArray(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
    return nums;
  }

  private void mergeSort(int[] nums, int start, int end) {
    if (start >= end) return;
    int mid = start + (end - start) / 2;
    mergeSort(nums, start, mid);
    mergeSort(nums, mid + 1, end);
    merge(nums, start, end, mid);
  }

  private void merge(int[] nums, int start, int end, int mid) {
    final int[] merged = new int[end - start + 1];
    int i = start, j = mid + 1, index = 0;
    while (i <= mid || j <= end) {
      if (i > mid || (j <= end && nums[i] > nums[j])) {
        merged[index] = nums[j];
        j++;
      } else {
        merged[index] = nums[i];
        i++;
      }
      index++;
    }
    System.arraycopy(merged, 0, nums, start, end - start + 1);
  }
}
