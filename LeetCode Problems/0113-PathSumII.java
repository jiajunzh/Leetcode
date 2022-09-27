package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node 
 * values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 *
 * 2. Examples
 * Example 1
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * 
 * Example 2
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * 
 * Example 3
 * Input: root = [1,2], targetSum = 0
 * Output: []
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class PathSumII {

  /**
   * 1. Approach 
   * DFS
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(N)
   * 
   * @param root
   * @param targetSum
   * @return
   */
  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    return pathSum(new ArrayList<>(), targetSum, root);
  }

  private List<List<Integer>> pathSum(final List<Integer> path, int remainingSum, TreeNode root) {
    final List<List<Integer>> pathSum = new ArrayList<>();
    if (root == null) return pathSum;
    path.add(root.val);
    if (root.left == null && root.right == null) {
      if (remainingSum == root.val) {
        pathSum.add(new ArrayList<>(path));
      }
      path.remove(path.size() - 1);
      return pathSum;
    }
    final List<List<Integer>> left = pathSum(path, remainingSum - root.val, root.left);
    final List<List<Integer>> right = pathSum(path, remainingSum - root.val, root.right);
    path.remove(path.size() - 1);
    pathSum.addAll(left);
    pathSum.addAll(right);
    return pathSum;
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
