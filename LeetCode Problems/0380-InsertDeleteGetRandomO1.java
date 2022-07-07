package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 1. Problem 
 * Implement the RandomizedSet class:
 *
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element 
 * exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 *
 * 2. Examples 
 * Example 1
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 *
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 *
 * 3. Constraints
 * -231 <= val <= 231 - 1
 * At most 2 * 105 calls will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 */
public class InsertDeleteGetRandomO1 {

  /**
   * 1. Approach 
   * HashMap + ArrayList + Random
   * 
   * 2. Complexity 
   * - Time O(1) for constructor, insert, remove and random()
   * - Space O(N)
   */
  private final Map<Integer, Integer> indicesMap;
  private final List<Integer> nums;
  private final Random random;

  public InsertDeleteGetRandomO1() {
    this.indicesMap = new HashMap<>();
    this.nums = new ArrayList<>();
    this.random = new Random();
  }

  public boolean insert(int val) {
    if (this.indicesMap.containsKey(val)) {
      return false;
    } else {
      this.indicesMap.put(val, this.nums.size());
      this.nums.add(val);
      return true;
    }
  }

  public boolean remove(int val) {
    if (this.indicesMap.containsKey(val)) {
      int indice = this.indicesMap.get(val);
      int lastElement = this.nums.get(this.nums.size() - 1);
      this.nums.set(indice, lastElement);
      this.nums.remove(this.nums.size() - 1);
      this.indicesMap.put(lastElement, indice);
      this.indicesMap.remove(val);
      return true;
    } else {
      return false;
    }
  }

  public int getRandom() {
    int randomNum = this.random.nextInt(this.nums.size());
    return this.nums.get(randomNum);
  }
}
