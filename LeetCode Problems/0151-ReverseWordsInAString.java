package problem;

/**
 * 1. Problem
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should 
 * only have a single space separating the words. Do not include any extra spaces.
 *
 * 2. Examples
 * Example 1
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * 
 * Example 2
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * 
 * Example 3
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 *
 * 3. Constraints
 * 1 <= s.length <= 104
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 *
 * 4. Follow-up
 * If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 */
public class ReverseWordsInAString {

  /**
   * 1. Approach 
   * Reverse + StringBuilder + Two Pointers
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * 3. Follow up
   * - In-Place =>  Reverse the whole string + Reverse each word + Trim 
   * 
   * @param s
   * @return
   */
  public String reverseWords(String s) {
    final int n = s.length();
    final StringBuilder sentence = new StringBuilder();
    int end = n - 1;
    while (end >= 0 && s.charAt(end) == ' ') end--;
    while (end >= 0) {
      final StringBuilder word = new StringBuilder();
      int start = end;
      while (start >= 0 && s.charAt(start) != ' ') {
        word.append(s.charAt(start));
        start--;
      }
      if (sentence.length() > 0) {
        sentence.append(" ");
      }
      sentence.append(word.reverse());
      end = start - 1;
      while (end >= 0 && s.charAt(end) == ' ') end--;
    }
    return sentence.toString();
  }
}
