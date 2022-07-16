package problem;

import java.util.LinkedList;
import java.util.List;

/**
 * 1. Problem 
 * Design a HashMap without using any built-in hash table libraries.
 *
 * Implement the MyHashMap class:
 *
 * MyHashMap() initializes the object with an empty map.
 * void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
 * int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 *
 * 2. Examples 
 * Example 1
 * Input
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * Output
 * [null, null, null, 1, -1, null, 1, null, -1]
 *
 * Explanation
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // The map is now [[1,1]]
 * myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
 * myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
 * myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
 * myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
 * myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
 * myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
 * myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 *
 * 3. Constraints
 * 0 <= key, value <= 106
 * At most 104 calls will be made to put, get, and remove.
 */
class DesignHashMap {

  private static final int BUCKET_NUM = 769;
  private final Bucket[] hashTable;

  /**
   * 1. Approach 
   * HashTable <==> Bucket[] <==> List<Item>[]
   * 
   * 2. Complexity 
   * - Time O(B) for constructor, O(L) for put/get/remove operation where B is the number of buckets and L is the 
   *   maximum number of items in one bucket
   * - Space O(B + N) where N is number of items
   */
  public DesignHashMap() {
    this.hashTable = new Bucket[BUCKET_NUM];
    for (int i = 0; i < BUCKET_NUM; i++) {
      this.hashTable[i] = new Bucket();
    }
  }

  public void put(int key, int value) {
    final int hashkey = getHashkey(key);
    this.hashTable[hashkey].put(new Item(key, value));
  }

  public int get(int key) {
    final int hashkey = getHashkey(key);
    return this.hashTable[hashkey].get(key);
  }

  public void remove(int key) {
    final int hashkey = getHashkey(key);
    this.hashTable[hashkey].remove(key);
  }

  private int getHashkey(int key) {
    return key % BUCKET_NUM;
  }

  private static class Bucket {

    private final List<Item> items;

    private Bucket() {
      this.items = new LinkedList<>();
    }

    private void put(Item newItem) {
      for (final Item item : this.items) {
        if (item.key == newItem.key) {
          item.value = newItem.value;
          return;
        }
      }
      this.items.add(newItem);
    }

    private int get(int key) {
      for (final Item item : this.items) {
        if (item.key == key) {
          return item.value;
        }
      }
      return -1;
    }

    private void remove(int key) {
      for (final Item item : this.items) {
        if (item.key == key) {
          this.items.remove(item);
          break;
        }
      }
    }
  }

  private static class Item {
    private int key;
    private int value;

    private Item(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }
}
