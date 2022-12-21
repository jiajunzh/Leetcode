package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to
 * any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has
 * its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes
 * should point to new nodes in the copied list such that the pointers in the original list and copied list represent
 * the same list state. None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding
 * two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 *
 * 2. Examples
 * Example 1
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * Example 2
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 *
 * Example 3
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 *
 * 3. Constraints
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random is null or is pointing to some node in the linked list.
 */
public class CopyListWithRandomPointer {

    /**
     * 1. Approach
     * Iteration/HashMap from old to copy nodes
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        final Node sentinel = new Node(0);
        final Map<Node, Node> map = new HashMap<>();
        Node curr = head, prevCopy = sentinel;
        while (curr != null) {
            prevCopy.next = map.getOrDefault(curr, new Node(curr.val));
            prevCopy = prevCopy.next;
            map.put(curr, prevCopy);
            if (curr.random != null) {
                prevCopy.random = map.getOrDefault(curr.random, new Node(curr.random.val));
                map.put(curr.random, prevCopy.random);
            }
            curr = curr.next;
        }

        return sentinel.next;
    }

    /**
     * 1. Approach
     * Three Passes/Iteration/No Extra Space
     *
     * - First pass: construct a merged list where node at odd position is the original node and node at even position is
     *   the copy node => A -> A' -> B -> B' -> C -> C' -> D -> D'
     * - Second pass: populate the random pointers
     * - Third pass: separate two list
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
            curr = curr.next.next;
        }
        curr = head;
        while (curr != null) {
            curr.next.random = curr.random == null ? null : curr.random.next;
            curr = curr.next.next;
        }
        Node newHead = new Node(0, head), newCurr = newHead;
        curr = head;
        while (curr != null) {
            newCurr.next = curr.next;
            curr.next = curr.next.next;
            curr = curr.next;
            newCurr = newCurr.next;
        }
        return newHead.next;
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
