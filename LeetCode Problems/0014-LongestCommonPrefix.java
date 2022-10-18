package problem;

/**
 * 1. Problem 
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * 2. Examples
 * Example 1
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * 
 * Example 2
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * 3. Constraints
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters.
 */
public class LongestCommonPrefix {

  /**
   * 1. Approach 
   * StringBuilder + Vertical Scanning 
   * 
   * 2. Complexity 
   * - Time O(N * L)
   * - Space O(N)
   * 
   * @param strs
   * @return
   */
  public String longestCommonPrefix(String[] strs) {
    final StringBuilder sb = new StringBuilder();
    int i = 0;
    while (true) {
      for (String str : strs) {
        if (str.length() == i) {
          return sb.toString();
        }
        if (strs[0].charAt(i) != str.charAt(i)) {
          return sb.toString();
        }
      }
      sb.append(strs[0].charAt(i));
      i++;
    }
  }
}
