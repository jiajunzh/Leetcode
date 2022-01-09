package problem;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. Problem
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence. You must 
 * write an algorithm that runs in O(n) time.
 * 
 * 2. Examples
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * 
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 *
 * 3. Constraints
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class LongestConsecutiveSequence {

  /**
   * HashSet.
   * 
   * Time: O(n). Although the time complexity appears to be quadratic due to the while loop nested within the for loop, 
   * closer inspection reveals it to be linear. Because the while loop is reached only when currentNum marks the 
   * beginning of a sequence (i.e. currentNum-1 is not present in nums), the while loop can only run for nn iterations 
   * throughout the entire runtime of the algorithm. This means that despite looking like O(n \cdot n)O(n⋅n) complexity, 
   * the nested loops actually run in O(n + n) = O(n)O(n+n)=O(n) time. All other computations occur in constant time, 
   * so the overall runtime is linear.
   *
   * Space complexity : O(n).
   * 
   * @param nums
   * @return
   */
  private static int longestConsecutive(int[] nums) {
    final Set<Integer> numSet = new HashSet<>();
    int result = 0;

    for (final int num : nums) {
      numSet.add(num);
    }

    for (final int num : nums) {
      if (!numSet.contains(num - 1)) {
        int curr = num;
        int numOfConsecutiveNumbers = 0;

        while (numSet.contains(curr)) {
          numOfConsecutiveNumbers++;
          curr++;
        }

        result = Math.max(result, numOfConsecutiveNumbers);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    assert(longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}) == 9);
  }
}
