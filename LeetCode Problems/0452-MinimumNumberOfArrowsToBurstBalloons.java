package problem;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1. Problem 
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented 
 * as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches 
 * between xstart and xend. You do not know the exact y-coordinates of the balloons.
 *
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. 
 * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the 
 * number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 *
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 *
 * 2. Examples
 * Example 1
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
 * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 * 
 * Example 2
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 * Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
 * 
 * Example 3
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
 * - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 *
 * 3. Constraints
 * 1 <= points.length <= 10^5
 * points[i].length == 2
 * -2^31 <= xstart < xend <= 2^31 - 1
 */
public class MinimumNumberOfArrowsToBurstBalloons {

  /**
   * 1. Approach 
   * Array Sorting. One way to think about it is to sort the array based on the order of Xend. For each element in the
   * array, if the previous Xend is smaller than the current Xstart, then it means we need another arrow here.
   * Point i         | ... |
   * Point i + 1              | ....|
   * 
   * However, another case is Xend is greater than or equal to Xstart. In this case, we don't need another arrow.
   * Point i         | ... |
   * Point i + 1         | ....|
   *
   * 2. Complexity
   * - Time O(NlogN)
   * - Space O(1)
   * 
   * 3. Improvement 
   * - An alternative way to view this problem is sort the array by Xstart first or by Xend to break the tie. For each 
   *  element in the array, 
   *  - if prev[end] < curr[start], then add one more arrow + prev[end] = curr[end]
   *  - if prev[end] >= curr[start], then prev[end] = min(prev[end], curr[end])
   * - An edge case in Comparator if using p1[0] - p2[0] is integer overflow. Use comparison or compareInt explicitly 
   *   to avoid integer overflow.
   * 
   * @param points
   * @return
   */
  public int findMinArrowShots(int[][] points) {
    Arrays.sort(points, (p1, p2) -> Integer.compare(p1[1], p2[1]));

    int minArrows = 1;
    int prevEnd = points[0][1];

    for (int i = 1; i < points.length; i++) {
      int[] point = points[i];
      if (prevEnd < point[0]) {
        minArrows++;
        prevEnd = point[1];
      }
    }

    return minArrows;
  }

  public int findMinArrowShots2(int[][] points) {
    Arrays.sort(points, new Comparator<int[]>() {
      @Override
      public int compare(int[] p1, int[] p2) {
        if (p1[0] == p2[0]) {
          return Integer.compare(p1[1], p2[1]);
        }
        return p1[0] > p2[0] ? 1 : -1;
      }
    });

    int minArrows = 0;
    int[] prev = points[0];

    for (int i = 1; i < points.length; i++) {
      if (prev[1] < points[i][0]) {
        prev = points[i];
        minArrows++;
      } else {
        prev[1] = Math.min(prev[1], points[i][1]);
      }
    }

    return minArrows + 1;
  }
}
