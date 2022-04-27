package problem;

/**
 * 1. Problem 
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and 
 * city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly 
 * connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 * 2. Examples
 * Example 1
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * 
 * Example 2
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 * 3. Constraints
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces {

  /**
   * 1. Approach 
   * DFS. For each node, search the path from it as deep as possible to the bottom. Count one for each path encountered.
   * 
   * 2. Complexity 
   * - Time O(N^2)
   * - Space O(N) for visited array and recursion stack
   * 
   * 3. Alternatives
   * - BFS. First search every node connected to the first node, then every node connected to the second node sets.
   * 
   * @param isConnected
   * @return
   */
  public int findCircleNum(int[][] isConnected) {
    final int n = isConnected.length;
    final boolean[] visited = new boolean[n];
    int numOfProvinces = 0;

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        dfs(isConnected, visited, i, n);
        numOfProvinces++;
      }
    }

    return numOfProvinces;
  }

  private void dfs(final int[][] isConnected, final boolean[] visited, final int cityId, final int n) {
    if (visited[cityId]) {
      return;
    }

    visited[cityId] = true;

    for (int i = 0; i < n; i++) {
      if (!visited[i] && isConnected[cityId][i] == 1) {
        dfs(isConnected, visited, i, n);
      }
    }
  }

  /**
   * 1. Approach 
   * Union Find.
   * 
   * 2. Complexity
   * - Time O(N^2)
   * - Space O(N)
   *
   * @param isConnected
   * @return
   */
  public int findCircleNumUnionFind(int[][] isConnected) {
    final int n = isConnected.length;
    final UnionFind uf = new UnionFind(isConnected);

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (isConnected[i][j] == 1) {
          uf.union(i, j);
        }
      }
    }

    return uf.getCount();
  }

  private static class UnionFind {

    private final int[] parent;
    private final int[] rank;
    private int count;

    private UnionFind(int[][] isConnected) {
      final int n = isConnected.length;
      parent = new int[n];
      rank = new int[n];
      count = n;

      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    private int find(int x) {
      if (parent[x] != x) {
        parent[x] = find(parent[x]);
      }
      return parent[x];
    }

    private void union(int x, int y) {
      int rootx = find(x);
      int rooty = find(y);

      if (rootx != rooty) {
        if (rank[rootx] > rank[rooty]) {
          parent[rooty] = rootx;
        } else if (rank[rootx] < rank[rooty]) {
          parent[rootx] = rooty;
        } else {
          parent[rootx] = rooty;
          rank[rooty]++;
        }
        count--;
      }
    }

    private int getCount() {
      return this.count;
    }
  }
}
