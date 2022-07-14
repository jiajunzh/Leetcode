package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem 
 * You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are 
 * given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
 *
 * To complete the ith replacement operation:
 *
 * Check if the substring sources[i] occurs at index indices[i] in the original string s.
 * If it does not occur, do nothing.
 * Otherwise if it does occur, replace that substring with targets[i].
 * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this 
 * replacement will be "eeecd".
 *
 * All replacement operations must occur simultaneously, meaning the replacement operations should not affect the 
 * indexing of each other. The testcases will be generated such that the replacements will not overlap.
 *
 * For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because 
 * the "ab" and "bc" replacements overlap.
 * Return the resulting string after performing all replacement operations on s.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 * 2. Examples 
 * Example 1
 * Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
 * Output: "eeebffff"
 * Explanation:
 * "a" occurs at index 0 in s, so we replace it with "eee".
 * "cd" occurs at index 2 in s, so we replace it with "ffff".
 * 
 * Example 2
 * Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation:
 * "ab" occurs at index 0 in s, so we replace it with "eee".
 * "ec" does not occur at index 2 in s, so we do nothing.
 *
 * 3. Constraints
 * 1 <= s.length <= 1000
 * k == indices.length == sources.length == targets.length
 * 1 <= k <= 100
 * 0 <= indexes[i] < s.length
 * 1 <= sources[i].length, targets[i].length <= 50
 * s consists of only lowercase English letters.
 * sources[i] and targets[i] consist of only lowercase English letters.
 */
public class FindAndReplaceInString {

  /**
   * 1. Approach 
   * HashMap from indices of string s to indice of indices.
   * 
   * For each index in string s:
   * if the map contains this index, meaning there is a matched replacement => replace the word
   * else append the char at index
   * 
   * 2. Complexity 
   * - Time O(N * K + N) where N is the length of S and K is the length of indices 
   * - Space O(N)
   * 
   * 3. Mistakes
   * - One caveat is that the indices array is not ordered. 
   * 
   * @param s
   * @param indices
   * @param sources
   * @param targets
   * @return
   */
  public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
    final Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < indices.length; i++) {
      int indice = indices[i];
      final String source = sources[i];
      if (containsAt(s, source, indice)) {
        map.put(indice, i);
      }
    }
    final StringBuilder sb = new StringBuilder();
    int i = 0;
    while (i < s.length()) {
      if (map.containsKey(i)) {
        sb.append(targets[map.get(i)]);
        i += sources[map.get(i)].length();
      } else {
        sb.append(s.charAt(i));
        i++;
      }
    }
    return sb.toString();
  }

  private boolean containsAt(String s, String source, int indice) {
    for (char c : source.toCharArray()) {
      if (indice == s.length() || s.charAt(indice) != c) {
        return false;
      }
      indice++;
    }
    return true;
  }
}
