package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * You are given a non-negative integer array nums. In one operation, you must:
 *
 * Choose a positive integer x such that x is less than or equal to the smallest non-zero element in nums.
 * Subtract x from every positive element in nums.
 * Return the minimum number of operations to make every element in nums equal to 0.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [1,5,0,3,5]
 * Output: 3
 * Explanation:
 * In the first operation, choose x = 1. Now, nums = [0,4,0,2,4].
 * In the second operation, choose x = 2. Now, nums = [0,2,0,0,2].
 * In the third operation, choose x = 2. Now, nums = [0,0,0,0,0].
 * 
 * Example 2
 * Input: nums = [0]
 * Output: 0
 * Explanation: Each element in nums is already 0 so no operations are needed.
 *
 * 3. Constraints
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class MakeArrayZeroBySubtractingEqualAmounts {

  /**
   * 1. Approach
   * Array Sorting. This problem is basically asking how many unique positive integers are there in the array.
   * 
   * 2. Complexity
   * - Time O(N*logN)
   * - Space O(1)
   * 
   * 3. Alternative 
   * - One could also use HashSet instead to trade memory for better time
   * 
   * @param nums
   * @return
   */
  public int minimumOperations(int[] nums) {
    Arrays.sort(nums);
    int count = 0;
    int prev = 0;
    for (int num : nums) {
      if (prev == num) {
        continue;
      }
      prev = num;
      count++;
    }
    return count;
  }
}
