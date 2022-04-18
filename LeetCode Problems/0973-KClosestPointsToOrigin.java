package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1. Problem 
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return 
 * the k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 *
 * 2. Examples
 * Example 1:
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 * 
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 *
 * 3. Constraints:
 * 1 <= k <= points.length <= 104
 * -104 < xi, yi < 104
 */
public class KClosestPointsToOrigin {

  /**
   * 1. Approach 
   * Sort with comparator.
   * 
   * 2. Complexity
   * - Time O(NLogN)
   * - Space O(1)
   * 
   * 3. Improvement
   * - This problem only cares about the K closest points instead of the all points. Instead of having NLogN time, we 
   *   could have NlogK by using PriorityQueue and only keeping track of K elements at a time.
   * 
   * @param points
   * @param k
   * @return
   */
  public int[][] kClosest1(int[][] points, int k) {
    final int[][] results = new int[k][2];
    Arrays.sort(points, (point1, point2) -> getDistance(point1) - getDistance(point2));

    for (int i = 0; i < k; i++) {
      results[i] = points[i];
    }

    return results;
  }

  /**
   * 1. Approach
   * PriorityQueue and Keep track of K elements.
   * 
   * 2. Complexity 
   * - Time O(NLogK)
   * - Space O(K)
   * 
   * 3. Improvement 
   * The first version of this approach store the pure point array into the queue, which leads to re-calculation on 
   * the distance. A little tweak to store int[]{distance, i} instead improves the performance.
   * 
   * @param points
   * @param k
   * @return
   */
  public int[][] kClosest2(int[][] points, int k) {
    final int[][] results = new int[k][2];

    final Queue<int[]> pq = new PriorityQueue<>((x, y) -> y[0] - x[0]);

    for (int i = 0; i < points.length; i++) {
      final int distance = getDistance(points[i]);

      if (pq.size() < k) {
        pq.offer(new int[]{distance, i});
      } else if (distance < pq.peek()[0]) {
        pq.poll();
        pq.offer(new int[]{distance, i});
      }
    }

    int i = 0;
    while (!pq.isEmpty()) {
      results[i] = points[pq.poll()[1]];
      i++;
    }

    return results;
  }

  /**
   * 1. Approach
   * Modified Binary Search. This problem is equals to find the minimum target value V such that there are exactly
   * k elements smaller or equals to V, it gives inspiration on binary search. However, the pure binary search method 
   * will have time complexity as O(NlogN) which is no better than the sorting and priority queue approach.
   * 
   * An optimized approach is to narrow down the points array search range along with binary search. For each iteration,
   * we could separate the points into two list, closer (if element is smaller or equals to target value) or farther (if
   * element is larger than target value). If the size of closer is greater than k, then it means all elements in 
   * farther won't be in the final result, throw them away. If the size is less than k, then it means all elements 
   * within closer will be in the final result, add them all into the result.
   * 
   * 2. Complexity 
   * - Time O(N): N + N/2 + N/4 + .... + N/N < 2N
   * - Space O(N)
   * 
   * 3. Improvement
   * - Precalculate the distance to avoid recalculation => time improvement
   * - Don't forget update boundary of the binary search even though it is not used as much as in other BS
   * 
   * 
   * @param points
   * @param k
   * @return
   */
  public int[][] kClosest3(int[][] points, int k) {
    double low = 0, high = 0;
    double[] distances = new double[points.length];
    List<Integer> remaining = new ArrayList<>();

    for (int i = 0; i < points.length; i++) {
      distances[i] = getDistance(points[i]);
      high = Math.max(high, distances[i]);
      remaining.add(i);
    }

    List<Integer> kClosestPoints = new ArrayList<>();
    while (k > 0) {
      double mid = low + (high - low) / 2;

      List[] splitList = splitArray(remaining, mid, distances);
      List<Integer> closer = splitList[0];
      List<Integer> farther = splitList[1];

      if (closer.size() <= k) {
        kClosestPoints.addAll(closer);
        k -= closer.size();
        remaining = farther;
        low = mid;
      } else {
        remaining = closer;
        high = mid;
      }
    }

    int[][] result = new int[kClosestPoints.size()][2];

    for (int i = 0; i < kClosestPoints.size(); i++) {
      result[i] = points[kClosestPoints.get(i)];
    }

    return result;
  }

  private List[] splitArray(List<Integer> remaining, double targetDistance, double[] distances) {
    List<Integer> closer = new ArrayList<>();
    List<Integer> farther = new ArrayList<>();

    for (Integer pointIndex : remaining) {
      if (distances[pointIndex] > targetDistance) {
        farther.add(pointIndex);
      } else {
        closer.add(pointIndex);
      }
    }

    return new List[]{closer, farther};
  }

  /**
   * 1. Approach
   * Quick Select. Quick Select is inspired by Quick Sort which includes pivoting and partitioning. For each iteration,
   * it partitions the array into three part [<= pivot][pivot][>=pivot]. For each pivot that is returned by the 
   * partition, if p == k, then all element to its left are the results. If p > k, then partition the left else if 
   * p < k, partition the right.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1) => in place
   * 
   * 3. Improvement 
   * - Pivot selection really matters for quick select/quick sort. Middle pivoting has 6ms time while end pivoting has 
   *   90ms time.
   *   
   * @param points
   * @param k
   * @return
   */
  public int[][] kClosest4(int[][] points, int k) {
    int left = 0, right = points.length - 1;
    int[][] result = new int[k][2];

    while (left < right) {
      int pivot = partition(points, left, right);

      if (pivot == k) {
        break;
      } else if (pivot < k) {
        left = pivot + 1;
      } else {
        right = pivot - 1;
      }
    }

    for (int i = 0; i < k; i++) {
      result[i] = points[i];
    }

    return result;
  }

  private int partition(int[][] points, int start, int end) {
    int left = start, right = end;
    int mid = left + (right - left) / 2;
    swap(points, mid, end);
    int distance = getDistance(points[end]);

    while (left < right) {
      while (left < right && getDistance(points[left]) <= distance) {
        left++;
      }

      while (left < right && getDistance(points[right]) >= distance) {
        right--;
      }

      swap(points, left, right);
    }

    swap(points, left, end);

    return left;
  }

  private void swap(int[][] points, int index1, int index2) {
    int[] tmp = points[index1];
    points[index1] = points[index2];
    points[index2] = tmp;
  }

  private int getDistance(final int[] point) {
    return point[0] * point[0] + point[1] * point[1];
  }
}
