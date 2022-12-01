package problem;

/**
 * 1. Problem
 * Given an array of integers arr, return true if and only if it is a valid mountain array.
 *
 * Recall that arr is a mountain array if and only if:
 *
 * arr.length >= 3
 * There exists some i with 0 < i < arr.length - 1 such that:
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 *
 * 2. Examples
 * Example 1
 * Input: arr = [2,1]
 * Output: false
 * 
 * Example 2
 * Input: arr = [3,5,5]
 * Output: false
 * 
 * Example 3
 * Input: arr = [0,3,2,1]
 * Output: true
 *
 * 3. Constraints
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 104
 */
public class ValidMountainArray {

  /**
   * 1. Approach 
   * One Pass 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param arr
   * @return
   */
  public boolean validMountainArray(int[] arr) {
    if (arr.length < 3) return false;
    int index = 0;
    while (index < arr.length - 1 && arr[index] < arr[index + 1]) index++;
    if (index == 0 || index == arr.length - 1) return false;
    while (index < arr.length - 1 && arr[index] > arr[index + 1]) index++;
    return index == arr.length - 1;
  }
}
