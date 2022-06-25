package problem;

import java.util.PriorityQueue;

/**
 * 1. Problem 
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted
 * order, not the kth distinct element.
 *
 * Implement KthLargest class:
 *
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element 
 * in the stream.
 *
 * 2. Examples 
 * Example 1
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 *
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *
 * 3. Constraints
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * At most 104 calls will be made to add.
 * It is guaranteed that there will be at least k elements in the array when you search for the kth element.
 */
public class KthLargestElementInAStream {
  
  private final PriorityQueue<Integer> pq;
  private final int k;

  /**
   * 1. Approach 
   * PriorityQueue / Heap 
   * 
   * 2. Complexity
   * - Time O(Nlogk) for constructor and O(logk) for add
   * - Space O(K)
   * 
   * 3. Mistakes
   * - One trick constraint in this problem is "It is guaranteed that there will be at least k elements in the array 
   *   when you search for the kth element." The keyword here is "when you search", which means it is not guaranteed
   *   when you are in constructor. Edge case, [1, []] add(1)
   * 
   * @param k
   * @param nums
   */
  public KthLargestElementInAStream(int k, int[] nums) {
    this.pq = new PriorityQueue<>();
    this.k = k;
    for (int num : nums) {
      add(num);
    }
  }

  public int add(int val) {
    pq.offer(val);
    if (pq.size() > k) pq.poll();
    return pq.peek();
  }
}
