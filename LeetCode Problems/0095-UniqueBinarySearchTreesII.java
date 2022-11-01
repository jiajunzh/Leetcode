package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of 
 * unique values from 1 to n. Return the answer in any order.
 *
 * 2. Examples
 * Example 1
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * 
 * Example 2
 * Input: n = 1
 * Output: [[1]]
 * 
 * 3. Constraints
 * 1 <= n <= 8
 */
public class UniqueBinarySearchTreesII {

  /**
   * 1. Approach
   * Recursion + Divide and Conquer
   * 
   * Imagine a BST as below sequence (i is the picked current root):
   * 1 2 3 ... (i - 1) i (i + 1) ... n 
   * Then nodes in [1, i - 1] builds up the left tree of root i and [i + 1, n] builds up the right tree of root i
   * 
   * The other benefit of this approach is that we don't need a deep copy for trees 
   * 
   * 2. Complexity 
   * - Time Complexity 
   *  - Catalan Number => T(N) = Sum(T(i - 1) * T(N -i)) where i is from 1 to N => O(4^n/(n)^1/2), 
   *  - which would be brought down to O(N^3) if using memoization 
   * - Space => N * Catalan Number T(N) as we keep n elements for each Catalan tree
   * 
   * @param n
   * @return
   */
  public List<TreeNode> generateTrees(int n) {
    return generateTrees(1, n);
  }

  private List<TreeNode> generateTrees(int start, int end) {
    final List<TreeNode> result = new ArrayList<>();
    if (end < start) {
      result.add(null);
      return result;
    }
    for (int i = start; i <= end; i++) {
      final List<TreeNode> leftTrees = generateTrees(start, i - 1);
      final List<TreeNode> rightTrees = generateTrees(i + 1, end);
      for (TreeNode leftTree : leftTrees) {
        for (TreeNode rightTree: rightTrees) {
          final TreeNode picked = new TreeNode(i);
          picked.left = leftTree;
          picked.right = rightTree;
          result.add(picked);
        }
      }
    }
    return result;
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
