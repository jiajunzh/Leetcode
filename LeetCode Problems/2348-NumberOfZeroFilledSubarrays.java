package problem;

/**
 * 1. Problem 
 * Given an integer array nums, return the number of subarrays filled with 0.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [1,3,0,0,2,0,0,4]
 * Output: 6
 * Explanation: 
 * There are 4 occurrences of [0] as a subarray.
 * There are 2 occurrences of [0,0] as a subarray.
 * There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.
 * 
 * Example 2
 * Input: nums = [0,0,0,2,0,0]
 * Output: 9
 * Explanation:
 * There are 5 occurrences of [0] as a subarray.
 * There are 3 occurrences of [0,0] as a subarray.
 * There is 1 occurrence of [0,0,0] as a subarray.
 * There is no occurrence of a subarray with a size more than 3 filled with 0. Therefore, we return 9.
 * 
 * Example 3
 * Input: nums = [2,10,2019]
 * Output: 0
 * Explanation: There is no subarray filled with 0. Therefore, we return 0.
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class NumberOfZeroFilledSubarrays {

  /**
   * 1. Problem 
   * One observation is that by adding one zero to the end of a subarray of size n, it will adds up by n + 1 to the 
   * total number of subarrays
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param nums
   * @return
   */
  public long zeroFilledSubarray(int[] nums) {
    long total = 0, curCount = 0;
    for (int num : nums) {
      if (num != 0) {
        curCount = 0;
      } else {
        ++curCount;
        total += curCount;
      }
    }
    return total;
  }
}
