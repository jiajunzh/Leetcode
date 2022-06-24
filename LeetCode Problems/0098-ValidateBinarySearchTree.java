package problem;

import java.util.Stack;

/**
 * 1. Problem 
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * 2. Examples
 * Example 1
 * Input: root = [2,1,3]
 * Output: true
 * 
 * Example 2
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 */
public class ValidateBinarySearchTree {

  public class TreeNode { 
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

  /**
   * 1. Approach
   * Recursion
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(H)
   * 
   * @param root
   * @return
   */
  public boolean isValidBST1(TreeNode root) {
    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean isValidBST(final TreeNode root, final long start, final long end) {
    if (root == null) {
      return true;
    }

    long val = root.val;
    return start < val && end > val && isValidBST(root.left, start, val) && isValidBST(root.right, val, end);
  }

  /**
   * 1. Approach 
   * Iterative In-order
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param root
   * @return
   */
  public boolean isValidBST(TreeNode root) {
    final Stack<TreeNode> stack = new Stack<>();
    Integer prev = null;

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      final TreeNode node = stack.pop();
      if (prev != null && prev >= node.val) {
        return false;
      }
      prev = node.val;
      root = node.right;
    }

    return true;
  }
}
