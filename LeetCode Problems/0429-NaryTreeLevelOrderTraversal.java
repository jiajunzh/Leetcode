package problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1. Problem 
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated
 * by the null value (See examples).
 *
 * 2. Examples 
 * Example 1
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [[1],[3,2,4],[5,6]]
 * 
 * Example 2
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 *
 * 3. Constraints
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 104]
 */
public class NaryTreeLevelOrderTraversal {

  /**
   * 1. Approach 
   * BFS 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param root
   * @return
   */
  public List<List<Integer>> levelOrder(Node root) {
    final List<List<Integer>> result = new ArrayList<>();
    final Queue<Node> queue = new LinkedList<>();
    if (root == null) return result;
    queue.offer(root);
    while (!queue.isEmpty()) {
      final int size = queue.size();
      final List<Integer> currentLevel = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        final Node node = queue.poll();
        currentLevel.add(node.val);
        for (final Node child : node.children) {
          queue.offer(child);
        }
      }
      result.add(currentLevel);
    }

    return result;
  }
  
  private static class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  };
}
