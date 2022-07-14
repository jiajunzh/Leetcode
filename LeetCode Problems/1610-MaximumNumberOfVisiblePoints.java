package problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1. Problem 
 * You are given an array points, an integer angle, and your location, where location = [posx, posy] and 
 * points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.
 *
 * Initially, you are facing directly east from your position. You cannot move from your position, but you can rotate.
 * In other words, posx and posy cannot be changed. Your field of view in degrees is represented by angle, determining 
 * how wide you can see from any given view direction. Let d be the amount in degrees that you rotate counterclockwise.
 * Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].
 *
 * You can see some set of points if, for each point, the angle formed by the point, your position, and the immediate 
 * east direction from your position is in your field of view.
 *
 * There can be multiple points at one coordinate. There may be points at your location, and you can always see these 
 * points regardless of your rotation. Points do not obstruct your vision to other points.
 *
 * Return the maximum number of points you can see.
 *
 * 2. Examples 
 * Example 1
 * Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
 * Output: 3
 * Explanation: The shaded region represents your field of view. All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.
 * 
 * Example 2
 * Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
 * Output: 4
 * Explanation: All points can be made visible in your field of view, including the one at your location.
 * 
 * Example 3
 * Input: points = [[1,0],[2,1]], angle = 13, location = [1,1]
 * Output: 1
 * Explanation: You can only see one of the two points, as shown above.
 *
 * 3. Constraints
 * 1 <= points.length <= 105
 * points[i].length == 2
 * location.length == 2
 * 0 <= angle < 360
 * 0 <= posx, posy, xi, yi <= 100
 */
public class MaximumNumberOfVisiblePoints {

  /**
   * 1. Approach 
   * Sliding Window 
   * 
   * 2. Complexity 
   * - Time O(NlogN)
   * - Space O(N)
   * 
   * @param points
   * @param angle
   * @param location
   * @return
   */
  public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
    final int n = points.size();
    final List<Double> degrees = new ArrayList<>();
    int visiblePoints = 0;
    for (int i = 0; i < n; i++) {
      final List<Integer> point = points.get(i);
      if (point.get(0) == location.get(0) && point.get(1) == location.get(1)) {
        visiblePoints++;
      } else {
        degrees.add(getDegree(points.get(i), location));
      }
    }
    Collections.sort(degrees);
    int i = 0, j = 0, maxPoints = 0;
    int size = degrees.size();
    while (i < size) {
      while (j - size < i && (degrees.get(j % size) - degrees.get(i) + 360) % 360 <= angle) {
        j++;
      }
      maxPoints = Math.max(maxPoints, j - i);
      i++;
    }
    return visiblePoints + maxPoints;
  }

  private double getDegree(final List<Integer> point, final List<Integer> origin) {
    return (Math.toDegrees(Math.atan2(point.get(1) - origin.get(1), point.get(0) - origin.get(0))) + 360) % 360;
  }
}
