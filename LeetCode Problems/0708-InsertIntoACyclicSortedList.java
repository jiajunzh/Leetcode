package problem;

/**
 * 1. Problem
 * Given a Circular Linked List node, which is sorted in non-descending order, write a function to insert a value
 * insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single
 * node in the list and may not necessarily be the smallest value in the circular list.
 *
 * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the
 * insertion, the circular list should remain sorted.
 *
 * If the list is empty (i.e., the given node is null), you should create a new single circular list and return the
 * reference to that single node. Otherwise, you should return the originally given node.
 *
 * 2. Examples
 * Example 1
 * Input: head = [3,4,1], insertVal = 2
 * Output: [3,4,1,2]
 * Explanation: In the figure above, there is a sorted circular list of three elements. You are given a reference to
 * the node with value 3, and we need to insert 2 into the list. The new node should be inserted between node 1 and
 * node 3. After the insertion, the list should look like this, and we should still return node 3.
 *
 * Example 2
 * Input: head = [], insertVal = 1
 * Output: [1]
 * Explanation: The list is empty (given head is null). We create a new single circular list and return the reference to that single node.
 *
 * Example 3
 * Input: head = [1], insertVal = 0
 * Output: [1,0]
 *
 * 3. Constraints
 * The number of nodes in the list is in the range [0, 5 * 104].
 * -106 <= Node.val, insertVal <= 106
 */
public class InsertIntoACyclicSortedList {

    /**
     * 1. Approach
     * Two Pointers
     *
     * Imagine we want to insert number 2, there are four cases:
     * - 1 -> 2 -> 3: prev.val <= insertVal && curr.val >= insertVal
     * - 5 -> 2 -> 3: prev.val > curr.val && insertVal <= curr.val
     * - 1 -> 2 -> 0: prev.val > curr.val && insertVal >= prev.val
     * - 2 -> 2 -> 2: loop the whole circle and then pick up any node to insert the next
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1)
     *
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }

        Node prev = head, curr = head.next;
        do {
            if ((prev.val <= insertVal && curr.val >= insertVal) ||
                    (prev.val > curr.val && (insertVal >= prev.val || insertVal <= curr.val))) {
                break;
            }
            prev = curr;
            curr = curr.next;
        } while (head != prev);

        prev.next = new Node(insertVal, curr);
        return head;
    }

    private static class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };
}
