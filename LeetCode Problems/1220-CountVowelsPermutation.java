package problem;

/**
 * 1. Problem 
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
 *
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'i'.
 * Each vowel 'i' may not be followed by another 'i'.
 * Each vowel 'o' may only be followed by an 'i' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * 2. Examples
 * Example 1
 * Input: n = 1
 * Output: 5
 * Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
 * 
 * Example 2
 * Input: n = 2
 * Output: 10
 * Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
 * 
 * Example 3
 * Input: n = 5
 * Output: 68
 * 
 * 3. Constraints
 * 1 <= n <= 2 * 10^4
 */
public class CountVowelsPermutation {
  
  private static final int MOD = 1000000007;
  
  /**
   * 1. Approach 
   * Dynamic Programming.
   * 
   * Define dp[i][j] as the count of string permutation ending at char j with length i.
   * - a: dp[i][0] = dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4]
   * - e: dp[i][1] = dp[i - 1][0] + dp[i - 1][2]
   * - i: dp[i][2] = dp[i - 1][1] + dp[i - 1][3] 
   * - o: dp[i][3] = dp[i - 1][2] 
   * - u: dp[i][4] = dp[i - 1][2] + dp[i - 1][3]
   * 
   * @param n
   * @return
   */
  public int countVowelPermutation(int n) {
    long aCount = 1, eCount = 1, iCount = 1, oCount = 1, uCount = 1;
    
    for (int i = 2; i <= n; i++) {
      long aCountTmp = (eCount + iCount + uCount) % MOD;
      long eCountTmp = (aCount + iCount) % MOD;
      long iCountTmp = (eCount + oCount) % MOD;
      long oCountTmp = iCount;
      long uCountTmp = (iCount + oCount) % MOD;
      aCount = aCountTmp;
      eCount = eCountTmp;
      iCount = iCountTmp;
      oCount = oCountTmp;
      uCount = uCountTmp;
    }
    
    long count = (aCount + eCount + iCount + oCount + uCount) % MOD;
    return (int) count;
  }
}
