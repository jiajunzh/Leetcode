package problem;

/**
 * 1. Problem 
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary
 * search tree.
 *
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs 
 * by more than one.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 *
 * Example 2
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 *
 * 3. Constraints
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in a strictly increasing order.
 */
public class ConvertSortedArrayToBinarySearchTree {

  /**
   * 1. Approach 
   * Recursive Traversal and Pick Middle Number 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(logN) if not considering the space to hold the results.
   * 
   * @param nums
   * @return
   */
  public TreeNode sortedArrayToBST(int[] nums) {
    return sortedArrayToBST(nums, 0, nums.length - 1);
  }

  private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
    if (start > end) return null;
    final int mid = start + (end - start) / 2;
    final TreeNode node = new TreeNode(nums[mid]);
    node.left = sortedArrayToBST(nums, start, mid - 1);
    node.right = sortedArrayToBST(nums, mid + 1, end);
    return node;
  }
  
  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }
}
