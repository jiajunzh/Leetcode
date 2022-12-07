package problem;

/**
 * 1. Problem
 * Given the root of a binary tree, return the number of uni-value subtrees.
 *
 * A uni-value subtree means all nodes of the subtree have the same value.
 *
 * 2. Examples
 * Example 1
 * Input: root = [5,1,5,5,5,null,5]
 * Output: 4
 *
 * Example 2
 * Input: root = []
 * Output: 0
 *
 * Example 3
 * Input: root = [5,5,5,5,5,null,5]
 * Output: 6
 *
 * 3. Constraints
 * The number of the node in the tree will be in the range [0, 1000].
 * -1000 <= Node.val <= 1000
 */
public class CountUnivalueSubtrees {

    private int count = 0;

    /**
     * 1. Approach
     * Recursion with Parent Value
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     * 
     * @param root
     * @return
     */
    public int countUnivalSubtrees(TreeNode root) {
        isUnivalSubtrees(root, 0);
        return count;
    }

    private boolean isUnivalSubtrees(TreeNode root, int prev) {
        if (root == null) return true;
        final boolean isUnivalLeftSubtree = isUnivalSubtrees(root.left, root.val);
        final boolean isUnivalRightSubtree = isUnivalSubtrees(root.right, root.val);
        if (isUnivalLeftSubtree && isUnivalRightSubtree) count++;
        return isUnivalLeftSubtree && isUnivalRightSubtree && root.val == prev;
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
