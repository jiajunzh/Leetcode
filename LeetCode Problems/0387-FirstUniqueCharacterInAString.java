package problem;

/**
 * 1. Problem 
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 *
 * 2. Examples 
 * Example 1
 * Input: s = "leetcode"
 * Output: 0
 * 
 * Example 2
 * Input: s = "loveleetcode"
 * Output: 2
 * 
 * Example 3
 * Input: s = "aabb"
 * Output: -1
 *
 * 3. Constraints
 * 1 <= s.length <= 105
 * s consists of only lowercase English letters.
 */
public class FirstUniqueCharacterInAString {

  /**
   * 1. Approach 
   * ArrayMap/HashMap
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(26)
   * 
   * @param s
   * @return
   */
  public int firstUniqChar(String s) {
    final int[] charMap = new int[26];
    for (char c : s.toCharArray()) {
      charMap[c - 'a']++;
    }
    for (int i = 0; i < s.length(); i++) {
      if (charMap[s.charAt(i) - 'a'] == 1) {
        return i;
      }
    }
    return -1;
  }
}
