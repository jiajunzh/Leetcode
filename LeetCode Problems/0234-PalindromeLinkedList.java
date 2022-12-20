package problem;

/**
 * 1. Problem
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 *
 * 2. Examples
 * Example 1
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Example 2
 * Input: head = [1,2]
 * Output: false
 *
 * 3. Constraints
 * The number of nodes in the list is in the range [1, 105].
 * 0 <= Node.val <= 9
 *
 * 4. Follow up
 * Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {

    private ListNode frontPointer;

    /**
     * 1. Approach
     * Two Pointers
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        this.frontPointer = head;
        return isPalindromeHelper(head);
    }

    public boolean isPalindromeHelper(ListNode head) {
        if (head == null) return true;
        if (!isPalindromeHelper(head.next) || head.val != frontPointer.val) {
            return false;
        }
        frontPointer = frontPointer.next;
        return true;
    }

    /**
     * 1. Approach
     * Two Pointers + Find Middle Node + Reverse List 
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(1)
     *
     * @param head
     * @return
     */
    public boolean isPalindrome2(ListNode head) {
        final ListNode middleNode = findMiddleNode(head);
        ListNode newHeadOfSecondHalf = reverseList(middleNode);
        ListNode headOfFirstHalf = head, headOfSecondHalf = newHeadOfSecondHalf;
        while (headOfFirstHalf != null && headOfSecondHalf != null) {
            if (headOfFirstHalf.val != headOfSecondHalf.val) {
                return false;
            }
            headOfFirstHalf = headOfFirstHalf.next;
            headOfSecondHalf = headOfSecondHalf.next;
        }
        reverseList(newHeadOfSecondHalf);
        return true;
    }

    private ListNode findMiddleNode(ListNode head) {
        ListNode slowRunner = head, fastRunner = head;
        while (fastRunner != null && fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
        }
        return slowRunner;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
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
