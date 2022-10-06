package problem;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1. Problem 
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 *
 * Return any possible rearrangement of s or return "" if not possible.
 *
 * 2. Examples
 * Example 1
 * Input: s = "aab"
 * Output: "aba"
 * 
 * Example 2
 * Input: s = "aaab"
 * Output: ""
 *
 * 3. Constraints
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 */
public class ReorganizeString {

  /**
   * 1. Approach
   * PriorityQueue
   * 
   * 2. Complexity 
   * - Time O(N + NlogR)
   * - Space O(R)
   * 
   * 3. Alternative 
   * - Another approach is to place same letter by jumping one gap between them each time starting from the one having 
   *   maximum frequency. For example, if we have "aaabbc", below are the steps:
   *   - "a_a_a_"
   *   - "ababac"
   *   
   * @param s
   * @return
   */
  public String reorganizeString(String s) {
    final int R = 26;
    final int[] counts = new int[R];
    for (final char c : s.toCharArray()) {
      counts[c - 'a']++;
    }
    final Queue<Integer> queue = new PriorityQueue<>((a, b) -> (counts[b] - counts[a]));
    for (int i = 0; i < R; i++) {
      if (counts[i] > 0) {
        queue.offer(i);
      }
    }
    final StringBuilder sb = new StringBuilder();
    while (queue.size() > 1) {
      int first = queue.poll();
      int second = queue.poll();
      sb.append((char) (first + 'a'));
      sb.append((char) (second + 'a'));
      counts[first]--;
      counts[second]--;
      if (counts[first] >= 1) queue.offer(first);
      if (counts[second] >= 1) queue.offer(second);
    }
    if (queue.size() == 1) {
      if (counts[queue.peek()] == 1) {
        sb.append((char) (queue.peek() + 'a'));
      } else {
        return "";
      }
    }
    return sb.toString();
  }
}
