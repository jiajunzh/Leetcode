package problem;

/**
 * 1. Problem 
 * This is an interactive problem.
 *
 * You have a sorted array of unique elements and an unknown size. You do not have an access to the array but you can 
 * use the ArrayReader interface to access it. You can call ArrayReader.get(i) that:
 *
 * returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
 * returns 231 - 1 if the i is out of the boundary of the array.
 * You are also given an integer target.
 *
 * Return the index k of the hidden array where secret[k] == target or return -1 otherwise.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * 2. Examples
 * Example 1
 * Input: secret = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in secret and its index is 4.
 * 
 * Example 2
 * Input: secret = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in secret so return -1.
 *
 * 3. Constraints
 * 1 <= secret.length <= 104
 * -104 <= secret[i], target <= 104
 * secret is sorted in a strictly increasing order.
 */
public class SearchInASortedArrayOfUnknownSize {

  /**
   * 1. Approach 
   * Binary Search. 
   * 
   * 2. Complexity 
   * - Time O(log10000)
   * - Space O(1)
   * 
   * 3. Alternatives 
   * - A few tricks about pre-processing the initial boundary:
   *  - Use 10_000 according to the constraint
   *  - Use target - Array[0] (Hint: the array is sorted and unique)
   *  - Confine the search space always in [2^i, 2^i+1]. See Example below
   *    int left = 0, right = 1;
   *    while (reader.get(right) < target) {
   *      left = right;
   *      right <<= 1;
   *    }
   *  
   * @param reader
   * @param target
   * @return
   */
  public int search(ArrayReader reader, int target) {
    int left = 0, right = 10_000;;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      int val = reader.get(mid);

      if (val == target) {
        return mid;
      } else if (val < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return -1;
  }
  
  interface ArrayReader { 
    public int get(int index);
  }
}
