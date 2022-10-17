package problem;

/**
 * 1. Problem 
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if 
 * needle is not part of haystack.
 *
 * 2. Examples
 * Example 1
 * Input: haystack = "sadbutsad", needle = "sad"
 * Output: 0
 * Explanation: "sad" occurs at index 0 and 6.
 * The first occurrence is at index 0, so we return 0.
 * 
 * Example 2
 * Input: haystack = "leetcode", needle = "leeto"
 * Output: -1
 * Explanation: "leeto" did not occur in "leetcode", so we return -1.
 *
 * 3. Constraints
 * 1 <= haystack.length, needle.length <= 104
 * haystack and needle consist of only lowercase English characters.
 */
public class FindTheIndexOfTheFirstOccurrenceInAString {

  /**
   * 1. Approach 
   * Sliding Window
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param haystack
   * @param needle
   * @return
   */
  public int strStr(String haystack, String needle) {
    int start = 0;
    while (start < haystack.length()) {
      if (start + needle.length() > haystack.length()) break;
      int end = start, needleIndex = 0;
      while (needleIndex < needle.length() && haystack.charAt(end) == needle.charAt(needleIndex)) {
        needleIndex++;
        end++;
      }
      if (needleIndex == needle.length()) return start;
      start++;
    }
    return -1;
  }
}
