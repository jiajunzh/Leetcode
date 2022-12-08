package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder
 * is the postorder traversal of the same tree, construct and return the binary tree.
 *
 * 2. Examples
 * Example 1
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 *
 * 3. Constraints
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder and postorder consist of unique values.
 * Each value of postorder also appears in inorder.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * postorder is guaranteed to be the postorder traversal of the tree.
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    private int[] postorder;
    private int currIndex;

    /**
     * 1. Approach
     * Recursion with Linear Search
     *
     * 2. Complexity
     * - Time O(N^2)
     * - Space O(N)
     *
     * 3. Improvement
     * - Searching for the index of target requires O(N) time. This could be optimized to O(1) time with HashMap as values in postorder array is unique
     * - The currIndex could be derived from target Index
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.currIndex = inorder.length - 1;
        return buildTreeHelper(inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] inorder, int start, int end) {
        if (start > end) return null;
        final int target = postorder[currIndex];
        currIndex--;
        final int inorderIndex = findTargetIndex(inorder, start, end, target);
        final TreeNode currNode = new TreeNode(target);
        currNode.right = buildTreeHelper(inorder, inorderIndex + 1, end);
        currNode.left = buildTreeHelper(inorder, start, inorderIndex - 1);
        return currNode;
    }

    private int findTargetIndex(int[] inorder, int start, int end, int target) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == target) return i;
        }
        return -1;
    }

    /**
     * 1. Approach
     * Recursion with HashMap
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        final Map<Integer, Integer> inorderIndiceMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndiceMap.put(inorder[i], i);
        }
        return buildTreeHelper(inorderIndiceMap, 0, inorder.length - 1, postorder, postorder.length - 1);
    }

    private TreeNode buildTreeHelper(final Map<Integer, Integer> inorderIndiceMap, int inStart, int inEnd, int[] postorder, int postIndex) {
        if (inStart > inEnd) return null;
        final int target = postorder[postIndex];
        final int inorderIndex = inorderIndiceMap.get(target);
        final TreeNode currNode = new TreeNode(target);
        currNode.right = buildTreeHelper(inorderIndiceMap, inorderIndex + 1, inEnd, postorder, postIndex - 1);
        currNode.left = buildTreeHelper(inorderIndiceMap, inStart, inorderIndex - 1, postorder, postIndex - 1 - (inEnd - inorderIndex));
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
