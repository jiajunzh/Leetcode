package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. 
 * No two characters may map to the same character, but a character may map to itself.
 *
 * 2. Examples 
 * Example 1
 * Input: s = "egg", t = "add"
 * Output: true
 * 
 * Example 2
 * Input: s = "foo", t = "bar"
 * Output: false
 * 
 * Example 3
 * Input: s = "paper", t = "title"
 * Output: true
 *
 * 3. Constraints
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s and t consist of any valid ascii character.
 */
public class IsomorphicStrings {

  /**
   * 1. Approach 
   * HashMap 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * 3. Alternatives 
   * - One other optimization is to provision an array in place of Hash Map as the characters are confined with ascii
   * - An alternative approach is to record the index of the first character occurrence in string and replace the same 
   *   character with index number in string. Compare the index number of the two string.
   * 
   * @param s
   * @param t
   * @return
   */
  public boolean isIsomorphic(String s, String t) {
    final Map<Character, Character> replacement = new HashMap<>();
    final boolean[] visited = new boolean[128];
    for (int i = 0; i < s.length(); i++) {
      final char charS = s.charAt(i);
      final char charT = t.charAt(i);
      if (replacement.containsKey(charS)) {
        final char replacedChar = replacement.get(charS);
        if (replacedChar != charT) {
          return false;
        }
      } else {
        if (visited[charT]) {
          return false;
        }
        visited[charT] = true;
        replacement.put(charS, charT);
      }
    }
    return true;
  }
}
