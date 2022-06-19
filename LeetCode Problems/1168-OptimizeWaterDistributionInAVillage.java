package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1. Problem 
 * There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
 *
 * For each house i, we can either build a well inside it directly with cost wells[i - 1] (note the -1 due to 
 * 0-indexing), or pipe in water from another well to it. The costs to lay pipes between houses are given by the 
 * array pipes where each pipes[j] = [house1j, house2j, costj] represents the cost to connect house1j and house2j 
 * together using a pipe. Connections are bidirectional, and there could be multiple valid connections between the
 * same two houses with different costs.
 *
 * Return the minimum total cost to supply water to all houses.
 *
 * 2. Examples
 * Example 1
 * Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
 * Output: 3
 * Explanation: The image shows the costs of connecting houses using pipes.
 * The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.
 * 
 * Example 2
 * Input: n = 2, wells = [1,1], pipes = [[1,2,1],[1,2,2]]
 * Output: 2
 * Explanation: We can supply water with cost two using one of the three options:
 * Option 1:
 *   - Build a well inside house 1 with cost 1.
 *   - Build a well inside house 2 with cost 1.
 * The total cost will be 2.
 * Option 2:
 *   - Build a well inside house 1 with cost 1.
 *   - Connect house 2 with house 1 with cost 1.
 * The total cost will be 2.
 * Option 3:
 *   - Build a well inside house 2 with cost 1.
 *   - Connect house 1 with house 2 with cost 1.
 * The total cost will be 2.
 * Note that we can connect houses 1 and 2 with cost 1 or with cost 2 but we will always choose the cheapest option. 
 *
 * 3. Constraints
 * 2 <= n <= 104
 * wells.length == n
 * 0 <= wells[i] <= 105
 * 1 <= pipes.length <= 104
 * pipes[j].length == 3
 * 1 <= house1j, house2j <= n
 * 0 <= costj <= 105
 * house1j != house2j
 */
public class OptimizeWaterDistributionInAVillage {

  /**
   * 1. Approach 
   * Minimum Spanning Tree + Union Find.
   * 
   * The problem asks the minimum cost to supply water (connect all nodes/houses with minimum costs). Essentially, this 
   * could be boiled down to the MST problem. The only difference is that there will be cost on both vertex and edge 
   * in this problem while there will only be cost on edges in MST problem. To adapt this problem to MST problem, one 
   * trick to do is to add a virtual node 0 (Think about all the water come from node 0) and convert the cost to dig
   * a well to the cost to construct a pipe between the house to node 0. For example, we have two houses with wells =
   * [1, 3] and pipes = [[1,2,2]]
   * 
   * 1 -(2)-> 2 Could be transformed to   1--(1)--|
   *                                      |       |
   *                                     (2)      0
   *                                      |       |
   *                                      2--(3)--|
   *                                      
   * For MST, there are two algorithms that could be utilized to solve the problem:
   * - Prim's Algorithm: put one node at once into the current MST set and then visit the edge with minimum cost that 
   *   directly connects to the current MST
   * - Kruskal's Algorithm: order all edges and visited edge one by one from minimum cost to maximum. 
   * 
   * 2. Complexity 
   * - Time O((N + P) log (N + P))
   *  - Build Edge List O(N + P)
   *  - Sorting O((N + P) log(N + P))
   *  - Union O((N + P) log*N)
   * - Space O(N + P)
   * 
   * @param n
   * @param wells
   * @param pipes
   * @return
   */
  public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
    final UnionFind uf = new UnionFind(n + 1); // Add 0 point as virtual node
    final List<int[]> orderedEdges = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      int well = wells[i];
      orderedEdges.add(new int[]{0, i + 1, well});
    }
    orderedEdges.addAll(Arrays.asList(pipes));
    orderedEdges.sort((edge1, edge2) -> (edge1[2] - edge2[2]));

    int minCost = 0;
    for (int[] edge : orderedEdges) {
      if (uf.union(edge[0], edge[1])) {
        minCost += edge[2];
      }
    }

    return minCost;
  }

  private static class UnionFind {

    private int[] parent;
    private int[] rank;

    private UnionFind(int n) {
      this.parent = new int[n];
      this.rank = new int[n];

      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    private int find(int x) {
      if (parent[x] == x) {
        return x;
      }
      parent[x] = find(parent[x]);
      return parent[x];
    }

    private boolean union(int x, int y) {
      int rootX = find(x);
      int rootY = find(y);

      if (rootX == rootY) {
        return false;
      }

      if (rank[rootX] > rank[rootY]) {
        parent[rootY] = rootX;
      } else if (rank[rootX] < rank[rootY]) {
        parent[rootX] = rootY;
      } else {
        parent[rootX] = rootY;
        rank[rootY]++;
      }
      return true;
    }
  }

  /**
   * 1. Approach 
   * MST + Prim's Algorithm.
   * 
   * 2. Complexity 
   * - Time O((N + P) log (N + P))
   * - Space O(N + P)
   * 
   * @param n
   * @param wells
   * @param pipes
   * @return
   */
  public int minCostToSupplyWater2(int n, int[] wells, int[][] pipes) {
    final boolean[] visited = new boolean[n + 1];
    final PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
    final List<List<int[]>> graph = buildGraph(n, wells, pipes, pq);
    int minCost = 0;

    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      int next = curr[0];

      if (!visited[next]) {
        visited[next] = true;
        minCost += curr[1];
        List<int[]> neighbors = graph.get(next);
        for (int[] neighbor : neighbors) {
          if (!visited[neighbor[0]]) {
            pq.offer(neighbor);
          }
        }
      }
    }

    return minCost;
  }

  private List<List<int[]>> buildGraph(int n, int[] wells, int[][] pipes, PriorityQueue<int[]> pq) {
    final List<List<int[]>> graph = new ArrayList<>();

    for (int i = 0; i <= n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < n; i++) {
      graph.get(0).add(new int[]{i + 1, wells[i]});
      pq.offer(new int[]{i + 1, wells[i]});
    }

    for (int[] pipe : pipes) {
      int a = pipe[0];
      int b = pipe[1];

      graph.get(a).add(new int[]{b, pipe[2]});
      graph.get(b).add(new int[]{a, pipe[2]});
    }

    return graph;
  }
}
