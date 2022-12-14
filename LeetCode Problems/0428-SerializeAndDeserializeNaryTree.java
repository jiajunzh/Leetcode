package problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1. Problem
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has
 * no more than N children. There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the
 * original tree structure.
 *
 * For example, you may serialize the following 3-ary tree
 *
 * as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.
 *
 * Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated
 * by the null value.
 *
 * For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
 *
 * You do not necessarily need to follow the above-suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 *
 * Example 2
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,null,3,2,4,null,5,6]
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
public class SerializeAndDeserializeNaryTree {

    /**
     * 1. Approach
     * Imagine we have a tree [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
     *
     * There are a various way of encoding an N-ary tree:
     * - [Used in this approach] Put a delimiter/ending char for each group of children => 1#2345##67#8#910##11#12#13##14
     * - DFS + Encode size of children for each node => [14][20][32][60][71][111][140][41][81][120][52][91][130][100]
     * - DFS + Sentinel node when all children are traversed => 12#36#71114####4812###5913##10##
     * - Store link information (child-parent pair) => [21][31][41][51][63][73][84][95][105][117][128][139][1411]
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        final StringBuilder sb = new StringBuilder();
        final Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
            sb.append((char) (root.val + '0'));
            sb.append("#");
        }
        while (!queue.isEmpty()) {
            final Node curr = queue.poll();
            final List<Node> children = curr.children;
            for (Node child : children) {
                queue.offer(child);
                sb.append((char) (child.val + '0'));
            }
            sb.append("#");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()) return null;
        final String[] partitionedData = data.split("#");
        final Queue<Node> queue = new LinkedList<>();
        final Node root = new Node(partitionedData[0].charAt(0) - '0', new ArrayList<>());
        queue.offer(root);
        for (int i = 1; i < partitionedData.length; i++) {
            final Node curr = queue.poll();
            final String siblings = partitionedData[i];
            for (char sibling : siblings.toCharArray()) {
                final Node siblingNode = new Node(sibling - '0', new ArrayList<>());
                curr.children.add(siblingNode);
                queue.offer(siblingNode);
            }
        }
        return root;
    }

    private class Node {
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
}
