package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = 
 * [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. 
 * If there is no such route, return -1.
 *
 * 2. Examples 
 * Example 1
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
 * Output: 700
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
 * Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
 * 
 * Example 2
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
 * 
 * Example 3
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph is shown above.
 * The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 *
 * 3. Constraints
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * There will not be any multiple flights between two cities.
 * 0 <= src, dst, k < n
 * src != dst
 */
public class CheapestFlightsWithinKStops {

  private static final int MAX_PRICE = 1_00_000_000;

  /**
   * 1. Approach 
   * Bellman-Ford Algorithm
   * 
   * Note: exit early when array does not change saves performance from 22ms to 6ms
   * 
   * 2. Complexity
   * - Time O(K * E)
   * - Space O(V)
   * 
   * @param n
   * @param flights
   * @param src
   * @param dst
   * @param k
   * @return
   */
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    int[] curr = new int[n];
    Arrays.fill(curr, MAX_PRICE);
    curr[src] = 0;
    for (int i = 1; i <= k + 1; i++) {
      int[] next = Arrays.copyOf(curr, n);
      boolean isConverge = true;
      for (int[] flight : flights) {
        next[flight[1]] = Math.min(next[flight[1]], curr[flight[0]] + flight[2]);
        if (next[flight[1]] < curr[flight[1]]) {
          isConverge = false;
        }
      }
      if (isConverge) break;
      curr = next;
    }
    return curr[dst] == MAX_PRICE ? -1 : curr[dst];
  }
}
