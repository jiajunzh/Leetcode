package problem;

/**
 * 1. Problem
 * You are given a 0-indexed integer array candies. Each element in the array denotes a pile of candies of size 
 * candies[i]. You can divide each pile into any number of sub piles, but you cannot merge two piles together.
 *
 * You are also given an integer k. You should allocate piles of candies to k children such that each child gets the 
 * same number of candies. Each child can take at most one pile of candies and some piles of candies may go unused.
 *
 * Return the maximum number of candies each child can get.
 *
 * 2. Example 
 * Example 1:
 * Input: candies = [5,8,6], k = 3
 * Output: 5
 * Explanation: We can divide candies[1] into 2 piles of size 5 and 3, and candies[2] into 2 piles of size 5 and 1. 
 * We now have five piles of candies of sizes 5, 5, 3, 5, and 1. We can allocate the 3 piles of size 5 to 3 children. 
 * It can be proven that each child cannot receive more than 5 candies.
 * 
 * Example 2:
 * Input: candies = [2,5], k = 11
 * Output: 0
 * Explanation: There are 11 children but only 7 candies in total, so it is impossible to ensure each child receives at least one candy. Thus, each child gets no candy and the answer is 0.
 *
 * 3. Constraints:
 * 1 <= candies.length <= 105
 * 1 <= candies[i] <= 107
 * 1 <= k <= 1012
 */
public class MaximumCandiesAllocatedToKChildren {

  /**
   * 1. Approach
   * Binary Search. This problem asks for the maximum number of candies each child could get (meaning candy piles 
   * assignable to k children). It could be translated into finding the minimum number K of candies that candy piles 
   * that we have could not assign to k children. Remember the answer is K - 1 instead of K itself.
   * 
   * 2. Complexity
   * Time O(NlogM) M is the search 
   * Space O(1)
   * 
   * 3. Improvement
   * 1) Upper Boundary. As the binary search we are using is to find the minimum K that is not assignable, so it should
   * be the maximum number of candies assignable plus one. The search space should plus one as well.
   * 2) Upper Boundary Average. Initially, maximum of element in this array was used but actually anything between 
   * [Sum of element/k (Avg), maximum of element] does not need to be considered. It improves the time from 61 ms to 
   * 23 ms.
   * 3) Condition Check. Do not minus k one by one it will exceed time limit if the k is large. Instead use 
   * k -= tmp / numOfCandies directly. (Remember to exclude numOfCandies = 0 case though)
   *
   * @param candies
   * @param k
   * @return
   */
  public int maximumCandies(int[] candies, long k) {
    long sum = 0;
    
    for (int candy : candies) {
      sum += candy;
    }
    
    int low = 0, high = (int)(sum / k) + 1;

    while (low < high) {
      int mid = low + (high - low) / 2;

      if (!isAllocatable(candies, k, mid)) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }

    return low - 1;
  }

  private boolean isAllocatable(int[] candies, long k, int numOfCandies) {
    if (numOfCandies == 0) {
      return true;
    }

    for (final int tmp : candies) {
      k -= tmp / numOfCandies;
      if (k <= 0) return true;
    }

    return false;
  }
}
