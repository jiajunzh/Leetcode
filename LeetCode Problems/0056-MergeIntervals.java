package problem;

import java.util.Arrays;
import java.util.Stack;

/**
 * 1. Problem 
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an 
 * array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * 2. Examples
 * Example 1
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * 
 * Example 2
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * 3. Constraints
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class MergeIntervals {

  /**
   * 1. Approach 
   * Sort + Stack.
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(N)
   *
   * @param intervals
   * @return
   */
  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (interval1, interval2) -> Integer.compare(interval1[0],interval2[0]));
    Stack<int[]> stack = new Stack<>();
    stack.push(intervals[0]);

    for (int i = 1; i < intervals.length; i++) {
      int[] previous = stack.peek();

      if (previous[1] >= intervals[i][0]) {
        previous[1] = Math.max(previous[1], intervals[i][1]);
      } else {
        stack.push(intervals[i]);
      }
    }

    int[][] mergedArray = new int[stack.size()][2];
    int i = 0;
    while (!stack.isEmpty()) {
      mergedArray[i] = stack.pop();
      i++;
    }

    return mergedArray;
  }
}
