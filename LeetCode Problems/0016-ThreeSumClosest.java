package problem;

import java.util.Arrays;

public class ThreeSumClosest {

    /**
     * 1. Approach
     * Two Pointers
     * The problem is asking to select a tuple nums[i] + nums[j] + nums[k] = sum such that |sum - target| is minimized.
     * Brutal force will require O(N^3)
     * If we fix one of the element say, nums[i]. Then the problem is transitioned to twoSumClosest (where the new
     * target is target - nums[i]), then the complexity can be reduced down to O(N^2)
     *
     * 2. Complexity
     * - Time O(N^2)
     * - Space O(logN) to O(N) depending on sorting algorithm. The space can be optimized to O(1) using selection sort
     *   while still keeping O(N^2) time (the overall time might be slower though).
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE, closestSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int currSum = nums[i] + nums[j] + nums[k];
                if (Math.abs(target - currSum) < minDiff) {
                    minDiff = Math.abs(target - currSum);
                    closestSum = currSum;
                }
                if (currSum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return closestSum;
    }
}
