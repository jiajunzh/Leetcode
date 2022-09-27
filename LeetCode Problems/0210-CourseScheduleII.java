package problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1. Problem 
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array 
 * prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of 
 * them. If it is impossible to finish all courses, return an empty array.
 * 
 * 2. Examples 
 * Example 1
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * 
 * Example 2
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * 
 * Example 3
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 * 3. Constraints
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 */
public class CourseScheduleII {
  
  /**
   * 1. Approach
   * DFS 
   * 
   * 2. Complexity 
   * - Time O(V + E)
   * - Space O(V + E)
   * 
   * @param numCourses
   * @param prerequisites
   * @return
   */
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    final List<Integer>[] graph = buildGraph(numCourses, prerequisites);
    final List<Integer> orderList = new ArrayList<>();
    final int[] count = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      if (dfs(graph, orderList, count, i)) {
        return new int[0];
      }
    }
    final int[] order = new int[numCourses];
    for (int i = 0; i < numCourses; i++) {
      order[i] = orderList.get(numCourses - 1 - i);
    }
    return order;
  }

  private boolean dfs(List<Integer>[] graph, List<Integer> orderList, int[] count, int curr) {
    if (count[curr] == 1) {
      return true;
    }
    if (count[curr] == 2) {
      return false;
    }
    boolean hasCycle = false;
    count[curr]++;
    for (int neighbor : graph[curr]) {
      hasCycle = hasCycle || dfs(graph, orderList, count, neighbor);
    }
    count[curr]++;
    orderList.add(curr);
    return hasCycle;
  }

  private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
    final List<Integer>[] graph = new List[numCourses];
    for (int i = 0; i < numCourses; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] prerequisite : prerequisites) {
      graph[prerequisite[1]].add(prerequisite[0]);
    }
    return graph;
  }

  /**
   * 1. Approach 
   * Kahn's Algorithm
   * 
   * 2. Complexity 
   * - Time O(V + E)
   * - Space O(V + E)
   * 
   * @param numCourses
   * @param prerequisites
   * @return
   */
  public int[] findOrder2(int numCourses, int[][] prerequisites) {
    final int[] indegrees = new int[numCourses];
    final List<Integer>[] graph = new List[numCourses];
    buildGraph(prerequisites, indegrees, graph);
    final Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegrees[i] == 0) {
        queue.offer(i);
      }
    }
    final int[] order = new int[numCourses];
    int index = 0;
    while (!queue.isEmpty()) {
      int curr = queue.poll();
      order[index] = curr;
      index++;
      for (int neighbor : graph[curr]) {
        indegrees[neighbor]--;
        if (indegrees[neighbor] == 0) {
          queue.offer(neighbor);
        }
      }
    }
    if (index != numCourses) return new int[0];
    return order;
  }

  private void buildGraph(final int[][] prerequisites, final int[] indegrees, final List<Integer>[] graph) {
    for (int i = 0; i < graph.length; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] prerequisite : prerequisites) {
      indegrees[prerequisite[0]]++;
      graph[prerequisite[1]].add(prerequisite[0]);
    }
  }
}
