package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. Problem
 * Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,2,3,4,null,2,4,null,null,4]
 * Output: [[2,4],[4]]
 * 
 * Example 2
 * Input: root = [2,1,1]
 * Output: [[1]]
 * 
 * Example 3
 * Input: root = [2,2,2,3,null,3,null]
 * Output: [[2,3],[3]]
 *
 * 3. Constraints
 * The number of the nodes in the tree will be in the range [1, 10^4]
 * -200 <= Node.val <= 200
 */
public class FindDuplicateSubtrees {
  
  private Map<String, Integer> map;
  private List<TreeNode> result;

  /**
   * 1. Approach 
   * Post Order Search + HashMap
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N * N)
   * 
   * 3. Mistakes 
   * -  The order to combine keys are crucial => String.join("-", leftKey, rightKey, String.valueOf(root.val));
   *    left + root + right is incorrect. One edge case could be [0,0,0,0,null,null,0,null,null,null,0]
   * 
   * @param root
   * @return
   */
  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    map = new HashMap<>();
    result = new ArrayList<>();
    getSubtreeKey(root);
    return result;
  }

  private String getSubtreeKey(final TreeNode root) {
    if (root == null) {
      return "N";
    }
    final String leftKey = getSubtreeKey(root.left);
    final String rightKey = getSubtreeKey(root.right);
    final String rootKey = String.join("-", leftKey, rightKey, String.valueOf(root.val));
    final int count = map.getOrDefault(rootKey, 0) + 1;
    if (count == 2) result.add(root);
    map.put(rootKey, count);
    return rootKey;
  }

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }
}
