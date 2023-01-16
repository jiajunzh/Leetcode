package problem;

import java.util.*;

/**
 * 1. Problem
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array
 * prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take
 * course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * 2. Examples
 * Example 1
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 * 3. Constraints
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {

    /**
     * 1. Approach
     * DFS
     *
     * 2. Complexity
     * - Time O(C + P)
     * - Space O(C + P)
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        final List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        final boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i]) continue;
            if (!dfs(graph, visited, i, new HashSet<>())) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(final List<Integer>[] graph, final boolean[] visited, int id, final Set<Integer> set) {
        if (set.contains(id)) return false;
        if (visited[id]) return true;
        List<Integer> neighbors = graph[id];
        visited[id] = true;
        set.add(id);
        for (int neighbor : neighbors) {
            if (!dfs(graph, visited, neighbor, set)) {
                return false;
            }
        }
        set.remove(id);
        return true;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        final List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[0]].add(prerequisite[1]);
        }
        return graph;
    }

    /**
     * 1. Approach
     * Indegree + BFS
     *
     * 2. Complexity
     * - Time O(C + P)
     * - Space O(C + P)
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        final List<Integer>[] graph = new List[numCourses];
        final int[] indegrees = new int[numCourses];
        buildGraph(numCourses, prerequisites, graph, indegrees);
        final Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;
            List<Integer> neighbors = graph[curr];
            for (int neighbor : neighbors) {
                indegrees[neighbor]--;
                if (indegrees[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return count == numCourses;
    }

    private void buildGraph(int numCourses, int[][] prerequisites, final List<Integer>[] graph, final int[] indegrees) {
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]].add(prerequisite[0]);
            indegrees[prerequisite[0]]++;
        }
    }
}
