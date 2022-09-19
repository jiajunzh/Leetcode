package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 *
 * 2. Examples
 * Example 1
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * 
 * Example 2
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 *
 * 3. Constraints
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti < endi <= 106
 */
public class MeetingRooms {

  /**
   * 1. Approach 
   * Sorting 
   * 
   * 2. Complexity 
   * - Time O(NlogN)
   * - Space O(1)
   * 
   * @param intervals
   * @return
   */
  public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] < intervals[i - 1][1]) {
        return false;
      }
    }
    return true;
  }
}
