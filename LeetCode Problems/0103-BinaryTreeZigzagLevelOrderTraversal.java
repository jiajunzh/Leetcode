package problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 1. Problem
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to
 * right, then right to left for the next level and alternate between).
 *
 * 2. Examples
 * Example 1
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 *
 * Example 2
 * Input: root = [1]
 * Output: [[1]]
 *
 * Example 3
 * Input: root = []
 * Output: []
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    /**
     * 1. Problem
     * BFS
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        final List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> currLevel = new ArrayList<>();
        if (root != null) currLevel.add(root);
        int level = 0;
        while (currLevel.size() > 0) {
            final int size = currLevel.size();
            final List<TreeNode> nextLevel = new ArrayList<>();
            final LinkedList<Integer> currLevelVal = new LinkedList<>();
            for (final TreeNode curr : currLevel) {
                if (level % 2 == 0) {
                    currLevelVal.addLast(curr.val);
                } else {
                    currLevelVal.addFirst(curr.val);
                }
                if (curr.left != null) nextLevel.add(curr.left);
                if (curr.right != null) nextLevel.add(curr.right);
            }
            result.add(currLevelVal);
            currLevel = nextLevel;
            level++;
        }
        return result;
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
