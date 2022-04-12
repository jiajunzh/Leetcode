package problem;

/**
 * 1. Problem 
 * You are given an undirected graph. You are given an integer n which is the number of nodes in the graph and an array 
 * edges, where each edges[i] = [ui, vi] indicates that there is an undirected edge between ui and vi.
 *
 * A connected trio is a set of three nodes where there is an edge between every pair of them.
 *
 * The degree of a connected trio is the number of edges where one endpoint is in the trio, and the other is not.
 *
 * Return the minimum degree of a connected trio in the graph, or -1 if the graph has no connected trios.
 *
 * 2. Examples
 * Example 1
 * Input: n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
 * Output: 3
 * Explanation: There is exactly one trio, which is [1,2,3]. The edges that form its degree are bolded in the figure 
 * above.
 * 
 * Example 2
 * Input: n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
 * Output: 0
 * Explanation: There are exactly three trios:
 * 1) [1,4,3] with degree 0.
 * 2) [2,5,6] with degree 2.
 * 3) [5,6,7] with degree 2.
 * 
 * 3. Constraints
 * 2 <= n <= 400
 * edges[i].length == 2
 * 1 <= edges.length <= n * (n-1) / 2
 * 1 <= ui, vi <= n
 * ui != vi
 * There are no repeated edges.
 */
public class MinimumDegreeOfAConnectedTrioInAGraph {

  /**
   * 1. Approach
   * Brutal Force - Build the graph using 2D array and iterate through each possible trio (three node combination).
   * If three nodes could connect to a trio, then the degree = sum(indegree(i, j, k)) - 6.
   * 
   * 2. Complexity
   * - Time O(N^3)
   * - Space O(N^2)
   * 
   * 3. Improvement
   * - Another idea is to iterate each edge and then any node greater than both edge, which produce O(E*N) time,
   *   essentially is the same as O(N^3).
   * 
   * @param n
   * @param edges
   * @return
   */
  public int minTrioDegree(int n, int[][] edges) {
    final boolean[][] graph = new boolean[n + 1][n + 1];
    final int[] indegree = new int[n + 1];
    int min = Integer.MAX_VALUE;

    constructGraph(n, edges, graph, indegree);

    for (int i = 1; i <= n; i++) {
      for (int j = i + 1; j <= n; j++) {
        if (graph[i][j]) {
          for (int k = j + 1; k <= n; k++) {
            if (graph[k][j] && graph[i][k]) {
              min =  Math.min(indegree[i] + indegree[j] + indegree[k] - 6, min);
            }
          }
        }
      }
    }

    return min != Integer.MAX_VALUE ? min : -1;
  }

  private void constructGraph(int n, int[][] edges, boolean[][] graph, int[] indegree) {
    for (int[] edge : edges) {
      graph[edge[0]][edge[1]] = true;
      graph[edge[1]][edge[0]] = true;

      indegree[edge[0]]++;
      indegree[edge[1]]++;
    }
  }
}
