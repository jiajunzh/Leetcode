import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

  private Map<Node, Node> nodeCopy = new HashMap<>();

  /**
   * Return a deep copy of the graph given a reference of a node
   * in a connected undirected graph.
   *
   * DFS used here.
   *
   * Deep copy: new copy objects are created for any originally referenced objects.
   *
   * Time Complexity: O(N)
   * Space Complexity: O(N) 
   * 
   * @param node
   * @return
   */
  public Node cloneGraphDfs(Node node) {
    if (node == null) {
      return null;
    }

    if (nodeCopy.containsKey(node)) {
      return nodeCopy.get(node);
    }

    Node newCopy = new Node(node.val);
    nodeCopy.put(node, newCopy);

    for (Node neighbor: node.neighbors) {
      Node neighborCopy = cloneGraphDfs(neighbor);
      newCopy.neighbors.add(neighborCopy);
    }

    return newCopy;
  }

  /**
   * Return a deep copy of the graph given a reference of a node
   * in a connected undirected graph.
   *
   * BFS used here.
   * 
   * Time Complexity: O(N) each node processed exactly once.
   * Space Complexity: O(N) map & queue
   *
   * @param node
   * @return
   */
  public Node cloneGraphBfs(Node node) {
    if (node == null) {
      return null;
    }
    
    Map<Node, Node> nodeCopy = new HashMap<>();
    Queue<Node> queue = new LinkedList<>();
    queue.add(node);
    nodeCopy.put(node, new Node(node.val));
    
    while (!queue.isEmpty()) {
      Node curr = queue.poll();
      
      for (Node neighbor: curr.neighbors) {
        if (!nodeCopy.containsKey(neighbor)) {
          nodeCopy.put(neighbor, new Node(neighbor.val));
          
          queue.add(neighbor);
        }
        
        nodeCopy.get(curr).neighbors.add(nodeCopy.get(neighbor));
      }
    }
    
    return nodeCopy.get(node);
  }
  
  class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
      val = 0;
      neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
      val = _val;
      neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
      val = _val;
      neighbors = _neighbors;
    }
  }
}

