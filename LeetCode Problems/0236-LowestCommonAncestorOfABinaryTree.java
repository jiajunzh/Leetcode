package problem;

import java.util.*;

/**
 * 1. Problem
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as
 * the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * 2. Examples
 * Example 1
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q will exist in the tree.
 */
public class LowestCommonAncestorOfABinaryTree {

    private TreeNode lcaNode = null;

    /**
     * 1. Approach
     * Recursion
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        lowestCommonAncestorHelper(root, p, q);
        return lcaNode;
    }

    public int lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return 0;
        final int leftMatchedCount = lowestCommonAncestorHelper(root.left, p, q);
        final int rightMatchedCount = lowestCommonAncestorHelper(root.right, p, q);
        final int rootMatchedCount = root.val == p.val || root.val == q.val ? 1 : 0;
        final int matchedCount = leftMatchedCount + rightMatchedCount + rootMatchedCount;
        if (matchedCount == 2 && lcaNode == null) lcaNode = root;
        return matchedCount;
    }

    /**
     * 1. Approach
     * Iterative DFS with Stack (Could also be BFS with Queue) + Parent Pointers Map + Upward Chain
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        final Deque<TreeNode> stack = new ArrayDeque<>();
        final Map<TreeNode, TreeNode> parents = new HashMap<>();
        stack.push(root);
        parents.put(root, null);
        while (!parents.containsKey(p) || !parents.containsKey(q)) {
            final TreeNode curr = stack.pop();
            if (curr.left != null) {
                stack.push(curr.left);
                parents.put(curr.left, curr);
            }
            if (curr.right != null) {
                stack.push(curr.right);
                parents.put(curr.right, curr);
            }
        }
        final Set<TreeNode> upwardChain = new HashSet<>();
        while (p != null) {
            upwardChain.add(p);
            p = parents.get(p);
        }
        while (!upwardChain.contains(q)) {
            q = parents.get(q);
        }
        return q;
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

    /**
     * 1. Approach
     * Iterative DFS with Stack (Could also be BFS with Queue) + No Parent Pointers Map
     * See https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solutions/194159/lowest-common-ancestor-of-a-binary-tree/
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        TreeNode lca = null;
        boolean oneNodeFound = false;
        final Deque<TreeState> stack = new ArrayDeque<>();
        stack.push(new TreeState(root));
        while (!stack.isEmpty()) {
            final TreeState treeState = stack.peek();

            if (treeState.nodesToTraverse == 0) {
                stack.pop();
                if (treeState.node == lca && oneNodeFound) {
                    lca = stack.peek().node;
                }
            } else {
                TreeNode next;
                if (treeState.nodesToTraverse == 2) {
                    if (treeState.node.val == p.val || treeState.node.val == q.val) {
                        if (oneNodeFound) {
                            return lca;
                        } else {
                            oneNodeFound = true;
                            lca = treeState.node;
                        }
                    }
                    next = treeState.node.left;
                } else {
                    next = treeState.node.right;
                }
                treeState.nodesToTraverse--;
                if (next != null) {
                    stack.push(new TreeState(next));
                }
            }
        }
        return lca;
    }

    private class TreeState {
        private int nodesToTraverse;
        private TreeNode node;

        private TreeState(final TreeNode node) {
            this.nodesToTraverse = 2;
            this.node = node;
        }
    }
}
