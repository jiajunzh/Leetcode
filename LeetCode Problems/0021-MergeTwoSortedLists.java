package problem;

/**
 * 1. Problem
 * You are given the heads of two sorted linked lists list1 and list2. Merge the two lists in a one sorted list. The
 * list should be made by splicing together the nodes of the first two lists. Return the head of the merged linked list.
 *
 * 2. Examples
 * Example 1:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * 
 * Example 2:
 * Input: list1 = [], list2 = []
 * Output: []
 * 
 * Example 3:
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 *
 * 3. Constraints
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoSortedLists {
  public class ListNode { 
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

  /**
   * 1. Approach 
   * Iterative approach with two pointers.
   * 
   * 2. Complexity 
   * Time O(N + M)
   * Space O(1)
   *
   * @param list1
   * @param list2
   * @return
   */
  public ListNode mergeTwoListsIterative(ListNode list1, ListNode list2) {
    final ListNode head = new ListNode(0);
    ListNode curr = head;

    while (list1 != null || list2 != null) {
      int v1 = list1 == null? 101 : list1.val;
      int v2 = list2 == null? 101 : list2.val;

      if (v1 < v2) {
        curr.next = list1;
        list1 = list1.next;
      } else {
        curr.next = list2;
        list2 = list2.next;
      }

      curr = curr.next;
    }

    return head.next;
  }

  /**
   * 1. Approach 
   * Recursive approach with two pointers.
   *
   * 2. Complexity 
   * Time O(N + M)
   * Space O(N + M)
   * 
   * @param list1
   * @param list2
   * @return
   */
  public ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
    if (list1 == null || list2 == null) {
      return list1 == null? list2 : list1;
    }

    if (list1.val < list2.val) {
      list1.next = mergeTwoListsRecursive(list1.next, list2);
      return list1;
    } else {
      list2.next = mergeTwoListsRecursive(list1, list2.next);
      return list2;
    }
  }
}
