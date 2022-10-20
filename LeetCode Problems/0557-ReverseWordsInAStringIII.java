package problem;

/**
 * 1. Problem
 * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace
 * and initial word order.
 *
 * 2. Examples
 * Example 1
 * Input: s = "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * 
 * Example 2
 * Input: s = "God Ding"
 * Output: "doG gniD"
 *
 * 3. Constraints
 * 1 <= s.length <= 5 * 104
 * s contains printable ASCII characters.
 * s does not contain any leading or trailing spaces.
 * There is at least one word in s.
 * All the words in s are separated by a single space.
 */
public class ReverseWordsInAStringIII {

  /**
   * 1. Approach 
   * StringBuilder + Two Pointers
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * 3. Alternative 
   * - For language that supports mutable string type, another solution is to reverse word by swapping in place 
   * 
   * @param s
   * @return
   */
  public String reverseWords(String s) {
    final StringBuilder sb = new StringBuilder();
    int start = 0;
    while (start < s.length()) {
      int end = start;
      while (end < s.length() && s.charAt(end) != ' ') {
        end++;
      }
      for (int i = end - 1; i >= start; i--) {
        sb.append(s.charAt(i));
      }
      if (end < s.length()) {
        sb.append(' ');
        end++;
      }
      start = end;
    }
    return sb.toString();
  }
}
