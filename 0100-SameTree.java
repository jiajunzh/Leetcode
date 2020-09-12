/**
 * Given two binary trees, write a function to check if they are the same or not.
 * 
 * (1) root is the same
 * (2) left tree and right tree is the same
 */
public class SameTree {
  /**
   * DFS
   * 
   * Time O(N)
   * Space O(N)
   * 
   * @param p
   * @param q
   * @return
   */
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    if (p.val != q.val) return false;

    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
