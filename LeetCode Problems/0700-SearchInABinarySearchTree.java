package problem;

/**
 * 1. Problem 
 * You are given the root of a binary search tree (BST) and an integer val.
 *
 * Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a 
 * node does not exist, return null.
 *
 * 2. Examples
 * Example 1
 * Input: root = [4,2,7,1,3], val = 2
 * Output: [2,1,3]
 * 
 * Example 2
 * Input: root = [4,2,7,1,3], val = 5
 * Output: []
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [1, 5000].
 * 1 <= Node.val <= 107
 * root is a binary search tree.
 * 1 <= val <= 107
 */
public class SearchInABinarySearchTree {

  /**
   * 1. Approach 
   * Recursion 
   * 
   * 2. Complexity 
   * - Time O(H)
   * - Space O(H)
   * 
   * @param root
   * @param val
   * @return
   */
  public TreeNode searchBSTRecursive(TreeNode root, int val) {
    if (root == null) {
      return root;
    }

    if (root.val == val) {
      return root;
    } else if (root.val < val) {
      return searchBSTRecursive(root.right, val);
    } else {
      return searchBSTRecursive(root.left, val);
    }
  }

  /**
   * 1. Approach 
   * Iterative
   * 
   * 2. Complexity 
   * - Time O(H)
   * - Space O(1)
   * 
   * @param root
   * @param val
   * @return
   */
  public TreeNode searchBSTIterative(TreeNode root, int val) {
    while (root != null) {
      if (root.val == val) {
        return root;
      } else if (root.val < val) {
        root = root.right;
      } else {
        root = root.left;
      }
    }
    return null;
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
