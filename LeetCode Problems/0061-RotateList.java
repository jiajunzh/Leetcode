package problem;

/**
 * 1. Problem
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * 2. Examples
 * Example 1
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 *
 * Example 2
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 *
 * 3. Constraints
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class RotateList {

    /**
     * 1. Approach
     * Two Pointers
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1)
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        int size = getListSize(head);
        if (size <= 1) return head;
        k = k % size;
        if (k == 0) return head;
        ListNode slowRunner = head, fastRunner = head;
        while (k > 0) {
            fastRunner = fastRunner.next;
            k--;
        }

        while (fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next;
        }

        ListNode newHead = slowRunner.next;
        slowRunner.next = null;
        fastRunner.next = head;
        return newHead;
    }

    private int getListSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    /**
     * 1. Approach
     * Break the Ring:
     * - Make this list as a cyclic list by connecting the tail and head together
     * - Count the number of nodes in the list
     * - Move cursor/pointer to the link which need to be broken 
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1)
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null) return null;
        int size = 1;
        ListNode curr = head;
        while (curr.next != null) {
            size++;
            curr = curr.next;
        }
        curr.next = head;

        k = size - k % size;
        while (k > 0) {
            curr = curr.next;
            k--;
        }
        ListNode newHead = curr.next;
        curr.next = null;
        return newHead;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
