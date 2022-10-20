package problem;

import java.util.PriorityQueue;

/**
 * 1. Problem 
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value 
 * and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *
 * 2. Examples
 * Example 1
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 * 3. Constraints
 * -105 <= num <= 105
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5 * 104 calls will be made to addNum and findMedian.
 *
 * 4. Follow up
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */
public class FindMedianFromDataStream {
  
  private final PriorityQueue<Integer> smallerNumbers;
  private final PriorityQueue<Integer> largerNumbers;

  /**
   * 1. Approach
   * Two Heaps
   * 
   * 2. Complexity
   * - Time O(logN) for addNum and O(1) for findMedian
   * - Space O(N)
   * 
   * 3. Follow up
   * 1) If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
   * Counting sort as find median will be O(100) ~ O(1)
   * 2) If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
   * If it guaranteed that numbers in [0,100] will come first then we are sure median will be any number in [0, 100] 
   * because there is only 1% outliers. With it, we could just keep track of the count of outliers so far and then still
   * use counting sort as the first follow-ups
   * 
   * If it is not guaranteed, then we need to keep a list of outliers and keep it sorted
   */
  public FindMedianFromDataStream() {
    this.smallerNumbers = new PriorityQueue<>((a, b) -> (b - a));
    this.largerNumbers = new PriorityQueue<>();
  }

  public void addNum(int num) {
    this.smallerNumbers.offer(num);
    this.largerNumbers.offer(this.smallerNumbers.poll());
    if (this.largerNumbers.size() > this.smallerNumbers.size()) {
      this.smallerNumbers.offer(this.largerNumbers.poll());
    }
  }

  public double findMedian() {
    if (this.largerNumbers.size() == this.smallerNumbers.size()) {
      return (this.largerNumbers.peek() + this.smallerNumbers.peek()) / 2.0;
    }
    return this.smallerNumbers.peek();
  }
}
