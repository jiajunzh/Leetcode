package problem;

/**
 * 1. Problem 
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 * 2. Examples 
 * Example 1
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * 
 * Example 2
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * 
 * Example 3
 * Input: root = []
 * Output: true
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 5000].
 * -104 <= Node.val <= 104
 */
public class BalancedBinaryTree {

  /**
   * 1. Approach 
   * Height Calculation + Recursion 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param root
   * @return
   */
  public boolean isBalanced(TreeNode root) {
    return isBalancedHelper(root) != null;
  }

  private Integer isBalancedHelper(TreeNode root) {
    if (root == null) return 0;
    Integer left = isBalancedHelper(root.left);
    Integer right = isBalancedHelper(root.right);
    if (left == null || right == null) return null;
    if (Math.abs(left - right) > 1) return null;
    return Math.max(left, right) + 1;
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }
}

