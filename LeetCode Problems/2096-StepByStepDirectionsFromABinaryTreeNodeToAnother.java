package problem;

/**
 * 1. Problem 
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are 
 * also given an integer startValue representing the value of the start node s, and a different integer destValue
 * representing the value of the destination node t.
 *
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path 
 * as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 *
 * 'L' means to go from a node to its left child node.
 * 'R' means to go from a node to its right child node.
 * 'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t.
 *
 * 2. Examples 
 * Example 1
 * Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 * Output: "UURL"
 * Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
 * 
 * Example 2
 * Input: root = [2,1], startValue = 2, destValue = 1
 * Output: "L"
 * Explanation: The shortest path is: 2 → 1.
 *
 * 3. Constraints
 * The number of nodes in the tree is n.
 * 2 <= n <= 105
 * 1 <= Node.val <= n
 * All the values in the tree are unique.
 * 1 <= startValue, destValue <= n
 * startValue != destValue
 */
public class StepByStepDirectionsFromABinaryTreeNodeToAnother {

  /**
   * 1. Approach 
   * DFS 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param root
   * @param startValue
   * @param destValue
   * @return
   */
  public String getDirections(TreeNode root, int startValue, int destValue) {
    final StringBuilder srcPath = new StringBuilder();
    final StringBuilder destPath = new StringBuilder();
    getDirections(root, startValue, destValue, srcPath, destPath);
    return srcPath + destPath.reverse().toString();
  }

  private int getDirections(TreeNode root, int startValue, int destValue, StringBuilder srcPath, StringBuilder destPath) {
    if (root == null) return 0;
    int left = getDirections(root.left, startValue, destValue, srcPath, destPath);
    if (left == 1) srcPath.append('U');
    if (left == 2) destPath.append('L');
    if (left == 3) return 3;
    int right = getDirections(root.right, startValue, destValue, srcPath, destPath);
    if (right == 1) srcPath.append('U');
    if (right == 2) destPath.append('R');
    int count = left + right;
    if (root.val == startValue) {
      count++;
    }
    if (root.val == destValue) {
      count += 2;
    }
    return count;
  }
  
  public class TreeNode { 
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }
}
