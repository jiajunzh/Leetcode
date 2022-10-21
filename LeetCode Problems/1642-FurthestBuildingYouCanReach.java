package problem;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1. Problem 
 * You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
 *
 * You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
 *
 * While moving from building i to building i+1 (0-indexed),
 *
 * If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
 * If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
 * Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
 *
 * 2. Examples
 * Example 1
 * Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
 * Output: 4
 * Explanation: Starting at building 0, you can follow these steps:
 * - Go to building 1 without using ladders nor bricks since 4 >= 2.
 * - Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
 * - Go to building 3 without using ladders nor bricks since 7 >= 6.
 * - Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
 * It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
 * 
 * Example 2
 * Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
 * Output: 7
 * 
 * Example 3
 * Input: heights = [14,3,19,3], bricks = 17, ladders = 0
 * Output: 3
 *
 * 3. Constraints
 * 1 <= heights.length <= 105
 * 1 <= heights[i] <= 106
 * 0 <= bricks <= 109
 * 0 <= ladders <= heights.length
 */
public class FurthestBuildingYouCanReach {

  /**
   * 1. Approach 
   * MinHeap. It is optimal to always use ladders at largest height. Based on it, we could keep a min heap with the top k 
   * largest height difference. If all ladders is used, then we could use bricks.
   * 
   * 2. Complexity 
   * - Time O(NlogL) where L is the number of ladders available 
   * - Space O(N)
   * 
   * 3. Alternatives 
   * - Binary Search
   *
   * @param heights
   * @param bricks
   * @param ladders
   * @return
   */
  public int furthestBuilding(int[] heights, int bricks, int ladders) {
    final Queue<Integer> queue = new PriorityQueue<>();
    for (int i = 1; i < heights.length; i++) {
      if (heights[i] > heights[i - 1]) {
        queue.offer(heights[i] - heights[i - 1]);
        if (queue.size() > ladders) {
          bricks -= queue.poll();
          if (bricks < 0) return i - 1;
        }
      }
    }
    return heights.length - 1;
  }
}
