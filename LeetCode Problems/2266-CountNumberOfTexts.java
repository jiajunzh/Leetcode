package problem;

/**
 * 1. Problem 
 * Alice is texting Bob using her phone. The mapping of digits to letters is shown in the figure below.
 * 
 * In order to add a letter, Alice has to press the key of the corresponding digit i times, where i is the position
 * of the letter in the key.
 *
 * For example, to add the letter 's', Alice has to press '7' four times. Similarly, to add the letter 'k', Alice 
 * has to press '5' twice.
 * Note that the digits '0' and '1' do not map to any letters, so Alice does not use them.
 * However, due to an error in transmission, Bob did not receive Alice's text message but received a string of 
 * pressed keys instead.
 *
 * For example, when Alice sent the message "bob", Bob received the string "2266622".
 * Given a string pressedKeys representing the string received by Bob, return the total number of possible text 
 * messages Alice could have sent.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * 2. Examples 
 * Example 1
 * Input: pressedKeys = "22233"
 * Output: 8
 * Explanation:
 * The possible text messages Alice could have sent are:
 * "aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae", and "ce".
 * Since there are 8 possible messages, we return 8.
 * 
 * Example 2
 * Input: pressedKeys = "222222222222222222222222222222222222"
 * Output: 82876089
 * Explanation:
 * There are 2082876103 possible text messages Alice could have sent.
 * Since we need to return the answer modulo 109 + 7, we return 2082876103 % (109 + 7) = 82876089.
 *
 * 3. Constraints
 * 1 <= pressedKeys.length <= 105
 * pressedKeys only consists of digits from '2' - '9'.
 */
public class CountNumberOfTexts {
  
  private static final int[] CHAR_NUM = new int[]{0, 0, 3, 3, 3, 3, 3, 4, 3, 4};

  /**
   * 1. Approach 
   * DP 
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param pressedKeys
   * @return
   */
  public int countTexts(String pressedKeys) {
    final int n = pressedKeys.length();
    final int[] dp = new int[n + 1];
    dp[0] = 1;
    for (int i = 1; i <= n; i++) {
      final char currDigit = pressedKeys.charAt(i - 1);
      for (int k = 0; k < CHAR_NUM[currDigit - '0']; k++) {
        if (i - k < 1 || pressedKeys.charAt(i - 1 - k) != currDigit) {
          break;
        }
        dp[i] = (dp[i] + dp[i - k - 1]) % 1_000_000_007;
      }
    }
    return dp[n];
  }
}
