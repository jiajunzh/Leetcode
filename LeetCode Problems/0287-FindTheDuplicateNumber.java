package problem;

/**
 * 1. Problem 
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * 
 * Example 2
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 *
 * 3. Constraints
 * 1 <= n <= 105
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * All the integers in nums appear only once except for precisely one integer which appears two or more times.
 *
 * 4. Follow up
 * How can we prove that at least one duplicate number must exist in nums?
 * Can you solve the problem in linear runtime complexity?
 */
public class FindTheDuplicateNumber {

  /**
   * 1. Approach 
   * Binary Search. Find the minimum k such that there are more than k element in array counting from 1 to k.
   * 
   * 2. Complexity 
   * - Time O(NlogN)
   * - Space O(1)
   * 
   * 3. Alternative 
   * - Another O(N) algorithm is inspired by Floyd's Tortoise and Hare Algorithm (Cycle Detection). The duplicate implies 
   *   that there are equal to or more than 2 elements pointing to the duplicate index. By finding the entrance of the 
   *   cycle, it finds the duplicates.
   *   Refer to https://leetcode.com/problems/find-the-duplicate-number/solution/
   *   
   * @param nums
   * @return
   */
  public int findDuplicate(int[] nums) {
    int low = 1, high = nums.length - 1;

    while (low < high) {
      int mid = low + (high - low) / 2;
      int count = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] <= mid) {
          count++;
        }
      }
      if (count <= mid) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }

    return low;
  }
}
