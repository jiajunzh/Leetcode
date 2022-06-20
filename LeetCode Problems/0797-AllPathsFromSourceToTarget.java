package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 1. Problem 
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to 
 * node n - 1 and return them in any order.
 *
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed
 * edge from node i to node graph[i][j]).
 *
 * 2. Examples 
 * Example 1
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * 
 * Example 2
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 *
 * 3. Constraints
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i (i.e., there will be no self-loops).
 * All the elements of graph[i] are unique.
 * The input graph is guaranteed to be a DAG.
 */
public class AllPathsFromSourceToTarget {

  /**
   * 1. Approach 
   * Graph + DFS. DAG guarantees that there will not be duplicate path or cyclic path when performing DFS.
   * 
   * 2. Complexity
   * - Time O(2^N * N) => There could be at most 2^N path between two nodes and it takes O(N) to build path.
   * - Space O(2^N * N)
   * 
   * 3. Optimization
   * - It is obvious that for each nextNode from neighbors(currNode): 
   *   AllPathsToTarget(currNode) = {currNode + AllPathsToTarget(nextNode)}
   *   With it, memoization could be used to optimize the time to build paths.
   *   
   * @param graph
   * @return
   */
  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    final List<List<Integer>> result = new ArrayList<>();
    final Stack<List<Integer>> stack = new Stack<>();
    stack.push(List.of(0));

    while (!stack.isEmpty()) {
      final List<Integer> currPath = stack.pop();
      final int size = currPath.size();
      final int lastNode = currPath.get(size - 1);

      for (int next : graph[lastNode]) {
        final List<Integer> newPath = new ArrayList<>(currPath);
        newPath.add(next);
        if (next == graph.length - 1) {
          result.add(newPath);
        } else {
          stack.push(newPath);
        }
      }
    }

    return result;
  }
}
