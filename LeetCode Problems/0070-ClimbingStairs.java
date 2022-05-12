package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * 2. Examples
 * Example 1
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * 
 * Example 2
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 * 3. Constraints
 * 1 <= n <= 45
 */
public class ClimbingStairs {

  private Map<Integer, Integer> memo = new HashMap<>();

  /**
   * 1. Approach 
   * Recursion + Memoization. This is exactly the same problem as problem 0509 Fibonacci Number essentially.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param n
   * @return
   */
  public int climbStairsMemo(int n) {
    if (n < 0) {
      return 0;
    } else if (n <= 1) {
      return 1;
    }

    if (memo.containsKey(n)) {
      return memo.get(n);
    } else {
      int climbN = climbStairsMemo(n - 1) + climbStairsMemo(n - 2);
      memo.put(n, climbN);
      return climbN;
    }
  }

  /**
   * 1. Approach 
   * Dynamic Programming.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param n
   * @return
   */
  public int climbStairs(int n) {
    int first = 1, second = 1;

    for (int i = 2; i <= n; i++) {
      int curr = first + second;
      second = first;
      first = curr;
    }

    return first;
  }
}
