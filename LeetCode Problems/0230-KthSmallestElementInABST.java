package problem;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1. Problem
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the
 * values of the nodes in the tree.
 *
 * 2. Examples
 * Example 1
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 *
 * Example 2
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 *
 * 3. Constraints
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 *
 * 4. Follow up
 * If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest
 * frequently, how would you optimize?
 */
public class KthSmallestElementInABST {

    /**
     * 1. Approach
     * Inorder Traversal + Stack
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * 3. Follow-up
     * - Expand the TreeNode structure to include two fields that record number of nodes in each left/right subtree
     *
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        final Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            k--;
            if (k == 0) return curr.val;
            curr = curr.right;
        }
        return -1;
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
