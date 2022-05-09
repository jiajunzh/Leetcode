package problem;

/**
 * 1. Problem 
 * Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.
 *
 * 2. Examples 
 * Example 1
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 * 
 * Example 2
 * Input: root = [1], target = 4.428571
 * Output: 1
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [1, 104].
 * 0 <= Node.val <= 109
 * -109 <= target <= 109
 */
public class ClosestBinarySearchTreeValue {

  /**
   * 1. Approach 
   * Binary Search. One thing that is guaranteed is that by using binary search to traversal through the binary search 
   * tree, the closest element will be traversed along with the searching path. With that, we could always track the 
   * closest element met up till now.
   * 
   * 2. Complexity 
   * - Time O(logN)
   * - Space O(1)
   * 
   * @param root
   * @param target
   * @return
   */
  public int closestValue(TreeNode root, double target) {
    TreeNode curr = root;
    int closestVal = -1_000_000_000;

    while (curr != null) {
      int val = curr.val;
      closestVal = Math.abs(closestVal - target) > Math.abs(val - target) ? val : closestVal;
      curr = val > target ? curr.left : curr.right;
    }

    return closestVal;
  }

  private static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
  };
}
