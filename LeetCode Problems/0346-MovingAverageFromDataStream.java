package problem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem 
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 *
 * 2. Examples
 * Example 1
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 *
 * 3. Constraints
 * 1 <= size <= 1000
 * -105 <= val <= 105
 * At most 104 calls will be made to next.
 */
public class MovingAverageFromDataStream {
  
  private final Queue<Integer> queue;
  private final int size;
  private int sum;

  /**
   * 1. Approach 
   * Queue
   * 
   * 2. Complexity 
   * - Time O(1)
   * - Space O(N)
   * 
   * @param size
   */
  public MovingAverageFromDataStream(int size) {
    this.queue = new LinkedList<>();
    this.size = size;
    this.sum = 0;
  }

  public double next(int val) {
    this.queue.offer(val);
    sum += val;
    if (this.queue.size() > this.size) {
      sum -= this.queue.poll();
    }
    return sum * 1.0 / this.queue.size();
  }
}
