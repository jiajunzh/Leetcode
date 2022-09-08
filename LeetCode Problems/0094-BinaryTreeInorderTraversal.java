package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 1. Problem 
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
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
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 * 4. Follow up
 * Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {

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
  public List<Integer> inorderTraversal(TreeNode root) {
    final List<Integer> result = new ArrayList<>();
    inorderTraversal(root, result);
    return result;
  }

  public void inorderTraversal(TreeNode root, List<Integer> result) {
    if (root == null) return;
    inorderTraversal(root.left, result);
    result.add(root.val);
    inorderTraversal(root.right, result);
  }

  /**
   * 1. Approach 
   * Iteration with Stack
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param root
   * @return
   */
  public List<Integer> inorderTraversal2(TreeNode root) {
    final List<Integer> result = new ArrayList<>();
    final Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = root;
    while (curr != null || !stack.isEmpty()) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
      curr = stack.pop();
      result.add(curr.val);
      curr = curr.right;
    }
    return result;
  }

  /**
   * 1. Approach 
   * Morris Traversal. The reason of having stack in regular traversal is to keep track of the previous nodes so that
   * we could traverse back later on. Morris traversal finds a way of traversing back by pointing rightmos node (predecessor) 
   * back to the current visited node, which eliminates the needs of stack.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param root
   * @return
   */
  public List<Integer> inorderTraversal3(TreeNode root) {
    final List<Integer> result = new ArrayList<>();
    TreeNode curr = root;
    while (curr != null) {
      if (curr.left == null) {
        result.add(curr.val);
        curr = curr.right;
      } else {
        TreeNode predecessor = curr.left;
        while (predecessor.right != null && predecessor.right != curr) {
          predecessor = predecessor.right;
        }
        if (predecessor.right == null) {
          predecessor.right = curr;
          curr = curr.left;
        } else {
          predecessor.right = null;
          result.add(curr.val);
          curr = curr.right;
        }
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
