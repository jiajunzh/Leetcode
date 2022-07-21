package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * You are playing a Flip Game with your friend.
 *
 * You are given a string currentState that contains only '+' and '-'. You and your friend take turns to flip two 
 * consecutive "++" into "--". The game ends when a person can no longer make a move, and therefore the other person 
 * will be the winner.
 *
 * Return all possible states of the string currentState after one valid move. You may return the answer in any order.
 * If there is no valid move, return an empty list [].
 *
 * 2. Examples
 * Example 1
 * Input: currentState = "++++"
 * Output: ["--++","+--+","++--"]
 * 
 * Example 2
 * Input: currentState = "+"
 * Output: []
 *
 * 3. Constraints
 * 1 <= currentState.length <= 500
 * currentState[i] is either '+' or '-'.
 */
public class FlipGame {

  /**
   * 1. Approach 
   * Array + String 
   * 
   * 2. Complexity 
   * - Time O(N * N)
   * - Space O(N) if not consider result space
   * 
   * @param currentState
   * @return
   */
  public List<String> generatePossibleNextMoves(String currentState) {
    final List<String> result = new ArrayList<>();
    final int n = currentState.length();
    final char[] cArray = currentState.toCharArray();
    for (int i = 0; i < n - 1; i++) {
      if (cArray[i] == '+' && cArray[i + 1] == '+') {
        cArray[i] = '-';
        cArray[i + 1] = '-';
        result.add(new String(cArray));
        cArray[i] = '+';
        cArray[i + 1] = '+';
      }
    }
    return result;
  }
}
