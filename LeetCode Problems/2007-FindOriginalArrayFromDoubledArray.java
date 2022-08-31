package problem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * An integer array original is transformed into a doubled array changed by appending twice the value of every element
 * in original, and then randomly shuffling the resulting array.
 *
 * Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an 
 * empty array. The elements in original may be returned in any order.
 *
 * 2. Examples
 * Example 1
 * Input: changed = [1,3,4,2,6,8]
 * Output: [1,3,4]
 * Explanation: One possible original array could be [1,3,4]:
 * - Twice the value of 1 is 1 * 2 = 2.
 * - Twice the value of 3 is 3 * 2 = 6.
 * - Twice the value of 4 is 4 * 2 = 8.
 * Other original arrays could be [4,3,1] or [3,1,4].
 * 
 * Example 2
 * Input: changed = [6,3,0,1]
 * Output: []
 * Explanation: changed is not a doubled array.
 * 
 * Example 3
 * Input: changed = [1]
 * Output: []
 * Explanation: changed is not a doubled array.
 *
 * 3. Constraints
 * 1 <= changed.length <= 105
 * 0 <= changed[i] <= 105
 */
public class FindOriginalArrayFromDoubledArray {

  /**
   * 1. Approach 
   * Sort + HashMap. 
   * 
   * For each element X in array, there could be either 2 * X or X / 2 also exist in array which makes things complicated.
   * One trick we could do is to always start from the smallest item in array, thus we only need to cover 2 * X as it is 
   * guaranteed that no X/2 exists.
   * 
   * 2. Complexity 
   * - Time O(NlogN)
   * - Space O(N)
   * 
   * @param changed
   * @return
   */
  public int[] findOriginalArray(int[] changed) {
    if (changed.length % 2 == 1) return new int[0];

    Arrays.sort(changed);
    final Map<Integer, Integer> map = new HashMap<>();
    for (int num : changed) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    final int[] original = new int[changed.length / 2];
    int i = 0;
    for (int num : changed) {
      if (map.getOrDefault(num, 0) > 0) {
        map.put(num, map.getOrDefault(num, 0) - 1);
        final int doubleNum = num * 2;
        if (map.getOrDefault(doubleNum, 0) == 0) {
          return new int[0];
        }
        map.put(doubleNum, map.getOrDefault(doubleNum, 0) - 1);
        original[i] = num;
        i++;
      }
    }
    return original;
  }

  /**
   * 1. Approach
   * Counting Sort
   * 
   * 2. Complexity
   * - Time O(M + N) where M is the maximum possible number.
   * - Space O(M)
   * 
   * @param changed
   * @return
   */
  public int[] findOriginalArray2(int[] changed) {
    final int n = changed.length;
    if (n % 2 == 1) return new int[0];
    final int[] count = new int[200001];
    final int[] original = new int[n / 2];

    for (int num : changed) {
      count[num]++;
    }

    int index = 0;
    for (int num = 0; num < 100001; num++) {
      if (count[num] > 0) {
        count[num]--;
        final int doubledNum = num * 2;
        if (count[doubledNum] > 0) {
          count[doubledNum]--;
          original[index] = num;
          index++;
          num--;
        } else {
          return new int[0];
        }
      }
    }
    return original;
  }
}
