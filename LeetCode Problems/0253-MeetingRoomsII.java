package problem;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1. Problem 
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 *
 * 2. Examples
 * Example 1
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 * 
 * Example 2
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 *
 * 3. Constraints
 * 1 <= intervals.length <= 104
 * 0 <= starti < endi <= 106
 */
public class MeetingRoomsII {

  /**
   * 1. Approach 
   * Prefix Sum
   * 
   * 2. Complexity 
   * - Time O(N + M)
   * - Space O(M)
   * 
   * @param intervals
   * @return
   */
  public int minMeetingRooms(int[][] intervals) {
    int max = 0;
    for (int[] interval : intervals) max = Math.max(max, interval[1]);
    final int[] counts = new int[max + 1];
    for (int[] interval : intervals) {
      counts[interval[0]]++;
      counts[interval[1]]--;
    }
    int meetingRooms = counts[0];
    for (int i = 1; i <= max; i++) {
      counts[i] += counts[i - 1];
      meetingRooms = Math.max(counts[i], meetingRooms);
    }
    return meetingRooms;
  }

  /**
   * 1. Approach
   * Priority Queue + Array Sort 
   * 
   * PriorityQueue stores all occupied meeting room's end time, after which this room will be available for reuse.
   * If the currently occupied meeting room ends earlier than the current interval's start time, then we could simply 
   * reuse this room except that the new end time will be updated. Otherwise, we create a new room with a new end time.
   * 
   * 2. Complexity 
   * - Time O(NlogN)
   * - Space O(N)
   * 
   * @param intervals
   * @return
   */
  public int minMeetingRooms2(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
    final Queue<Integer> pq = new PriorityQueue<>();
    pq.offer(intervals[0][1]);
    for (int i = 1; i < intervals.length; i++) {
      final int minEnd = pq.peek();
      if (minEnd <= intervals[i][0]) {
        pq.poll();
      }
      pq.offer(intervals[i][1]);
    }
    return pq.size();
  }

  /**
   * 1. Approach 
   * Chronological Time. This approach separates start and end time into two arrays and iterate them as if we are looking 
   * at them chronologically. When start index or end index is hit, we pretend that the current time is startTime[start]
   * or endTime[end].
   * 
   * Thus, if startTime[start] < endTime[end], we know that another meeting starts and we increase meeting room count by one
   * Otherwise, we know a meeting ending at endTime[end] finishes and a meeting room should be released, thus count--
   * 
   * 2. Complexity 
   * - Time O(NLogN)
   * - Space O(N)
   * 
   * @param intervals
   * @return
   */
  public int minMeetingRooms3(int[][] intervals) {
    final int[] startTime = new int[intervals.length];
    final int[] endTime = new int[intervals.length];
    for (int i = 0; i < intervals.length; i++) {
      startTime[i] = intervals[i][0];
      endTime[i] = intervals[i][1];
    }
    Arrays.sort(startTime);
    Arrays.sort(endTime);
    int start = 0, end = 0, count = 0, meetingRooms = 0;
    while (start < intervals.length) {
      if (startTime[start] < endTime[end]) {
        count++;
        start++;
        meetingRooms = Math.max(meetingRooms, count);
      } else {
        count--;
        end++;
      }
    }
    return meetingRooms;
  }
}
