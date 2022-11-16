package problem;

/**
 * 1. Problem 
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked
 * list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of
 * the last element is the first element.
 *
 * We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to 
 * its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest
 * element of the linked list.
 *
 * 2. Examples
 * Example 1
 * Input: root = [4,2,5,1,3]
 *
 * Output: [1,2,3,4,5]
 *
 * Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
 *
 * Example 2
 * Input: root = [2,1,3]
 * Output: [1,2,3]
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 * All the values of the tree are unique.
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

  /**
   * 1. Approach 
   * Recursion + Build left first and then right
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param root
   * @return
   */
  public Node treeToDoublyList(Node root) {
    if (root == null) return null;
    return helper(root);
  }

  private Node helper(Node root) {
    Node smallest = root;
    if (root.left != null) {
      smallest = helper(root.left);
      root.left = smallest.left;
      smallest.left.right = root;
    }
    Node largest = root;
    if (root.right != null) {
      Node rightSmallest = helper(root.right);
      largest = rightSmallest.left;
      root.right = rightSmallest;
      rightSmallest.left = root;
    }
    smallest.left = largest;
    largest.right = smallest;
    return smallest;
  }

  /**
   * 1. Approach
   * Morris Traversal 
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param root
   * @return
   */
  public Node treeToDoublyList2(Node root) {
    if (root == null) return root;
    final Node head = new Node();
    Node last = head;
    Node curr = root;
    while (curr != null) {
      if (curr.left == null) {
        last.right = curr;
        curr.left = last;
        last = curr;
        curr = curr.right;
      } else {
        Node predecessor = curr.left;
        while (predecessor.right != null) {
          predecessor = predecessor.right;
        }
        predecessor.right = curr;
        Node tmp = curr.left;
        curr.left = null;
        curr = tmp;
      }
    }
    last.right = head.right;
    head.right.left = last;
    return head.right;
  }
  
  private static class Node {
    int val;
    Node left;
    Node right;
    Node() {}
    Node(int val) { this.val = val; }
    Node(int val, Node left, Node right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
