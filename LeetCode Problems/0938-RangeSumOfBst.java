package problem;

import java.util.Stack;

/**
 * 1. Problem
 * Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes 
 * with a value in the inclusive range [low, high].
 *
 * 2. Examples
 * Example 1:
 * Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 * Output: 32
 * Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
 * 
 * Example 2:
 * Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * Output: 23
 * Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [1, 2 * 104].
 * 1 <= Node.val <= 105
 * 1 <= low <= high <= 105
 * All Node.val are unique.
 */
public class RangeSumOfBst {

  /**
   * 1. Approach 
   * BFS recursive. 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(logN)
   * 
   * @param root
   * @param low
   * @param high
   * @return
   */
  public int rangeSumBST1(TreeNode root, int low, int high) {
    if (root == null) {
      return 0;
    }

    if (root.val >= low && root.val <= high) {
      return root.val + rangeSumBST1(root.right, low, high) + rangeSumBST1(root.left, low, high);
    } else if (root.val < low) {
      return rangeSumBST1(root.right, low, high);
    } else {
      return rangeSumBST1(root.left, low, high);
    }
  }

  /**
   * 1. Approach 
   * BFS iterative.
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(logN)
   * 
   * @param root
   * @param low
   * @param high
   * @return
   */
  public int rangeSumBST2(TreeNode root, int low, int high) {
    final Stack<TreeNode> stack = new Stack<>();
    int sum = 0;
    stack.push(root);

    while (!stack.isEmpty()) {
      final TreeNode curr = stack.pop();
      if (curr != null) {
        if (curr.val <= high && curr.val >= low) {
          sum += curr.val;
        }
        if (curr.val < high) {
          stack.push(curr.right);
        }
        if (curr.val > low) {
          stack.push(curr.left);
        }
      }
    }

    return sum;
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
