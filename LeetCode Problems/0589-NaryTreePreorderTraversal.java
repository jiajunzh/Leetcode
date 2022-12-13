package problem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 1. Problem
 * Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
 *
 * Nary-Tree input serialization is represented in their level order traversal. Each group of children is separated by
 * the null value (See examples)
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,3,5,6,2,4]
 *
 * Example 2
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 104].
 * 0 <= Node.val <= 104
 * The height of the n-ary tree is less than or equal to 1000.
 *
 * 4. Follow up
 * Recursive solution is trivial, could you do it iteratively?
 */
public class NaryTreePreorderTraversal {

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
    public List<Integer> preorder(Node root) {
        final List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    public void preorderHelper(Node root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        for (Node child : root.children) {
            preorderHelper(child, result);
        }
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
    public List<Integer> preorder2(Node root) {
        final List<Integer> result = new ArrayList<>();
        final Deque<Node> stack = new ArrayDeque<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            final Node curr = stack.pop();
            result.add(curr.val);
            final List<Node> children = curr.children;
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
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
    };
}
