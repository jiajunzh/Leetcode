package problem;

import java.util.Arrays;

/**
 * 1. Problem
 * You are given an integer array nums and an integer k.
 *
 * For each index i where 0 <= i < nums.length, change nums[i] to be either nums[i] + k or nums[i] - k.
 *
 * The score of nums is the difference between the maximum and minimum elements in nums.
 *
 * Return the minimum score of nums after changing the values at each index.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1], k = 0
 * Output: 0
 * Explanation: The score is max(nums) - min(nums) = 1 - 1 = 0.
 *
 * Example 2
 * Input: nums = [0,10], k = 2
 * Output: 6
 * Explanation: Change nums to be [2, 8]. The score is max(nums) - min(nums) = 8 - 2 = 6.
 *
 * Example 3
 * Input: nums = [1,3,6], k = 3
 * Output: 3
 * Explanation: Change nums to be [4, 6, 3]. The score is max(nums) - min(nums) = 6 - 3 = 3.
 *
 * 3. Constraints
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 104
 * 0 <= k <= 104
 */
public class SmallestRangeII {

    /**
     * 1. Approach
     * Sorting
     *
     * Sort the vector.
     *
     * Assuming there is a point, on the left of the point, all elements add K, on the right of the point, all elements
     * subtract K, check each possible point. (a point is between two numbers).
     *
     * Cause there are two segments (A[0]+K, A[1]+K, ..., A[i]+K, A[i+1]-K, ..., A[n]-K), the first one is on the left
     * of the current point (A[i] + K is the last element of the first segment), the second one is on the right of the
     * current point (A[i + 1] - K is the first element of the second segment).
     *
     * For the first segment, the smallest element is left, the largest is A[i] + K; For the second segment,
     * A[i + 1] - K is the smallest element, the largest is right. Thus, for each point, the largest element should
     * be either A[i] + K or right, the smallest element should be either left or A[i + 1] - K.”
     *
     * 2. Complexity
     * - Time O(NlogN)
     * - Space O(NlogN)
     * @param nums
     * @param k
     * @return
     */
    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int smallestRange = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int high = Math.max(nums[i] + k, nums[nums.length - 1] - k);
            int low = (i + 1 == nums.length) ? nums[0] + k : Math.min(nums[i + 1] - k, nums[0] + k);
            smallestRange = Math.min(smallestRange, high - low);
        }
        return smallestRange;
    }
}
