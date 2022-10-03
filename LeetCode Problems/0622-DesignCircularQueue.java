package problem;

/**
 * 1. Problem 
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the 
 * operations are performed based on FIFO (First In First Out) principle, and the last position is connected back to 
 * the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal 
 * queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. 
 * But using the circular queue, we can use the space to store new values.
 *
 * Implement the MyCircularQueue class:
 *
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 * You must solve the problem without using the built-in queue data structure in your programming language. 
 *
 * 2. Examples
 * Example 1
 * Input
 * ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 3, true, true, true, 4]
 *
 * Explanation
 * MyCircularQueue myCircularQueue = new MyCircularQueue(3);
 * myCircularQueue.enQueue(1); // return True
 * myCircularQueue.enQueue(2); // return True
 * myCircularQueue.enQueue(3); // return True
 * myCircularQueue.enQueue(4); // return False
 * myCircularQueue.Rear();     // return 3
 * myCircularQueue.isFull();   // return True
 * myCircularQueue.deQueue();  // return True
 * myCircularQueue.enQueue(4); // return True
 * myCircularQueue.Rear();     // return 4
 *
 * 3. Constraints
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.
 */
public class DesignCircularQueue {
  
  private final int[] queue;
  private final int size;
  private int frontIndex;
  private int count;

  /**
   * 1. Approach 
   * Circular Queue + Array
   * 
   * 2. Complexity 
   * - Time O(1) for all methods
   * - Space O(K)
   *
   * 3. Improvement
   * - ThreadSafe
   * - LinkedList Based but missing the point of having circular array
   * 
   * @param k
   */
  public DesignCircularQueue(int k) {
    this.queue = new int[k];
    this.frontIndex = 0;
    this.size = k;
    this.count = 0;
  }

  public boolean enQueue(int value) {
    if (isFull()) return false;
    this.queue[(this.frontIndex + this.count) % this.size] = value;
    this.count++;
    return true;
  }

  public boolean deQueue() {
    if (isEmpty()) return false;
    this.frontIndex = (this.frontIndex + 1) % this.size;
    this.count--;
    return true;
  }

  public int Front() {
    if (isEmpty()) return -1;
    return this.queue[this.frontIndex];
  }

  public int Rear() {
    if (isEmpty()) return -1;
    return this.queue[(this.frontIndex + this.count - 1) % this.size];
  }

  public boolean isEmpty() {
    return this.count == 0;
  }

  public boolean isFull() {
    return this.count == this.size;
  }
}
