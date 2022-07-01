package problem;

import java.util.LinkedList;
import java.util.List;

/**
 * 1. Problem 
 * We have n cities and m bi-directional roads where roads[i] = [ai, bi] connects city ai with city bi. Each city has 
 * a name consisting of exactly three upper-case English letters given in the string array names. Starting at any city
 * x, you can reach any city y where y != x (i.e., the cities and the roads are forming an undirected connected graph).
 *
 * You will be given a string array targetPath. You should find a path in the graph of the same length and with the
 * minimum edit distance to targetPath.
 *
 * You need to return the order of the nodes in the path with the minimum edit distance. The path should be of the same 
 * length of targetPath and should be valid (i.e., there should be a direct road between ans[i] and ans[i + 1]). If 
 * there are multiple answers return any one of them.
 *
 * The edit distance is defined as follows:
 * if there is a name in your final path different from the target path => edit cost ++
 *
 * 2. Examples  
 * Example 1
 * Input: n = 5, roads = [[0,2],[0,3],[1,2],[1,3],[1,4],[2,4]], names = ["ATL","PEK","LAX","DXB","HND"], targetPath = ["ATL","DXB","HND","LAX"]
 * Output: [0,2,4,2]
 * Explanation: [0,2,4,2], [0,3,0,2] and [0,3,1,2] are accepted answers.
 * [0,2,4,2] is equivalent to ["ATL","LAX","HND","LAX"] which has edit distance = 1 with targetPath.
 * [0,3,0,2] is equivalent to ["ATL","DXB","ATL","LAX"] which has edit distance = 1 with targetPath.
 * [0,3,1,2] is equivalent to ["ATL","DXB","PEK","LAX"] which has edit distance = 1 with targetPath.
 * 
 * Example 2
 * Input: n = 4, roads = [[1,0],[2,0],[3,0],[2,1],[3,1],[3,2]], names = ["ATL","PEK","LAX","DXB"], targetPath = ["ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"]
 * Output: [0,1,0,1,0,1,0,1]
 * Explanation: Any path in this graph has edit distance = 8 with targetPath.
 * 
 * Example 3
 * Input: n = 6, roads = [[0,1],[1,2],[2,3],[3,4],[4,5]], names = ["ATL","PEK","LAX","ATL","DXB","HND"], targetPath = ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 * Output: [3,4,5,4,3,2,1]
 * Explanation: [3,4,5,4,3,2,1] is the only path with edit distance = 0 with targetPath.
 * It's equivalent to ["ATL","DXB","HND","DXB","ATL","LAX","PEK"]
 *
 * 3. Constraints
 * 2 <= n <= 100
 * m == roads.length
 * n - 1 <= m <= (n * (n - 1) / 2)
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * The graph is guaranteed to be connected and each pair of nodes may have at most one direct road.
 * names.length == n
 * names[i].length == 3
 * names[i] consists of upper-case English letters.
 * There can be two cities with the same name.
 * 1 <= targetPath.length <= 100
 * targetPath[i].length == 3
 * targetPath[i] consists of upper-case English letters.
 *
 *
 * Follow up: If each node can be visited only once in the path, What should you change in your solution?
 */
public class TheMostSimilarPathInAGraph {

  /**
   * 1. Approach 
   * Dynamic Programming 
   * 
   * Define 
   * - dp[i][j][0] as the minimum cost of constructing path from [0, i] that ends at city j (at i position) 
   * - dp[i][j][1] as the parent (i.e. previous neighbor node at i - 1that gets you the minimum cost) of the current node j.
   * 
   * Steps:
   * 1) Build Graph. Here we use a boolean 2D array to represent the graph
   * 2) DP to find the minimum cost and the previous node in the path
   * 3) Construct the path 
   * 
   * 2. Complexity 
   * - Time O(M * N^2)
   * - Space O(M * N)
   * 
   * 3. Follow up
   * - If each node can be visited only once in the path, What should you change in your solution?
   *   If each node can be visited only once, then we will need to use backtracking dfs instead to keep track of 
   *   an array of visited nodes till path so far.
   *   See https://leetcode.com/problems/the-most-similar-path-in-a-graph/discuss/1403591/Easy-Move-from-72.-Edit-Distance-with-follow-up-thinking-process
   *   
   * @param n
   * @param roads
   * @param names
   * @param targetPath
   * @return
   */
  public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
    final int m = targetPath.length; 
    final int[][][] dp = new int[m][n][2]; 
    final boolean[][] adjacentList = buildAdjacentList(n, roads);
    for (int j = 0; j < n; j++) {
      dp[0][j][0] = targetPath[0].equals(names[j]) ? 0 : 1;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int min = m + 1;
        for (int k = 0; k < n; k++) {
          if (adjacentList[j][k]) {
            if (dp[i - 1][k][0] < min) {
              min = dp[i - 1][k][0];
              dp[i][j][1] = k;
            }
          }
        }
        dp[i][j][0] = min + (targetPath[i].equals(names[j]) ? 0 : 1);
      }
    }
    final LinkedList<Integer> result = new LinkedList<>();
    int candidate = 0;
    int min = m + 1;
    for (int i = 0; i < n; i++) {
      if (min > dp[m - 1][i][0]) {
        min = dp[m - 1][i][0];
        candidate = i;
      }
    }
    for (int i = m - 1; i >= 0; i--) {
      result.addFirst(candidate);
      candidate = dp[i][candidate][1];
    }
    return result;
  }

  private boolean[][] buildAdjacentList(int n, int[][] roads) {
    final boolean[][] adjacentList = new boolean[n][n];
    for (int[] road : roads) {
      adjacentList[road[0]][road[1]] = true;
      adjacentList[road[1]][road[0]] = true;
    }
    return adjacentList;
  }
}
