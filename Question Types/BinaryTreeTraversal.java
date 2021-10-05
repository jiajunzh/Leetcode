package algorithm;

import java.util.Stack;

/**
 * This class will cover the three binary trees traversal patterns 
 * using both DFS and BFS approaches.
 * 
 * 
 * Pre-Order: Visited Node - Left Tree - Right Tree
 * In-Order: L - V - R
 * Post-Order: L - R - V 
 */
public class BinaryTreeTraversal {

  /**
   * Depth first binary tree pre-order traversal.
   * 
   * @param node
   */
  public static void preorderDfs(final TreeNode node) {
    if (node != null) {
      visit(node);
      preorderDfs(node.left);
      preorderDfs(node.right);
    }
  }

  /**
   * Depth first binary tree in-order traversal.
   *
   * @param node
   */
  public static void inorderDfs(final TreeNode node) {
    if (node != null) {
      inorderDfs(node.left);
      visit(node);
      inorderDfs(node.right);
    }
  }

  /**
   * Depth first binary tree post-order traversal.
   *
   * @param node
   */
  public static void postorderDfs(final TreeNode node) {
    if (node != null) {
      postorderDfs(node.left);
      postorderDfs(node.right);
      visit(node);
    }
  }

  /**
   * Iterative in-order traversal.
   * Time - O(N)
   * Space - O(H)
   *
   * @param node
   */
  public static void inorderIterative(final TreeNode node) {
    if (node == null) {
      return;
    }

    final Stack<TreeNode> stack = new Stack<>();

    stack.push(node);
    TreeNode currentNode = node.left;
       
    while (true) {
      if (currentNode != null) {
        stack.push(currentNode);
        currentNode = currentNode.left;
      } else {
        if (stack.isEmpty()) {
          break;
        }
        final TreeNode currentRoot = stack.pop();
        visit(currentRoot);
        currentNode = currentRoot.right;
      }
    }
  }
  
  /**
   * Iterative post-order traversal using two stack.
   * Time - O(N)
   * Space - O(N)
   * 
   * @param node
   */
  public static void postorderIterativeUsingTwoStacks(final TreeNode node) {
    if (node == null) {
      return;
    }
    
    final Stack<TreeNode> stack1 = new Stack<>();
    final Stack<TreeNode> stack2 = new Stack<>();
    
    stack1.push(node);
    
    while (!stack1.isEmpty()) {
      final TreeNode currentNode = stack1.pop();
      
      stack2.push(currentNode);
      
      if (currentNode.left != null) {
        stack1.push(currentNode.left);
      }
      if (currentNode.right != null) {
        stack1.push(currentNode.right);
      }
    }
    
    while (!stack2.isEmpty()) {
      visit(stack2.pop());
    }
  }

  /**
   * Dummy visit method that simply prints out the tree node value. 
   */
  public static void visit(final TreeNode node) {
    System.out.print(node.val);
    System.out.print(" ");
  }
  
  public static void main(String[] args) {
    final TreeNode root = constructTree();
    
    System.out.println("Pre-Order Traversal using DFS.");
    preorderDfs(root);
    System.out.print("\n");
    System.out.println("In-Order Traversal using DFS.");
    inorderDfs(root);
    System.out.print("\n");
    System.out.println("In-Order Traversal using iterative approach.");
    inorderDfs(root);
    System.out.print("\n");
    System.out.println("Post-Order Traversal using DFS.");
    postorderDfs(root);
    System.out.print("\n");
    System.out.println("Post-Order Traversal using iterative two stacks.");
    postorderIterativeUsingTwoStacks(root);
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
