package problem;

/**
 * 1. Problem 
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 * 2. Examples
 * Example 1:
 * Input: s = "aba"
 * Output: true
 * 
 * Example 2:
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 * 
 * Example 3:
 * Input: s = "abc"
 * Output: false
 * 
 * 3. Constraints
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 */
public class ValidPalindromeII {
  
  /**
   * 1. Approach
   * Two pointers 
   * if Si == Sj => validPalindrome(i+1 .... j-1) with remaining deletion 1 
   * if Si != Sj => validPalindrome(i+1 .... j) || validPalindrome(i .... j - 1) with remaining deletion 0.
   *  
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * 3. Improvement
   * - Do not use substring() to get substring, just specifying the start and end index should be enough.
   *  
   * @param s
   * @return
   */
  public boolean validPalindrome(String s) {
    int i = 0, j = s.length() - 1;

    while (i <= j) {
      if (s.charAt(i) != s.charAt(j)) {
        return checkPalindrome(s, i + 1, j) || checkPalindrome(s, i, j - 1);
      }

      i++;
      j--;
    }

    return true;
  }

  private boolean checkPalindrome(String s, int i, int j) {
    while (i <= j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }

      i++;
      j--;
    }

    return true;
  }
}
