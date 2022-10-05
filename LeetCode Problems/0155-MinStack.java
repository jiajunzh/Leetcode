package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 *
 * 2. Examples
 * Example 1
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 *
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 *
 * 3. Constraints
 * -231 <= val <= 231 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */
public class MinStack {
  
  private final List<Node> stack;

  /**
   * 1. Problem
   * [Val, CurrentMin] Pair
   * 
   * 2. Complexity 
   * - Time O(1) for all methods
   * - Space O(N)
   * 
   * 3. Alternatives
   * - One downside of this approach is we don't actually needs to keep the current minimum value for all pairs as they
   *   might be repetitive. For example, consider sequence = [7,5,8,9,10,11,12]. Starting from number 5, we know that 
   *   the minimum of all numbers after 5 will be 5 as later numbers are greater than 5. So another approach is to 
   *   keep two stacks, one with main values and the other with minimum value (Only if when the current value is smaller 
   *   than or EQUAL TO the current minimum value).
   */
  public MinStack() {
    this.stack = new ArrayList<>();
  }

  public void push(int val) {
    int lastMin = this.stack.isEmpty() ? Integer.MAX_VALUE : getLast().min;
    this.stack.add(new Node(val, Math.min(lastMin, val)));
  }

  public void pop() {
    this.stack.remove(this.stack.size() - 1);
  }

  public int top() {
    return getLast().val;
  }

  public int getMin() {
    return getLast().min;
  }

  private Node getLast() {
    return this.stack.get(this.stack.size() - 1);
  }

  private static class Node {

    private final int val;
    private final int min;

    private Node(final int val, final int min) {
      this.val = val;
      this.min = min;
    }
  }
}
