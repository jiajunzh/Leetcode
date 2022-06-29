package problem;

import java.util.LinkedList;
import java.util.List;

/**
 * 1. Problem 
 * Design a HashSet without using any built-in hash table libraries.
 *
 * Implement MyHashSet class:
 *
 * void add(key) Inserts the value key into the HashSet.
 * bool contains(key) Returns whether the value key exists in the HashSet or not.
 * void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 *
 * 2. Examples
 * Example 1
 * Input
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * Output
 * [null, null, null, true, false, null, true, null, false]
 *
 * Explanation
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // return True
 * myHashSet.contains(3); // return False, (not found)
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // return True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // return False, (already removed)
 *
 * 3. Constraints
 * 0 <= key <= 106
 * At most 104 calls will be made to add, remove, and contains.
 */
public class DesignHashSet {

  private static final int KEY_RANGE = 769;
  private final Bucket[] buckets;

  /**
   * 1. Approach 
   * Chaining LinkedList 
   * 
   * 2. Complexity 
   * - Time O(N / K) where K is the number of buckets 
   * - Space O(K + M) where M is the number of unique values inserted into the HashSet
   * 
   * 3. Improvement 
   * - Another optimized approach to implement this is to build up a BST for each of the bucket.
   * 
   */
  public DesignHashSet() {
    buckets = new Bucket[KEY_RANGE];
    for (int i = 0; i < KEY_RANGE; i++) {
      buckets[i] = new Bucket();
    }
  }

  public void add(int key) {
    int hashCode = hashCode(key);
    buckets[hashCode].add(key);
  }

  public void remove(int key) {
    int hashCode = hashCode(key);
    buckets[hashCode].remove(key);
  }

  public boolean contains(int key) {
    int hashCode = hashCode(key);
    return buckets[hashCode].contains(key);
  }

  private int hashCode(int key) {
    return key % KEY_RANGE;
  }

  private static class Bucket {

    private final List<Integer> items;

    private Bucket() {
      items = new LinkedList<>();
    }

    private void add(int key) {
      final int index = this.items.indexOf(key);
      if (index == -1) {
        this.items.add(key);
      }
    }

    private boolean contains(int key) {
      return this.items.contains(key);
    }

    private void remove(int key) {
      final int index = this.items.indexOf(key);
      if (index != -1) {
        this.items.remove(index);
      }
    }
  }
}
