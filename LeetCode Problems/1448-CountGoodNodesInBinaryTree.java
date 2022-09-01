package problem;

import java.util.Stack;

/**
 * 1. Problem 
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 *
 * 2. Examples
 * Example 1
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 * 
 * Example 2
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 * 
 * Example 3
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 *
 * 3. Constraints
 * The number of nodes in the binary tree is in the range [1, 10^5].
 * Each node's value is between [-10^4, 10^4].
 */
public class CountGoodNodesInBinaryTree {

  /**
   * 1. Approach 
   * Recursive DFS
   * 
   * 2. Complexity 
   * - Time O(N) where N is the number of nodes
   * - Space O(H)
   * 
   * @param root
   * @return
   */
  public int goodNodes(TreeNode root) {
    return goodNodes(root, Integer.MIN_VALUE);
  }

  private int goodNodes(TreeNode root, int currMax) {
    if (root == null) return 0;
    int goodNodes = 0;
    if (root.val >= currMax) {
      goodNodes++;
    }
    currMax = Math.max(currMax, root.val);
    goodNodes += goodNodes(root.left, currMax);
    goodNodes += goodNodes(root.right, currMax);
    return goodNodes;
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

  /**
   * 1. Approach 
   * Iterative DFS
   *
   * 2. Complexity 
   * - Time O(N) where N is the number of nodes
   * - Space O(H)
   *
   * @param root
   * @return
   */
  public int goodNodes2(TreeNode root) {
    final Stack<StackNode> stack = new Stack<>();
    stack.push(new StackNode(root, Integer.MIN_VALUE));
    int count = 0;
    while(!stack.isEmpty()) {
      final StackNode curr = stack.pop();
      if (curr.prevMax <= curr.node.val) {
        count++;
      }
      if (curr.node.left != null) {
        stack.push(new StackNode(curr.node.left, Math.max(curr.prevMax, curr.node.val)));
      }
      if (curr.node.right != null) {
        stack.push(new StackNode(curr.node.right, Math.max(curr.prevMax, curr.node.val)));
      }
    }
    return count;
  }

  private static class StackNode {
    private final TreeNode node;
    private final int prevMax;

    private StackNode(TreeNode node, int prevMax) {
      this.node = node;
      this.prevMax = prevMax;
    }
  }
}
