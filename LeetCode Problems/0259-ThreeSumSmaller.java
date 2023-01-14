package problem;

import java.util.Arrays;

/**
 * 1. Problem
 * Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with
 * 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [-2,0,1,3], target = 2
 * Output: 2
 * Explanation: Because there are two triplets which sums are less than 2:
 * [-2,0,1]
 * [-2,0,3]
 *
 * Example 2
 * Input: nums = [], target = 0
 * Output: 0
 *
 * Example 3
 * Input: nums = [0], target = 0
 * Output: 0
 *
 * 3. Constraints
 * n == nums.length
 * 0 <= n <= 3500
 * -100 <= nums[i] <= 100
 * -100 <= target <= 100
 */
public class ThreeSumSmaller {

    /**
     * 1. Approach
     * Sort + Binary Search
     *
     * 2. Complexity
     * - Time O(N^2*logN)
     * - Space O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            count += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return count;
    }

    // Find the minimum index k such that nums[k] >= bound
    // => [i, nums.length - 1]
    private int twoSumSmaller(int[] nums, int start, int target) {
        int count = 0;
        for (int i = start; i < nums.length; i++) {
            int bound = target - nums[i];
            int left = i + 1, right = nums.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] >= bound) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            count += left - i - 1;
        }
        return count;
    }

    /**
     * 1. Approach
     * Sort + Two Pointers
     *
     * 2. Complexity
     * - Time O(N^2)
     * - Space O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller2(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            count += twoSumSmaller2(nums, i + 1, target - nums[i]);
        }
        return count;
    }

    private int twoSumSmaller2(int[] nums, int start, int target) {
        int count = 0, left = start, right = nums.length - 1;
        while (left < right) {
            int twoSum = nums[left] + nums[right];
            if (twoSum < target) {
                count += right - left;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }
}
