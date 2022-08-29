package problem;

/**
 * 1. Problem 
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
 *
 * Each letter in magazine can only be used once in ransomNote.
 *
 * 2. Examples
 * Example 1
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * 
 * Example 2
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * 
 * Example 3
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *
 * 3. Constraints
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote and magazine consist of lowercase English letters.
 */
public class RansomNote {

  /**
   * 1. Approach 
   * CharMap
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param ransomNote
   * @param magazine
   * @return
   */
  public boolean canConstruct(String ransomNote, String magazine) {
    final int[] count = new int[26];
    for (final char c : magazine.toCharArray()) {
      count[c - 'a']++;
    }
    for (final char c : ransomNote.toCharArray()) {
      if (count[c - 'a'] == 0) return false;
      count[c - 'a']--;
    }
    return true;
  }
}
