package problem;

/**
 * 1. Problem 
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * 2. Examples
 * Example 1
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * 
 * Example 2
 * Input: head = [1], n = 1
 * Output: []
 * 
 * Example 3
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 * 3. Constraints
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * 4. Follow up
 * Could you do this in one pass?
 */
public class RemoveNthNodeFromEndOfList {

  /**
   * 1. Approach 
   * Two Pointers + Sliding Window
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param head
   * @param n
   * @return
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    final ListNode sentinel = new ListNode(0, head);
    ListNode windowStart = sentinel;
    ListNode windowEnd = sentinel;
    for (int i = 0; i < n; i++) {
      windowEnd = windowEnd.next;
    }
    while (windowEnd.next != null) {
      windowStart = windowStart.next;
      windowEnd = windowEnd.next;
    }
    windowStart.next = windowStart.next.next;
    return sentinel.next;
  }

  private static class ListNode { 
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
