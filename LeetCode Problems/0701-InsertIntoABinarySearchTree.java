package problem;

/**
 * 1. Problem 
 * You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node 
 * of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 *
 * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. 
 * You can return any of them.
 *
 * 2. Examples 
 * Example 1
 * Input: root = [4,2,7,1,3], val = 5
 * Output: [4,2,7,1,3,5]
 * Explanation: Another accepted tree is:
 *
 * Example 2
 * Input: root = [40,20,60,10,30,50,70], val = 25
 * Output: [40,20,60,10,30,50,70,null,null,25]
 * 
 * Example 3
 * Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * Output: [4,2,7,1,3,5]
 *
 * 3. Constraints
 * The number of nodes in the tree will be in the range [0, 104].
 * -108 <= Node.val <= 108
 * All the values Node.val are unique.
 * -108 <= val <= 108
 * It's guaranteed that val does not exist in the original BST.
 * 
 */
public class InsertIntoABinarySearchTree {

  /**
   * 1. Approach 
   * Iteration + BST Insertion
   * 
   * 2. Complexity
   * - Time O(H)
   * - Space O(1)
   * 
   * @param root
   * @param val
   * @return
   */
  public TreeNode insertIntoBST(TreeNode root, int val) {
    TreeNode curr = root;
    while (curr != null) {
      if (curr.val > val) {
        if (curr.left == null) {
          curr.left = new TreeNode(val);
          return root;
        }
        curr = curr.left;
      }
      if (curr.val < val) {
        if (curr.right == null) {
          curr.right = new TreeNode(val);
          return root;
        }
        curr = curr.right;
      }
    }
    return new TreeNode(val);
  }
  
  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

}
