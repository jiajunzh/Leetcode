package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * Design a map that allows you to do the following:
 *
 * Maps a string key to a given value.
 * Returns the sum of the values that have a key with a prefix equal to a given string.
 * Implement the MapSum class:
 *
 * MapSum() Initializes the MapSum object.
 * void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
 * int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 *
 * 2. Examples
 * Example 1
 * Input
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * Output
 * [null, null, 3, null, 5]
 *
 * Explanation
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);  
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);    
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *
 * 3. Constraints
 * 1 <= key.length, prefix.length <= 50
 * key and prefix consist of only lowercase English letters.
 * 1 <= val <= 1000
 * At most 50 calls will be made to insert and sum.
 */
public class MapSumPairs {

  /**
   * 1. Approach 
   * Trie + HashMap.
   * 
   * An initial version of Trie implementation is to find the prefix node first and then sum all its descendants. This 
   * requires search downwards each time of calling sum. A better way is to define the score as the sum of all the 
   * descendants values directly, saving the effort to search downwards
   * 
   * 2. Complexity 
   * - Time O(K)
   * - Space O(K * N)
   */
  private final Map<String, Integer> map;
  private final TrieNode root;

  public MapSumPairs() {
    this.map = new HashMap<>();
    this.root = new TrieNode();
  }
  
  public void insert(String key, int val) {
    final int oldVal = this.map.getOrDefault(key, 0);
    this.map.put(key, val);
    final int delta = val - oldVal;
    TrieNode curr = root;
    curr.setValue(delta + curr.getValue());
    for (char c : key.toCharArray()) {
      if (!curr.containsKey(c)) {
        curr.put(c, new TrieNode());
      }
      curr = curr.get(c);
      curr.setValue(delta + curr.getValue());
    }
  }
  
  public int sum(String prefix) {
    TrieNode curr = root;
    for (char c : prefix.toCharArray()) {
      if (!curr.containsKey(c)) {
        return 0;
      }
      curr = curr.get(c);
    }
    return curr.getValue();
  }
  
  class TrieNode {

    private static final int R = 26;
    private final TrieNode[] links;
    private int value;

    public TrieNode() {
      this.links = new TrieNode[R];
    }

    public boolean containsKey(char c) {
      return this.links[c - 'a'] != null;
    }

    public TrieNode get(char c) {
      return this.links[c - 'a'];
    }

    public void put(char c, TrieNode child) {
      this.links[c - 'a'] = child;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public int getValue() {
      return this.value;
    }
  }
}
