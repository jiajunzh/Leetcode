package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *
 * Collect all the leaf nodes.
 * Remove all the leaf nodes.
 * Repeat until the tree is empty.
 *
 * 2. Examples 
 * Example 1
 * Input: root = [1,2,3,4,5]
 * Output: [[4,5,3],[2],[1]]
 * Explanation:
 * [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter 
 * the order on which elements are returned.
 * 
 * Example 2
 * Input: root = [1]
 * Output: [[1]]
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [1, 100].
 * -100 <= Node.val <= 100
 */
public class FindLeavesOfBinaryTree {

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
  public List<List<Integer>> findLeaves(TreeNode root) {
    final List<List<Integer>> result = new ArrayList<>();
    findLeaves(root, result);
    return result;
  }

  private int findLeaves(final TreeNode root, final List<List<Integer>> result) {
    if (root == null) return 0;
    int level = Math.max(findLeaves(root.left, result), findLeaves(root.right, result)) + 1;
    if (result.size() < level) {
      result.add(new ArrayList<>());
    }
    result.get(level - 1).add(root.val);
    return level;
  }

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }
}
