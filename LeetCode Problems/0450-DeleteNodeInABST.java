package problem;

/**
 * 1. Problem 
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node 
 * reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove. If the node is found, delete the node.
 *
 * 2. Examples 
 * Example 1
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 *
 * Example 2
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 * 
 * Example 3
 * Input: root = [], key = 0
 * Output: []
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [0, 104].
 * -105 <= Node.val <= 105
 * Each node has a unique value.
 * root is a valid binary search tree.
 * -105 <= key <= 105
 *
 * 4. Follow up
 * Could you solve it with time complexity O(height of tree)?
 */
public class DeleteNodeInABST {

  /**
   * 1. Approach 
   * Deletion Operation + BST.
   * 
   * Three use cases:
   * 1) Node with no child
   * 2) Node with one child
   * 3) Node with two children
   * 
   * 2. Complexity 
   * - Time O(H)
   * - Space O(H)
   * 
   * @param root
   * @param key
   * @return
   */
  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
      return null;
    }
    if (root.val < key) {
      root.right = deleteNode(root.right, key);
    } else if (root.val > key) {
      root.left = deleteNode(root.left, key);
    } else {
      if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      } else {
        TreeNode curr = root.right;
        while (curr.left != null) {
          curr = curr.left;
        }
        root.val = curr.val;
        root.right = deleteNode(root.right, root.val);
      }
    }
    return root;
  }

  /**
   * 1. Approach 
   * Iterative Deletion 
   * 
   * 2. Complexity 
   * - Time O(H)
   * - Space (H)
   * 
   * @param root
   * @param key
   * @return
   */
  public TreeNode deleteNode2(TreeNode root, int key) {
    TreeNode curr = root;
    while (curr != null) {
      if (curr.val < key) {
        if (curr.right != null && curr.right.val == key) {
          curr.right = deleteNodeHelper(curr.right, key);
          break;
        } else {
          curr = curr.right;
        }
      } else if (curr.val > key) {
        if (curr.left != null && curr.left.val == key) {
          curr.left = deleteNodeHelper(curr.left, key);
          break;
        } else {
          curr = curr.left;
        }
      } else {
        return deleteNodeHelper(curr, key);
      }
    }

    return root;
  }

  private TreeNode deleteNodeHelper(TreeNode root, int key) {
    if (root.left == null) {
      return root.right;
    } else if (root.right == null) {
      return root.left;
    } else {
      TreeNode curr = root.right;
      while (curr.left != null) {
        curr = curr.left;
      }
      root.val = curr.val;
      root.right = deleteNode2(root.right, root.val);
      return root;
    }
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }
}
