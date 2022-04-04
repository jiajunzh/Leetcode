package problem;

/**
 * 1. Problem 
 * Given a string s, return the longest palindromic substring in s.
 * 
 * 2. Example 
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * 
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * 3. Constraints:
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class LongestPalindromicSubstring {

  /**
   * 1. Approach 
   * Dynamic Programming. One straightforward rule we could think of is if Si = Sj and substring from i + 1 to j - 1 is 
   * considered as palindrome, then substring from i to j is also palindrome.
   * 
   * Define P(i, j) = true if Si = Sj and P(i + 1, j - 1) or false otherwise.
   * Base Case: 
   * - P(i, j) = true if i == j 
   * - P(i, j) = true if i == j - 1 && Si = Sj 
   *
   * 2. Complexity 
   * Time O(N^2)
   * Space O(1)   
   *
   * 3. Improvement & Mistakes
   * 1) Don't ignore the 2rd base case "P(i, j) = true if i == j - 1 && Si = Sj".
   *   => when j == i + 1, don't need to check dp[i + 1][j - 1].
   * 2) Start from i + 1 for the 2rd for loop as we don't care about case where i > j.
   */
  public String longestPalindromeDp(String s) {
    final int length = s.length();
    final boolean[][] dp = new boolean[length][length];
    int start = 0, end = 0;

    for (int i = length - 1; i >= 0; i--) {
      dp[i][i] = true;
      for (int j = i + 1; j < length; j++) {
        dp[i][j] = (dp[i + 1][j - 1] || j == i + 1) && s.charAt(i) == s.charAt(j);

        if (dp[i][j]) {
          if (end - start < j - i) {
            start = i;
            end = j;
          }
        }
      }
    }

    return s.substring(start, end + 1);
  }
  
  /**
   * 1. Approach 
   * Expand from center. Iterate through each character in the string and consider it as the center of the substring and
   * expand the string from the center till the longest possible length.
   * 
   * 2. Complexity 
   * Time O(N^2)
   * - Iterate string N times and for each char, there are 2N - 1 potential substring (i itself as center and i with 
   *   i + 1 as center). 
   * Space O(1)   
   * 
   * 3. Improvement & Mistakes
   * 1) longestLength could be calculated by start and end indice, no need for extra variable
   * 2) Check index boundary when expanding the substring
   * 3) The length returned should be right - left - 1 instead of right - left + 1 (left minus 1 and right plus 1 in the
   * last while loop).
   */
  public String longestPalindromeExpandFromCenter(String s) {
    int start = 0, end = 0;

    for (int i = 0; i < s.length(); i++) {
      int len1 = expandFromCenter(s, i, i);
      int len2 = expandFromCenter(s, i, i + 1);
      int len = Math.max(len1, len2);

      if (end - start < len) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
      }
    }

    return s.substring(start, end + 1);
  }

  private int expandFromCenter(String s, int left, int right) {
    while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
      left--;
      right++;
    }

    return right - left - 1;
  }
}
