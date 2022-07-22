package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth 
 * characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra 
 * spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not
 * divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 * 2. Examples
 * Example 1
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 
 * Example 2
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be 
 * left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 * 
 * Example 3
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art",
 * "is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 * 3. Constraints
 * 1 <= words.length <= 300
 * 1 <= words[i].length <= 20
 * words[i] consists of only English letters and symbols.
 * 1 <= maxWidth <= 100
 * words[i].length <= maxWidth
 */
public class TextJustification {

  /**
   * 1. Approach 
   * String/Array
   * 
   * 2. Complexity 
   * - Time O(NumOfWords + MaxWidth * NumOfWords)
   * - Space O(MaxWidth)
   * 
   * @param words
   * @param maxWidth
   * @return
   */
  public List<String> fullJustify(String[] words, int maxWidth) {
    final List<String> text = new ArrayList<>();
    final int n = words.length;
    int start = 0, end = 0;
    while (end < n) {
      int length = 0;
      int wordCount = 0;
      while (end < n && length + words[end].length() + wordCount <= maxWidth) {
        length += words[end].length();
        wordCount++;
        end++;
      }
      int numOfSpaces = maxWidth - length;
      int minimumNumOfSpaces = wordCount == 1 ? numOfSpaces : numOfSpaces / (wordCount - 1);
      int extraNumOfSpaces = wordCount == 1 ? 0 : numOfSpaces % (wordCount - 1);
      final StringBuilder sb = new StringBuilder();
      if (end == n) {
        minimumNumOfSpaces = 1;
        extraNumOfSpaces = 0;
      }
      for (int i = start; i < end; i++) {
        sb.append(words[i]);
        if (i == end - 1) break;
        for (int k = 0; k < minimumNumOfSpaces; k++) sb.append(' ');
        if (extraNumOfSpaces > 0) {
          sb.append(' ');
          extraNumOfSpaces--;
        }
      }
      while (sb.length() < maxWidth) sb.append(' ');
      text.add(sb.toString());
      start = end;
    }
    return text;
  }
}
