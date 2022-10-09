package problem;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. Problem 
 * Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the 
 * functions of a normal stack (push, top, pop, and empty).
 *
 * Implement the MyStack class:
 *
 * void push(int x) Pushes element x to the top of the stack.
 * int pop() Removes the element on the top of the stack and returns it.
 * int top() Returns the element on the top of the stack.
 * boolean empty() Returns true if the stack is empty, false otherwise.
 * Notes:
 *
 * You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and 
 * is empty operations are valid.
 * Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque 
 * (double-ended queue) as long as you use only a queue's standard operations.
 *
 * 2. Examples
 * Example 1
 * Input
 * ["MyStack", "push", "push", "top", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 2, 2, false]
 *
 * Explanation
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // return 2
 * myStack.pop(); // return 2
 * myStack.empty(); // return False
 *
 * 3. Constraints
 * 1 <= x <= 9
 * At most 100 calls will be made to push, pop, top, and empty.
 * All the calls to pop and top are valid.
 *
 * 4. Follow-up
 * Can you implement the stack using only one queue?
 */
public class ImplementStackUsingQueues {
  
  private final Queue<Integer> queue;

  /**
   * 1. Approach
   * One Queue 
   * 
   * 2. Complexity
   * - Time O(N) for push and O(1) for others
   * - Space O(N)
   */
  public ImplementStackUsingQueues() {
    this.queue = new LinkedList<>();
  }

  public void push(int x) {
    int size = this.queue.size();
    this.queue.offer(x);
    while (size > 0) {
      this.queue.offer(this.queue.poll());
      size--;
    }
  }

  public int pop() {
    return this.queue.poll();
  }

  public int top() {
    return this.queue.peek();
  }

  public boolean empty() {
    return this.queue.isEmpty();
  }
}
