package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. Problem 
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all
 * the original letters exactly once.
 *
 * 2. Examples 
 * Example 1
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * Example 2
 * Input: strs = [""]
 * Output: [[""]]
 * 
 * Example 3
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 * 3. Constraints
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams {

  /**
   * 1. Approach
   * HashMap with designed hashmap key.
   * 
   * Key => a1b2c3...zx
   * 
   * 2. Complexity
   * - Time O(N * (K + 26)) K is the length of each str
   * - Space O(N * K)
   * 
   * @param strs
   * @return
   */
  public List<List<String>> groupAnagrams(String[] strs) {
    final Map<String, List<String>> map = new HashMap<>();
    for (final String str : strs) {
      final String key = buildHashMapKey(str);
      final List<String> anagramGroup = map.getOrDefault(key, new ArrayList<>());
      anagramGroup.add(str);
      map.put(key, anagramGroup);
    }
    return new ArrayList<>(map.values());
  }

  private String buildHashMapKey(String str) {
    final int[] charCount = new int[26];
    for (char c : str.toCharArray()) {
      charCount[c - 'a']++;
    }
    final StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      if (charCount[i] > 0) {
        sb.append(i + 'a');
        sb.append(charCount[i]);
      }
    }
    return sb.toString();
  }
}
