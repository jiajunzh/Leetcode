package problem;

import java.util.Arrays;
import java.util.Stack;

/**
 * 1. Problem 
 * There are n cars traveling at different speeds in the same direction along a one-lane road. You are given an array 
 * cars of length n, where cars[i] = [positioni, speedi] represents:
 *
 * positioni is the distance between the ith car and the beginning of the road in meters. It is guaranteed that 
 * positioni < positioni+1.
 * speedi is the initial speed of the ith car in meters per second.
 * For simplicity, cars can be considered as points moving along the number line. Two cars collide when they occupy the 
 * same position. Once a car collides with another car, they unite and form a single car fleet. The cars in the formed 
 * fleet will have the same position and the same speed, which is the initial speed of the slowest car in the fleet.
 *
 * Return an array answer, where answer[i] is the time, in seconds, at which the ith car collides with the next car, 
 * or -1 if the car does not collide with the next car. Answers within 10-5 of the actual answers are accepted.
 *
 * 2. Examples
 * Example 1
 * Input: cars = [[1,2],[2,1],[4,3],[7,2]]
 * Output: [1.00000,-1.00000,3.00000,-1.00000]
 * Explanation: After exactly one second, the first car will collide with the second car, and form a car fleet with 
 * speed 1 m/s. After exactly 3 seconds, the third car will collide with the fourth car, and form a car fleet with 
 * speed 2 m/s.
 * 
 * Example 2
 * Input: cars = [[3,4],[5,4],[6,3],[9,1]]
 * Output: [2.00000,1.00000,1.50000,-1.00000]
 *
 * 3. Constraints
 * 1 <= cars.length <= 105
 * 1 <= positioni, speedi <= 106
 * positioni < positioni+1
 */
public class CarFleetII {

  /**
   * 1. Approach 
   * Monotonic Stack. One observation in this problem is for each car, the collision time only depend on the car post 
   * to it rather than cars prior to it as it does not change its position and speed by being hit by the previous car.
   * With it, we could iterate from the end to start and maintain a monotonic stack all the time to keep track of all 
   * the collision candidate for car i.
   * 
   * Apparently, if the car i + 1 hits the car i + 2 first before car i could hit car i + 1, then the car i will hit 
   * car i + 2 eventually instead of car i + 1. In that case, it is guaranteed that all cars before i will not be able
   * to hit car i + 1. In the stack scenario, we could pop the car i + 1 as it is useless for car [0, i].
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(N)
   * 
   * @param cars
   * @return
   */
  public double[] getCollisionTimes(int[][] cars) {
    final Stack<Integer> stack = new Stack<>();
    final double[] result = new double[cars.length];
    Arrays.fill(result, -1.00000);
    for (int i = cars.length - 1; i >= 0; i--) {
      while (!stack.isEmpty()) {
        int nextCar = stack.peek();
        if (cars[nextCar][1] >= cars[i][1]) {
          stack.pop();
          continue;
        }
        double collisionTime = (cars[nextCar][0] - cars[i][0]) / (double) (cars[i][1] - cars[nextCar][1]);
        if (collisionTime < result[nextCar] || result[nextCar] < 0) {
          result[i] = collisionTime;
          break;
        }
        stack.pop();
      }
      stack.push(i);
    }
    return result;
  }
}
