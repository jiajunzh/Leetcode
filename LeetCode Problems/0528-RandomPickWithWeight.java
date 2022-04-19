package problem;

/**
 * 1. Problem 
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 *
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] 
 * (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 *
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the 
 * probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 *
 * 2. Examples
 * Example 1
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 *
 * Explanation
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0. The only option is to return 0 since there is only one element in w.
 * 
 * Example 2
 * Input
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output
 * [null,1,1,1,1,0]
 *
 * Explanation
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.
 *
 * Since this is a randomization problem, multiple answers are allowed.
 * All of the following outputs can be considered correct:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * and so on.
 *
 * 3. Constraints
 * 1 <= w.length <= 104
 * 1 <= w[i] <= 105
 * pickIndex will be called at most 104 times.
 */
public class RandomPickWithWeight {

  private final int[] prefixSum;

  /**
   * 1. Approach 
   * Prefix Sum + Binary Search. The problem wants to pick the index randomly with its weight, which could be separated 
   * into below items:
   * 
   * - Weighting: each unit should have the equal possibility of being picked up, so each number should be able to be 
   *   split into multiple units based on its weight. Intuitively, sum could used to weight up each index.
   *   For example, we have [1, 2, 3, 4], then we could have a possibility distribution
   *   -|--|---|----
   *   1 2   3   4
   *   It could be simulated by throwing a ball to the line and the possibility of the ball falling on each position. 
   *   In other words, it is asking the possibility that a random number generated will fall into each range.
   *   
   * - Randomization: there are a few utility we could use to generate a random number: (1) Math.random() gets you a 
   *   double number between [0, 1] (2) new Random().nextInt(max) gets you a random integer between [0, max]
   *   
   * - Find the range that the random number is in => Binary Search
   * 
   * 2. Complexity
   * - Time O(N) for constructor and O(logN) for pickIndex
   * - Space O(N) for constructor and O(1) for pickIndex
   * 
   * @param w
   */
  public RandomPickWithWeight(int[] w) {
    prefixSum = new int[w.length];
    prefixSum[0] = w[0];

    for (int i = 1; i < w.length; i++) {
      prefixSum[i] = w[i] + prefixSum[i - 1];
    }
  }

  public int pickIndex() {
    double target = Math.random() * prefixSum[prefixSum.length - 1];
    int low = 0, high = prefixSum.length - 1;

    // find minimum k such that k >= target
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (prefixSum[mid] >= target) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }

    return low;
  }
}
