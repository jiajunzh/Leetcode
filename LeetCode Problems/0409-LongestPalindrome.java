package problem;

/**
 * 1. Problem 
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that 
 * can be built with those letters.
 *
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 *
 * 2. Examples
 * Example 1
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation:
 * One longest palindrome that can be built is "dccaccd", whose length is 7.
 * 
 * Example 2
 * Input: s = "a"
 * Output: 1
 * 
 * Example 3
 * Input: s = "bb"
 * Output: 2
 *
 * 3. Constraints
 * 1 <= s.length <= 2000
 * s consists of lowercase and/or uppercase English letters only.
 */
public class LongestPalindrome {

  /**
   * 1. Approach 
   * Greedy + Array Map.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * 3. Mistakes
   * - In ASCII coding, A comes before a instead of the other way around.
   * - ASCII contains 128 elements using 7 bits => 2^7 
   * 
   * @param s
   * @return
   */
  public int longestPalindrome(String s) {
    int[] charCnt = new int[64];
    for (char c : s.toCharArray()) {
      charCnt[c - 'A']++;
    }
    int singleLetterCenter = 0;
    int result = 0;
    for (int count : charCnt) {
      if (count % 2 == 1) {
        singleLetterCenter = 1;
        count--;
      }
      result += count;
    }
    return result + singleLetterCenter;
  }
}
