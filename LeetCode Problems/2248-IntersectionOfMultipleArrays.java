package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given a 2D integer array nums where nums[i] is a non-empty array of distinct positive integers, return the list of 
 * integers that are present in each array of nums sorted in ascending order.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [[3,1,2,4,5],[1,2,3,4],[3,4,5,6]]
 * Output: [3,4]
 * Explanation: 
 * The only integers present in each of nums[0] = [3,1,2,4,5], nums[1] = [1,2,3,4], and nums[2] = [3,4,5,6] are 3 and 4, so we return [3,4].
 * 
 * Example 2
 * Input: nums = [[1,2,3],[4,5,6]]
 * Output: []
 * Explanation: 
 * There does not exist any integer present both in nums[0] and nums[1], so we return an empty list [].
 *
 * 3. Constraints
 * 1 <= nums.length <= 1000
 * 1 <= sum(nums[i].length) <= 1000
 * 1 <= nums[i][j] <= 1000
 * All the values of nums[i] are unique.
 */
public class IntersectionOfMultipleArrays {

  /**
   * 1. Approach 
   * Fixed Length Array. One naive solution is to iterate each array in nums and keep track of all numbers appearing in
   * all arrays till now. However, this approach does not solve the problem of ordering well (Result needs to be sorted).
   * 
   * A few highlights to notice in this problem: 
   * - Constraint 1 <= nums[i][j] <= 1000
   * - Answer needs to be sorted 
   * - Number appearing in all array == Number appears exactly N times for N arrays
   * 
   * Based on the above hints, we could keep track of an array A[i] to define the count of number i in nums array. If 
   * by the end, the count of number i is N, then it is one of the answers we want and it also helps with the sorting
   * requirement. The maximum space required to keep track of all numbers is 101 because of the constraint.
   * 
   * 2. Complexity 
   * - Time O(N x M)
   * - Space O(1)
   * 
   * @param nums
   * @return
   */
  public List<Integer> intersection(int[][] nums) {
    final int[] count = new int[1001];
    final int n = nums.length;

    for (int i = 0; i < n; i++) {
      for (int num : nums[i]) {
        count[num]++;
      }
    }

    final List<Integer> result = new ArrayList<>();

    for (int i = 0; i < 1001; i++) {
      if (n == count[i]) {
        result.add(i);
      }
    }

    return result;
  }
}
