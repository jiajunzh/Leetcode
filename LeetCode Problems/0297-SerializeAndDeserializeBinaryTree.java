package problem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/
 * deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily
 * need to follow this format, so please be creative and come up with different approaches yourself.
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 *
 * Example 2
 * Input: root = []
 * Output: []
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 */
public class SerializeAndDeserializeBinaryTree {
    private int index;

    /**
     * 1. Approach
     * DFS with StringBuilder
     *
     * 2. Complexity
     * - Time O(N) for serialize and de-serialize
     * - Space O(N) for serialize and de-serialize
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        final StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("N");
        } else {
            sb.append(root.val);
            sb.append("/");
            serializeHelper(root.left, sb);
            sb.append("/");
            serializeHelper(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        final String[] serializedData = data.split("/");
        this.index = 0;
        return deserializeHelper(serializedData);
    }

    private TreeNode deserializeHelper(String[] serializedData) {
        final String currData = serializedData[index];
        index++;
        if ("N".equals(currData)) {
            return null;
        }
        TreeNode curr = new TreeNode(Integer.parseInt(currData));
        curr.left = deserializeHelper(serializedData);
        curr.right = deserializeHelper(serializedData);
        return curr;
    }

    /**
     * 1. Approach
     * BFS with StringBuilder
     *
     * 2. Complexity
     * - Time O(N) for serialize and de-serialize
     * - Space O(N) for serialize and de-serialize
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        final StringBuilder sb = new StringBuilder();
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            final TreeNode curr = queue.poll();
            if (curr != null) {
                sb.append(curr.val);
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                sb.append("N");
            }
            sb.append("/");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        final String[] partitionedData = data.split("/");
        if (partitionedData.length == 0 || partitionedData[0].equals("N")) return null;
        final TreeNode root = new TreeNode(Integer.parseInt(partitionedData[0]));
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < partitionedData.length; i += 2) {
            TreeNode curr =  queue.poll();
            if (!partitionedData[i].equals("N")) {
                curr.left = new TreeNode(Integer.parseInt(partitionedData[i]));
                queue.offer(curr.left);
            }
            if (!partitionedData[i + 1].equals("N")) {
                curr.right = new TreeNode(Integer.parseInt(partitionedData[i + 1]));
                queue.offer(curr.right);
            }
        }
        return root;
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
