package problem;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. Problem 
 * Given an array arr of integers, check if there exist two indices i and j such that :
 *
 * i != j
 * 0 <= i, j < arr.length
 * arr[i] == 2 * arr[j]
 *
 * 2. Examples
 * Example 1
 * Input: arr = [10,2,5,3]
 * Output: true
 * Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
 * 
 * Example 2
 * Input: arr = [3,1,7,11]
 * Output: false
 * Explanation: There is no i and j that satisfy the conditions.
 *
 * 3. Constraints
 * 2 <= arr.length <= 500
 * -103 <= arr[i] <= 103
 */
public class CheckIfNAndItsDoubleExist {

  /**
   * 1. Approach 
   * HashSet
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param arr
   * @return
   */
  public boolean checkIfExist(int[] arr) {
    final Set<Integer> set = new HashSet<>();

    for (int num : arr) {
      if (set.contains(num * 2)) return true;
      if (num % 2 == 0 && set.contains(num / 2)) return true;
      set.add(num);
    }

    return false;
  }
}
