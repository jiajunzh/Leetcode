package problem;

/**
 * 1. Problem 
 * You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given by the array 
 * sweetness.
 *
 * You want to share the chocolate with your k friends so you start cutting the chocolate bar into k + 1 pieces using
 * k cuts, each piece consists of some consecutive chunks.
 *
 * Being generous, you will eat the piece with the minimum total sweetness and give the other pieces to your friends.
 *
 * Find the maximum total sweetness of the piece you can get by cutting the chocolate bar optimally.
 *
 * 2. Example
 * Example 1
 * Input: sweetness = [1,2,3,4,5,6,7,8,9], k = 5
 * Output: 6
 * Explanation: You can divide the chocolate to [1,2,3], [4,5], [6], [7], [8], [9]
 * 
 * Example 2
 * Input: sweetness = [5,6,7,8,9,1,2,3,4], k = 8
 * Output: 1
 * Explanation: There is only one way to cut the bar into 9 pieces.
 * 
 * Example 3
 * Input: sweetness = [1,2,2,1,2,2,1,2,2], k = 2
 * Output: 5
 * Explanation: You can divide the chocolate to [1,2,2], [1,2,2], [1,2,2]
 *
 * 3. Constraints
 * 0 <= k < sweetness.length <= 104
 * 1 <= sweetness[i] <= 105
 */
public class DivideChocolate {

  /**
   * 1. Approach 
   * Binary Search. The approach asks the maximum sweetness S such that the chocolate could be cut into (k+1) pieces 
   * with each greater than or equal to S. It could be translated into finding the minimum S such that the chocolate
   * is not cutable.
   * 
   * 2. Complexity 
   * - Time O(Nlog(Sum(N)/(k+1)))
   * - Space O(1)
   * 
   * 3. Improvement
   * - Search Space Upper Boundary. A very naive upper bound to come up with easily is sum(sweetness). However, if you
   *   think deeper, the upper bound will not be greater than sum/(k + 1). Also another thing to notice here is we are
   *   finding the minimum sweetness S such that chocolate is NOT cutable which will be answer + 1. Thus, remember to 
   *   add one to the upper boundary to include this edge case.
   *   
   * @param sweetness
   * @param k
   * @return
   */
  public int maximizeSweetness(int[] sweetness, int k) {
    int low = 1, high = 0;
    for (int i : sweetness) {
      high += i;
    }

    high = high / (k + 1) + 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (cutable(sweetness, k + 1, mid)) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }

    return low - 1;
  }

  private boolean cutable(int[] sweetness, int k, int target) {
    int sum = 0;
    for (int i : sweetness) {
      sum += i;

      if (sum >= target) {
        k--;
        sum = 0;
      }
    }

    return k <= 0;
  }
}
