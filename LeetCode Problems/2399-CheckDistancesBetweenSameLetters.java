package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * You are given a 0-indexed string s consisting of only lowercase English letters, where each letter in s appears 
 * exactly twice. You are also given a 0-indexed integer array distance of length 26.
 *
 * Each letter in the alphabet is numbered from 0 to 25 (i.e. 'a' -> 0, 'b' -> 1, 'c' -> 2, ... , 'z' -> 25).
 *
 * In a well-spaced string, the number of letters between the two occurrences of the ith letter is distance[i]. 
 * If the ith letter does not appear in s, then distance[i] can be ignored.
 *
 * Return true if s is a well-spaced string, otherwise return false.
 *
 * 2. Examples
 * Example 1
 * Input: s = "abaccb", distance = [1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: true
 * Explanation:
 * - 'a' appears at indices 0 and 2 so it satisfies distance[0] = 1.
 * - 'b' appears at indices 1 and 5 so it satisfies distance[1] = 3.
 * - 'c' appears at indices 3 and 4 so it satisfies distance[2] = 0.
 * Note that distance[3] = 5, but since 'd' does not appear in s, it can be ignored.
 * Return true because s is a well-spaced string.
 * 
 * Example 2
 * Input: s = "aa", distance = [1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: false
 * Explanation:
 * - 'a' appears at indices 0 and 1 so there are zero letters between them.
 * Because distance[0] = 1, s is not a well-spaced string.
 *
 * 3. Constraints
 * 2 <= s.length <= 52
 * s consists only of lowercase English letters.
 * Each letter appears in s exactly twice.
 * distance.length == 26
 * 0 <= distance[i] <= 50
 */
public class CheckDistancesBetweenSameLetters {

  /**
   * 1. Approach 
   * HashMap
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param s
   * @param distance
   * @return
   */
  public boolean checkDistances(String s, int[] distance) {
    final Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      final char c = s.charAt(i);
      if (map.containsKey(c)) {
        if (distance[c - 'a'] != (i - map.get(c) - 1)) {
          return false;
        }
      }
      map.put(c, i);
    }
    return true;
  }
}
