package problem;

/**
 * 1. Problem 
 * An attendance record for a student can be represented as a string where each character signifies whether the student
 * was absent, late, or present on that day. The record only contains the following three characters:
 *
 * 'A': Absent.
 * 'L': Late.
 * 'P': Present.
 * Any student is eligible for an attendance award if they meet both of the following criteria:
 *
 * The student was absent ('A') for strictly fewer than 2 days total.
 * The student was never late ('L') for 3 or more consecutive days.
 * Given an integer n, return the number of possible attendance records of length n that make a student eligible for 
 * an attendance award. The answer may be very large, so return it modulo 109 + 7.
 *
 * 2. Examples
 * Example 1
 * Input: n = 2
 * Output: 8
 * Explanation: There are 8 records with length 2 that are eligible for an award:
 * "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
 * 
 * Example 2
 * Input: n = 1
 * Output: 3
 * 
 * Example 3
 * Input: n = 10101
 * Output: 183236316
 *
 * 3. Constraints
 * 1 <= n <= 105
 */
public class StudentAttendanceRecordII {
  
  private static final int MOD = 1_000_000_007;

  /**
   * 1. Problem 
   * Dynamic Programming 
   * 
   * Complexity Reduction 1: Constraints on A record - Only one or zero allowed
   * Based on this, we could separate this problem into two case:
   * - There is zero A in the whole record: [....]
   * - There is one A in the whole record: [....]A[....]
   * Only P or L is allowed within [....]. Now we only need to know number of possible records consisting of only L and P
   * 
   * Complexity Reduction 2: Dynamic Programming 
   * Define dp[i] as the number of possible records consisting of only L and P when there are i records in total.
   * 
   * For n records, we have two cases:
   * - [.. n - 1 ..]P 
   * - [.. n - 1 .. ]L
   * Apparently, all from first case satisfies the constraints => dp[n] += dp[n - 1]
   * For case 2, we need to eliminate the case where we have three consecutive Ls => [.. n - 4] PLL|L => dp[n] += dp[n - 1] - dp[n - 4]
   * Combining all two cases above, dp[n] = 2dp[n - 1] - dp[n - 4]
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param n
   * @return
   */
  public int checkRecord(int n) {
    int size = Math.max(n, 3);
    final long[] dp = new long[size + 1];
    dp[0] = 1;
    dp[1] = 2;
    dp[2] = 4;
    dp[3] = 7;
    for (int i = 4; i <= n; i++) {
      dp[i] = (2 * dp[i - 1] + MOD - dp[i - 4]) % MOD;
    }
    long count = dp[n];
    for (int i = 0; i < n; i++) {
      count = (count + dp[i] * dp[n - 1 - i]) % MOD;
    }
    return (int) count;
  }

  /**
   * 1. Approach 
   * Dynamic Programming + State Machine
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param n
   * @return
   */
  public int checkRecord2(int n) {
    long a0l0 = 1, a0l1 = 1, a0l2 = 0, a1l0 = 1, a1l1 = 0, a1l2 = 0;
    for (int i = 2; i <= n; i++) {
      long a0l0New = (a0l0 + a0l1 + a0l2) % MOD;
      long a0l1New = a0l0;
      long a0l2New = a0l1;
      long a1l0New = (a0l0 + a0l1 + a0l2 + a1l0 + a1l1 + a1l2) % MOD;
      long a1l1New = a1l0;
      long a1l2New = a1l1;
      a0l0 = a0l0New;
      a0l1 = a0l1New;
      a0l2 = a0l2New;
      a1l0 = a1l0New;
      a1l1 = a1l1New;
      a1l2 = a1l2New;
    }
    return (int) ((a0l0 + a0l1 + a0l2 + a1l0 + a1l1 + a1l2) % MOD);
  }
}
