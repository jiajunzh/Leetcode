package problem;

/**
 * 1. Problem 
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in 
 * reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as 
 * a linked list. You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 
 * 2. Example 
 * 
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * 
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * 
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * 
 * 3. Constraints
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbers {

  /**
   * LinkedList. The key to this problem is to notice that if the digit does not exist in ith position in one of the 
   * lists, just take it as zero value.
   * 
   * Mistakes:
   * 1. Null pointer check when assign next node to the current node.
   * 
   * if (l1 != null) l1 = l1.next;
   * 
   * Test case: l1 = [8, 9], l2 = [3].
   * 
   * 2. Ignore the last carryout
   * 
   * if (carryOut == 1) {
   *     prev.next = new ListNode(1);
   * }
   * 
   * Test case: l1 = [8, 9], l2 = [3], result = [1, 0, 1] instead of [1, 0]
   *
   * @param l1
   * @param l2
   * @return
   */
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    /* Sentinel node will point to the first node of the returned list */
    final ListNode sentinel = new ListNode(0, null);
    ListNode curr = sentinel;
    int carryout = 0;
        
    while (l1 != null || l2 != null || carryout != 0) {
      final int v1 = l1 == null? 0 : l1.val;
      final int v2 = l2 == null? 0 : l2.val;
      final int sum = v1 + v2 + carryout;
      final int digit = sum % 10;
      carryout = sum / 10;
            
      curr.next = new ListNode(digit, null);
      curr = curr.next;
            
      l1 = l1 == null? null : l1.next;
      l2 = l2 == null? null : l2.next;
    }
        
      return sentinel.next;
  }
  
  public static void main(String[] args) {
    testCase1();
    testCase2();
    testCase3();
  }

  private static void testCase1() {
    final ListNode listNode1 = new ListNode(0);
    final ListNode listNode2 = new ListNode(0);
    final int[] expected = new int[]{0};
    
    verify(listNode1, listNode2, expected);
  }
  
  private static void testCase2() {
    final ListNode listNode1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, 
      new ListNode(9, new ListNode(9, new ListNode(9)))))));
    final ListNode listNode2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
    final int[] expected = new int[]{8, 9, 9, 9, 0, 0, 0, 1};

    verify(listNode1, listNode2, expected);
  }
  
  private static void testCase3() {
    final ListNode listNode1 = new ListNode(2, new ListNode(4, new ListNode(3)));
    final ListNode listNode2 = new ListNode(5, new ListNode(6, new ListNode(4)));
    final int[] expected = new int[]{7, 0, 8};

    verify(listNode1, listNode2, expected);
  }
  
  private static void verify(final ListNode listNode1, final ListNode listNode2, final int[] expected) {
    ListNode curr = addTwoNumbers(listNode1, listNode2);
    
    for (final int val: expected) {
      assert(curr != null);
      assert(val == curr.val);
      curr = curr.next;
    }
    
    assert(curr == null);
  }
  
  private static class ListNode { 
    int val;
    ListNode next;
    
    ListNode() {}
    
    ListNode(int val) { 
      this.val = val; 
    }
    
    ListNode(int val, ListNode next) {
      this.val = val; 
      this.next = next; 
    }
  }
}
