package problem;

/**
 * 1. Problem
 * Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of 
 * the ith balloon.
 *
 * Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she asks 
 * Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed integer array
 * neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon from the rope.
 *
 * Return the minimum time Bob needs to make the rope colorful.
 *
 * 2. Examples
 * Example 1
 * Input: colors = "abaac", neededTime = [1,2,3,4,5]
 * Output: 3
 * Explanation: In the above image, 'a' is blue, 'b' is red, and 'c' is green.
 * Bob can remove the blue balloon at index 2. This takes 3 seconds.
 * There are no longer two consecutive balloons of the same color. Total time = 3.
 * 
 * Example 2
 * Input: colors = "abc", neededTime = [1,2,3]
 * Output: 0
 * Explanation: The rope is already colorful. Bob does not need to remove any balloons from the rope.
 * 
 * Example 3
 * Input: colors = "aabaa", neededTime = [1,2,3,4,1]
 * Output: 2
 * Explanation: Bob will remove the ballons at indices 0 and 4. Each ballon takes 1 second to remove.
 * There are no longer two consecutive balloons of the same color. Total time = 1 + 1 = 2.
 *
 * 3. Constraints
 * n == colors.length == neededTime.length
 * 1 <= n <= 105
 * 1 <= neededTime[i] <= 104
 * colors contains only lowercase English letters.
 */
public class MinimumTimeToMakeRopeColorful {

  /**
   * 1 Approach
   * Greedy One Pass
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param colors
   * @param neededTime
   * @return
   */
  public int minCost(String colors, int[] neededTime) {
    final int n = colors.length();
    int minCost = 0;
    char lastBallon = colors.charAt(0);
    int lastBallonNeededTime = neededTime[0];
    for (int i = 1; i < n; i++) {
      if (colors.charAt(i) == lastBallon) {
        minCost += Math.min(lastBallonNeededTime, neededTime[i]);
        lastBallonNeededTime = Math.max(lastBallonNeededTime, neededTime[i]);
      } else {
        lastBallonNeededTime = neededTime[i];
      }
      lastBallon = colors.charAt(i);
    }
    return minCost;
  }
}
