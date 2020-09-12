import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Condition:
 * (1) Left subtree is less than the root;
 * (2) Right subtree is greater than the root;
 * ==> Make a range for root value
 * (3) Left and right subtrees are also BST.
 */
public class ValidateBinarySearchTree {

  /**
   * Recursion approach. 
   * (1) Use Long or Double which has wider range than Integer
   * (2) Exploit that Integer could be null
   * 
   * Time: O(N) visit each node for once 
   * Space: O(H) relevant to tree depth, O(N) in worst cases
   * 
   * @param root
   * @return
   */
  public boolean isValidBST1(TreeNode root) {
    return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private boolean isValidBSTHelper(TreeNode root, long min, long max) {
    if (root == null) return true;

    if (root.val >= max || root.val <= min) {
      return false;
    }

    return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val, max);
  }

  /**
   * Inorder traversal: the element visited first should be less than the next one.
   * 
   * Time O(N)
   * Space O(N)
   * 
   * @param root
   * @return
   */
  public boolean isValidBST2(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    double min = - Double.MAX_VALUE;
    
    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.push(root);
        root = root.left;
      }
      
      root = stack.pop();
      
      if (root.val <= min) return false;
      min = root.val;
      
      root = root.right;
    }
    
    return true;
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
