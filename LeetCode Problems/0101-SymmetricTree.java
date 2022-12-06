package problem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem 
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * 
 * Example 2
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 *
 * 4. Follow up
 * Could you solve it both recursively and iteratively?
 */
public class SymmetricTree {

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
  public boolean isSymmetric(TreeNode root) {
    return isSymmetric(root, root);
  }

  private boolean isSymmetric(TreeNode left, TreeNode right) {
    if (left == null && right == null) return true;
    if (left == null || right == null || left.val != right.val) return false;
    return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
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
  public boolean isSymmetric2(TreeNode root) {
    final Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    queue.offer(root);
    while (!queue.isEmpty()) {
      TreeNode left = queue.poll();
      TreeNode right = queue.poll();
      if (left == null && right == null) continue;
      if (left == null || right == null || left.val != right.val) return false;
      queue.offer(left.left);
      queue.offer(right.right);
      queue.offer(left.right);
      queue.offer(right.left);
    }
    return true;
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
