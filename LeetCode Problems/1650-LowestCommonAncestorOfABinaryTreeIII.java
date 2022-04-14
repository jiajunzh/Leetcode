package problem;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. Problem
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the
 * lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
 *
 * 2. Examples
 * Example 1
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * 
 * Example 2
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 * 
 * Example 3
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * 
 * 3. Constraints
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q exist in the tree.
 */
public class LowestCommonAncestorOfABinaryTreeIII {

  /**
   * 1. Approach 
   * HashSet + Traversal to root
   * 
   * 2. Complexity
   * - Time O(DepthP + DepthQ)
   * - Space O(DepthP)
   *
   * @param p
   * @param q
   * @return
   */
  public Node lowestCommonAncestor(Node p, Node q) {
    final Set<Node> parentSet = new HashSet<>();

    while (p != null) {
      parentSet.add(p);
      p = p.parent;
    }

    while (q != null) {
      if (parentSet.contains(q)) {
        return q;
      }

      q = q.parent;
    }

    return null;
  }

  /**
   * 1. Approach
   * This approach iterate through both q and p. For example,
   * 
   * p 4 -> 5 -> 3 -> 6   Unique Path p + Common Path p 
   * q 1 -> 2 -> 10 -> 9 -> 3 -> 6   Unique Path q + Common Path q
   * 
   * If we iterate both of them either starting from p or q, then there will be two traces below
   * 
   * 4 -> 5 -> 3 -> 6 -> 1 -> 2 -> 10 -> 9 -> 3 -> 6
   * 1 -> 2 -> 10 -> 9 -> 3 -> 6 -> 4 -> 5 -> 3 -> 6
   * 
   * (Unique Path q + Common Path q + Unique Path p) + Common Path p 
   * 
   * It is not hard to notice that the two paths will overlap for sure in the end, the earliest overlapping node is
   * the LCA.
   * 
   * 2. Complexity
   * - Time O(DepthP + DepthQ)
   * - Space O(1)
   * 
   * @param p
   * @param q
   * @return
   */
  public Node lowestCommonAncestor2(Node p, Node q) {
    Node pointerP = p;
    Node pointerQ = q;

    while (pointerP != pointerQ) {
      pointerP = pointerP.parent == null ? q : pointerP.parent;
      pointerQ = pointerQ.parent == null ? p : pointerQ.parent;
    }

    return pointerP;
  }

  private static class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
  };
}
