package problem;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * 1. Problem
 * There is a hotel with n rooms. The rooms are represented by a 2D integer array rooms where 
 * rooms[i] = [roomIdi, sizei] denotes that there is a room with room number roomIdi and size equal to sizei. 
 * Each roomIdi is guaranteed to be unique.
 *
 * You are also given k queries in a 2D array queries where queries[j] = [preferredj, minSizej]. The answer to the jth 
 * query is the room number id of a room such that:
 *
 * The room has a size of at least minSizej, and
 * abs(id - preferredj) is minimized, where abs(x) is the absolute value of x.
 * If there is a tie in the absolute difference, then use the room with the smallest such id. If there is no such room, 
 * the answer is -1.
 *
 * Return an array answer of length k where answer[j] contains the answer to the jth query.
 *
 * 2. Example 
 * Example 1:
 * Input: rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
 * Output: [3,-1,3]
 * Explanation: The answers to the queries are as follows:
 * Query = [3,1]: Room number 3 is the closest as abs(3 - 3) = 0, and its size of 2 is at least 1. The answer is 3.
 * Query = [3,3]: There are no rooms with a size of at least 3, so the answer is -1.
 * Query = [5,2]: Room number 3 is the closest as abs(3 - 5) = 2, and its size of 2 is at least 2. The answer is 3.
 * 
 * Example 2:
 * Input: rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
 * Output: [2,1,3]
 * Explanation: The answers to the queries are as follows:
 * Query = [2,3]: Room number 2 is the closest as abs(2 - 2) = 0, and its size of 3 is at least 3. The answer is 2.
 * Query = [2,4]: Room numbers 1 and 3 both have sizes of at least 4. The answer is 1 since it is smaller.
 * Query = [2,5]: Room number 3 is the only room with a size of at least 5. The answer is 3.
 *
 * 3. Constraints
 * n == rooms.length
 * 1 <= n <= 105
 * k == queries.length
 * 1 <= k <= 104
 * 1 <= roomIdi, preferredj <= 107
 * 1 <= sizei, minSizej <= 107
 */
public class ClosestRoom {

  /**
   * 1. Approach 
   * Sort + TreeSet/Binary Search. Each query could be separated into two steps:
   * 
   * 1) Find all the ids with size greater than the queried size
   * 2) Find the closest id to the queried id 
   * 
   * For the first step, a very naive way is to use brutal force, iterating the rooms array for each query. It leads
   * to O(R * Q) ~ 10^9. The other way to think about is array sorting which means O(RlogR, QlogQ) ~ 10^5 for highest
   * size to lowest. 
   * 
   * For the second step, we would like to keep an ordered set (TreeSet for example) to keep all applicable ids. For 
   * each query, it is guaranteed that the current tree set contains only ids with size greater than the queried size.
   * 
   * 2. Complexity
   * - Time O(RlogR + QlogQ + QlogR + RlogR) RlogR => iterate R times, each insertion in TreeSet is logR.
   * - Space O(R)
   * 
   * 3. Improvement
   * - TreeSet needs to be explicitly typed as Set does not have method such as floor or ceiling
   * - High/low initial bound needs to make sure abs(high-pid) and abs(low-pid) is greater than all possible values
   *   which is 10^7, as pid could be 10^7, so that high must be at least 2 x 10^7.
   * 
   * @param rooms
   * @param queries
   * @return
   */
  public int[] closestRoom(int[][] rooms, int[][] queries) {
    // 1. Sort Rooms Array 
    // 2. Sort Queries Array => For each query, we want to find all rooms with size greater than or equals to queries[i][1].
    // 3. Build A Set with order to store all the applicable Ids
    // 4. Binary Search among the tree set
    final int r = rooms.length;
    final int q = queries.length;
    final int[] closestRooms = new int[q];
    for (int i = 0; i < q; i++) {
      queries[i] = new int[]{ queries[i][0], queries[i][1], i};
    }

    Arrays.sort(rooms, (room1, room2) -> room2[1] - room1[1]);
    Arrays.sort(queries, (query1, query2) -> query2[1] - query1[1]);

    int j = 0;
    final TreeSet<Integer> orderedIdSet = new TreeSet<>();
    for (int[] query: queries) {
      int requiredSize = query[1];
      int index = query[2];
      while (j < r && rooms[j][1] >= requiredSize) {
        orderedIdSet.add(rooms[j][0]);
        j++;
      }

      if (orderedIdSet.isEmpty()) {
        closestRooms[index] = -1;
      } else {
        int qid = query[0];
        int high = 20000000;
        int low = -20000000;

        if (orderedIdSet.floor(qid) != null) low = orderedIdSet.floor(qid);
        if (orderedIdSet.ceiling(qid) != null) high = orderedIdSet.ceiling(qid);

        if (high - qid >= qid - low) {
          closestRooms[index] = low;
        } else {
          closestRooms[index] = high;
        }
      }
    }

    return closestRooms;
  }
  
  /**
   * 1. Approach
   * Brutal Force. Time Limit Exceeded.
   * 
   * 2. Complexity 
   * - Time O(R * Q) ~ 10^9
   * - Space O(1)
   *
   * @param rooms
   * @param queries
   * @return
   */
  public int[] closestRoomBrutalForce(int[][] rooms, int[][] queries) {
    final int[] closestRooms = new int[queries.length];

    for (int i = 0; i < queries.length; i++) {
      closestRooms[i] = getClosestRoom(rooms, queries[i]);
    }

    return closestRooms;
  }

  public int getClosestRoom(int[][] rooms, int[] query) {
    int preferredId = query[0];
    int preferredSize = query[1];
    int distance = Integer.MAX_VALUE;
    int closestRoom = -1;

    for (final int[] room : rooms) {
      if (room[1] >= preferredSize) {
        int tmp = Math.abs(room[0] - preferredId);
        if (tmp < distance) {
          closestRoom = room[0];
          distance = tmp;
        } else if (tmp == distance) {
          closestRoom = Math.min(room[0], closestRoom);
        }
      }
    }

    return closestRoom;
  }
}
