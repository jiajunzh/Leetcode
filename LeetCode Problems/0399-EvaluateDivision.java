package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 1. Problem 
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi]
 * and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer 
 * for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and 
 * that there is no contradiction.
 *
 * 2. Examples
 * Example 1
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation: 
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 
 * Example 2
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * 
 * Example 3
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 * 3. Constraints
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class EvaluateDivision {

  /**
   * 1. Approach 
   * Graph + BFS. One critical observation in this problem is if a/b = v1, b/c = v2 then a/c = v1 * v2, meaning the 
   * division is transitive. Based on it, this problem could be transformed into a graph problem. For example, if we
   * want to query [x, y], then we just need to find if there is a path exists between x and y and what is the 
   * cumulative product of the weight of each edge along the path.
   * 
   * 1. Build a graph represented by Map<String, Map<String, Double>>. E.g. a/b = 2.0 => (a, (b, 2.0)) and (b, (a, 1/2.0))
   * 2. BFS or DFS search the path and build the answer along the path
   * 
   * 2. Complexity
   * If N is the number of equations (edges) and M is the number of queries.
   * - Time O(M + N * M) => O(N) to build the graph and O(N * M) for queries in worst case
   * - Space O(N) => Not consider the space used to hold the results 
   * 
   * 3. Mistakes
   * - One edge case is ['x', 'x']. The test case treat it as not found instead of 1.00000.
   * 
   * @param equations
   * @param values
   * @param queries
   * @return
   */
  public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    final Map<String, Map<String, Double>> graph = buildGraph(equations, values);
    final int n = queries.size();
    final double[] answers = new double[n];

    for (int i = 0; i < n; i++) {
      answers[i] = resolveQuery(graph, queries.get(i));
    }

    return answers;
  }

  private Map<String, Map<String, Double>> buildGraph(final List<List<String>> equations, final double[] values) {
    final Map<String, Map<String, Double>> graph = new HashMap<>();
    final int n = values.length;

    for (int i = 0; i < n; i++) {
      final List<String> equation = equations.get(i);
      final String a = equation.get(0);
      final String b = equation.get(1);
      final double value = values[i];

      if (!graph.containsKey(a)) {
        graph.put(a, new HashMap<>());
      }
      if (!graph.containsKey(b)) {
        graph.put(b, new HashMap<>());
      }
      graph.get(a).put(b, value);
      graph.get(b).put(a, 1 / value);
    }

    return graph;
  }

  private double resolveQuery(final Map<String, Map<String, Double>> graph, final List<String> query) {
    final String a = query.get(0);
    final String b = query.get(1);

    final Queue<Pair> queue = new LinkedList<>();
    queue.offer(new Pair(a, 1.00000));
    final Set<String> visited = new HashSet<>();
    while(!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        final Pair curr = queue.poll();
        if (graph.containsKey(b) && b.equals(curr.variable)) {
          return curr.weight;
        }
        if (!visited.contains(curr.variable)) {
          visited.add(curr.variable);
          final Map<String, Double> neighbors = graph.getOrDefault(curr.variable, new HashMap<>());
          for (String variable : neighbors.keySet()) {
            queue.offer(new Pair(variable, curr.weight * neighbors.get(variable)));
          }
        }
      }
    }

    return -1.00000;
  }

  private class Pair {
    private String variable;
    private double weight;

    private Pair(String variable, double weight) {
      this.variable = variable;
      this.weight = weight;
    }
  }

  /**
   * 1. Approach
   * Modified Union Find. One thing that Union Find is able to do is to detect whether there is a path between two
   * variables. The only thing remaining now is to calculate the weight product along with the path, which could be 
   * solved by modifying Union Find with two data structures:
   * - Map<String, Pair> => <Node, Node's Root and the weight to reach to the Root>
   * - Map<String, List<String> => <Root, Root's Chidren>
   *   
   * 1. Find O(1)
   * Return Pair for the queried Node
   * 2. Union
   * Think of the model below. We initially have two separated sets as below (A, B, C) and (D, E). Now we want to connect
   * A and C together and we have weight between B and D. Imagine we connect (D, E) to point to (A, B, C)
   * 
   *    A         E - C
   *    |         |
   *    B         D
   * 
   * For D, to calculate the weight from D to A, we need to know weight(A,B), weight(B,D) as well as weight(D,E) and 
   * weight(E,C). With these four weights, we could calculate weight(B,A)
   * 
   * 2. Complexity O(V)
   * If N is the number of equations (edges) and M is the number of queries.
   * - Time O(N * V + M) 
   * - Space O(N) 
   * 
   * @param equations
   * @param values
   * @param queries
   * @return
   */
  public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
    final UnionFind uf = new UnionFind(equations, values);
    final double[] answers = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      final List<String> query = queries.get(i);
      final Pair rootA = uf.find(query.get(0));
      final Pair rootB = uf.find(query.get(1));

      if (rootA == null || rootB == null || !rootA.variable.equals(rootB.variable)) {
        answers[i] = -1.00000;
      } else {
        answers[i] = rootA.weight / rootB.weight;
      }
    }
    return answers;
  }

  private class UnionFind {

    private Map<String, Pair> childToRoot = new HashMap<>();
    private Map<String, List<String>> parentToChildren = new HashMap<>();

    UnionFind(final List<List<String>> equations, final double[] values) {
      for (int i = 0; i < values.length; i++) {
        final List<String> equation = equations.get(i);
        final double value = values[i];
        final String a = equation.get(0);
        final String b = equation.get(1);

        if (!childToRoot.containsKey(a)) {
          childToRoot.put(a, new Pair(a, 1.00000));
          parentToChildren.put(a, new ArrayList<>());
          parentToChildren.get(a).add(a);
        }

        if (!childToRoot.containsKey(b)) {
          childToRoot.put(b, new Pair(b, 1.00000));
          parentToChildren.put(b, new ArrayList<>());
          parentToChildren.get(b).add(b);
        }

        union(a, b, value);
      }
    }

    private Pair find(String x) {
      return childToRoot.getOrDefault(x, null);
    }

    private void union(String x, String y, Double weight) {
      final Pair rootX = find(x);
      final Pair rootY = find(y);

      if (!rootX.variable.equals(rootY.variable)) {
        final List<String> childrenX = parentToChildren.get(rootX.variable);
        final List<String> childrenY = parentToChildren.get(rootY.variable);

        if (childrenX.size() >= childrenY.size()) {
          childrenX.addAll(childrenY);
          double weightY = rootY.weight;
          for (String children : childrenY) {
            final Pair rootChildren = find(children);
            rootChildren.variable = rootX.variable;
            rootChildren.weight = rootX.weight / weight * rootChildren.weight / weightY;
          }
        } else {
          union(y, x, 1 / weight);
        }
      }
    }
  }
}
