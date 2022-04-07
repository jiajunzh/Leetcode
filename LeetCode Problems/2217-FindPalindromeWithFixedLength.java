package problem;

/**
 * 1. Problem
 * Given an integer array queries and a positive integer intLength, return an array answer where answer[i] is either 
 * the queries[i]th smallest positive palindrome of length intLength or -1 if no such palindrome exists.
 *
 * A palindrome is a number that reads the same backwards and forwards. Palindromes cannot have leading zeros.
 *
 * 2. Example
 * Example 1:
 * Input: queries = [1,2,3,4,5,90], intLength = 3
 * Output: [101,111,121,131,141,999]
 * Explanation:
 * The first few palindromes of length 3 are:
 * 101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 202, ...
 * The 90th palindrome of length 3 is 999.
 * 
 * Example 2:
 * Input: queries = [2,4,6], intLength = 4
 * Output: [1111,1331,1551]
 * Explanation:
 * The first six palindromes of length 4 are:
 * 1001, 1111, 1221, 1331, 1441, and 1551.
 *
 * 3. Constraints
 * 1 <= queries.length <= 5 * 104
 * 1 <= queries[i] <= 109
 * 1 <= intLength <= 15
 */
public class FindPalindromeWithFixedLength {

  /**
   * 1. Approach 
   * StringBuilder. One thing to notice in this problem is the relationship between the left half of the palindrome 
   * and the query. The left half number of the palindrome could be calculated by the start number and query. The 
   * start number with a specific length is pow(10, (length - 1) / 2) (1st palindrome number).
   *
   * Once the left half is sorted out, the whole string is just the left half + reversal of left half
   * 
   * 2. Complexity 
   * - Time O(N * L)
   * - Space O(L)
   * 
   * @param queries
   * @param intLength
   * @return
   */
  public long[] kthPalindrome1(int[] queries, int intLength) {
    final long[] answers = new long[queries.length];
    long startNum = (int) Math.pow(10, (intLength - 1) / 2) - 1;

    for (int i = 0; i < answers.length; i++) {
      answers[i] = getKthPalindrome1(queries[i], intLength, startNum);
    }

    return answers;
  }

  private Long getKthPalindrome1(int query, int intLength, long startNum) {
    long halfNumber = startNum + query;
    final StringBuilder sb = new StringBuilder(String.valueOf(halfNumber));
    final StringBuilder tmp = new StringBuilder(sb);
    sb.append(tmp.reverse().substring(intLength % 2, tmp.length()));

    if (sb.length() > intLength) {
      return -1L;
    }

    return Long.valueOf(sb.toString());
  }

  /**
   * 1. Approach 
   * The algorithm in this approach is the same as the one above. Only difference is that it uses math calculation
   * instead of String.
   * 
   * 2. Complexity 
   * - Time O(N * L)
   * - Space O(1)
   * 
   * @param queries
   * @param intLength
   * @return
   */
  public long[] kthPalindrome2(int[] queries, int intLength) {
    final long[] answers = new long[queries.length];
    long startNum = (long) Math.pow(10, (intLength - 1) / 2) - 1;
    long endNum = (long) Math.pow(10, (intLength + 1) / 2);

    for (int i = 0; i < answers.length; i++) {
      if (startNum + queries[i] >= endNum) {
        answers[i] = -1L;
      } else {
        answers[i] = getKthPalindrome2(queries[i], intLength, startNum);
      }
    }

    return answers;
  }

  private Long getKthPalindrome2(int query, int intLength, long startNum) {
    long halfNumber = startNum + query;
    long tmp = halfNumber;
    int halfCnt = intLength / 2;
    long answer = 0;

    if (intLength % 2 == 1) {
      tmp /= 10;
    }

    int i = 1;
    while (tmp > 0) {
      answer = answer * 10 + tmp % 10;
      i++;
      tmp = tmp / 10;
    }

    return answer + halfNumber * (long) Math.pow(10, halfCnt);
  }
}
