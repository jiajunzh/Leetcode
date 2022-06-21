package problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * 1. Problem 
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges
 * in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional 
 * edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge
 * to itself.
 *
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 *
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source to 
 * destination, or false otherwise.
 * 
 * 2. Examples 
 * Example 1
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 * 
 * Example 2
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 *
 * 3. Constraints
 * 1 <= n <= 2 * 105
 * 0 <= edges.length <= 2 * 105
 * edges[i].length == 2
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= source, destination <= n - 1
 * There are no duplicate edges.
 * There are no self edges.
 */
public class FindIfPathExistsInGraph {

  /**
   * 1. Approach 
   * Graph (Adjacent List) + DFS 
   * 
   * 2. Complexity 
   * - Time O(V + E)
   * - Space O(V + E)
   * 
   * @param n
   * @param edges
   * @param source
   * @param destination
   * @return
   */
  public boolean validPathDfs(int n, int[][] edges, int source, int destination) {
    final List<Integer>[] graph = buildGraph(n, edges);
    final Stack<Integer> stack = new Stack<>();
    final boolean[] visited = new boolean[n];
    stack.push(source);

    while (!stack.isEmpty()) {
      final int curr = stack.pop();
      final List<Integer> neighbors = graph[curr];

      if (!visited[curr]) {
        if (curr == destination) {
          return true;
        }
        visited[curr] = true;
        for (final int neighbor : neighbors) {
          if (!visited[neighbor]) {
            stack.push(neighbor);
          }
        }
      }
    }

    return false;
  }

  private List<Integer>[] buildGraph(int n, int[][] edges) {
    final List<Integer>[] graph = new List[n];

    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      graph[edge[0]].add(edge[1]);
      graph[edge[1]].add(edge[0]);
    }

    return graph;
  }

  /**
   * 1. Approach 
   * Graph (Adjacent List) + BFS
   *
   * 2. Complexity 
   * - Time O(V + E)
   * - Space O(V + E)
   *
   * @param n
   * @param edges
   * @param source
   * @param destination
   * @return
   */
  public boolean validPathBfs(int n, int[][] edges, int source, int destination) {
    final List<Integer>[] graph = buildGraph(n, edges);
    final Queue<Integer> queue = new LinkedList<>();
    final boolean[] visited = new boolean[n];
    queue.offer(source);

    while (!queue.isEmpty()) {
      int curr = queue.poll();
      if (destination == curr) {
        return true;
      }
      visited[curr] = true;
      for (final int neighbor : graph[curr]) {
        if (!visited[neighbor]) {
          queue.offer(neighbor);
        }
      }
    }

    return false;
  }
}
