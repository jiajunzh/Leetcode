package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1. Approach
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a
 * distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. 
 * Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, 
 * which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. 
 * Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 *
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, 
 * [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one 
 * in the final output as such: [...,[2 3],[4 5],[12 7],...]
 *
 * 2. Examples
 * Example 1
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
 * 
 * Example 2
 * Input: buildings = [[0,2,3],[2,5,3]]
 * Output: [[0,3],[5,0]]
 *
 * 3. Constraints
 * 1 <= buildings.length <= 104
 * 0 <= lefti < righti <= 231 - 1
 * 1 <= heighti <= 231 - 1
 * buildings is sorted by lefti in non-decreasing order.
 */
public class TheSkylineProblem {

  /**
   * 1. Approach 
   * PriorityQueue + Sweep Line. Construct an array that stores all index of either left/right edge of each building.
   * 
   * For each index:
   * 1) Put all buildings starting from this index into PriorityQueue
   * 2) Clean up buildings from pq whose right edge is before or at this index as they are no longer useful
   * 3) Query the tallest building and add result.
   * 
   * 2. Complexity
   * - Time O(NlogN)
   * - Space O(N)
   * 
   * @param buildings
   * @return
   */
  public List<List<Integer>> getSkyline(int[][] buildings) {
    final List<int[]> edges = new ArrayList<>();
    for (int i = 0; i < buildings.length; i++) {
      final int[] building = buildings[i];
      edges.add(new int[]{building[0], i});
      edges.add(new int[]{building[1], i});
    }
    edges.sort((a, b) -> (a[0] - b[0]));
    final PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[2] - a[2]));
    final List<List<Integer>> skyline = new ArrayList<>();
    int currHeight = 0;
    int edgeIndex = 0;
    while (edgeIndex < edges.size()) {
      int currX = edges.get(edgeIndex)[0];
      while (edgeIndex < edges.size() && edges.get(edgeIndex)[0] == currX) {
        int index = edges.get(edgeIndex)[1];
        if (currX == buildings[index][0]) {
          pq.offer(buildings[index]);
        }
        edgeIndex++;
      }
      while (!pq.isEmpty() && pq.peek()[1] <= currX) {
        pq.poll();
      }
      int nextHeight = pq.isEmpty() ? 0 : pq.peek()[2];
      if (nextHeight != currHeight) {
        skyline.add(List.of(currX, nextHeight));
      }
      currHeight = nextHeight;
    }
    return skyline;
  }

  /**
   * 1. Approach 
   * Divide and Conquer.
   * 
   * 1) Split the whole problem into two
   * 2) Solve left and right part of the problem
   * 3) Merge the two parts into one
   * 
   * 2. Complexity
   * - Time O(NlogN)
   * - Space O(N)
   * 
   * @param buildings
   * @return
   */
  public List<List<Integer>> getSkyline2(int[][] buildings) {
    return getSkyline(buildings, 0, buildings.length - 1);
  }

  private List<List<Integer>> getSkyline(int[][] buildings, int left, int right) {
    if (left == right) {
      final List<List<Integer>> skyline = new ArrayList<>();
      skyline.add(List.of(buildings[left][0], buildings[left][2]));
      skyline.add(List.of(buildings[left][1], 0));
      return skyline;
    }
    int mid = left + (right - left) / 2;
    final List<List<Integer>> leftSkylines = getSkyline(buildings, left, mid);
    final List<List<Integer>> rightSkylines = getSkyline(buildings, mid + 1, right);
    return mergeSkyline(leftSkylines, rightSkylines);
  }

  private List<List<Integer>> mergeSkyline(final List<List<Integer>> leftSkylines, final List<List<Integer>> rightSkylines) {
    final List<List<Integer>> skylines = new ArrayList<>();
    int i = 0, j = 0;
    int currHeight = 0;
    int currX;
    int prevLeftHeight = 0, prevRightHeight = 0;
    while (i < leftSkylines.size() && j < rightSkylines.size()) {
      int nextHeight;
      List<Integer> leftSkyline = leftSkylines.get(i);
      List<Integer> rightSkyline = rightSkylines.get(j);
      if (leftSkyline.get(0) < rightSkyline.get(0)) {
        prevLeftHeight = leftSkyline.get(1);
        currX = leftSkyline.get(0);
        nextHeight = Math.max(prevLeftHeight, prevRightHeight);
        i++;
      } else if (leftSkyline.get(0) > rightSkyline.get(0)) {
        prevRightHeight = rightSkyline.get(1);
        currX = rightSkyline.get(0);
        nextHeight = Math.max(prevLeftHeight, prevRightHeight);
        j++;
      } else {
        prevLeftHeight = leftSkyline.get(1);
        prevRightHeight = rightSkyline.get(1);
        currX = leftSkyline.get(0);
        nextHeight = Math.max(prevLeftHeight, prevRightHeight);
        i++;
        j++;
      }
      if (nextHeight != currHeight) {
        skylines.add(List.of(currX, nextHeight));
      }
      currHeight = nextHeight;
    }

    while (i < leftSkylines.size()) {
      skylines.add(leftSkylines.get(i));
      i++;
    }
    while (j < rightSkylines.size()) {
      skylines.add(rightSkylines.get(j));
      j++;
    }
    return skylines;
  }
}
