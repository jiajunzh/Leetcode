package problem;

/**
 * 1. Problem 
 * Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written. Do the above modifications to the input array in place and do not return anything.
 *
 * 2. Examples
 * Example 1
 * Input: arr = [1,0,2,3,0,4,5,0]
 * Output: [1,0,0,2,3,0,0,4]
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
 * 
 * Example 2
 * Input: arr = [1,2,3]
 * Output: [1,2,3]
 * Explanation: After calling your function, the input array is modified to: [1,2,3]
 *
 * 3. Constraints
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 9
 */
public class DuplicateZeros {

  /**
   * 1. Approach 
   * Two Pass
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param arr
   */
  public void duplicateZeros(int[] arr) {
    int duplicateCount = 0;
    int lastOccupied = 0;
    for (int i = 0; i < arr.length - duplicateCount; i++) {
      if (arr[i] == 0) {
        if (i + duplicateCount == arr.length - 1) {
          arr[arr.length - 1] = 0;
          lastOccupied = 1;
          break;
        }
        duplicateCount++;
      }
    }
    int last = arr.length - 1 - duplicateCount - lastOccupied;
    for (int i = last; i >= 0; i--) {
      if (arr[i] == 0) {
        arr[i + duplicateCount] = 0;
        duplicateCount--;
        arr[i + duplicateCount] = 0;
      } else {
        arr[i + duplicateCount] = arr[i];
      }
    }
  }

  /**
   * 1. Approach 
   * Two Pointers
   *
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   */
  public void duplicateZeros2(int[] arr) {
    final int n = arr.length;
    int zeros = 0;
    for (int num : arr) {
      if (num == 0) zeros++;
    }
    for (int i = n - 1, j = n + zeros - 1; i >= 0; i--, j--) {
      if (j < n) arr[j] = arr[i];
      if (arr[i] == 0) j--;
      if (j < n) arr[j] = arr[i];
    }
  }
}
