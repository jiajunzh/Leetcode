import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
  
  private static final String PATH_SYMBOL = "->";

  /**
   * Return all root-to-leaf paths given a binary tree.
   * Leaf is a node without children.
   *
   * Time O(N) all nodes will be visited once
   * Space O(H) H is the maximum height of the given tree
   */
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();

    binaryTreePaths(root, new ArrayList<Integer>(), result);

    return result;
  }

  private void binaryTreePaths(TreeNode root, List<Integer> path, List<String> result) {
    if (root == null) return;
    path.add(root.val);

    if (root.left == null && root.right == null) {
      result.add(buildPathString(path));
    }

    binaryTreePaths(root.left, path, result);
    binaryTreePaths(root.right, path, result);
    path.remove(path.size() - 1);
  }

  private String buildPathString(List<Integer> path) {
    StringBuilder sb = new StringBuilder();
    sb.append(path.get(0)); // path list will have at least one element

    for (int i = 1; i < path.size(); i++) {
      sb.append(PATH_SYMBOL);
      sb.append(path.get(i));
    }

    return sb.toString();
  }
  
  public class TreeNode {
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
