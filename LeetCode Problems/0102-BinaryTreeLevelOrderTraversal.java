package problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1. Problem 
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 * 2. Examples
 * Example 1
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * 
 * Example 2
 * Input: root = [1]
 * Output: [[1]]
 * 
 * Example 3
 * Input: root = []
 * Output: []
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */
public class BinaryTreeLevelOrderTraversal {

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
  public List<List<Integer>> levelOrder(TreeNode root) {
    final List<List<Integer>> result = new ArrayList<>();
    final Queue<TreeNode> queue = new LinkedList<>();
    if (root == null) return result;
    queue.offer(root);
    while (!queue.isEmpty()) {
      final int size = queue.size();
      final List<Integer> nodes = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        TreeNode curr = queue.poll();
        nodes.add(curr.val);
        if (curr.left != null) {
          queue.offer(curr.left);
        }
        if (curr.right != null) {
          queue.offer(curr.right);
        }
      }
      result.add(nodes);
    }
    return result;
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
