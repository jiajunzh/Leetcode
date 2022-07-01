package problem;

/**
 * 1. Problem 
 * You're given strings jewels representing the types of stones that are jewels, and stones representing the stones 
 * you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have 
 * are also jewels.
 *
 * Letters are case sensitive, so "a" is considered a different type of stone from "A".
 *
 * 2. Examples 
 * Example 1
 * Input: jewels = "aA", stones = "aAAbbbb"
 * Output: 3
 * 
 * Example 2
 * Input: jewels = "z", stones = "ZZ"
 * Output: 0
 *
 * 3. Constraints
 * 1 <= jewels.length, stones.length <= 50
 * jewels and stones consist of only English letters.
 * All the characters of jewels are unique.
 */
public class JewelsAndStones {

  /**
   * 1. Approach 
   * HashMap or Boolean Array
   * 
   * 2. Complexity 
   * - Time O(J + S)
   * - Space O(1)
   * 
   * @param jewels
   * @param stones
   * @return
   */
  public int numJewelsInStones(String jewels, String stones) {
    final boolean[] isJewel = new boolean[128];
    int numJewels = 0;
    for (final char c : jewels.toCharArray()) {
      isJewel[c] = true;
    }
    for (final char c : stones.toCharArray()) {
      if (isJewel[c]) {
        numJewels++;
      }
    }
    return numJewels;
  }
}
