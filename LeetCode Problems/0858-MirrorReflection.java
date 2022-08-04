package problem;

/**
 * 1. Problem 
 * There is a special square room with mirrors on each of the four walls. Except for the southwest corner, there are 
 * receptors on each of the remaining corners, numbered 0, 1, and 2.
 *
 * The square room has walls of length p and a laser ray from the southwest corner first meets the east wall at a
 * distance q from the 0th receptor.
 *
 * Given the two integers p and q, return the number of the receptor that the ray meets first.
 *
 * The test cases are guaranteed so that the ray will meet a receptor eventually.
 *
 * 2. Examples
 * Example 1
 * Input: p = 2, q = 1
 * Output: 2
 * Explanation: The ray meets receptor 2 the first time it gets reflected back to the left wall.
 * 
 * Example 2
 * Input: p = 3, q = 1
 * Output: 1
 *
 * 3. Constraints
 * 1 <= q <= p <= 1000
 */
public class MirrorReflection {

  /**
   * 1. Approach 
   * Math. 
   * 
   * Hint: The mirror could be extended unlimitedly upwards with the reflection rules.
   * See https://leetcode.com/problems/mirror-reflection/discuss/2376191/C%2B%2B-Java-Python-or-Faster-then-100-or-Full-explanations-or
   * 
   * 2. Complexity 
   * - Time O(Min(logP, logQ))
   * - Space O(1)
   * 
   * @param p
   * @param q
   * @return
   */
  public int mirrorReflection(int p, int q) {
    boolean isLeft = false;
    int yDist = q;
    while (true) {
      if (yDist % p == 0) {
        if (!isLeft) return (yDist / p) % 2;
        if ((yDist / p) % 2 == 1) return 2;
      }
      isLeft = !isLeft;
      yDist += q;
    }
  }
}
