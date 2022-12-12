package problem;

/**
 * 1. Problem
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * 2. Examples
 * Example 1
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 * Example 2
 * Input: root = []
 * Output: []
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 6000].
 * -100 <= Node.val <= 100
 *
 * 4. Follow-up
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class PopulatingNextRightPointersInEachNodeII {

    /**
     * 1.Approach
     * Iteration + Sentinel Node
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1)
     * 
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return null;
        Node leftMost = root;
        while (leftMost != null) {
            Node sentinel = new Node(0), curr = sentinel;
            while (leftMost != null) {
                if (leftMost.left != null) {
                    curr.next = leftMost.left;
                    curr = curr.next;
                }
                if (leftMost.right != null) {
                    curr.next = leftMost.right;
                    curr = curr.next;
                }
                leftMost = leftMost.next;
            }
            leftMost = sentinel.next;
        }
        return root;
    }

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
