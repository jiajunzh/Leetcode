package problem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 1. Problem 
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 * 
 * Example 2
 * Input: root = []
 * Output: []
 * 
 * Example 3
 * Input: root = [1]
 * Output: [1]
 *
 * 3. Constraints
 * The number of the nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 * 4. Follow up
 * Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePostorderTraversal {

  /**
   * 1. Approach 
   * Recursion 
   *
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   *
   * @param root
   * @return
   */
  public List<Integer> postorderTraversalRecursion(TreeNode root) {
    final List<Integer> result = new ArrayList<>();
    postorderTraversalRecursiveHelper(root, result);
    return result;
  }

  private void postorderTraversalRecursiveHelper(TreeNode root, List<Integer> result) {
    if (root == null) return;
    postorderTraversalRecursiveHelper(root.left, result);
    postorderTraversalRecursiveHelper(root.right, result);
    result.add(root.val);
  }

  /**
   * 1. Approach 
   * Iteration => Simply a reversal of pre-order variant 
   * Pre-order (NLR) => NRL => Reverse(LRN)
   *
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   *
   * @param root
   * @return
   */
  public List<Integer> postorderTraversalIteration(TreeNode root) {
    final LinkedList<Integer> result = new LinkedList<>();
    final Deque<TreeNode> stack = new ArrayDeque<>();
    if (root != null) stack.push(root);
    while (!stack.isEmpty()) {
      final TreeNode curr = stack.pop();
      result.addFirst(curr.val);
      if (curr.left != null) stack.push(curr.left);
      if (curr.right != null) stack.push(curr.right);
    }
    return result;
  }

  /**
   * 1. Approach
   * Morris => Reversal of pre-order
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param root
   * @return
   */
  public List<Integer> postorderTraversal(TreeNode root) {
    final LinkedList<Integer> result = new LinkedList<>();
    TreeNode curr = root;
    while (curr != null) {
      if (curr.right == null) {
        result.addFirst(curr.val);
        curr = curr.left;
      } else {
        TreeNode successor = curr.right;
        while (successor.left != null) {
          successor = successor.left;
        }
        successor.left = curr.left;
        result.addFirst(curr.val);
        TreeNode tmp = curr.right;
        curr.right = null;
        curr = tmp;
      }
    }
    return result;
  }
  
  private static class TreeNode {
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
