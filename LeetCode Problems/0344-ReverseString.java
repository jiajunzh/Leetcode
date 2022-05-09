package problem;

/**
 * 1. Problem
 * Write a function that reverses a string. The input string is given as an array of characters s.
 *
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 * 2. Examples
 * Example 1
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * 
 * Example 2
 * Input: s = ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 *
 * 3. Constraints
 * 1 <= s.length <= 105
 * s[i] is a printable ascii character.
 */
public class ReverseString {

  /**
   * 1. Approach
   * Two Pointers
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param s
   */
  public void reverseString(char[] s) {
    int i = 0, j = s.length - 1;

    while (i < j) {
      swap(s, i, j);
      i++;
      j--;
    }
  }

  private void swap(char[] s, int i, int j) {
    char tmp = s[i];
    s[i] = s[j];
    s[j] = tmp;
  }
}
