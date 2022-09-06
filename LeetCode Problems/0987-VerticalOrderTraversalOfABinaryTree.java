package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 1. Problem 
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and 
 * (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting 
 * from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same 
 * column. In such a case, sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 *
 * 2. Examples
 * Example 1
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 * Column 2: Only node 7 is in this column.
 * 
 * Example 2
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * Column -2: Only node 4 is in this column.
 * Column -1: Only node 2 is in this column.
 * Column 0: Nodes 1, 5, and 6 are in this column.
 *           1 is at the top, so it comes first.
 *           5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
 * Column 1: Only node 3 is in this column.
 * Column 2: Only node 7 is in this column.
 * 
 * Example 3
 * Input: root = [1,2,3,4,6,5,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * This case is the exact same as example 2, but with nodes 5 and 6 swapped.
 * Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
 *
 * 3. Constraints
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 1000
 */
public class VerticalOrderTraversalOfABinaryTree {

  /**
   * 1. Approach 
   * BFS/DFS + Global Ordering.
   * 
   * The order of elements in result needs to satisfy three orders:
   * (1) Smaller column number comes first
   * (2) If column number meets a tie, smaller row number comes first 
   * (3) If row number meets a tie, smaller value comes first
   * 
   * 2. Complexity 
   * - Time O(N + NlogN)
   * - Space O(N)
   * 
   * @param root
   * @return
   */
  public List<List<Integer>> verticalTraversal(TreeNode root) {
    final List<List<Integer>> result = new ArrayList<>();
    final List<Node> nodes = new ArrayList<>();
    verticalTraversal(nodes, root, 0, 0);
    nodes.sort((a, b) -> {
      if (a.col == b.col) {
        if (a.row == b.row) {
          return a.val - b.val;
        } else {
          return a.row - b.row;
        }
      } else {
        return a.col - b.col;
      }
    });
    int prev = Integer.MIN_VALUE;
    for (final Node node: nodes) {
      if (node.col > prev) {
        prev = node.col;
        result.add(new ArrayList<>());
      }
      result.get(result.size() - 1).add(node.val);
    }
    return result;
  }

  public void verticalTraversal(List<Node> nodes, TreeNode root, int row, int col) {
    if (root == null) return;
    nodes.add(new Node(row, col, root.val));
    verticalTraversal(nodes, root.left, row + 1, col - 1);
    verticalTraversal(nodes, root.right, row + 1, col + 1);
  }

  private static class Node {
    private final int row;
    private final int col;
    private final int val;

    private Node(int row, int col, int val) {
      this.row = row;
      this.col = col;
      this.val = val;
    }
  }

  private static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

  /**
   * 1. Problem 
   * Sorting with Partitioning. On top of approach 1, we could further split all elements into groups by column number 
   * and then sort each group. This generates better runtime in theory of O(k * N/k * log N/k) = O(N log N/k)
   * 
   * 2. Complexity 
   * - Time O(N logN/k)
   * - Space O(N)
   * 
   * @param root
   * @return
   */
  public List<List<Integer>> verticalTraversal2(TreeNode root) {
    final Map<Integer, List<NodeInfo>> map = new HashMap<>();
    int minCol = 0, maxCol = 0;
    final Queue<NodeInfo> queue = new LinkedList<>();
    queue.offer(new NodeInfo(0, 0, root));
    while (!queue.isEmpty()) {
      final NodeInfo curr = queue.poll();
      final List<NodeInfo> columnList = map.getOrDefault(curr.col, new ArrayList<>());
      columnList.add(curr);
      map.put(curr.col, columnList);
      minCol = Math.min(minCol, curr.col);
      maxCol = Math.max(maxCol, curr.col);
      if (curr.node.left != null) queue.offer(new NodeInfo(curr.row + 1, curr.col - 1, curr.node.left));
      if (curr.node.right != null) queue.offer(new NodeInfo(curr.row + 1, curr.col + 1, curr.node.right));
    }
    final List<List<Integer>> result = new ArrayList<>();
    for (int i = minCol; i <= maxCol; i++) {
      final List<NodeInfo> columnList = map.get(i);
      columnList.sort((a, b) -> (a.row == b.row ? a.node.val - b.node.val : a.row - b.row));
      final List<Integer> values = columnList.stream().map(a -> a.node.val).collect(Collectors.toList());
      result.add(values);
    }
    return result;
  }

  private static class NodeInfo {
    private final int row;
    private final int col;
    private final TreeNode node;

    private NodeInfo(int row, int col, TreeNode node) {
      this.row = row;
      this.col = col;
      this.node = node;
    }
  }
}
