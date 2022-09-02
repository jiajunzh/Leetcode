package problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1. Problem 
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. 
 * Answers within 10-5 of the actual answer will be accepted.
 *
 * 2. Examples
 * Example 1
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 * 
 * Example 2
 * Input: root = [3,9,20,15,7]
 * Output: [3.00000,14.50000,11.00000]
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 */
public class AverageOfLevelsInBinaryTree {

  /**
   * 1. Approach 
   * BFS
   * 
   * 2. Complexity
   * - Time O(N) where N is the number of nodes in tree
   * - Space O(M) where M is the maximum number of nodes in each level
   * 
   * @param root
   * @return
   */
  public List<Double> averageOfLevels(TreeNode root) {
    final List<Double> result = new ArrayList<>();
    final Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      final int size = queue.size();
      long sum = 0;
      for (int i = 0; i < size; i++) {
        final TreeNode curr = queue.poll();
        sum += curr.val;
        if (curr.left != null) {
          queue.offer(curr.left);
        }
        if (curr.right != null) {
          queue.offer(curr.right);
        }
      }
      result.add(sum * 1.0 / size);
    }
    return result;
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
