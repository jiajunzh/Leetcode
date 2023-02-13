package problem;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1. Problem
 * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped
 * by mistake. Recover the tree without changing its structure.
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 *
 * Example 2
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [2, 1000].
 * -231 <= Node.val <= 231 - 1
 *
 * 4. Follow up
 * A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
 */
public class RecoverBinarySearchTree {

    /**
     * 1. Approach
     * Iterative approach.
     *
     * This problem could be separated into multiple pieces:
     * 1) Inorder traversal of binary search tree is a sorted array. This problem is translated into swap two elements
     * that are swapped by mistake in an almost sorted array
     * 2) Find the two items in an almost sorted array could be done in O(N) time
     * 3) Swap two items
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        final Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root, prev = null, firstNode = null, secondNode = null;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (prev != null && prev.val > curr.val) {
                secondNode = curr;
                if (firstNode == null) {
                    firstNode = prev;
                } else {
                    break;
                }
            }
            prev = curr;
            curr = curr.right;
        }

        swap(firstNode, secondNode);
    }

    private void swap(TreeNode firstNode, TreeNode secondNode) {
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }

    /**
     * 1. Approach
     * Morris Traversal
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1)
     *
     * @param root
     */
    public void recoverTree2(TreeNode root) {
        TreeNode curr = root, prev = null, firstNode = null, secondNode = null;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode predecessor = curr.left;
                while (predecessor.right != curr && predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = curr;
                    curr = curr.left;
                    continue;
                } else {
                    predecessor.right = null;
                }
            }

            if (prev != null && curr.val < prev.val) {
                secondNode = curr;
                if (firstNode == null) {
                    firstNode = prev;
                }
                // We could not do break here as it will exit early and some of the linking will remaing in the tree
                // and cause a cycle in the tree
                // else {
                //     break;
                // }
            }
            prev = curr;
            curr = curr.right;
        }

        swap(firstNode, secondNode);
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
