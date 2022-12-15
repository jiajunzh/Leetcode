package problem;

/**
 * 1. Problem
 * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and
 * next is a pointer/reference to the next node.
 * If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the
 * linked list. Assume all nodes in the linked list are 0-indexed.
 *
 * Implement the MyLinkedList class:
 *
 * MyLinkedList() Initializes the MyLinkedList object.
 * int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
 * void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion,
 * the new node will be the first node of the linked list.
 * void addAtTail(int val) Append a node of value val as the last element of the linked list.
 * void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index
 * equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater
 * than the length, the node will not be inserted.
 * void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
 *
 * 2. Examples
 * Example 1
 * Input
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * Output
 * [null, null, null, null, 2, null, 3]
 *
 * Explanation
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
 * myLinkedList.get(1);              // return 2
 * myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
 * myLinkedList.get(1);              // return 3
 *
 * 3. Constraints
 * 0 <= index, val <= 1000
 * Please do not use the built-in LinkedList library.
 * At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
 */
public class DesignLinkedListSLLVersion {

    private final Node sentinel;
    private int size;

    /**
     * 1. Approach
     *
     * 2. Complexity
     * - Time O(N) for get, O(1) for addAtHead, O(N) for addAtTail, O(N) for addAtIndex and O(N) for deleteAtIndex
     * - Space O(N)
     */
    public DesignLinkedListSLLVersion() {
        this.sentinel = new Node(0);
        this.size = 0;
    }

    public int get(int index) {
        if (index < 0 || index > this.size - 1) return -1;
        return getNode(index).val;
    }

    private Node getNode(int index) {
        Node curr = sentinel;
        while (index >= 0) {
            curr = curr.next;
            index--;
        }
        return curr;
    }

    public void addAtHead(int val) {
        final Node newHead = new Node(val);
        newHead.next = this.sentinel.next;
        this.sentinel.next = newHead;
        this.size++;
    }

    public void addAtTail(int val) {
        final Node lastNode = getNode(this.size - 1);
        lastNode.next = new Node(val);
        this.size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > this.size) return;
        final Node nodeAtIndex = getNode(index - 1);
        final Node curr = new Node(val);
        curr.next = nodeAtIndex.next;
        nodeAtIndex.next = curr;
        this.size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index > this.size - 1) return;
        final Node nodeAtIndex = getNode(index - 1);
        nodeAtIndex.next = nodeAtIndex.next.next;
        this.size--;
    }

    private static class Node {
        private int val;
        private Node next;

        private Node(int val) {
            this.val = val;
        }
    }
}
