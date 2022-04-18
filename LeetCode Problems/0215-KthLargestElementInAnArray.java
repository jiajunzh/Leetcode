package problem;

/**
 * 1. Problem 
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * 2. Example
 * Example 1
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * 
 * Example 2
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 *
 * 3. Constraints:
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 */
public class KthLargestElementInAnArray {

  /**
   * 1. Approach 
   * Quick Select.
   * 
   * 2. Complexity
   * - Time O(N) but O(N^2) in worst case
   * - Space O(1)
   * 
   * 3. Mistakes
   * - The condition for while loop should be start <= end instead of start < end as in the case where start == end,
   *   the array is indeed already successfully sorted, but the pivot is still assigned to the previous value instead 
   *   of the last one. One more loop is needed to get correct pivot value as the result is related to it.
   * - This problem needs the kth largest number instead of the smallest one.
   * 
   * @param nums
   * @param k
   * @return
   */
  public int findKthLargest(int[] nums, int k) {
    int start = 0, end = nums.length - 1;
    int pivot = 0;

    while (start <= end) {
      pivot = quickSelect(nums, start, end);

      if (pivot == k - 1) {
        break;
      } else if (pivot > k - 1) {
        end = pivot - 1;
      } else {
        start = pivot + 1;
      }
    }

    return nums[pivot];
  }

  private int quickSelect(int[] nums, int start, int end) {
    int pivot = start + (end - start) / 2;
    swap(nums, pivot, end);

    for (int i = start; i < end; i++) {
      if (nums[i] > nums[end]) {
        swap(nums, i, start);
        start++;
      }
    }

    swap(nums, start, end);

    return start;
  }

  private void swap(int[] nums, int index1, int index2) {
    int tmp = nums[index1];
    nums[index1] = nums[index2];
    nums[index2] = tmp;
  }
}
