package problem;

/**
 * 1. Problem 
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes 
 * greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * 2. Examples
 * Example 1
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * 
 * Example 2
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 * 3. Constraints
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class PartitionList {

  /**
   * 1. Approach 
   * Sentinel + Two Pointers + List
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param head
   * @param x
   * @return
   */
  public ListNode partition(ListNode head, int x) {
    ListNode left = new ListNode(0, null);
    ListNode right = new ListNode(0, null);
    final ListNode leftHead = left;
    final ListNode rightHead = right;
    while (head != null) {
      if (head.val < x) {
        left.next = head;
        left = left.next;
      } else {
        right.next = head;
        right = right.next;
      }
      head = head.next;
    }
    right.next = null;
    left.next = rightHead.next;
    return leftHead.next;
  }
  
  private static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
