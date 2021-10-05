package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
  
  public static List<Integer> inorderTraversalDfs(TreeNode root) {
    final List<Integer> result = new ArrayList<>();

    /**
     * PreOrder Traversal: root left right 
     * InOrder Traversal: left root right 
     * PostOrder Traversal: left right root
     */
    inorderTraversalHelper(root, result);

    return result;
  }

  private static void inorderTraversalHelper(final TreeNode root, final List<Integer> result) {
    if (root == null) {
      return;
    }

    inorderTraversalHelper(root.left, result);
    visit(root, result);
    inorderTraversalHelper(root.right, result);
  }

  public static List<Integer> inorderTraversalIterative(TreeNode root) {
    final List<Integer> result = new ArrayList<>();

    if (root == null) {
      return result;
    }

    final Stack<TreeNode> stack = new Stack<>();

    stack.push(root);
    TreeNode currentNode = root.left;

    while (true) {
      if (currentNode != null) {
        stack.push(currentNode);
        currentNode = currentNode.left;
      } else {
        if (stack.isEmpty()) {
          break;
        }
        final TreeNode currentRoot = stack.pop();
        visit(currentRoot, result);
        currentNode = currentRoot.right;
      }
    }

    return result;
  }

  private static void visit(final TreeNode root, final List<Integer> result) {
    result.add(root.val);
  }
  
  public static void main(String[] args) {
    final TreeNode root = constructTree();
    
    System.out.println("In-Order Traversal using DFS.");
    System.out.println(inorderTraversalDfs(root));
    System.out.print("\n");

    System.out.println("In-Order Traversal using iterative approach.");
    System.out.println(inorderTraversalIterative(root));
    System.out.print("\n");
  }

  /**
   * Construct a tree as below.
   *
   *
   *          10  
   *     15       30
   *   3    6        2
   * 5             9   8
   *
   * @return
   */
  private static TreeNode constructTree() {
    return new TreeNode(10,
      new TreeNode(15,
        new TreeNode(3, new TreeNode(5), null),
        new TreeNode(6, null, null)
      ),
      new TreeNode(30,
        null,
        new TreeNode(2,
          new TreeNode(9),
          new TreeNode(8)
        )
      )
    );
  }

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
