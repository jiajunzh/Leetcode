package problem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
  public List<Integer> inorderTraversalRecursion(TreeNode root) {
    final List<Integer> result = new ArrayList<>();
    inorderTraversalRecursiveHelper(root, result);
    return result;
  }

  private void inorderTraversalRecursiveHelper(TreeNode root, List<Integer> result) {
    if (root == null) return;
    inorderTraversalRecursiveHelper(root.left, result);
    result.add(root.val);
    inorderTraversalRecursiveHelper(root.right, result);
  }

  /**
   * 1. Approach 
   * Iteration
   *
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   *
   * @param root
   * @return
   */
  public List<Integer> inorderTraversalIteration(TreeNode root) {
    final List<Integer> result = new ArrayList<>();
    final Deque<TreeNode> stack = new ArrayDeque<>();
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
   * Morris Traversal. Think about three cases below:
   * - The current tree node has non-null left subtree
   *  - The rightmost node of the left subtree is pointing to null => Link this node to current node and traverse down left subtree
   *  - The rightmost node of the left subtree is pointing to current => Meaning current node's left subtree has been visited, go current node's right 
   * - The current tree's left subtree is null => visit current node and traverse right subtree
   *
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   *
   * @param root
   * @return
   */
  public List<Integer> inorderTraversal(TreeNode root) {
    final List<Integer> result = new ArrayList<>();
    TreeNode curr = root;
    while (curr != null) {
      if (curr.left == null) {
        result.add(curr.val);
        curr = curr.right;
      } else {
        TreeNode predecessor = curr.left;
        while (predecessor.right != null) {
          predecessor = predecessor.right;
        }
        predecessor.right = curr;
        TreeNode tmp = curr.left;
        curr.left = null;
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
