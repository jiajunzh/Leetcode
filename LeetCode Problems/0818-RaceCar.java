package problem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 1. Problem 
 * Your car starts at position 0 and speed +1 on an infinite number line. Your car can go into negative positions. 
 * Your car drives automatically according to a sequence of instructions 'A' (accelerate) and 'R' (reverse):
 *
 * When you get an instruction 'A', your car does the following:
 * position += speed
 * speed *= 2
 * When you get an instruction 'R', your car does the following:
 * If your speed is positive then speed = -1
 * otherwise speed = 1
 * Your position stays the same.
 * For example, after commands "AAR", your car goes to positions 0 --> 1 --> 3 --> 3, and your speed goes
 * to 1 --> 2 --> 4 --> -1.
 *
 * Given a target position target, return the length of the shortest sequence of instructions to get there.
 *
 * 2. Examples 
 * Example 1
 * Input: target = 3
 * Output: 2
 * Explanation: 
 * The shortest instruction sequence is "AA".
 * Your position goes from 0 --> 1 --> 3.
 * 
 * Example 2
 * Input: target = 6
 * Output: 5
 * Explanation: 
 * The shortest instruction sequence is "AAARA".
 * Your position goes from 0 --> 1 --> 3 --> 7 --> 7 --> 6.
 *
 * 3. Constraints
 * 1 <= target <= 104
 */
public class RaceCar {

  /**
   * 1. Approach 
   * BFS with 3 optimizations:
   * - Optimization 1: de-dup node with the same position and speed
   * - Optimization 2: prune the search space by removing the cases where the direction is negative
   * - Optimization 3: prune the cases where position exceeds twice of the target
   * 
   * 2. Complexity 
   * - Time O(2^D) where D is the length of the shortest sequence of instructions 
   * - Space O(2^D)
   * 
   * @param target
   * @return
   */
  public int racecar(int target) {
    int length = 0;
    final Set<String> visited = new HashSet<>(); // Optimization 1: de-dup node with the same position and speed
    final Queue<State> queue = new LinkedList<>();
    queue.offer(new State(0, 1));
    visited.add("0_1");
    visited.add("0_-1"); // Optimization 2: prune the search space by removing the cases where the direction is negative
    while (!queue.isEmpty()) {
      final int size = queue.size();
      for (int i = 0; i < size; i++) {
        final State curr = queue.poll();
        if (curr.position == target) return length;
        final int positionA = curr.position + curr.speed;
        final int speedA = curr.speed * 2;
        if (positionA < 2 * target) {
          queue.offer(new State(positionA, speedA)); // Optimization 3: prune the cases where position exceeds twice of the target
        }
        final int positionR = curr.position;
        final int speedR = curr.speed > 0 ? -1 : 1;
        final String keyR = positionR + "_" + speedR;
        if (!visited.contains(keyR)) {
          queue.offer(new State(positionR, speedR));
          visited.add(keyR);
        }
      }
      length++;
    }

    return -1;
  }

  private static class State {
    private int position;
    private int speed;

    private State(int position, int speed) {
      this.position = position;
      this.speed = speed;
    }
  }

  /**
   * 1. Approach 
   * Dynamic Programming. Three cases to consider:
   * 
   * (1) Keep forwarding until it hits target. Assume we end up with N forward instructions such as AAA...AAA when hitting
   * target, we will have a sequence as below:
   * 1 | 2^1 | 2^2 | ... | 2^(n - 1)
   * The total distance that the car could run through is 2^n - 1 and it equals to target in this case. 
   * (2) Keep forwarding until the car surpasses the target and then turns back to hit the target. Then we will have 
   * a sequence of instructions such as [AAA...AAA]R[AAA..AAA]
   * 1 | 2^1 | 2^2 | ... | .... | 2^(n - 1) | ->>>>
   *           target!
   *         | 2^(rest - 1) | ... | 2^1 | 1 | <<<<-
   * In this case, the turning point could be treated as the new origin. Then length of the second trace becomes 
   * dp[2^rest - 1] = dp[2^n - 1 - t]. Then the total dp[t] = n + 1 + dp[2^n - 1 - t]
   * (3) Keep forwarding before the car surpasses the target and then turns back a little and turns back again to 
   * hit the target. Then we will have a sequence of instructions such as [AAA...AAA]R[AAA...AAA]R[AAA...AAA].
   * 1 | 2^1 | 2^2 | ... | .... | 2^(n - 2) | ->>>>
   *                                                  target!
   *            | 2^(m - 1) | ... | 2^1 | 1 | <<<<-
   *            1 | 2^1 | 2^2 | ... | .... | 2^(n - 1) | ->>>>
   * dp[t] = n + m + 1 + dp[t - 2^(n - 1) + 2^m] where m in [0, n - 2]
   *  
   * 2. Complexity 
   * - Time O(TlogT)
   * - Space O(T)
   * 
   * @param target
   * @return
   */
  public int racecar2(int target) {
    final int[] dp = new int[target + 1];
    for (int t = 1; t <= target; t++) {
      final int n = (int) Math.ceil((Math.log(t + 1) / Math.log(2)));
      if (t + 1 == (1 << n)) {
        dp[t] = n;
        continue;
      }
      dp[t] = n + 1 + dp[(1 << n) - 1 - t];
      for (int m = 0; m < n - 1; m++) {
        // Starting from m = zero has the effect of reducing the speed to 1 without changing direction (it takes two extra instructions for back and forth though)
        dp[t] = Math.min(dp[t], n + m + 1 + dp[t - (1 << (n - 1)) + (1 << m)]);
      }
    }
    return dp[target];
  }
}
