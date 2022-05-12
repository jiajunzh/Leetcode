package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * You are given an integer array nums. You want to maximize the number of points you get by performing the following 
 * operation any number of times:
 *
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1
 * and every element equal to nums[i] + 1. Return the maximum number of points you can earn by applying the above 
 * operation some number of times.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 * 
 * Example 2
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 *
 * 3. Constraints
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 */
public class DeleteAndEarn {
  
  private final Map<Integer, Integer> numCntMap = new HashMap<>();
  private final Map<Integer, Integer> memo = new HashMap<>();

  /**
   * 1. Approach 
   * Top-Down Dynamic Programming with Memoization. We could eliminate the order of elements by aggregating the same 
   * elements together. For example, having a map from num to its occurrence count. 
   * 
   * Define Dp[i] as the maximum points got by iterating through number 1 to i. Then for each num in the map, 
   * we could either select or not select it, which leads to the recurrence relation below:
   * - Select num: Dp[i] = Dp[i - 2] + num * occurrence 
   * - Not Select num: Dp[i] = Dp[i]
   * 
   * 2. Complexity 
   * Given N as the length of nums and K as the maximum element in nums
   * - Time O(N + K)
   * - Space O(N + K)
   * 
   * @param nums
   * @return
   */
  public int deleteAndEarn1(int[] nums) {
    int maxNumber = 0;
    for (int num : nums) {
      numCntMap.put(num, numCntMap.getOrDefault(num, 0) + 1);
      maxNumber = Math.max(num, maxNumber);
    }
    return maxPoint(maxNumber);
  }

  private int maxPoint(final int num) {
    if (num <= 0) return 0;
    if (memo.containsKey(num)) return memo.get(num);

    final int points = num * numCntMap.getOrDefault(num, 0);
    memo.put(num, Math.max(points + maxPoint(num - 2), maxPoint(num - 1)));
    return memo.get(num);
  }

  /**
   * 1. Approach 
   * Bottom-up Dynamic Programming with Optimized Space.
   * 
   * 2. Complexity 
   * Given N as the length of nums and K as the maximum element in nums
   * - Time O(N + K)
   * - Space O(N)
   * 
   * 3. Improvement
   * - For number count map, it improves the time from 20ms to 2 ms by using array instead of HashMap.
   * 
   * 4. Alternative 
   * - Another way is to list out all element in the nums array uniquely and sort the list. Iterate the list one by one,
   *   For each element in the list:
   *   if list[i] = list[i - 1] + 1 => oneBack = Math.max(oneBack, twoBack + list[i])
   *   else => oneBack += list[i]
   *   This approach will generate O(NLogN) time and O(N) space
   *    
   * @param nums
   * @return
   */
  public int deleteAndEarn(int[] nums) {
    int maxNumber = 0;
    for(int num : nums) {
      maxNumber = Math.max(num, maxNumber);
    }
    int[] numCntMap = new int[maxNumber + 1];
    for (int num : nums) {
      numCntMap[num] += num;
    }

    int twoBack = 0;
    int oneBack = numCntMap[1];
    for (int i = 2; i <= maxNumber; i++) {
      int max = Math.max(twoBack + numCntMap[i], oneBack);
      twoBack = oneBack;
      oneBack = max;
    }
    return oneBack;
  }
}
