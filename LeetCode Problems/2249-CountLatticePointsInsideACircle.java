package problem;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. Problem 
 * Given a 2D integer array circles where circles[i] = [xi, yi, ri] represents the center (xi, yi) and radius ri of the
 * ith circle drawn on a grid, return the number of lattice points that are present inside at least one circle.
 *
 * Note:
 * A lattice point is a point with integer coordinates.
 * Points that lie on the circumference of a circle are also considered to be inside it.
 *
 * 2. Examples
 * Example 1
 * Input: circles = [[2,2,1]]
 * Output: 5
 * Explanation:
 * The figure above shows the given circle.
 * The lattice points present inside the circle are (1, 2), (2, 1), (2, 2), (2, 3), and (3, 2) and are shown in green.
 * Other points such as (1, 1) and (1, 3), which are shown in red, are not considered inside the circle.
 * Hence, the number of lattice points present inside at least one circle is 5.
 * 
 * Example 2
 * Input: circles = [[2,2,2],[3,4,1]]
 * Output: 16
 * Explanation:
 * The figure above shows the given circles.
 * There are exactly 16 lattice points which are present inside at least one circle. 
 * Some of them are (0, 2), (2, 0), (2, 4), (3, 2), and (4, 4).
 *
 * 3. Constraints:
 * 1 <= circles.length <= 200
 * circles[i].length == 3
 * 1 <= xi, yi <= 100
 * 1 <= ri <= min(xi, yi)
 */
public class CountLatticePointsInsideACircle {

  /**
   * 1. Approach
   * Brutal Force + HashSet.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(K) K is number of lattice points
   * 
   * 3. Improvement 
   * - One trick here is to define a point Id in hashset for each pair (i, j) as i * 1000 + k, as based on the 
   *   constraints, the maximum value of i or j is 200. So it is guaranteed that the id is unique for each pair/
   * - Another way to think of this is to calculate the search space (i.e. collect all possible candidate points) and 
   *   then for each point, iterate the circles array to see if any of the circle holds it.
   * 
   * @param circles
   * @return
   */
  public int countLatticePoints(int[][] circles) {
    Set<Integer> latticePoints = new HashSet<>();

    for (int i = 0; i < circles.length; i++) {
      int x = circles[i][0];
      int y = circles[i][1];
      int r = circles[i][2];

      for (int j = x - r; j <= x + r; j++) {
        for (int k = y - r; k <= y + r; k++) {
          if (calculateDistance(x, y, j, k) <= r * r) {
            int pointId = j * 1000 + k;
            if (!latticePoints.contains(pointId)) {
              latticePoints.add(pointId);
            }
          }
        }
      }
    }

    return latticePoints.size();
  }

  private int calculateDistance(int x0, int y0, int x, int y) {
    return (x - x0) * (x - x0) + (y - y0) * (y - y0);
  }
}
