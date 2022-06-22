package problem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer 
 * should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * 2. Examples 
 * Example 1
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to
 * point to its next right node, just like in Figure B. The serialized output is in level order as connected by the 
 * next pointers, with '#' signifying the end of each level.
 * 
 * Example 2
 * Input: root = []
 * Output: []
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 212 - 1].
 * -1000 <= Node.val <= 1000
 *
 * 4. Follow-up
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class PopulatingNextRightPointersInEachNode {

  /**
   * 1. Approach 
   * BFS 
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(N)
   * 
   * @param root
   * @return
   */
  public Node connect1(Node root) {
    final Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();

      Node prev = new Node(0);
      for (int i = 0; i < size; i++) {
        Node curr = queue.poll();
        if (curr == null) continue;
        prev.next = curr;
        prev = curr;

        queue.offer(curr.left);
        queue.offer(curr.right);
      }
    }

    return root;
  }

  /**
   * 1. Approach
   * Reuse Established Next Pointer. One thing to notice is that all next pointers at level N are already established
   * when we are building next pointer at level N + 1. Then the previously built next pointers could be reused to 
   * build the next level. As this is a perfect binary tree, there are two cases to consider:
   * 1) root.left.next = root.right
   * 2) root.right.next = root.next.left
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param root
   * @return
   */
  public Node connect2(Node root) {
    if (root == null) {
      return null;
    }

    Node levelStart = root;
    while (levelStart.left != null) {
      Node head = levelStart;
      while (head != null) {
        head.left.next = head.right;
        if (head.next != null) {
          head.right.next = head.next.left;
        }
        head = head.next;
      }
      levelStart = levelStart.left;
    }
    return root;
  }

  private static class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }
  };
}
