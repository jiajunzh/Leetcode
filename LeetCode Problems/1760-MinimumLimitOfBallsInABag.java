package problem;

/**
 * 1. Problem 
 * You are given an integer array nums where the ith bag contains nums[i] balls. You are also given an integer
 * maxOperations.
 *
 * You can perform the following operation at most maxOperations times:
 *
 * Take any bag of balls and divide it into two new bags with a positive number of balls.
 * For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
 * Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.
 *
 * Return the minimum possible penalty after performing the operations.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [9], maxOperations = 2
 * Output: 3
 * Explanation: 
 * - Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
 * - Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
 * The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.
 * 
 * Example 2
 * Input: nums = [2,4,8,2], maxOperations = 4
 * Output: 2
 * Explanation:
 * - Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,4,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,2,2,4,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [2,2,2,2,2,4,2].
 * - Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
 * The bag with the most number of balls has 2 balls, so your penalty is 2 an you should return 2.
 * 
 * Example 3
 * Input: nums = [7,17], maxOperations = 2
 * Output: 7
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * 1 <= maxOperations, nums[i] <= 109
 */
public class MinimumLimitOfBallsInABag {

  /**
   * 1. Approach 
   * Binary Search. This problem is typical Binary Search problem where you want to find the minimum k such that 
   * all elements greater than k could be split into elements smaller or equal to k within maxOperations operations
   * 
   * 2. Complexity
   * - Time O(NLogN)
   * - Space O(1)
   * 
   * 3. Improvement
   * - For each number, the number of operations that is needed to split the element into elements smaller or equals to
   *   the target penalty could be calculated by dividing the num - 1 by target penalty. Do not use subtraction to loop
   *   over the number, it will cause time limit exceeded.
   * - Only check the element greater than penalty and bypass those smaller or equal to the target.
   * 
   * @param nums
   * @param maxOperations
   * @return
   */
  public int minimumSize(int[] nums, int maxOperations) {
    int low = 1, high = 1_000_000_000;

    while (low < high) {
      int mid = low + (high - low) / 2;

      if (checkOperatable(nums, mid, maxOperations)) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }

    return low;
  }

  private boolean checkOperatable(int[] nums, int penalty, int maxOperations) {
    for (int num : nums) {
      if (num > penalty) {
        maxOperations -= (num - 1) / penalty;
      }
    }

    return maxOperations >= 0;
  }
}
