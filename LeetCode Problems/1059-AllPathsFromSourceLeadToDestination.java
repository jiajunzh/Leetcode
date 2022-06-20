package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi, 
 * and two nodes source and destination of this graph, determine whether or not all paths starting from source 
 * eventually, end at destination, that is:
 *
 * At least one path exists from the source node to the destination node
 * If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
 * The number of possible paths from source to destination is a finite number.
 * Return true if and only if all roads from source lead to destination.
 *
 * 2. Examples 
 * Example 1
 * Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
 * Output: false
 * Explanation: It is possible to reach and get stuck on both node 1 and node 2.
 * 
 * Example 2
 * Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
 * Output: false
 * Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
 * 
 * Example 3
 * Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
 * Output: true
 *
 * 3. Constraints
 * 1 <= n <= 104
 * 0 <= edges.length <= 104
 * edges.length == 2
 * 0 <= ai, bi <= n - 1
 * 0 <= source <= n - 1
 * 0 <= destination <= n - 1
 * The given graph may have self-loops and parallel edges.
 */
public class AllPathsFromSourceLeadToDestination {

  /**
   * 1. Approach 
   * Graph + DFS.
   * 
   * 1) Build Graph (Adjacent List)
   * 2) DFS traverses through the graph
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
  public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
    final List<Integer>[] graph = buildGraph(n, edges);
    final boolean[] visited = new boolean[n];
    return dfs(source, destination, visited, graph);
  }

  private boolean dfs(int source, int destination, boolean[] visited, List<Integer>[] graph) {
    if (graph[source].isEmpty()) {
      return destination == source;
    }
    if (visited[source]) {
      return false;
    }
    visited[source] = true;
    for (final int next : graph[source]) {
      if (!dfs(next, destination, visited, graph)) {
        return false;
      }
    }
    visited[source] = false;
    return true;
  }

  private List<Integer>[] buildGraph(int n, int[][] edges) {
    final List<Integer>[] graph = new List[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      graph[edge[0]].add(edge[1]);
    }
    return graph;
  }
}
