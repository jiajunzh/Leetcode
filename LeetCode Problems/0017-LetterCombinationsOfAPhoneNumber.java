package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 1. Problem
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could 
 * represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any
 * letters.
 *
 * 2. Examples
 * Example 1
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 
 * Example 2
 * Input: digits = ""
 * Output: []
 * 
 * Example 3
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * 3. Constraints
 * 0 <= digits.length <= 4
 * digits[i] is a digit in the range ['2', '9'].
 */
public class LetterCombinationsOfAPhoneNumber {

  private static final Map<Character, List<Character>> LETTER_MAP = Map.of(
    '2', List.of('a', 'b', 'c'),
    '3', List.of('d', 'e', 'f'),
    '4', List.of('g', 'h', 'i'),
    '5', List.of('j', 'k', 'l'),
    '6', List.of('m', 'n', 'o'),
    '7', List.of('p', 'q', 'r', 's'),
    '8', List.of('t', 'u', 'v'),
    '9', List.of('w', 'x', 'y', 'z')
  );

  /**
   * 1. Approach 
   * Backtracking
   * 
   * 2. Complexity 
   * - Time O(4^N * N)
   * - Space O(N)
   * 
   * @param digits
   * @return
   */
  public List<String> letterCombinations(String digits) {
    final List<String> result = new ArrayList<>();
    if (digits.length() == 0) return result;
    letterCombinationsHelper(digits, 0, new StringBuilder(), result);
    return result;
  }

  private void letterCombinationsHelper(String digits, int index, StringBuilder curr, List<String> result) {
    if (digits.length() == index) {
      result.add(curr.toString());
      return;
    }
    final char digit = digits.charAt(index);
    for (final char letter : LETTER_MAP.get(digit)) {
      curr.append(letter);
      letterCombinationsHelper(digits, index + 1, curr, result);
      curr.deleteCharAt(curr.length() - 1);
    }
  }
}
