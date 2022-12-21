package problem;

import java.util.ArrayDeque;
import java.util.Deque;

public class FlattenAMultilevelDoublyLinkedList {

    /**
     * 1. Approach
     * Recursion version 1.
     *
     * Flatten the child list first and return the tail, then link the upper level nodes
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param head
     * @return
     */
    public Node flatten1(Node head) {
        flattenHelper1(head);
        return head;
    }

    public Node flattenHelper1(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            prev = curr;
            if (curr.child != null) {
                final Node childLastNode = flattenHelper1(curr.child);
                childLastNode.next = curr.next;
                if (curr.next != null) curr.next.prev = childLastNode;
                curr.next = curr.child;
                curr.child.prev = curr;
                curr.child = null;
                prev = childLastNode;
            }
            curr = next;
        }
        return prev;
    }

    /**
     * 1. Approach
     * Recursion version 2.
     *
     * Flatten the child list first and return the tail, then link the upper level nodes
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param head
     * @return
     */
    public Node flatten2(Node head) {
        if (head == null) return null;
        final Node sentinel = new Node(0, null, head, null);
        flattenHelper2(sentinel, head);
        head.prev = null;
        return sentinel.next;
    }

    public Node flattenHelper2(Node prev, Node curr) {
        if (curr == null) return prev;
        curr.prev = prev;
        prev.next = curr;

        Node next = curr.next;
        Node tail = flattenHelper2(curr, curr.child);
        curr.child = null;

        return flattenHelper2(tail, next);
    }

    /**
     * 1. Approach
     * Stack/Iteration
     *
     * Flatten the child list first and return the tail, then link the upper level nodes
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        if (head == null) return null;
        final Deque<Node> stack = new ArrayDeque<>();
        stack.push(head);
        Node prev = new Node(0, null, head, null);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            prev.next = curr;
            curr.prev = prev;
            if (curr.next != null) stack.push(curr.next);
            if (curr.child != null) {
                stack.push(curr.child);
                curr.child = null;
            }
            prev = curr;
        }
        head.prev = null;
        return head;
    }

    private static class Node {
        private int val;
        private Node prev;
        private Node next;
        private Node child;

        private Node(int val, Node prev, Node next, Node child) {
            this.val = val;
            this.prev = prev;
            this.next = next;
            this.child = child;
        }
    }
}
