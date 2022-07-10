package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * You are given a 0-indexed integer array buses of length n, where buses[i] represents the departure time of the ith bus.
 * You are also given a 0-indexed integer array passengers of length m, where passengers[j] represents the arrival time of 
 * the jth passenger. All bus departure times are unique. All passenger arrival times are unique.
 *
 * You are given an integer capacity, which represents the maximum number of passengers that can get on each bus.
 *
 * The passengers will get on the next available bus. You can get on a bus that will depart at x minutes if you arrive at
 * y minutes where y <= x, and the bus is not full. Passengers with the earliest arrival times get on the bus first.
 *
 * Return the latest time you may arrive at the bus station to catch a bus. You cannot arrive at the same time as 
 * another passenger.
 *
 * Note: The arrays buses and passengers are not necessarily sorted.
 *
 * 2. Examples 
 * Example 1
 * Input: buses = [10,20], passengers = [2,17,18,19], capacity = 2
 * Output: 16
 * Explanation: 
 * The 1st bus departs with the 1st passenger. 
 * The 2nd bus departs with you and the 2nd passenger.
 * Note that you must not arrive at the same time as the passengers, which is why you must arrive before the 2nd passenger to catch the bus.
 * 
 * Example 2
 * Input: buses = [20,30,10], passengers = [19,13,26,4,25,11,21], capacity = 2
 * Output: 20
 * Explanation: 
 * The 1st bus departs with the 4th passenger. 
 * The 2nd bus departs with the 6th and 2nd passengers.
 * The 3rd bus departs with the 1st passenger and you.
 *
 * 3. Constraints
 * n == buses.length
 * m == passengers.length
 * 1 <= n, m, capacity <= 105
 * 2 <= buses[i], passengers[i] <= 109
 * Each element in buses is unique.
 * Each element in passengers is unique.
 */
public class TheLatestTimeToCatchABus {

  /**
   * 1. Approach 
   * Array Sorting + Greedy. 
   * For each bus, calculate the passengers to be onboarded and the latest time catch the bus till bus[i]. The decision
   * does not impact the latest time [i + 1], so it is greedy rather than DP.
   * 
   * For each bus, we loop the passenger when meeting three conditions below:
   * 1) Remaining capacity > 0
   * 2) There are passengers, meaning currPassenger < m
   * 3) The bus comes later than passengers, meaning buses[i] >= passengers[currPassenger]
   * 
   * For each loop, if the current passenger could be onboarded and the time this passenger arrives at minus 1 is not 
   * the time previous passenger arrives at, then we update the latest time to passengers[currPassenger] - 1.
   * 
   * If the capacity is not consumed up while the condition 2 or 3 breaks, the loop should be terminated. In this case,
   * the latest time could be updated as buses[i] as long as the last previous passenger came earlier than buses[i]
   * 
   * 2. Complexity 
   * - Time O(N + M)
   * - Space O(1)
   * 
   * 3. Alternatives 
   * - Another straightforward way to think of it is to use HashSet to check if the current latest time is duplicate 
   *   to the ones already in set instead of using array check
   * 
   * @param buses
   * @param passengers
   * @param capacity
   * @return
   */
  public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
    final int n = buses.length, m = passengers.length;
    Arrays.sort(buses);
    Arrays.sort(passengers);
    int latestTime = 1;
    int currPassenger = 0;
    for (int bus : buses) {
      int remainingCapacity = capacity;
      while (remainingCapacity > 0) {
        if (currPassenger == m || bus < passengers[currPassenger]) {
          if (currPassenger == 0 || bus != passengers[currPassenger - 1]) latestTime = bus;
          break;
        }
        if (currPassenger == 0 || passengers[currPassenger] - passengers[currPassenger - 1] > 1) {
          latestTime = passengers[currPassenger] - 1;
        }
        currPassenger++;
        remainingCapacity--;
      }
    }
    return latestTime;
  }
}
