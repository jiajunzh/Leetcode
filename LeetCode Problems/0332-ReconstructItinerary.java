package problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. Problem
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival 
 * airports of one flight. Reconstruct the itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are
 * multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single 
 * string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 *
 * 2. Examples 
 * Example 1
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 * 
 * Example 2
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 *
 * 3. Constraints
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * fromi.length == 3
 * toi.length == 3
 * fromi and toi consist of uppercase English letters.
 * fromi != toi
 */
public class ReconstructItinerary {

  /**
   * 1. Approach 
   * Graph + DFS.
   * 
   * 1) Build Adjacent List as Graph 
   * 2) Backtracking and return once path found. 
   * 
   * 2. Complexity 
   * - Time O(E^d) E is the number of flights and d is the number of maximum number of flights from an airport
   * - Space O(V + E)
   * 
   * 3. Improvement 
   * - Another to approach this using DFS is post-order traversal.
   *   For each node in the graph:
   *   - traverse all its dest first (remove the traversed node from list)
   *   - put the origin node at the first of the path.
   *   
   * @param tickets
   * @return
   */
  public List<String> findItinerary(List<List<String>> tickets) {
    final Map<String, List<String>> graph = buildGraph(tickets);
    final int n = tickets.size();
    final List<String> path = new ArrayList<>();
    path.add("JFK");
    dfs(graph, path, n);
    return path;
  }

  private Boolean dfs(final Map<String, List<String>> graph, final List<String> path, final int n) {
    if (n + 1 == path.size()) {
      return true;
    }

    final String last = path.get(path.size() - 1);
    if (graph.containsKey(last)) {
      List<String> neighbors = graph.get(last);
      for (int i = 0; i < neighbors.size(); i++) {
        String neighbor = neighbors.remove(i);
        path.add(neighbor);
        if (dfs(graph, path, n)) {
          return true;
        }
        path.remove(path.size() - 1);
        neighbors.add(i, neighbor);
      }
    }

    return false;
  }

  private Map<String, List<String>> buildGraph(final List<List<String>> tickets) {
    final Map<String, List<String>> graph = new HashMap<>();

    for (final List<String> ticket : tickets) {
      final String source = ticket.get(0);
      final String destination = ticket.get(1);

      if (!graph.containsKey(source)) {
        graph.put(source, new ArrayList<>());
      }

      graph.get(source).add(destination);
    }

    for (String key : graph.keySet()) {
      Collections.sort(graph.get(key));
    }

    return graph;
  }
}
