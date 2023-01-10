package problem;

/**
 * 1. Problem
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 *
 * A subarray is a contiguous part of an array.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *
 * Example 2
 * Input: nums = [5], k = 9
 * Output: 0
 *
 * 3. Constraints
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * 2 <= k <= 104
 */
public class SubarraySumsDivisibleByK {

    /**
     * 1. Approach
     * Prefix + Counting
     *
     * If PS[i] % k == PS[j] % k => (PS[i] - PS[j]) % k == 0 => subarray % k == 0.
     * Based on this, if we find two indexes i and j such that PS[i] % k == PS[j] % k, then we know there is a match.
     *
     * 2. Complexity
     * - Time O(K + N)
     * - Space O(K)
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraysDivByK(int[] nums, int k) {
        final int[] mods = new int[k];
        int count = 0;
        mods[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            nums[i] += i == 0 ? 0 : nums[i - 1];
            int mod = (nums[i] % k + k) % k;
            count += mods[mod];
            mods[mod]++;
        }
        return count;
    }

    /**
     * Thinking Process
     * 1. Requirement
     *      Input:
     *       - nums: nums.length? could nums[i] be negative or zero?
     *       - k: 0 < k < ?
     *      Output: number of non-empty subarrays satisfying the condition
     *      Condition/Function: subarray (1) sum of all elements is divisble by k => Sum % k = 0 (2) subarray has at lease one element
     *
     *      [4,5,0,-2,-3,1], k = 5 ==> [4,5,0,-2,-3,1], [5], [5,0], [5,0,-2,-3], [0], [0,-2,-3], [-2,-3]
     * 2. Solutions
     *      - Solution 1: Enumeration. Define the start and end index of the window/subarray, sum them up and check the conditon. We could do this check for each possible subarrays.
     *        Time O(N^3)
     *        Space O(1)
     *
     *      - Solution 2: prefix sum => TLE
     *        Time O(N^2)
     *        Space O(1)
     *
     *      - Solution 3: prefix sum + Mod + Counting/HashMap
     *        if PS[i] % k == PS[j] % k => (PS[i] - PS[j]) % k == 0 => subarray % k == 0
     *        Time O(N + k)
     *        Space O(k)
     */
}
