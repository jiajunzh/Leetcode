package problem;

import java.util.LinkedList;
import java.util.List;

/**
 * 1. Problem
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start
 * and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval
 * newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still
 * does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 * 2. Examples
 * Example 1
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 * 3. Constraints
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 105
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 105
 */
public class InsertInterval {

    /**
     * 1. Approach
     * Binary Search
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1) if not consider memory used to store results
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Find the index of interval A whose end is strictly less than new interval's start.
        // Find the index of interval B whose start is strictly greater than new interval's end
        int indexA = findIntervalIndex1(intervals, newInterval[0]);
        int indexB = findIntervalIndex2(intervals, newInterval[1]);

        // All intervals prior to A should be copied over directly to results
        // Construct new interval
        // All intervals post to B (including) should be copied over directly to results
        int newSize = indexA + (intervals.length - indexB) + 1;
        final int[][] newIntervals = new int[newSize][2];
        for (int i = 0; i < indexA; i++) {
            newIntervals[i] = intervals[i];
        }
        if (indexA != intervals.length) newInterval[0] = Math.min(newInterval[0], intervals[indexA][0]);
        if (indexB != 0) newInterval[1] = Math.max(newInterval[1], intervals[indexB - 1][1]);
        newIntervals[indexA] = newInterval;
        for (int i = indexB; i < intervals.length; i++) {
            indexA++;
            newIntervals[indexA] = intervals[i];
        }
        return newIntervals;
    }

    // Find the minimum index of interval whose end is greater than or equal to target
    private int findIntervalIndex1(int[][] intervals, int target) {
        int start = 0, end = intervals.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (intervals[mid][1] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    // Find the minimum index of interval B whose start is strictly greater than new interval's end
    private int findIntervalIndex2(int[][] intervals, int target) {
        int start = 0, end = intervals.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (intervals[mid][0] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    /**
     * 1. Approach
     * One Pass + Merge with Iteration
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1) if not consider memory used to store results
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        final List<int[]> resultList = new LinkedList<>();
        for (int[] interval : intervals) {
            if (newInterval == null || interval[1] < newInterval[0]) {
                resultList.add(interval);
            } else if (interval[0] > newInterval[1]) {
                resultList.add(newInterval);
                resultList.add(interval);
                newInterval = null;
            } else {
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }

        if (newInterval != null) resultList.add(newInterval);

        final int[][] result = new int[resultList.size()][2];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}
