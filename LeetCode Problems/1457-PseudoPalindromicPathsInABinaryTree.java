package problem;

/**
 * 1. Problem 
 * Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be 
 * pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.
 *
 * Return the number of pseudo-palindromic paths going from the root node to leaf nodes.
 *
 * 2. Examples 
 * Example 1
 * Input: root = [2,3,1,3,1,null,1]
 * Output: 2 
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to 
 * leaf nodes: the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and 
 * green path are pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and 
 * the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * 
 * Example 2
 * Input: root = [2,1,1,1,3,null,null,null,null,null,1]
 * Output: 1 
 * Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes: the green path [2,1,1], the path [2,1,3,1], and the path [2,1]. Among these paths only the green path is pseudo-palindromic since [2,1,1] can be rearranged in [1,2,1] (palindrome).
 * 
 * Example 3
 * Input: root = [9]
 * Output: 1
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [1, 105].
 * 1 <= Node.val <= 9
 */
public class PseudoPalindromicPathsInABinaryTree {

  /**
   * 1. Approach 
   * Preorder traversal
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(H)
   * 
   * 3. Improvement
   * - Another alternative to keep counts array is to use bitmask
   *   - (mask - 1) & mask == 0 could check if there is at most one bit that is one
   *   - mask = mask ^ (1 << val) will set the mask bit at valth digit
   *   
   * @param root
   * @return
   */
  public int pseudoPalindromicPaths (TreeNode root) {
    return pseudoPalindromicPaths(root, new int[10]);
  }

  public int pseudoPalindromicPaths (TreeNode root, int[] counts) {
    if (root == null) {
      return 0;
    }
    int path = 0;
    counts[root.val]++;
    if (root.left == null && root.right == null) {
      path += ispseudoPalindromicPath(counts) ? 1 : 0;
    } else {
      path += pseudoPalindromicPaths(root.left, counts);
      path += pseudoPalindromicPaths(root.right, counts);
    }
    counts[root.val]--;
    return path;
  }

  private boolean ispseudoPalindromicPath(final int[] counts) {
    int oddNum = 0;
    for (int count : counts) {
      if (count % 2 == 1) {
        oddNum++;
      }
    }
    return oddNum <= 1;
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
