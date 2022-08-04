package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1. Problem 
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed
 * edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a 
 * signal to travel from source to target.
 *
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
 * If it is impossible for all the n nodes to receive the signal, return -1.
 *
 * 2. Examples 
 * Example 1
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * Output: 2
 * 
 * Example 2
 * Input: times = [[1,2,1]], n = 2, k = 1
 * Output: 1
 * 
 * Example 3
 * Input: times = [[1,2,1]], n = 2, k = 2
 * Output: -1
 *
 * 3. Constraints
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
public class NetworkDelayTime {

  /**
   * 1. Approach 
   * Dijkstra's Algorithm. This problem could also be solved by DFS and BFS of graph.
   * 
   * 2. Complexity 
   * - Time O(E + ELogE + V) => O(E + ElogV^2) => O(E + ElogV)
   * - Space O(E)
   * 
   * @param times
   * @param n
   * @param k
   * @return
   */
  public int networkDelayTime(int[][] times, int n, int k) {
    final List<int[]>[] graph = buildGraph(times, n);
    final int[] distTo = new int[n + 1];
    Arrays.fill(distTo, Integer.MAX_VALUE);
    dijkstra(graph, distTo, k);
    int delayTime = 0;
    for (int i = 1; i <= n; i++) delayTime = Math.max(delayTime, distTo[i]);
    return delayTime == Integer.MAX_VALUE ? -1 : delayTime;
  }

  private List[] buildGraph(int[][] times, int n) {
    final List[] graph = new List[n + 1];
    for (int i = 1; i <= n; i++) {
      graph[i] = new ArrayList<int[]>();
    }
    for (int[] time : times) {
      graph[time[0]].add(new int[]{time[1], time[2]});
    }
    return graph;
  }

  private void dijkstra(final List<int[]>[] graph, final int[] distTo, final int k) {
    final PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
    pq.offer(new int[]{k, 0});
    distTo[k] = 0;
    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      int currNode = curr[0];
      int distance = curr[1];
      if (distance > distTo[currNode]) {
        continue;
      }
      for (int[] neighbor : graph[currNode]) {
        if (neighbor[1] + distance < distTo[neighbor[0]]) {
          distTo[neighbor[0]] = neighbor[1] + distance;
          pq.offer(new int[]{neighbor[0], distTo[neighbor[0]]});
        }
      }
    }
  }
}
