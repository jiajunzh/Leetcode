package problem;

/**
 * 1. Problem 
 * Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 * Return the sum of all odd-length subarrays of arr.
 *
 * 2. Examples
 * Example 1
 * Input: arr = [1,4,2,5,3]
 * Output: 58
 * Explanation: The odd-length subarrays of arr and their sums are:
 * [1] = 1
 * [4] = 4
 * [2] = 2
 * [5] = 5
 * [3] = 3
 * [1,4,2] = 7
 * [4,2,5] = 11
 * [2,5,3] = 10
 * [1,4,2,5,3] = 15
 * If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 * 
 * Example 2
 * Input: arr = [1,2]
 * Output: 3
 * Explanation: There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.
 * 
 * Example 3
 * Input: arr = [10,11,12]
 * Output: 66
 *
 * 3. Constraints
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= 1000
 */
public class SumOfAllOddLengthSubarrays {

  /**
   * 1. Approach 
   * Prefix Sum
   * 
   * 2. Complexity
   * - Time O(N^2)
   * - Space O(1)
   * 
   * @param arr
   * @return
   */
  public int sumOddLengthSubarraysPreSum(int[] arr) {
    int sum = 0;
    for (int i = 1; i < arr.length; i++) {
      arr[i] += arr[i - 1];
    }
    for (int i = 0; i < arr.length; i++) {
      for (int j = i; j < arr.length; j += 2) {
        int subSum = arr[j] - (i == 0 ? 0 : arr[i - 1]);
        sum += subSum;
      }
    }
    return sum;
  }

  /**
   * 1. Approach
   * This problem uses mathematical formula to calculate the result which results in O(N) time. The answer could be 
   * converted into calculating the sum(arr[i] * occurrence in all odd-length subarrays).
   * 
   * - arr[i] occurrence in all odd-length subarrays = (occurrence in all subarrays + 1) / 2
   * - arr[i] occurrence in all subarrays = number of subarray starting at arr[i] * number of subarray ending at arr[i]
   *   e.g. [8,4,3,0] if we want to calculate 2th occurrence => 2 * 3 = 6;
   *   4    => 4 + 4
   *   84   => 84 + 4 
   *   43   => 4 + 43
   *   843  => 84 + 43
   *   430  => 4 + 430
   *   8430 => 84 + 430
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param arr
   * @return
   */
  public int sumOddLengthSubarraysMath(int[] arr) {
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum = sum + ((i + 1) * (arr.length - i) + 1) / 2 * arr[i];
    }
    return sum;
  }
}
