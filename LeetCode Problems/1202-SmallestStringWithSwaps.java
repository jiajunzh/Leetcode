package problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. Problem
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 
 * 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 * 2. Examples
 * Example 1
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination: 
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * 
 * Example 2
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination: 
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * 
 * Example 3
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination: 
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 * 3. Constraints
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 */
public class SmallestStringWithSwaps {

  /**
   * 1. Approach 
   * Graph DFS. One important observation in this problem is that we could swap any pair for unlimited time. That implies
   * that the swapping could be transitive. For example, if we have string abc and pair [0,1] and [1,2]. Despite that we 
   * don't have direct pair such as [0,2], we could still swap 0 and 2 as we could swap 0 and 1 (bac), then swap 1 and 2
   * (bca) and in the end swap 0 and 1 (cba). Essentially, this could be transformed into a graph problem where within 
   * a connected set, each component in the set could be swapped as much time as wanted.
   * 
   * On top of it, we could find all connected sets from pairs and iterate through each connected set to collect the 
   * related indices/positions and chars occurred. Then sort the chars based on the lexicographical order and put ith
   * char into ith indice.
   * 
   * 2. Complexity 
   * - Time O(VlogV + E) 
   *   - BuildGraph takes O(V + E)
   *   - DFS and Sorting (V + VlogV)
   * - Space O(V + E)
   * 
   * @param s
   * @param pairs
   * @return
   */
  public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
    final int n = s.length();
    final List<Integer>[] graph = buildGraph(n, pairs);

    final char[] result = new char[n];
    final boolean[] visited = new boolean[n];
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        final List<Character> charSet = new ArrayList<>();
        final List<Integer> indices = new ArrayList<>();
        dfs(charSet, indices, visited, i, graph, s);
        Collections.sort(charSet);
        Collections.sort(indices);
        for (int j = 0; j < indices.size(); j++) {
          result[indices.get(j)] = charSet.get(j);
        }
      }
    }

    return String.valueOf(result);
  }

  private List<Integer>[] buildGraph(final int n, final List<List<Integer>> pairs) {
    final List<Integer>[] graph = new List[n];

    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }

    for (final List<Integer> pair : pairs) {
      int p1 = pair.get(0);
      int p2 = pair.get(1);

      graph[p1].add(p2);
      graph[p2].add(p1);
    }

    return graph;
  }

  private void dfs(final List<Character> charSet, final List<Integer> indices, final boolean[] visited, final int node, final List<Integer>[] graph, final String s) {
    if (visited[node]) {
      return;
    }

    charSet.add(s.charAt(node));
    indices.add(node);
    visited[node] = true;

    for (int neighbor : graph[node]) {
      dfs(charSet, indices, visited, neighbor, graph, s);
    }
  }

  /**
   * 1. Approach 
   * Union Find.
   *
   * 2. Complexity 
   * - Time O(VlogV + (E + V) * a (V)) 
   *   - UnionFind constructor and union operations take O(V + E * a (V))
   *   - Find Root and Obtain Indices and CharSet O(V * a(V) + V)
   *   - Construct Result O(V)
   *   - Sort O(VlogV)
   * - Space O(V)
   *
   * @param s
   * @param pairs
   * @return
   */
  public String smallestStringWithSwaps2(String s, List<List<Integer>> pairs) {
    final int n = s.length();
    final UnionFind uf = new UnionFind(n);

    for (List<Integer> pair : pairs) {
      uf.union(pair.get(0), pair.get(1));
    }

    final Map<Integer, List<Integer>> disjointSets = new HashMap<>();
    final Map<Integer, List<Character>> charSets = new HashMap<>();
    for (int i = 0; i < n; i++) {
      int root = uf.find(i);
      if (!disjointSets.containsKey(root)) {
        disjointSets.put(root, new ArrayList<>());
        charSets.put(root, new ArrayList<>());
      }
      disjointSets.get(root).add(i);
      charSets.get(root).add(s.charAt(i));
    }

    final char[] result = new char[n];
    for (final Integer root : disjointSets.keySet()) {
      final List<Integer> indices = disjointSets.get(root);
      final List<Character> charSet = charSets.get(root);

      Collections.sort(charSet);

      for (int i = 0; i < indices.size(); i++) {
        result[indices.get(i)] = charSet.get(i);
      }
    }

    return String.valueOf(result);
  }

  private static class UnionFind {
    private final int[] parent;
    private final int[] rank;

    private UnionFind(int n) {
      parent = new int[n];
      rank = new int[n];

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
      }
    }
  }
}
