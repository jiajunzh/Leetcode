package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1. Problem 
 * Implement a SnapshotArray that supports the following interface:
 *
 * SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 * void set(index, val) sets the element at the given index to be equal to val.
 * int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 * int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 * 2. Examples 
 * Example 1
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 * Explanation: 
 * SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
 * snapshotArr.set(0,5);  // Set array[0] = 5
 * snapshotArr.snap();  // Take a snapshot, return snap_id = 0
 * snapshotArr.set(0,6);
 * snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
 *
 * 3. Constraints
 * 1 <= length <= 50000
 * At most 50000 calls will be made to set, snap, and get.
 * 0 <= index < length
 * 0 <= snap_id < (the total number of times we call snap())
 * 0 <= val <= 10^9
 */
public class SnapshotArray {
  
  private final List<int[]>[] snaps;
  private int currSnapId;

  /**
   * 1. Approach 
   * The first naive idea coming into head is just as simple as keeping a list of integer array and make a new 
   * copy of array at each snap point. However, if the times of snap being called is large, say 5 x 10^4, it will 
   * trigger TLE as each snap, it takes at most O(length) ~ 5 x 10^4. The total amount of time needed is 2.5 x 10^9
   * 
   * The main reason is that we make a copy when snap is being called even when most of the elements in the array are 
   * zero (which is unnecessary). What we could do instead, is to only record the changes occurred on each index as a 
   * list. Then we could provision a array of List<changes>.
   * 
   * When we want to get the value at some index at snap_id, we simply use binary search to find the exact snap_id
   * 
   * 2. Complexity 
   * - Time O(1) for constructor, O(1) for set, O(1) snap, O(logN) where N is the maximum number of changes happening 
   *   at index.
   * - Space O(N * Length)
   * 
   * @param length
   */
  public SnapshotArray(int length) {
    this.snaps = new List[length];
    this.currSnapId = -1;
  }

  public void set(int index, int val) {
    if (this.snaps[index] == null) this.snaps[index] = new ArrayList<>();
    List<int[]> list = this.snaps[index];
    list.add(new int[]{currSnapId, val});
  }

  public int snap() {
    this.currSnapId++;
    return this.currSnapId;
  }

  public int get(int index, int snap_id) {
    List<int[]> list = this.snaps[index];
    if (list == null) return 0;
    // Find the minimum snap id k such that k is greater than or equal to snap_id
    int low = 0, high = list.size();
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (list.get(mid)[0] >= snap_id) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    if (low == 0) return 0;
    return list.get(low - 1)[1];
  }
}

/**
 * 1. Approach 
 * Another similar approach is to use the built-in binary search feature in TreeMap.
 * Remember treemap.floorKey() returns the greatest value that is less than or equal to target.
 * 
 * 2. Complexity 
 * - Time O(1) for constructor, O(logN) for set, O(1) snap, O(logN) where N is the maximum number of changes happening 
 *   at index.
 * - Space O(N * Length)
 */
class SnapshotArray2 {

  private final Map<Integer, TreeMap<Integer, Integer>> map;
  private int currSnapId = -1;

  public SnapshotArray2(int length) {
    this.map = new HashMap<>();
  }

  public void set(int index, int val) {
    final TreeMap<Integer, Integer> snapMap = map.getOrDefault(index, new TreeMap<>());
    snapMap.put(currSnapId, val);
    map.put(index, snapMap);
  }

  public int snap() {
    currSnapId++;
    return currSnapId;
  }

  public int get(int index, int snap_id) {
    if (map.containsKey(index)) {
      final TreeMap<Integer, Integer> snapMap = map.get(index);
      final Integer snapId = snapMap.floorKey(snap_id - 1);
      if (snapId != null) {
        return snapMap.get(snapId);
      }
    }
    return 0;
  }
}
