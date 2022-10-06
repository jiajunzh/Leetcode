package problem;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1. Problem
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i]
 * is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for 
 * which this is possible, keep answer[i] == 0 instead.
 *
 * 2. Examples
 * Example 1
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * 
 * Example 2
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * 
 * Example 3
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 * 3. Constraints
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {

  /**
   * 1. Approach 
   * Stack
   * 
   * 2. Complexity
   * - Time O(N)
   * - space O(N)
   * 
   * 3. Alternative 
   * - Array Based Space Optimized => In this approach, we scan from right to left. This means we will have all answers 
   *   to the right of the current wanted answer i. With that we could use it to jump to look up for the next warm day
   *   day += answers[day + i]
   *   
   * @param temperatures
   * @return
   */
  public int[] dailyTemperatures(int[] temperatures) {
    final int[] result = new int[temperatures.length];
    final Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < temperatures.length; i++) {
      while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
        final int prevDay = stack.pop();
        result[prevDay] = i - prevDay;
      }
      stack.push(i);
    }
    return result;
  }
}
