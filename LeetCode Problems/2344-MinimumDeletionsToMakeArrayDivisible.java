package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * You are given two positive integer arrays nums and numsDivide. You can delete any number of elements from nums.
 *
 * Return the minimum number of deletions such that the smallest element in nums divides all the elements of numsDivide.
 * If this is not possible, return -1.
 *
 * Note that an integer x divides y if y % x == 0.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [2,3,2,4,3], numsDivide = [9,6,9,3,15]
 * Output: 2
 * Explanation: 
 * The smallest element in [2,3,2,4,3] is 2, which does not divide all the elements of numsDivide.
 * We use 2 deletions to delete the elements in nums that are equal to 2 which makes nums = [3,4,3].
 * The smallest element in [3,4,3] is 3, which divides all the elements of numsDivide.
 * It can be shown that 2 is the minimum number of deletions needed.
 * 
 * Example 2
 * Input: nums = [4,3,6], numsDivide = [8,2,6,10]
 * Output: -1
 * Explanation: 
 * We want the smallest element in nums to divide all the elements of numsDivide.
 * There is no way to delete elements from nums to allow this.
 *
 * 3. Constraints
 * 1 <= nums.length, numsDivide.length <= 105
 * 1 <= nums[i], numsDivide[i] <= 109
 */
public class MinimumDeletionsToMakeArrayDivisible {

  /**
   * 1. Approach 
   * Sort + Greatest Common Divisor 
   * 
   * 2. Complexity 
   * - Time O(DlogNUM + NlogN) where D is the length of numsDivide, NUM is avg value of numbers in numsDivide and N is 
   *   the length of nums
   * - Space O(logNUM)
   * 
   * @param nums
   * @param numsDivide
   * @return
   */
  public int minOperations(int[] nums, int[] numsDivide) {
    int gcd = numsDivide[0];
    for (int i = 1; i < numsDivide.length; i++) {
      gcd = gcd(numsDivide[i], gcd);
    }
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (gcd % nums[i] == 0) {
        return i;
      }
    }
    return -1;
  }

  private int gcd(int a, int b) {
    if (a % b == 0) {
      return b;
    }
    return gcd(b, a % b);
  }
}
