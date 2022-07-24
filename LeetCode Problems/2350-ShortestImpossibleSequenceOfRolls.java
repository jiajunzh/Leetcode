package problem;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 1. Problem 
 * You are given an integer array rolls of length n and an integer k. You roll a k sided dice numbered from 1 to k,
 * n times, where the result of the ith roll is rolls[i].
 *
 * Return the length of the shortest sequence of rolls that cannot be taken from rolls.
 *
 * A sequence of rolls of length len is the result of rolling a k sided dice len times.
 *
 * Note that the sequence taken does not have to be consecutive as long as it is in order.
 *
 * 2. Examples 
 * Example 1
 * Input: rolls = [4,2,1,2,3,3,2,4,1], k = 4
 * Output: 3
 * Explanation: Every sequence of rolls of length 1, [1], [2], [3], [4], can be taken from rolls.
 * Every sequence of rolls of length 2, [1, 1], [1, 2], ..., [4, 4], can be taken from rolls.
 * The sequence [1, 4, 2] cannot be taken from rolls, so we return 3.
 * Note that there are other sequences that cannot be taken from rolls.
 * 
 * Example 2
 * Input: rolls = [1,1,2,2], k = 2
 * Output: 2
 * Explanation: Every sequence of rolls of length 1, [1], [2], can be taken from rolls.
 * The sequence [2, 1] cannot be taken from rolls, so we return 2.
 * Note that there are other sequences that cannot be taken from rolls but [2, 1] is the shortest.
 * 
 * Example 3
 * Input: rolls = [1,1,3,2,2,2,3,3], k = 4
 * Output: 1
 * Explanation: The sequence [4] cannot be taken from rolls, so we return 1.
 * Note that there are other sequences that cannot be taken from rolls but [4] is the shortest.
 *
 * 3. Constraints
 * n == rolls.length
 * 1 <= n <= 105
 * 1 <= rolls[i] <= k <= 105
 */
public class ShortestImpossibleSequenceOfRolls {

  /**
   * 1. Approach
   * PriorityQueue + Greedy.
   * 
   * An initial naive thinking is that we need to greedily check length from 1 to expected answer whether the length 
   * could give you all sequences of length. So each time we could think,
   *
   * Sequence of length L will give you all possible sequences if sequence of length (L - 1) could do the same as well
   * as there are number provided after the maximum index needed to compose the sequence of length (L - 1).
   * Take rolls = [4,2,1,2,3,3,2,4,1] and k = 4 as an example, we will have a map from number to indexes set as below
   * 1 => [2,8]
   * 2 => [1,3,6] 
   * 3 => [4,5]
   * 4 => [0,7]
   * If we want sequence of length 1, then array from 0 to 4 (maximum index value among the first index of all possible
   * numbers) will suffice. 
   * Then we think about sequence of length 2. Note that we need at least [0:4] to get all sequences of length 1, so we
   * could only start looking at numbers at index greater than 4 to place them as second number in the sequence. 
   * Fortunately, [5:8] is enough.
   * Then we think about sequence of length 3, but unfortunately, we don't have any other usable element, so the shortest
   * impossible sequence length is 3.
   * 
   * 2. Complexity 
   * - Time O(K + NlogN + N*logN)
   * - Space O(N)
   * 
   * @param rolls
   * @param k
   * @return
   */
  public int shortestSequence1(int[] rolls, int k) {
    final int n = rolls.length;
    final PriorityQueue<Integer>[] pq = new PriorityQueue[k + 1];
    for (int i = 1; i <= k; i++) {
      pq[i] = new PriorityQueue<Integer>();
    }
    for (int i = 0; i < n; i++) {
      pq[rolls[i]].offer(i);
    }

    int maxIndex = -1;
    int shortestSequence = 1;
    while (true) {
      int currMax = 0;
      for (int i = 1; i <= k; i++) {
        while (!pq[i].isEmpty() && pq[i].peek() <= maxIndex) {
          pq[i].poll();
        }
        if (pq[i].isEmpty()) {
          return shortestSequence;
        }
        int curr = pq[i].poll();
        currMax = Math.max(currMax, curr);
      }
      maxIndex = currMax;
      shortestSequence++;
    }
  }

  /**
   * 1. Approach 
   * HashSet. A further look at the initial approach 1 will reveal that using priority queue is an overkill as pq is
   * used to preserve the order of indexes which are already there in original array. So we could just use a hashset
   * to see if number 1 to k has been used at all. If so, we could check length + 1.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(K)
   * 
   * @param rolls
   * @param k
   * @return
   */
  public int shortestSequence2(int[] rolls, int k) {
    Set<Integer> usedNumbers = new HashSet<>();
    int shortestSequence = 1;
    for (int roll : rolls) {
      usedNumbers.add(roll);
      if (usedNumbers.size() == k) {
        usedNumbers = new HashSet<>();
        shortestSequence++;
      }
    }
    return shortestSequence;
  }
}
