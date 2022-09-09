package problem;

import java.util.Arrays;

/**
 * 1. Problem 
 * You are playing a game that contains multiple characters, and each of the characters has two main properties: attack
 * and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the
 * properties of the ith character in the game.
 *
 * A character is said to be weak if any other character has both attack and defense levels strictly greater than this 
 * character's attack and defense levels. More formally, a character i is said to be weak if there exists another
 * character j where attackj > attacki and defensej > defensei.
 *
 * Return the number of weak characters.
 *
 * 2. Examples
 * Example 1
 * Input: properties = [[5,5],[6,3],[3,6]]
 * Output: 0
 * Explanation: No character has strictly greater attack and defense than the other.
 * 
 * Example 2
 * Input: properties = [[2,2],[3,3]]
 * Output: 1
 * Explanation: The first character is weak because the second character has a strictly greater attack and defense.
 * 
 * Example 3
 * Input: properties = [[1,5],[10,4],[4,3]]
 * Output: 1
 * Explanation: The third character is weak because the second character has a strictly greater attack and defense.
 *
 * 3. Constraints
 * 2 <= properties.length <= 105
 * properties[i].length == 2
 * 1 <= attacki, defensei <= 105
 */
public class TheNumberOfWeakCharactersInTheGame {

  /**
   * 1. Problem 
   * Sorting 
   * 
   * 2. Complexity 
   * - Time O(NlogN)
   * - Space O(1)
   * 
   * @param properties
   * @return
   */
  public int numberOfWeakCharacters(int[][] properties) {
    Arrays.sort(properties, (a, b) -> (b[0] == a[0] ? a[1] - b[1] : b[0] - a[0]));
    int maxDefense = 0;
    int count = 0;
    for (int[] property : properties) {
      if (maxDefense > property[1]) {
        count++;
      }
      maxDefense = Math.max(maxDefense, property[1]);
    }
    return count;
  }

  /**
   * 1. Problem 
   * Greedy by keeping an array where A[i] represents the maximum possible defense for attack value i
   *
   * 2. Complexity 
   * - Time O(N + K)
   * - Space O(K)
   *
   * @param properties
   * @return
   */
  public int numberOfWeakCharacters2(int[][] properties) {
    int maxAttack = 0;
    for (int[] property : properties) {
      maxAttack = Math.max(property[0], maxAttack);
    }
    final int[] maxDefense = new int[maxAttack + 2];
    for (int[] property : properties) {
      maxDefense[property[0]] = Math.max(maxDefense[property[0]], property[1]);
    }
    for (int i = maxAttack - 1; i > 0; i--) {
      maxDefense[i] = Math.max(maxDefense[i], maxDefense[i + 1]);
    }
    int weakChars = 0;
    for (int[] property : properties) {
      if (maxDefense[property[0] + 1] > property[1]) {
        weakChars++;
      }
    }
    return weakChars;
  }
}
