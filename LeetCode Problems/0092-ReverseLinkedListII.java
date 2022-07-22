package problem;

/**
 * 1. Problem 
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of 
 * the list from position left to position right, and return the reversed list.
 *
 * 2. Examples
 * Example 1
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * 
 * Example 2
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 * 3. Constraints
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * Follow up: Could you do it in one pass?
 */
public class ReverseLinkedListII {

  /**
   * 1. Approach 
   * Recursion 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param head
   * @param left
   * @param right
   * @return
   */
  public ListNode reverseBetween1(ListNode head, int left, int right) {
    if (head == null || head.next == null) return head;
    if (left > 1) {
      head.next = reverseBetween1(head.next, left - 1, right - 1);
      return head;
    }
    if (right == left) return head;
    final ListNode next = head.next;
    final ListNode reversedNode = reverseBetween1(head.next, left, right - 1);
    head.next = next.next;
    next.next = head;
    return reversedNode;
  }

  /**
   * 1. Approach 
   * Iteration 
   *
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   *
   * @param head
   * @param left
   * @param right
   * @return
   */
  public ListNode reverseBetween2(ListNode head, int left, int right) {
    final ListNode sentinal = new ListNode(0, head);
    ListNode leftUnreversed = sentinal;
    int numOfReversedNodes = right - left + 1;
    while (left > 1) {
      leftUnreversed = leftUnreversed.next;
      left--;
    }
    ListNode prev = leftUnreversed;
    ListNode curr = leftUnreversed.next;
    while (numOfReversedNodes > 0) {
      final ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
      numOfReversedNodes--;
    }
    leftUnreversed.next.next = curr;
    leftUnreversed.next = prev;
    return sentinal.next;
  }
  
  private static class ListNode { 
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
