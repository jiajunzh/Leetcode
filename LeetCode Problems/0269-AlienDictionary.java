package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1. Problem
 * There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.
 *
 * You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted 
 * lexicographically by the rules of this new language.
 *
 * Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new
 * language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.
 *
 * A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes
 * before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is 
 * smaller if and only if s.length < t.length.
 *
 * 2. Examples 
 * Example 1
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * 
 * Example 2
 * Input: words = ["z","x"]
 * Output: "zx"
 * 
 * Example 3
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 *
 * 3. Constraints
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 */
public class AlienDictionary {

  /**
   * 1. Approach 
   * Kahn's Algorithm
   * 
   * 2. Complexity 
   * - Time O(N * L)
   * - Space O(26 * 26)
   * 
   * @param words
   * @return
   */
  public String alienOrder(String[] words) {
    final int[] indegrees = new int[26];
    Arrays.fill(indegrees, -1);
    final List<Integer>[] graph = new List[26];
    int charCount = 0;
    for (int i = 0; i < 26; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int index = 0; index < words.length; index++) {
      for (char c : words[index].toCharArray()) {
        if (indegrees[c - 'a'] < 0) {
          indegrees[c - 'a'] = 0;
          charCount++;
        }
      }
    }
    for (int index = 0; index < words.length - 1; index++) {
      int i = 0, j = 0;
      final String curr = words[index];
      final String next = words[index + 1];
      while (i < curr.length() && j < next.length()) {
        if (curr.charAt(i) != next.charAt(j)) {
          indegrees[next.charAt(j) - 'a']++;
          graph[curr.charAt(i) - 'a'].add(next.charAt(j) - 'a');
          break;
        }
        i++;
        j++;
      }
      if (i < curr.length() && j == next.length()) {
        // For case ["ac","a"] which should not have been a valid test case
        return "";
      }
    }
    final Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < 26; i++) {
      if (indegrees[i] == 0) {
        queue.offer(i);
      }
    }
    final StringBuilder sb = new StringBuilder();
    while (!queue.isEmpty()) {
      int c = queue.poll();
      sb.append((char) (c + 'a'));
      for (int neighbor : graph[c]) {
        indegrees[neighbor]--;
        if (indegrees[neighbor] == 0) {
          queue.offer(neighbor);
        }
      }
    }
    return sb.length() != charCount ? "" : sb.toString();
  }
}
