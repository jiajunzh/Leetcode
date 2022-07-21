package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) 
 * deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 * 2. Examples
 * Example 1
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 * 
 * Example 2
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 *
 * 3. Constraints
 * 1 <= s.length <= 5 * 104
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * s and words[i] consist of only lowercase English letters.
 */
public class NumberOfMatchingSubsequences {

  /**
   * 1. Approach 
   * Char Map.
   * 
   * Keep a mapping from character c to a list of words whose current first position/cursor is at char c. Then for each
   * char in string s, we just examine the list stored at position c and move cursors of all words in list to next one.
   * 
   * 2. Complexity
   * - Time O(S + Sum(W * L))
   * - Space O(W)
   * 
   * @param s
   * @param words
   * @return
   */
  public int numMatchingSubseq(String s, String[] words) {
    final List<int[]>[] map = (List<int[]>[]) new ArrayList[26];
    for (int i = 0; i < 26; i++) {
      map[i] = new ArrayList<>();
    }
    for (int i = 0; i < words.length; i++) {
      map[words[i].charAt(0) - 'a'].add(new int[]{i, 0});
    }
    int count = 0;
    for (char c : s.toCharArray()) {
      final List<int[]> oldList = map[c - 'a'];
      final List<int[]> newList = new ArrayList<>();
      map[c - 'a'] = newList;
      for (int[] indices : oldList) {
        if (indices[1] == words[indices[0]].length() - 1) {
          count++;
        } else {
          map[words[indices[0]].charAt(indices[1] + 1) - 'a'].add(new int[]{indices[0], indices[1] + 1});
        }
      }
    }
    return count;
  }

  /**
   * 1. Approach
   * CharMap + BinarySearch
   * 
   * 2. Complexity
   * - Time O(W * L * logS)
   * - Space O(S)
   * 
   * @param s
   * @param words
   * @return
   */
  public int numMatchingSubseq2(String s, String[] words) {
    final List<Integer>[] map = (List<Integer>[]) new ArrayList[26];
    for (int i = 0; i < 26; i++) {
      map[i] = new ArrayList<>();
    }
    for (int i = 0; i < s.length(); i++) {
      map[s.charAt(i) - 'a'].add(i);
    }
    int count = 0;
    for (final String word : words) {
      int index = -1;
      for (final char c : word.toCharArray()) {
        index = binarySearch(map[c - 'a'], index);
        if (index == -1) break;
      }
      if (index != -1) count++;
    }
    return count;
  }

  private int binarySearch(final List<Integer> indices, final int lowerBound) {
    // Find the minimum indice in indices list such that it is greater than lowerBound
    if (indices.isEmpty() || indices.get(indices.size() - 1) <= lowerBound) return -1;
    int low = 0, high = indices.size() - 1;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (indices.get(mid) > lowerBound) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return indices.get(low);
  }
}
