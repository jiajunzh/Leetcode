package problem;

/**
 * 1. Problem 
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 * 2. Examples
 * Example 1
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * 
 * Example 2
 * Input: head = [1,2]
 * Output: [2,1]
 * 
 * Example 3
 * Input: head = []
 * Output: []
 *
 * 3. Constraints
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 *
 * Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {

  /**
   * 1. Approach 
   * Recursive 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * 3. Mistakes
   * - Make sure the head.next points to null, otherwise it will cause cyclic list. E.g. [1,2]
   * 
   * @param head
   * @return
   */
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;

    return newHead;
  }

  /**
   * 1. Approach 
   * Iterative 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param head
   * @return
   */
  public ListNode reverseListIterative(ListNode head) {
    ListNode prev = null;

    while (head != null) {
      ListNode nextNode = head.next;
      head.next = prev;
      prev = head;
      head = nextNode;
    }

    return prev;
  }
  
  private static class ListNode { 
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
