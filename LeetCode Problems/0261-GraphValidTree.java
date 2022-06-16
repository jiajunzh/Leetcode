package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 1. Problem 
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where 
 * edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
 *
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 *
 * 2. Examples 
 * Example 1
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 * 
 * Example 2
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * Output: false
 *
 * 3. Constraints
 * 1 <= n <= 2000
 * 0 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * There are no self-loops or repeated edges.
 */
public class GraphValidTree {

  /**
   * 1. Approach 
   * Union Find. Constructing the graph as a valid tree means that: 
   * (1) All components in the graph are connected => Only one disjoint set in the end 
   * (2) The number of edges in the tree is equal to the number of components - 1
   * 
   * 2. Complexity 
   * - Time O(V + E)
   * - Space O(V)
   * 
   * @param n
   * @param edges
   * @return
   */
  public boolean validTree(int n, int[][] edges) {
    if (edges.length + 1 != n) {
      return false;
    }
    UnionFind uf = new UnionFind(n);

    for (int[] edge : edges) {
      uf.union(edge[0], edge[1]);
    }

    return uf.getCount() == 1;
  }

  private static class UnionFind {
    private final int[] parent;
    private final int[] rank;
    private int count;

    private UnionFind(int n) {
      parent = new int[n];
      rank = new int[n];
      count = n;

      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    private int find(int x) {
      if (x == parent[x]) {
        return x;
      }

      parent[x] = find(parent[x]);
      return parent[x];
    }

    private void union(int p, int q) {
      int rootP = find(p);
      int rootQ = find(q);

      if (rootP != rootQ) {
        if (rank[rootP] > rank[rootQ]) {
          parent[rootQ] = rootP;
        } else if (rank[rootP] < rank[rootQ]) {
          parent[rootP] = rootQ;
        } else {
          parent[rootP] = rootQ;
          rank[rootQ]++;
        }
        count--;
      }
    }

    private int getCount() {
      return this.count;
    }
  }

  /**
   * 1. Approach
   * Iterative DFS.
   * - All components connected => number of visited elements = n
   * - No cycle => component not visited before when getting traversed. However, this needs to exclude the case where
   *   the traversed neighbor is not a parent of the current component due to the nature of undirected graph. For 
   *   example, with A - B, we have A -> B -> A. This should not be counted as a cycle.
   * 
   * 2. Complexity
   * - Time O(V + E)
   * - Space O(V + E) - adjacent list is a list whose length is N and inner lists adds up to E
   * 
   * @param n
   * @param edges
   * @return
   */
  public boolean validTree2(int n, int[][] edges) {
    final List[] graph = buildGraph(n, edges);
    final Stack<Integer> stack = new Stack<>();
    final int[] parent = new int[n];
    int count = 0;
    stack.push(0);
    Arrays.fill(parent, -1);

    while (!stack.isEmpty()) {
      int curr = stack.pop();
      List<Integer> neighbors = graph[curr];

      for (int neighbor : neighbors) {
        if (parent[curr] == neighbor) {
          continue;
        }
        if (parent[neighbor] != -1) {
          return false;
        }
        parent[neighbor] = curr;
        stack.push(neighbor);
      }
      count++;
    }

    return count == n;
  }
  
  private List[] buildGraph(int n, int[][] edges) {
    List[] graph = new List[n];

    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      int a = edge[0];
      int b = edge[1];

      graph[a].add(b);
      graph[b].add(a);
    }

    return graph;
  }
}
