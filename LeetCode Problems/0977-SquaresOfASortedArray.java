package problem;

/**
 * 1. Problem 
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 * 
 * Example 2
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 * 3. Constraints
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in non-decreasing order.
 *
 * 4. Follow up
 * Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
 */
public class SquaresOfASortedArray {

  /**
   * 1. Approach
   * Two Pointers
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param nums
   * @return
   */
  public int[] sortedSquares(int[] nums) {
    final int n = nums.length;
    int i = 0;
    for (int num : nums) {
      if (num < 0) i++;
    }

    int j = i - 1;
    final int[] result = new int[n];
    for (int k = 0; k < n; k++) {
      int left = j >= 0 ? -nums[j] : Integer.MAX_VALUE;
      int right = i < n ? nums[i] : Integer.MAX_VALUE;
      if (left > right) {
        result[k] = right * right;
        i++;
      } else {
        result[k] = left * left;
        j--;
      }
    }
    return result;
  }
}
