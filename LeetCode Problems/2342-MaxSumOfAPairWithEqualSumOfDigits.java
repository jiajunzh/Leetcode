package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * You are given a 0-indexed array nums consisting of positive integers. You can choose two indices i and j, such that 
 * i != j, and the sum of digits of the number nums[i] is equal to that of nums[j].
 *
 * Return the maximum value of nums[i] + nums[j] that you can obtain over all possible indices i and j that satisfy the
 * conditions.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [18,43,36,13,7]
 * Output: 54
 * Explanation: The pairs (i, j) that satisfy the conditions are:
 * - (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
 * - (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
 * So the maximum sum that we can obtain is 54.
 * 
 * Example 2
 * Input: nums = [10,12,19,14]
 * Output: -1
 * Explanation: There are no two numbers that satisfy the conditions, so we return -1.
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 */
public class MaxSumOfAPairWithEqualSumOfDigits {

  /**
   * 1. Approach 
   * HashMap to store mapping from digitSum to maximum number so far.
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(N)
   * 
   * @param nums
   * @return
   */
  public int maximumSum(int[] nums) {
    int maxSum = -1;
    final Map<Integer, Integer> map = new HashMap<>(); 
    for (int num : nums) {
      int digitSum = getDigitSum(num);
      if (map.containsKey(digitSum)) {
        int another = map.get(digitSum);
        maxSum = Math.max(maxSum, num + another);
        map.put(digitSum, Math.max(another, num));
      } else {
        map.put(digitSum, num);
      }
    }
    return maxSum;
  }

  private int getDigitSum(int num) {
    int sum = 0;
    while (num > 0) {
      sum += num % 10;
      num = num / 10;
    }
    return sum;
  }
}
