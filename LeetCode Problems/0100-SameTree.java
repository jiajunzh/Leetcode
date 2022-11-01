package problem;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1. Problem 
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 *
 * 2. Examples
 * Example 1
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * 
 * Example 2
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * Example 3
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 *
 * 3. Constraints
 * The number of nodes in both trees is in the range [0, 100].
 * -104 <= Node.val <= 104
 */
public class SameTree {

  /**
   * 1. Approach 
   * Iterative Approach + Stack
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param p
   * @param q
   * @return
   */
  public boolean isSameTree(TreeNode p, TreeNode q) {
    final Deque<TreeNode> stackP = new ArrayDeque<>();
    final Deque<TreeNode> stackQ = new ArrayDeque<>();
    if (!isSameNode(p, q)) return false;
    if (p == null) return true;
    stackP.push(p);
    stackQ.push(q);
    while (!stackP.isEmpty()) {
      final TreeNode currP = stackP.poll();
      final TreeNode currQ = stackQ.poll();
      if (!isSameNode(currP.left, currQ.left) || !isSameNode(currP.right, currQ.right)) {
        return false;
      }
      if (currP.left != null) {
        stackP.push(currP.left);
        stackQ.push(currQ.left);
      }
      if (currP.right != null) {
        stackP.push(currP.right);
        stackQ.push(currQ.right);
      }
    }
    return true;
  }

  private boolean isSameNode(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    return p.val == q.val;
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
