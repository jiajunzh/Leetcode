package problem;

/**
 * 1. Problem
 * Given an integer array nums, find three numbers whose product is maximum and return the maximum product.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,2,3]
 * Output: 6
 *
 * Example 2
 * Input: nums = [1,2,3,4]
 * Output: 24
 *
 * Example 3
 * Input: nums = [-1,-2,-3]
 * Output: -6
 *
 * 3. Constraints
 * 3 <= nums.length <= 104
 * -1000 <= nums[i] <= 1000
 */
public class MaximumProductOfThreeNumbers {

    /**
     * 1. Problem
     * One Pass Inspired by Sorting
     * smallest middle largest result
     * - N       N       N      N => max three
     * - N       N       P      P => min two + max
     * - N       P       P      N => max three
     * - P       P       P      P => max three
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1)
     */
    public int maximumProduct(int[] nums) {
        int max = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE, thirdMax = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num > max) {
                thirdMax = secondMax;
                secondMax = max;
                max = num;
            } else if (num > secondMax) {
                thirdMax = secondMax;
                secondMax = num;
            } else if (num > thirdMax) {
                thirdMax = num;
            }

            if (num < min) {
                secondMin = min;
                min = num;
            } else if (num < secondMin) {
                secondMin = num;
            }
        }
        return Math.max(max * min * secondMin, max * secondMax * thirdMax);
    }
}
