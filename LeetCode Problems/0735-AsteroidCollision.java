package problem;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1. Problem
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning
 * right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both
 * are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 * 2. Examples
 * Example 1
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 *
 * Example 2
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 *
 * Example 3
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 * 3. Constraints
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 */
public class AsteroidCollision {

    /**
     * 1. Approach
     * Stack
     * 
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        final Deque<Integer> deque = new ArrayDeque<>();
        for (int asteroid : asteroids) {
            if (asteroid > 0) {
                deque.push(asteroid);
            } else {
                while (!deque.isEmpty() && deque.peek() > 0 && deque.peek() + asteroid < 0) {
                    deque.pop();
                }

                if (deque.isEmpty() || deque.peek() < 0) deque.push(asteroid);
                if (!deque.isEmpty() && deque.peek() + asteroid == 0) {
                    deque.pop();
                }
            }
        }
        final int size = deque.size();
        final int[] result = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            result[i] = deque.pop();
        }
        return result;
    }
}
