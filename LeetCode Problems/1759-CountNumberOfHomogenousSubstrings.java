package problem;

/**
 * 1. Problem 
 * Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it
 * modulo 109 + 7.
 *
 * A string is homogenous if all the characters of the string are the same.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 * 2. Examples
 * Example 1
 * Input: s = "abbcccaa"
 * Output: 13
 * Explanation: The homogenous substrings are listed as below:
 * "a"   appears 3 times.
 * "aa"  appears 1 time.
 * "b"   appears 2 times.
 * "bb"  appears 1 time.
 * "c"   appears 3 times.
 * "cc"  appears 2 times.
 * "ccc" appears 1 time.
 * 3 + 1 + 2 + 1 + 3 + 2 + 1 = 13.
 * 
 * Example 2
 * Input: s = "xy"
 * Output: 2
 * Explanation: The homogenous substrings are "x" and "y".
 * 
 * Example 3
 * Input: s = "zzzzz"
 * Output: 15
 *
 * 3. Constraints
 * 1 <= s.length <= 105
 * s consists of lowercase letters.
 */
public class CountNumberOfHomogenousSubstrings {
  
  private static final int MOD = 1_000_000_007;

  /**
   * 1. Approach
   * Math problem. A pattern to notice is for a longest homo string with n length, it will have n, n - 1,  ...  2, 1 
   * different combinations of homo string with length less or equal to n.
   * 
   * If string[n] is a homo string, then cnt[n] = cnt[n - 1] + n.
   * For example,
   * "n" => 1
   * "nn" => "n" + "n" => 1 + 2 ("n" and "nn")
   * "nnn" => "nn" + "n" => 1 + 2 + 3
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * 3. Improvement
   * - Integer Overflow. The maximum length of string is 10^5 which means the maximum possible count will be 
   *   5,000,050,000 (~ 5 x 10^9) while the maximum value of Integer is 2,147,483,647 (~ 2 x 10^9).
   * - Use long instead of int when calculating the count as you don't need to calculate module each iteration. It will 
   *   be faster in time.
   * - (int) cnt % MOD will convert cnt to integer first and then calculate the module.
   * 
   * @param s
   * @return
   */
  public int countHomogenous(String s) {
    long cnt = 1;
    long found = 1;

    for (int i = 1; i < s.length(); i++) {
      found = 1 + (s.charAt(i) == s.charAt(i - 1) ? found : 0);
      cnt += found;
    }

    return (int) (cnt % MOD);
  }
}
