package problem;

/**
 * 1. Problem 
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. 
 * The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if
 * the given words are sorted lexicographically in this alien language.
 *
 * 2. Example 
 * Example 1:
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * 
 * Example 2:
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * 
 * Example 3:
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 *
 * 3. Constraints:
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 */
public class VerifyingAnAlienDictionary {

  /**
   * 1. Approach
   * Compare adjacent words 
   * 
   * 2. Complexity 
   * - Time O(M) M is the total number of chars in words
   * - Space O(1)
   * 
   * 3. Improvement 
   * - The orderMap array could be reduced to only hold 26 English letters. e.g. char - 'a' 
   * 
   * @param words
   * @param order
   * @return
   */
  public boolean isAlienSorted(String[] words, String order) {
    final int[] orderMap = new int[256];
    orderMap['-'] = -1;

    for (int i = 0; i < order.length(); i++) {
      orderMap[order.charAt(i)] = i;
    }

    for (int i = 1; i < words.length; i++) {
      if (!isInOrder(words[i - 1], words[i], orderMap)) {
        return false;
      }
    }

    return true;
  }

  private boolean isInOrder(final String word1, final String word2, final int[] orderMap) {
    int i = 0;

    while (i < word1.length() || i < word2.length()) {
      char c1 = i < word1.length() ? word1.charAt(i) : '-';
      char c2 = i < word2.length() ? word2.charAt(i) : '-';

      if (orderMap[c1] < orderMap[c2]) {
        return true;
      } else if (orderMap[c1] == orderMap[c2]) {
        i++;
      } else {
        return false;
      }
    }

    return true;
  }
}
