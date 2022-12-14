package problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1. Problem
 * Design an algorithm to encode an N-ary tree into a binary tree and decode the binary tree to get the original N-ary
 * tree. An N-ary tree is a rooted tree in which each node has no more than N children. Similarly, a binary tree is a
 * rooted tree in which each node has no more than 2 children. There is no restriction on how your encode/decode
 * algorithm should work. You just need to ensure that an N-ary tree can be encoded to a binary tree and this binary
 * tree can be decoded to the original N-nary tree structure.
 *
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by
 * the null value (See following example).
 *
 * For example, you may encode the following 3-ary tree to a binary tree in this way:
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Note that the above is just an example which might or might not work. You do not necessarily need to follow this
 * format, so please be creative and come up with different approaches yourself.
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,null,3,2,4,null,5,6]
 *
 * Example 2
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 *
 * Example 3
 * Input: root = []
 * Output: []
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The height of the n-ary tree is less than or equal to 1000
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 */
public class EncodeNaryTreeToBinaryTree {

    /**
     * 1. Approach
     * Recursion
     *
     * Encoding:
     * Each TreeNode's left points to the first child of the next level and right points to its siblings in the current level.
     * For example, if we have a 3-ary tree as below
     *                1
     *        2       3       4
     *     5  6  7          8 9 10
     * Then we could get a binary tree as below
     *               1
     *               |
     *               2     ->      3     -> 4
     *               |                      |
     *               5 -> 6 -> 7            8 -> 9 -> 10
     * In here | represents left pointer and -> represents right pointer
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param root
     * @return
     */
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root == null) return null;
        final TreeNode rootCopy = new TreeNode(root.val);
        final List<Node> children = root.children;
        if (!children.isEmpty()) {
            rootCopy.left = encode(children.get(0));
            TreeNode curr = rootCopy.left;
            for (int i = 1; i < children.size(); i++) {
                curr.right = encode(children.get(i));
                curr = curr.right;
            }
        }
        return rootCopy;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null) return null;
        final List<Node> children = new ArrayList<>();
        TreeNode curr = root.left;
        while (curr != null) {
            children.add(decode(curr));
            curr = curr.right;
        }
        return new Node(root.val, children);
    }

    /**
     * 1. Approach
     * BFS version
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param root
     * @return
     */
    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode2(Node root) {
        if (root == null) return null;
        final Queue<Node> naryTreeQueue = new LinkedList<>();
        final Queue<TreeNode> binaryTreeQueue = new LinkedList<>();
        final TreeNode binaryTreeRoot = new TreeNode(root.val);
        naryTreeQueue.offer(root);
        binaryTreeQueue.offer(binaryTreeRoot);
        while (!naryTreeQueue.isEmpty()) {
            final Node naryTreeNode = naryTreeQueue.poll();
            TreeNode binaryTreeNode = binaryTreeQueue.poll();
            final List<Node> children = naryTreeNode.children;
            if (! children.isEmpty()) {
                Node firstChild = children.get(0);
                binaryTreeNode.left = new TreeNode(firstChild.val);
                naryTreeQueue.offer(firstChild);
                binaryTreeQueue.offer(binaryTreeNode.left);
                binaryTreeNode = binaryTreeNode.left;
                for (int i = 1; i < children.size(); i++) {
                    Node ithChild = children.get(i);
                    binaryTreeNode.right = new TreeNode(ithChild.val);
                    naryTreeQueue.offer(ithChild);
                    binaryTreeQueue.offer(binaryTreeNode.right);
                    binaryTreeNode = binaryTreeNode.right;
                }
            }
        }
        return binaryTreeRoot;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode2(TreeNode root) {
        if (root == null) return null;
        final Queue<Node> naryTreeQueue = new LinkedList<>();
        final Queue<TreeNode> binaryTreeQueue = new LinkedList<>();
        final Node naryTreeRoot = new Node(root.val, new ArrayList<>());
        naryTreeQueue.offer(naryTreeRoot);
        binaryTreeQueue.offer(root);
        while (!naryTreeQueue.isEmpty()) {
            final Node naryTreeNode = naryTreeQueue.poll();
            final TreeNode binaryTreeNode = binaryTreeQueue.poll();
            TreeNode binaryTreeChildNode = binaryTreeNode.left;
            while (binaryTreeChildNode != null) {
                Node naryTreeChildNode = new Node(binaryTreeChildNode.val, new ArrayList<>());
                naryTreeNode.children.add(naryTreeChildNode);
                naryTreeQueue.offer(naryTreeChildNode);
                binaryTreeQueue.offer(binaryTreeChildNode);
                binaryTreeChildNode = binaryTreeChildNode.right;
            }
        }
        return naryTreeRoot;
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
