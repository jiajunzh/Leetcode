package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder
 * is the inorder traversal of the same tree, construct and return the binary tree.
 *
 * 2. Examples
 * Example 1
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 * 3. Constraints
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    private int currIndex = 0;

    /**
     * 1. Approach
     * Recursion with HashMap
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param inorder
     * @param preorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        final Map<Integer, Integer> inorderIndiceMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndiceMap.put(inorder[i], i);
        }
        return buildTree(preorder, inorderIndiceMap, 0, preorder.length - 1);
    }

    public TreeNode buildTree(final int[] preorder, final Map<Integer, Integer> inorderIndiceMap, final int inStart, final int inEnd) {
        if (inStart > inEnd) return null;
        final int target = preorder[currIndex];
        currIndex++;
        final int targetInorderIndex = inorderIndiceMap.get(target);
        final TreeNode currNode = new TreeNode(target);
        currNode.left = buildTree(preorder, inorderIndiceMap, inStart, targetInorderIndex - 1);
        currNode.right = buildTree(preorder, inorderIndiceMap, targetInorderIndex + 1, inEnd);
        return currNode;
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
