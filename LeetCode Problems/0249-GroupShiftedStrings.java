package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. Problem 
 * We can shift a string by shifting each of its letters to its successive letter.
 *
 * For example, "abc" can be shifted to be "bcd".
 * We can keep shifting the string to form a sequence.
 *
 * For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return
 * the answer in any order.
 *
 * 2. Examples 
 * Example 1
 * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
 * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 * 
 * Example 2
 * Input: strings = ["a"]
 * Output: [["a"]]
 *
 * 3. Constraints
 * 1 <= strings.length <= 200
 * 1 <= strings[i].length <= 50
 * strings[i] consists of lowercase English letters.
 */
public class GroupShiftedStrings {

  /**
   * 1. Approach 
   * HashMap.
   * 
   * The key could be constructed by shifting the string with new base. For example, we always select the first char of 
   * string as the original base and then switch to base 'a', then we get 
   * newChar = ((string.charAt(i) - base + 26) % 26 + 'a');
   * 
   * 2. Complexity 
   * - Time O(N * K)
   * - Space O(N * K)
   * 
   * @param strings
   * @return
   */
  public List<List<String>> groupStrings(String[] strings) {
    final Map<String, List<String>> map = new HashMap<>();
    for (final String string : strings) {
      final String key = getKey(string);
      final List<String> group = map.getOrDefault(key, new ArrayList<>());
      group.add(string);
      map.put(key, group);
    }

    return new ArrayList<>(map.values());
  }

  private String getKey(final String string) {
    final StringBuilder sb = new StringBuilder();
    final char base = string.charAt(0);
    for (int i = 0; i < string.length(); i++) {
      char c = (char) ((string.charAt(i) - base + 26) % 26 + 'a');
      sb.append(c);
    }
    return sb.toString();
  }
}
