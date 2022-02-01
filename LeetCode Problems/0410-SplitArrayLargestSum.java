package problem;

/**
 * 1. Problem 
 * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m 
 * non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * 2. Examples
 * Input: nums = [7,2,5,10,8], m = 2
 * Output: 18
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 *
 * Input: nums = [1,2,3,4,5], m = 2
 * Output: 9
 *
 * Input: nums = [1,4,4], m = 3
 * Output: 4
 *
 * 3. Constraints:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= m <= min(50, nums.length)
 */
public class SplitArrayLargestSum {
  /**
   * 1. Approach 
   * Binary Search. Find the minimum k such that the sum of all m subarrays is smaller than or equals to k. This problem 
   * is fairly similar to problem 1011 Find capacity to ship packages within D days. In this problem, the largest sum 
   * is the capacity in 1011 such that sum of all subarrays should be smaller than or equals to the largest sum. m 
   * here is comparable to D days in problem 1011.
   * 
   * Q1: What if the largestSum in the current iteration could only split the array to k subarrays (k < m)?
   * The goal of the binary search is to find the minimum k satisfying the condition. It is okay the largestSum
   * checked in the current iteration could only split the array into k subarrays where k < m. The next iterations 
   * will find a smaller largest sum than the current one where the array could be split into exactly m subarrays.
   * (It is guaranteed as m <= nums.length, the worst case m will be equals to nums.length and the largest sum will
   * be Math.max(nums)).
   * 
   * Q2: What if the the largestSum in the current iteration is not equals to the sum of any subarrays?
   * In this case, it is guaranteed that there is a k < largestSum in the current iteration and will be found in
   * next a few iterations for sure. For example, if the current largestSum is x and none of the sum of any subarrays
   * is equals to x (meaning all sum are less than x). Then (x - 1) will satisfy the condition for sure.
   * 
   * 2. Complexity 
   * Time: O(NlogN)
   * Space: O(1)
   */
  public int splitArray(int[] nums, int m) {
    int low = 0, high = 0;

    for (int num : nums) {
      high += num;
      low = Math.max(num, low);
    }

    while (low < high) {
      int mid = low + (high - low) / 2;

      if (splittable(nums, m, mid)) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }

    return low;
  }
  
  private boolean splittable(int[] nums, int m, int largestSum) {
    int remainingSum = largestSum;

    for (int num : nums) {
      if (num > largestSum) {
        return false;
      }

      if (num > remainingSum) {
        remainingSum = largestSum - num;
        m--;
      } else {
        remainingSum -= num;
      }
    }

    return m > 0;
  }
}
