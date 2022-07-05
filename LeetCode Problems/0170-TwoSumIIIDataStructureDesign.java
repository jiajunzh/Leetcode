package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a
 * particular value.
 *
 * Implement the TwoSum class:
 *
 * TwoSum() Initializes the TwoSum object, with an empty array initially.
 * void add(int number) Adds number to the data structure.
 * boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, 
 * it returns false.
 *
 * 2. Examples 
 * Example 1
 * Input
 * ["TwoSum", "add", "add", "add", "find", "find"]
 * [[], [1], [3], [5], [4], [7]]
 * Output
 * [null, null, null, null, true, false]
 *
 * Explanation
 * TwoSum twoSum = new TwoSum();
 * twoSum.add(1);   // [] --> [1]
 * twoSum.add(3);   // [1] --> [1,3]
 * twoSum.add(5);   // [1,3] --> [1,3,5]
 * twoSum.find(4);  // 1 + 3 = 4, return true
 * twoSum.find(7);  // No two integers sum up to 7, return false
 *
 * 3. Constraints
 * -105 <= number <= 105
 * -231 <= value <= 231 - 1
 * At most 104 calls will be made to add and find.
 */
public class TwoSumIIIDataStructureDesign {
  
  private final Map<Integer, Integer> map;

  /**
   * 1. Approach 
   * HashTable 
   * 
   * 2. Complexity 
   * - Time O(1) for constructor and add and O(N) for find
   * - Space O(N)
   * 
   * 3. Improvement/Alternatives
   * - if (value > 200_000 || value < -200_000) return false; is not actually needed due to the constraint that number 
   *   is in [-200_000, 200_000] no value with integer overflow such as 2147483647 + 1 will be found in the map.
   * - Another approach is to sort the array if not and have two pointers from left and right to find the pair, it gets 
   *   you the O(1) time for constructor and add but O(NlogN) for find
   */
  public TwoSumIIIDataStructureDesign() {
    this.map = new HashMap<>();
  }

  public void add(int number) {
    map.put(number, map.getOrDefault(number, 0) + 1);
  }

  public boolean find(int value) {
    if (value > 200_000 || value < -200_000) return false;
    for (final int num1 : map.keySet()) {
      final int num2 = value - num1;
      if (map.containsKey(num2)) {
        if (num1 == num2) {
          if (map.get(num1) > 1) {
            return true;
          }
        } else {
          return true;
        }
      }
    }
    return false;
  }
}
