package problem;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 1. Problem 
 * Given the root of a binary tree, construct a string consisting of parenthesis and integers from a binary tree with 
 * the preorder traversal way, and return it.
 *
 * Omit all the empty parenthesis pairs that do not affect the one-to-one mapping relationship between the string 
 * and the original binary tree.
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,2,3,4]
 * Output: "1(2(4))(3)"
 * Explanation: Originally, it needs to be "1(2(4)())(3()())", but you need to omit all the unnecessary empty parenthesis pairs. And it will be "1(2(4))(3)"
 * 
 * Example 2
 * Input: root = [1,2,3,null,4]
 * Output: "1(2()(4))(3)"
 * Explanation: Almost the same as the first example, except we cannot omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [1, 104].
 * -1000 <= Node.val <= 1000
 */
public class ConstructStringFromBinaryTree {

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
  public String tree2str(TreeNode root) {
    final StringBuilder sb = new StringBuilder();
    tree2str(sb, root);
    return sb.toString();
  }

  private void tree2str(StringBuilder sb, TreeNode root) {
    if (root == null) return;
    sb.append(root.val);
    if (root.left == null && root.right == null) return;
    sb.append('(');
    tree2str(sb, root.left);
    sb.append(')');
    if (root.right != null) {
      sb.append('(');
      tree2str(sb, root.right);
      sb.append(')');
    }
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
  public String tree2str2(TreeNode root) {
    final Stack<TreeNode> stack = new Stack<>();
    final Set<TreeNode> set = new HashSet<>();
    final StringBuilder sb = new StringBuilder();
    stack.push(root);
    while (!stack.isEmpty()) {
      final TreeNode curr = stack.peek();
      if (set.contains(curr)) {
        stack.pop();
        sb.append(')');
      } else {
        set.add(curr);
        sb.append("(").append(curr.val);
        if (curr.left == null && curr.right != null) {
          sb.append("()");
        }
        if (curr.right != null) {
          stack.push(curr.right);
        }
        if (curr.left != null) {
          stack.push(curr.left);
        }
      }
    }
    final String result = sb.toString();
    return result.substring(1, result.length() - 1);
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
