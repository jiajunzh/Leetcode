package problem;

/**
 * 1. Problem
 * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even
 * indices, and return the reordered list.
 *
 * The first node is considered odd, and the second node is even, and so on.
 *
 * Note that the relative order inside both the even and odd groups should remain as it was in the input.
 *
 * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
 *
 * 2. Examples
 * Example 1
 * Input: head = [1,2,3,4,5]
 * Output: [1,3,5,2,4]
 *
 * Example 2
 * Input: head = [2,1,3,5,6,4,7]
 * Output: [2,3,6,7,1,5,4]
 *
 * 3. Constraints
 * The number of nodes in the linked list is in the range [0, 104].
 * -106 <= Node.val <= 106
 */
public class OddEvenLinkedList {

    /**
     * 1. Approach
     * Two Pointers
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1)
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        final ListNode oddSentinel = new ListNode(0);
        final ListNode evenSentinel = new ListNode(0);
        ListNode oddCurr = oddSentinel, evenCurr = evenSentinel;

        while (head != null) {
            oddCurr.next = head;
            oddCurr = oddCurr.next;
            head = head.next;
            evenCurr.next = head;
            evenCurr = evenCurr.next;
            if (head != null) {
                head = head.next;
            }
        }

        oddCurr.next = evenSentinel.next;
        return oddSentinel.next;
    }

    /**
     * 1. Approach
     * Same Approach with Cleaner Implementation
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1)
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
