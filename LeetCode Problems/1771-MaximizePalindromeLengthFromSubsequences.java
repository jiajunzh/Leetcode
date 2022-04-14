package problem;

/**
 * 1. Problem 
 * You are given two strings, word1 and word2. You want to construct a string in the following manner:
 *
 * Choose some non-empty subsequence subsequence1 from word1.
 * Choose some non-empty subsequence subsequence2 from word2.
 * Concatenate the subsequences: subsequence1 + subsequence2, to make the string.
 * Return the length of the longest palindrome that can be constructed in the described manner. If no palindromes can 
 * be constructed, return 0.
 *
 * A subsequence of a string s is a string that can be made by deleting some (possibly none) characters from s without 
 * changing the order of the remaining characters.
 *
 * A palindrome is a string that reads the same forward as well as backward.
 *
 * 2. Examples
 * Example 1
 * Input: word1 = "cacb", word2 = "cbba"
 * Output: 5
 * Explanation: Choose "ab" from word1 and "cba" from word2 to make "abcba", which is a palindrome.
 *
 * Example 2
 * Input: word1 = "ab", word2 = "ab"
 * Output: 3
 * Explanation: Choose "ab" from word1 and "a" from word2 to make "aba", which is a palindrome.
 * 
 * Example 3
 * Input: word1 = "aa", word2 = "bb"
 * Output: 0
 * Explanation: You cannot construct a palindrome from the described method, so return 0.
 *
 * 3. Constraints
 * 1 <= word1.length, word2.length <= 1000
 * word1 and word2 consist of lowercase English letters.
 */
public class MaximizePalindromeLengthFromSubsequences {

  /**
   * 1. Approach 
   * Dynamic Programming. This problem is a slight variation from problem 0516 Longest Palindromic Subsequence. It asks
   * for the longest palindromic subsequence after concatenating two words, it could be simplified to finding the 
   * longest palindromic subsequence within word1+word2 under the condition that the subsequence starts before 
   * word1.length() but end after word1.length().
   * 
   * 2. Complexity 
   * - Time O(N^2)
   * - Space O(N^2)
   * 
   * 3. Mistakes & Improvements 
   * - The first mistake is to think that the first half of the palindrome must be within word1 while the second half
   *   must be within word2. For example, if there is a palindrome aaaaaaaa, there must be four in word1 and four in 
   *   word2. However, it is totally wrong as it could totally be that word1 = aaaaaaa and word2 = a.
   * - The condition to check whether the palindrome starts before word 1 but end after word 1 should be checked 
   *   when encountering the case where the start char is equal to end char. Because for Ai Ai+1 .... Aj-1 Aj, if Ai is 
   *   not equal to Aj, the longest palindrome found in dp[i][j] could be anything between Ai+1 ... Aj-1. The check 
   *   might pass at [i, j] but does not necessarily will pass if the palindrome starts e.g. at i + 1 and end at j - 1.
   *   For example, if we have acd|bbb. dp[2][5] = 3, but the palindrome found here is bbb, even though [2,5] could pass
   *   the condition, but the actual palindrome bbb [3,5] won't.
   * 
   * @param word1
   * @param word2
   * @return
   */
  public int longestPalindrome(String word1, String word2) {
    final String word = word1 + word2;
    final char[] wordChars = word.toCharArray();
    final int n = word.length();
    final int boundary = word1.length();
    final int[][] dp = new int[n][n];
    int ans = 0;

    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        if (wordChars[i] == wordChars[j]) {
          dp[i][j] = i == j ? 1 : dp[i + 1][j - 1] + 2;
          if (i < boundary && j >= boundary) {
            ans = Math.max(dp[i][j], ans);
          }
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }

    return ans;
  }
}
