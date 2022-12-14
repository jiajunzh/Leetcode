package problem;

import java.util.*;

/**
 * 1. Problem
 * Given the root of an n-ary tree, return the postorder traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated
 * by the null value (See examples)
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [5,6,3,2,4,1]
 *
 * Example 2
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The height of the n-ary tree is less than or equal to 1000.
 *
 * 4. Follow up
 * Recursive solution is trivial, could you do it iteratively?
 */
public class NaryTreePostorderTraversal {

    /**
     * 1. Approach
     * Recursion
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        final List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(Node root, List<Integer> result) {
        if (root == null) return;
        for (Node child : root.children) {
            postorder(child, result);
        }
        result.add(root.val);
    }

    /**
     * 1. Approach
     * Iteration
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param root
     * @return
     */
    public List<Integer> postorder2(Node root) {
        final LinkedList<Integer> result = new LinkedList<>();
        final Deque<Node> stack = new ArrayDeque<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            final Node curr = stack.pop();
            result.addFirst(curr.val);
            final List<Node> children = curr.children;
            for (Node child : children) {
                stack.push(child);
            }
        }
        return result;
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
