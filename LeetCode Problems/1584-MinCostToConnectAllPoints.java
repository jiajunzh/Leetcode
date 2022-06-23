package problem;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1. Problem
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where 
 * points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|,
 * where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path 
 * between any two points.
 *
 * 2. Examples 
 * Example 1
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 * Explanation: 
 *
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points.
 *
 * Example 2
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 *
 * 3. Constraints
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * All pairs (xi, yi) are distinct.
 */
public class MinCostToConnectAllPoints {

  /**
   * 1. Approach 
   * Minimum Spanning Tree + Kruskal's Algorithm + Union Find 
   * 
   * 2. Complexity 
   * - Time O(N^2 + N^2 * logE + N^2 * a(N))
   * - Space O(N^2)
   * 
   * @param points
   * @return
   */
  public int minCostConnectPoints1(int[][] points) {
    final int n = points.length;
    final UnionFind uf = new UnionFind(n);
    final PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> (p1[2] - p2[2]));

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        pq.offer(new int[]{i, j, getDistance(points[i], points[j])});
      }
    }

    int minCost = 0;
    while (!pq.isEmpty()) {
      final int[] edge = pq.poll();
      if (uf.union(edge[0], edge[1])) {
        minCost += edge[2];
      }
    }

    return minCost;
  }

  private int getDistance(int[] point1, int[] point2) {
    return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
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
      if (x == parent[x]) {
        return x;
      }
      parent[x] = find(parent[x]);
      return parent[x];
    }

    private boolean union(int p, int q) {
      int rootP = find(p);
      int rootQ = find(q);

      if (rootP == rootQ) {
        return false;
      }

      if (rank[rootP] > rank[rootQ]) {
        parent[rootQ] = rootP;
      } else if (rank[rootP] < rank[rootQ]) {
        parent[rootP] = rootQ;
      } else {
        parent[rootP] = rootQ;
        rank[rootQ]++;
      }

      return true;
    }
  }

  /**
   * 1. Approach 
   * MST + Optimized Prim's Algorithm
   * 
   * 2. Complexity 
   * - Time O(N^2)
   * - Space O(N)
   * 
   * @param points
   * @return
   */
  public int minCostConnectPoints2(int[][] points) {
    final int n = points.length;
    final int[] minDist = new int[n];
    final boolean[] isMst = new boolean[n];
    int minCost = 0;
    int edgesUsed = 0;
    int next = 0;
    Arrays.fill(minDist, Integer.MAX_VALUE);
    int nextCost = 0;

    while (edgesUsed < n) {
      isMst[next] = true;
      edgesUsed++;
      minCost += nextCost;
      int curr = next;
      nextCost = Integer.MAX_VALUE;
      for (int i = 0; i < n; i++) {
        if (!isMst[i]) {
          minDist[i] = Math.min(minDist[i], getDistance(points[i], points[curr]));
          if (minDist[i] < nextCost) {
            nextCost = minDist[i];
            next = i;
          }
        }
      }
    }

    return minCost;
  }
}
