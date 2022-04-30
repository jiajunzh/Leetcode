package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingScheduler {

  /**
   * 1. Approach
   * Two Pointers + Array Sorting.
   * 
   * Having two pointers i and j pointing to the current iterated slot. For each iteration:
   * - start = max(slot1[0], slot2[0])
   * - end = min(slot1[1], slot2[1])
   * - if end - start >= duration, meaning that we find a slot 
   * - if duration > end - start >= 0, meaning there is overlap but overlap not sufficient
   * - if end - start < 0, meaning there is no overlap between slot1 and slot2
   * - For whichever the slot[1] is smaller, iterate to the next slot.
   *
   * 2. Complexity 
   * - Time O(NlogN + MlogM)
   * - Space O(1)
   * 
   * @param slots1
   * @param slots2
   * @param duration
   * @return
   */
  public List<Integer> minAvailableDurationTwoPointers(int[][] slots1, int[][] slots2, int duration) {
    final List<Integer> result = new ArrayList<>();

    Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
    Arrays.sort(slots2, (a, b) -> a[0] - b[0]);

    int i = 0, j = 0;
    while (i < slots1.length && j < slots2.length) {
      int start = Math.max(slots1[i][0], slots2[j][0]);
      int end = Math.min(slots1[i][1], slots2[j][1]);
      if (end - start >= duration) {
        result.add(start);
        result.add(start + duration);
        break;
      } else {
        if (slots1[i][1] < slots2[j][1]) {
          i++;
        } else {
          j++;
        }
      }
    }

    return result;
  }

  /**
   * 1. Approach 
   * Heap/PriorityQueue. This approach keeps all time slots in a priority queue which is sorted by the start time of the
   * time slot. With that, if there is any overlapping found between the two time slots, we could guarantee that the two
   * time slots are from two different persons (The problem asserts that there could not be any overlapping between time
   * slots from the same person).
   * 
   * 2. Complexity
   * - Time O((M + N) log (M + N))
   * - Space O(M + N)
   * 
   * @param slots1
   * @param slots2
   * @param duration
   * @return
   */
  public List<Integer> minAvailableDurationHeap(int[][] slots1, int[][] slots2, int duration) {
    final PriorityQueue<int[]> timeSlots = new PriorityQueue<>((slot1, slot2) -> slot1[0] - slot2[0]);

    for (int[] slot1 : slots1) {
      if (slot1[1] - slot1[0] >= duration) {
        timeSlots.add(slot1);
      }
    }

    for (int[] slot2 : slots2) {
      if (slot2[1] - slot2[0] >= duration) {
        timeSlots.add(slot2);
      }
    }

    final List<Integer> result = new ArrayList<>();
    while (timeSlots.size() > 1) {
      int[] slot1 = timeSlots.poll();
      int[] slot2 = timeSlots.peek();

      int start = Math.max(slot1[0], slot2[0]);
      int end = Math.min(slot1[1], slot2[1]);

      if (end - start >= duration) {
        result.add(start);
        result.add(start + duration);
        break;
      }
    }
    return result;
  }
}
