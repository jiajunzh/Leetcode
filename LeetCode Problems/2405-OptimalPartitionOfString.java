package problem;

/**
 * 1. Problem 
 * Given a string s, partition the string into one or more substrings such that the characters in each substring are 
 * unique. That is, no letter appears in a single substring more than once.
 *
 * Return the minimum number of substrings in such a partition.
 *
 * Note that each character should belong to exactly one substring in a partition.
 * 
 * 2. Examples
 * Example 1
 * Input: s = "abacaba"
 * Output: 4
 * Explanation:
 * Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
 * It can be shown that 4 is the minimum number of substrings needed.
 * 
 * Example 2
 * Input: s = "ssssss"
 * Output: 6
 * Explanation:
 * The only valid partition is ("s","s","s","s","s","s").
 *
 * 3. Constraints
 * 1 <= s.length <= 105
 * s consists of only English lowercase letters.
 */
public class OptimalPartitionOfString {

  /**
   * 1. Approach 
   * Boolean Array
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param s
   * @return
   */
  public int partitionString(String s) {
    int count = 1;
    boolean[] exist = new boolean[26];
    for (char c : s.toCharArray()) {
      if (exist[c - 'a']) {
        exist = new boolean[26];
        count++;
      }
      exist[c - 'a'] = true;
    }
    return count;
  }
}
