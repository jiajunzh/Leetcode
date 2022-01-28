package problem;

/**
 * 1. Problem
 * A conveyor belt has packages that must be shipped from one port to another within days days. The ith package on the
 * conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order
 * given by weights). We may not load more weight than the maximum weight capacity of the ship. Return the least weight 
 * capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.
 *
 * 2. Example
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 *
 * Input: weights = [3,2,2,4,1,4], days = 3
 * Output: 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 *
 * Input: weights = [1,2,3,1,1], days = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 * 
 * 3. Constraints
 * 1 <= days <= weights.length <= 5 * 104
 * 1 <= weights[i] <= 500
 */
public class CapacityToShipPackagesWithinDDays {

  /**
   * 1. Approach
   * Binary Search. This problem could be translated to finding the minimum k such that all packages could be shipped 
   * with capacity k within D days. So the major thing to construct is the condition.
   * 
   * 2. Complexity
   * Time O(NlogM) - N is the length of the weights array and M is the sum of weights (search space)
   * Space O(1)
   * 
   * 3. Mistakes
   * 1) [Improvement] Lower boundary of search space is maximum(weights)
   * As it will not be feasible if capacity is smaller than maximum(weights)
   * 
   * 2) One condition to check is if capacity is smaller than any weight.
   * This is not an issue here as the lower boundary is already set to maximum(weights). But it will be if the lower
   * boundary is 0.
   * 
   * 3) remainingCapacity >= weight instead of remainingCapacity > weight 
   * 
   * @param weights
   * @param days
   * @return
   */
  public int shipWithinDays(int[] weights, int days) {
    int lo = 0, hi = 0;

    for (int weight : weights) {
      lo = Math.max(weight, lo);
      hi += weight;
    }

    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;

      if (feasibleInDDays(weights, days, mid)) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }

    return lo;
  }

  private boolean feasibleInDDays(int [] weights, int days, int capacity) {
    int requiredDays = 1;
    int remainingCapacity = capacity;

    for (int weight : weights) {
      if (remainingCapacity >= weight) {
        remainingCapacity -= weight;
      } else {
        if (capacity < weight) {
          return false;
        } else {
          remainingCapacity = capacity - weight;
          requiredDays += 1;
        }
      }
    }

    return days >= requiredDays;
  }
}
