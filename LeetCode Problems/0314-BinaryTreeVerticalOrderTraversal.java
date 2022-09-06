package problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 1. Problem 
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom,
 * column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * 2. Examples
 * Example 1
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * 
 * Example 2
 * Input: root = [3,9,8,4,0,1,7]
 * Output: [[4],[9],[3,0,1],[8],[7]]
 * 
 * Example 3
 * Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 * Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class BinaryTreeVerticalOrderTraversal {

  /**
   * 1. Approach 
   * BFS + Sorting 
   * 
   * The problem could be translated into the two constraints below:
   * (1) element at smaller column number should appear before what is at larger column number in final result
   * (2) For each column, element at smaller row number should appear before what is at larger number 
   * 
   * 2. Complexity 
   * - Time O(N + N * logN)
   * - Space O(N)
   * 
   * @param root
   * @return
   */
  public List<List<Integer>> verticalOrder(TreeNode root) {
    final Queue<Node> queue = new LinkedList<>();
    final Map<Integer, List<Integer>> map = new HashMap<>();
    if (root != null) queue.offer(new Node(0, root));
    while (!queue.isEmpty()) {
      final Node curr = queue.poll();
      final List<Integer> columns = map.getOrDefault(curr.col, new ArrayList<>());
      columns.add(curr.node.val);
      map.put(curr.col, columns);
      if (curr.node.left != null) {
        queue.offer(new Node(curr.col - 1, curr.node.left));
      }
      if (curr.node.right != null) {
        queue.offer(new Node(curr.col + 1, curr.node.right));
      }
    }
    final List<List<Integer>> result = new ArrayList<>();
    final List<Integer> keys = new ArrayList<>(map.keySet());
    Collections.sort(keys);
    for (final int key : keys) {
      result.add(map.get(key));
    }
    return result;
  }

  /**
   * 1. Approach 
   * BFS with no Sorting version 1
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param root
   * @return
   */
  public List<List<Integer>> verticalOrder2(TreeNode root) {
    final LinkedList<List<Integer>> result = new LinkedList<>();
    final Queue<Node> queue = new LinkedList<>();
    if (root != null) queue.offer(new Node(0, root));
    int minCol = 1;
    while (!queue.isEmpty()) {
      final Node curr = queue.poll();
      if (curr.col < minCol) {
        result.addFirst(new ArrayList<>());
        minCol = curr.col;
      } else if (curr.col - minCol > result.size() - 1) {
        result.add(new ArrayList<>());
      }
      result.get(curr.col - minCol).add(curr.node.val);
      if (curr.node.left != null) {
        queue.offer(new Node(curr.col - 1, curr.node.left));
      }
      if (curr.node.right != null) {
        queue.offer(new Node(curr.col + 1, curr.node.right));
      }
    }
    return result;
  }

  /**
   * 1. Approach 
   * BFS with no Sorting version 2 (Map)
   * 
   * Note that minCol and maxCol are initialized as 1 and -1 instead of 0s is to avoid the edge case when root == null
   *
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   *
   * @param root
   * @return
   */
  public List<List<Integer>> verticalOrder3(TreeNode root) {
    final Queue<Node> queue = new LinkedList<>();
    final Map<Integer, List<Integer>> columnMap = new HashMap<>();
    if (root != null) queue.offer(new Node(0, root));
    int minCol = 1, maxCol = -1;
    while (!queue.isEmpty()) {
      final Node curr = queue.poll();
      final List<Integer> columnList = columnMap.getOrDefault(curr.col, new ArrayList<>());
      columnList.add(curr.node.val);
      columnMap.put(curr.col, columnList);
      minCol = Math.min(minCol, curr.col);
      maxCol = Math.max(maxCol, curr.col);
      if (curr.node.left != null) {
        queue.offer(new Node(curr.col - 1, curr.node.left));
      }
      if (curr.node.right != null) {
        queue.offer(new Node(curr.col + 1, curr.node.right));
      }
    }

    final List<List<Integer>> result = new ArrayList<>();
    for (int i = minCol; i <= maxCol; i++) {
      result.add(columnMap.get(i));
    }
    return result;
  }

  private static class Node {

    private final int col;
    private final TreeNode node;

    private Node(int col, TreeNode node) {
      this.col = col;
      this.node = node;
    }
  }

  private static class TreeNode { 
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }
}
