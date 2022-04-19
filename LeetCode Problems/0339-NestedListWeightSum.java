package problem;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1. Problem
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may 
 * also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] 
 * has each integer's value set to its depth.
 *
 * Return the sum of each integer in nestedList multiplied by its depth.
 *
 * 2. Examples
 * Example 1
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
 * 
 * Example 2:
 * Input: nestedList = [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
 * 
 * Example 3:
 * Input: nestedList = [0]
 * Output: 0
 *
 * 3. Constraints:
 * 1 <= nestedList.length <= 50
 * The values of the integers in the nested list is in the range [-100, 100].
 * The maximum depth of any integer is less than or equal to 50.
 */
public class NestedListWeightSum {

  /**
   * 1. Approach
   * BFS
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N) in worst case e.g. [1, 2, 3, 4]
   * 
   * 3. Improvement
   * - An alternative is to have Queue<NestedInteger> directly while use .addAll() to add all element into queue for 
   *   once.
   *   
   * @param nestedList
   * @return
   */
  public int depthSumBfs(List<NestedInteger> nestedList) {
    int depthSum = 0, depth = 1;
    Queue<List<NestedInteger>> queue = new LinkedList<>();
    queue.offer(nestedList);

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        final List<NestedInteger> currList = queue.poll();

        for (final NestedInteger nestedInteger : currList) {
          if (nestedInteger.isInteger()) {
            depthSum += depth * nestedInteger.getInteger();
          } else {
            queue.offer(nestedInteger.getList());
          }
        }
      }
      depth++;
    }

    return depthSum;
  }

  /**
   * 1. Approach
   * DFS
   2. Complexity 
   * - Time O(N)
   * - Space O(N) in worst case e.g. [[[[[[]]]]]]
   * 
   * @param nestedList
   * @return
   */
  public int depthSumDfs(List<NestedInteger> nestedList) {
    return depthSum(nestedList, 1);
  }

  private int depthSum(List<NestedInteger> nestedList, int depth) {
    int depthSum = 0;

    for (final NestedInteger element : nestedList) {
      if (element.isInteger()) {
        depthSum += element.getInteger() * depth;
      } else {
        depthSum += depthSum(element.getList(), depth + 1);
      }
    }

    return depthSum;
  }

  // This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
  public interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);
    
    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
  }
}
