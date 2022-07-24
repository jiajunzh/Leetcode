package problem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1. Problem 
 * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing 
 * zero or more conversions.
 *
 * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
 *
 * Return true if and only if you can transform str1 into str2.
 *
 * 2. Examples
 * Example 1
 * Input: str1 = "aabcc", str2 = "ccdee"
 * Output: true
 * Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
 * 
 * Example 2
 * Input: str1 = "leetcode", str2 = "codeleet"
 * Output: false
 * Explanation: There is no way to transform str1 to str2.
 *
 * 3. Constraints
 * 1 <= str1.length == str2.length <= 104
 * str1 and str2 contain only lowercase English letters.
 */
public class StringTransformsIntoAnotherString {

  /**
   * 1. Approach 
   * Case Analysis below.
   * 
   * Case 1: One to One Mapping 
   * - Example: abcd -> pqrs
   * - Always convertible 
   * 
   * Case 2: One to Many Mapping 
   * - Example: abcc -> pqrs
   * - Always impossible to convert
   * 
   * Case 3: Linked List 
   * - Example: abcde -> bcdef => It forms a linked list a -> b -> c -> d -> e -> f
   * - If will not generate correct answer if you first map a to b because then we will have two bs in str1 mapped to 
   *   two different chars in str2
   * - This is convertible from last to start (i.e. map e to f first and then d -> e and so on)
   * 
   * Case 4: Cyclic Linked List
   * - Example: abc -> cab
   * - It will not generate correct answer either you map from start to end or end to start
   * - However, we could introduce a new letter z where you could map b -> z then, you could abc -> azc -> azb -> czb -> cab
   * 
   * Case 5: Multiple Cyclic Linked Lists
   * - Example: abcdef -> cabefd
   * - It is convertible by using single characters to break both loops
   * 
   * Case 6: Cyclic Linked List with 26 Letters
   * - Example: abcdefghijklmnopqrstuvwxyz -> bcdefghijklmnopqrstuvwxyza
   * - Impossible
   * 
   * Case 7: Linked List with 26 Letters and One Loop
   * - Example: abcdefghijklmnopqrstuvwxyz -> bcdefghijklmnopqrstuvwxyzz
   * - Possible by converting y -> z first and then x -> y and so on because str2 only has 25 letters
   * 
   * Condition:
   * - No char in str1 is mapped to multiple chars in str2
   * - The number of unique chars in str2 should be less than 26 OR str1 is equals to str2
   * 
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param str1
   * @param str2
   * @return
   */
  public boolean canConvert(String str1, String str2) {
    if (str1.equals(str2)) return true;
    final Map<Character, Character> conversionMap = new HashMap<>();
    final Set<Character> charSet = new HashSet<>();
    final int n = str1.length();
    for (int i = 0; i < n; i++) {
      final char c1 = str1.charAt(i);
      final char c2 = str2.charAt(i);
      charSet.add(c2);
      if (!conversionMap.containsKey(c1)) {
        conversionMap.put(c1, c2);
      }
      if (c2 != conversionMap.get(c1)) {
        return false;
      }
    }
    return charSet.size() < 26;
  }
}
