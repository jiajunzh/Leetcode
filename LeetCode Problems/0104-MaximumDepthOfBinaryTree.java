package problem;

/**
 * 1. Problem 
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * 2. Examples
 * Example 1
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * 
 * Example 2
 * Input: root = [1,null,2]
 * Output: 2
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 */
public class MaximumDepthOfBinaryTree {

  /**
   * 1. Approach 
   * Recursion
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * 3. Alternative 
   * - Another approach is to use BFS instead of recursion
   * 
   * @param root
   * @return
   */
  public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);
    return Math.max(leftDepth, rightDepth) + 1;
  }

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
}
