package problem;

/**
 * 1. Problem 
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that
 * there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 *
 * 2. Examples
 * Example 1
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 * 
 * Example 2
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 *
 * 3. Constraints
 * 1 <= n <= 2000
 * 1 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai <= bi < n
 * ai != bi
 * There are no repeated edges.
 */
public class NumberOfConnectedComponentsInAnUndirectedGraph {

  /**
   * 1. Approach 
   * Union Find.
   * 
   * 2. Complexity 
   * - Time O(V + E)
   * - Space O(V)
   * 
   * 3. Alternative 
   * - DFS with visited array
   * 
   * @param n
   * @param edges
   * @return
   */
  public int countComponents(int n, int[][] edges) {
    final UnionFind uf = new UnionFind(n);

    for (int[] edge : edges) {
      uf.union(edge[0], edge[1]);
    }

    return uf.getCount();
  }

  private static class UnionFind {
    private final int[] parent;
    private final int[] rank;
    private int count;

    UnionFind(int n) {
      parent = new int[n];
      rank = new int[n];
      count = n;

      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
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

    private int find(int x) {
      if (x == parent[x]) {
        return x;
      }
      parent[x] = find(parent[x]);
      return parent[x];
    }

    private int getCount() {
      return this.count;
    }
  }
}
