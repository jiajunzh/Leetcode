package problem;

/**
 * 1. Problem
 * Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of 
 * the values in its subtree.
 *
 * Note:
 * The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
 * A subtree of root is a tree consisting of root and all of its descendants.
 *
 * 2. Examples
 * Example 1
 * Input: root = [4,8,5,0,1,null,6]
 * Output: 5
 * Explanation: 
 * For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
 * For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
 * For the node with value 0: The average of its subtree is 0 / 1 = 0.
 * For the node with value 1: The average of its subtree is 1 / 1 = 1.
 * For the node with value 6: The average of its subtree is 6 / 1 = 6.
 * 
 * Example 2
 * Input: root = [1]
 * Output: 1
 * Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
 *
 * 3. Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 1000
 */
public class CountNodesEqualToAverageOfSubtree {
  
  private int maximumNode = 0;

  /**
   * 1. Approach 
   * DFS.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(H)
   * 
   * @param root
   * @return
   */
  public int averageOfSubtree(TreeNode root) {
    avgOfSubtree(root);
    return maximumNode;
  }

  private int[] avgOfSubtree(TreeNode root) {
    if (root == null) {
      return new int[]{0, 0};
    }

    final int[] leftAvg = avgOfSubtree(root.left);
    final int[] rightAvg = avgOfSubtree(root.right);

    int sum = root.val + leftAvg[0] + rightAvg[0];
    int count = 1 + leftAvg[1] + rightAvg[1];

    if (sum / count == root.val) {
      maximumNode++;
    }

    return new int[]{sum, count};
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
