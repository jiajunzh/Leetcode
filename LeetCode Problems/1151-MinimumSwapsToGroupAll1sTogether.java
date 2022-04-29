package problem;

/**
 * 1. Problem 
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together
 * in any place in the array.
 *
 * 2. Examples
 * Example 1
 * Input: data = [1,0,1,0,1]
 * Output: 1
 * Explanation: There are 3 ways to group all 1's together:
 * [1,1,1,0,0] using 1 swap.
 * [0,1,1,1,0] using 2 swaps.
 * [0,0,1,1,1] using 1 swap.
 * The minimum is 1.
 * 
 * Example 2
 * Input: data = [0,0,0,1,0]
 * Output: 0
 * Explanation: Since there is only one 1 in the array, no swaps are needed.
 * 
 * Example 3
 * Input: data = [1,0,1,0,1,0,0,1,1,0,1]
 * Output: 3
 * Explanation: One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
 *
 * 3. Constraints
 * 1 <= data.length <= 105
 * data[i] is either 0 or 1.
 */
public class MinimumSwapsToGroupAll1sTogether {

  /**
   * 1. Approach 
   * Sliding Window + Two Pointers. Suppose we have already known the final grouped 1s subarray somewhere. For example,
   * [1, 0, 1, 0, 1] => [1, 1, 1, 0, 0]. 
   * 
   * Two highlights:
   * - Number of swaps == Number of zeros in the subarray
   * - The length of the final subarray == number of ones in the array == Sum(array)
   * 
   * With that, we could keep a sliding window with fixed length as number of ones in the array and move two pointers 
   * from start to end to calculate the number of zeros in the sliding window.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param data
   * @return
   */
  public int minSwaps(int[] data) {
    int windowSize = 0;
    for (int num : data) {
      windowSize += num;
    }

    int oneCnt = 0, maxOnes = 0;
    for (int i = 0; i < data.length; i++) {
      oneCnt += data[i];
      if (i >= windowSize) oneCnt -= data[i - windowSize];
      maxOnes = Math.max(maxOnes, oneCnt);
    }

    return windowSize - maxOnes;
  }
}
