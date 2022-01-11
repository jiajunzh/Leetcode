package problem;

/**
 * 1. Problem
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * 
 * 2. Examples
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * 
 * Input: head = [1], n = 1
 * Output: []
 * 
 * Input: head = [1,2], n = 1
 * Output: [1]
 * 
 * 3. Constraints
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class RemoveNthNodeFromEnd {
  
  private static class ListNode { 
    int val;
    ListNode next;
    
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  /**
   * Two Pointers. 
   * 
   * Time O(N)
   * Space O(C)
   * 
   * @param head
   * @param n
   * @return
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    final ListNode newHead = new ListNode(0, head);
    ListNode pointer1 = newHead;
    ListNode pointer2 = newHead;

    while (n > 0) {
      pointer2 = pointer2.next;
      n--;
    }

    while (pointer2.next != null) {
      pointer1 = pointer1.next;
      pointer2 = pointer2.next;
    }

    pointer1.next = pointer1.next.next;

    return newHead.next;
  }
  
  /**
   * Recursive solution.
   * 
   * Time O(N)
   * Space O(N)
   * 
   * @param head
   * @param n
   * @return
   */
  public ListNode removeNthFromEndRecursive(ListNode head, int n) {
    final ListNode newHead = new ListNode(0, head);

    removeNthFromEndHelper(newHead, n);

    return newHead.next;
  }

  private int removeNthFromEndHelper(ListNode head, int n) {
    if (head == null) {
      return n;
    }

    int remainingNodes = removeNthFromEndHelper(head.next, n);

    if (remainingNodes == 0) {
      head.next = head.next.next;
    }

    return remainingNodes - 1;
  }
}
