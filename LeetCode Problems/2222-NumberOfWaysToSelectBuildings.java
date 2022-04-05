package problem;

/**
 * 1. Problem 
 * You are given a 0-indexed binary string s which represents the types of buildings along a street where:
 *
 * s[i] = '0' denotes that the ith building is an office and
 * s[i] = '1' denotes that the ith building is a restaurant.
 * As a city official, you would like to select 3 buildings for random inspection. However, to ensure variety, no two
 * consecutive buildings out of the selected buildings can be of the same type.
 *
 * For example, given s = "001101", we cannot select the 1st, 3rd, and 5th buildings as that would form "011" which is 
 * not allowed due to having two consecutive buildings of the same type.
 * 
 * Return the number of valid ways to select 3 buildings.
 *
 * 2. Example
 * Example 1:
 * Input: s = "001101"
 * Output: 6
 * Explanation: 
 * The following sets of indices selected are valid:
 * - [0,2,4] from "001101" forms "010"
 * - [0,3,4] from "001101" forms "010"
 * - [1,2,4] from "001101" forms "010"
 * - [1,3,4] from "001101" forms "010"
 * - [2,4,5] from "001101" forms "101"
 * - [3,4,5] from "001101" forms "101"
 * No other selection is valid. Thus, there are 6 total ways.
 * 
 * Example 2:
 * Input: s = "11100"
 * Output: 0
 * Explanation: It can be shown that there are no valid selections.
 * 
 * 3. Constraints:
 * 3 <= s.length <= 105
 * s[i] is either '0' or '1'.
 */
public class NumberOfWaysToSelectBuildings {

  /**
   * 1. Approach 
   * Memoization. One important thing to notice in this problem is that having two consecutive buildings of the same 
   * type is not allowed. So generally only two combination is allowed which are 101 and 010.
   * 
   * It could be broken down into smaller question as 
   * Cnt101[i] = Cnt10[i - 1] when Si = 1
   * Cnt010[i] = Cnt01[i - 1] when Si = 0
   * Cnt10[i] = Cnt1[i - 1] when Si = 0
   * Cnt01[i] = Cnt0[i - 1] when Si = 1
   *
   * 2. Complexity
   * Time O(N)
   * Space O(1)
   * 
   * @param s
   * @return
   */
  public long numberOfWays(String s) {
    long cnt0 = 0, cnt1 = 0, cnt10 = 0, cnt01 = 0, cnt101 = 0, cnt010 = 0;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '1') {
        cnt101 += cnt10;
        cnt01 += cnt0;
        cnt1++;
      } else {
        cnt010 += cnt01;
        cnt10 += cnt1;
        cnt0++;
      }
    }

    return cnt010 + cnt101;
  }
}
