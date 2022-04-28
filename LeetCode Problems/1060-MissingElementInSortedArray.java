package problem;

/**
 * 1. Problem 
 * Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an
 * integer k, return the kth missing number starting from the leftmost number of the array.
 *
 * 2. Examples
 * Example 1:
 * Input: nums = [4,7,9,10], k = 1
 * Output: 5
 * Explanation: The first missing number is 5.
 * 
 * Example 2:
 * Input: nums = [4,7,9,10], k = 3
 * Output: 8
 * Explanation: The missing numbers are [5,6,8,...], hence the third missing number is 8.
 * 
 * Example 3:
 * Input: nums = [1,2,4], k = 3
 * Output: 6
 * Explanation: The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 *
 * 3. Constraints
 * 1 <= nums.length <= 5 * 104
 * 1 <= nums[i] <= 107
 * nums is sorted in ascending order, and all the elements are unique.
 * 1 <= k <= 108
 */
public class MissingElementInSortedArray {

  /**
   * 1. Approach
   * Binary Search. One thing to notice in this problem is there is a relationship between the index of the element, k
   * and nums[0]. 
   * 
   * Define: 
   * - Total Number Till/Including Index i: nums[i] - nums[0] + 1 (nums[1] - nums[0] + 1 = 4)
   * - Number Existing Till/Including Index i: i + 1 (1 + 1 = 2)
   * - Missing Number Till/Including Index i: nums[i] - nums[0] + 1 - (i + 1) = nums[i] - nums[0] - i
   * 
   * For example, if nums = [4,7,9,10] k = 3, we know that the answer is 8. The maximum element prior to 8 
   * is nums[1] = 7. The number of missing elements could be calculated as (nums[1] - nums[0] - 1 = 2) which is 5 and 6.
   * 
   * With the relationship, this problem is transformed to find the maximum index i such that the number of missing 
   * element till nums[i] is smaller than k. In other words, find the minimum index i such that the number of missing
   * element till nums[i] is greater than or equal to k. With that the answer will be 
   * nums[i - 1] + k - (nums[i - 1] - nums[0] - (i + 1)) = k + nums[0] + i - 1
   * 
   * However, one edge case here is there could be no such element satisfying that number of missing element till 
   * nums[i] is greater than or equal to k. For example if nums = [3,4], k = 1000. For this case, assume we the index 
   * we find is length - 1 (1 here), then the answer is k + nums[0] + i.
   * 
   * 2. Complexity 
   * - Time O(logN)
   * - Space O(1)
   * 
   * 3. Alternative 
   * - Another solution is one pass by searching the rightmost index with number of missing element smaller than k. An
   *   array could be used to keep track of the number of missing element till index. Obviously, it generates worse 
   *   time complexity O(N)
   * 
   * @param nums
   * @param k
   * @return
   */
  public int missingElement(int[] nums, int k) {
    int low = 0, high = nums.length - 1;

    while (low < high) {
      int mid = low + (high - low) / 2;

      int missingCnt = nums[mid] - nums[0] - mid;
      if (missingCnt < k) {
        low = mid + 1;
      } if (missingCnt >= k) {
        high = mid;
      }
    }

    return (nums[low] - nums[0] - low < k ? 0 : -1) + k + nums[0] + low;
  }
}
