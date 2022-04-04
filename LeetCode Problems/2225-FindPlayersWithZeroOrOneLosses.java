package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1. Problem
 * You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri
 * defeated player loseri in a match.
 *
 * Return a list answer of size 2 where:
 *
 * answer[0] is a list of all players that have not lost any matches.
 * answer[1] is a list of all players that have lost exactly one match.
 * The values in the two lists should be returned in increasing order.
 *
 * Note:
 *
 * You should only consider the players that have played at least one match.
 * The testcases will be generated such that no two matches will have the same outcome.
 *
 * 2. Examples
 * Example 1:
 * Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
 * Output: [[1,2,10],[4,5,7,8]]
 * Explanation:
 * Players 1, 2, and 10 have not lost any matches.
 * Players 4, 5, 7, and 8 each have lost one match.
 * Players 3, 6, and 9 each have lost two matches.
 * Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
 * 
 * Example 2:
 * Input: matches = [[2,3],[1,3],[5,4],[6,4]]
 * Output: [[1,2,5,6],[]]
 * Explanation:
 * Players 1, 2, 5, and 6 have not lost any matches.
 * Players 3 and 4 each have lost two matches.
 * Thus, answer[0] = [1,2,5,6] and answer[1] = [].
 *
 * 3. Constraints
 * 1 <= matches.length <= 105
 * matches[i].length == 2
 * 1 <= winneri, loseri <= 105
 * winneri != loseri
 * All matches[i] are unique.
 */
public class FindPlayersWithZeroOrOneLosses {

  /**
   * 1. Approach
   * Array Map.
   * 
   * 2. Complexity
   * Time O(N) -> 72 ms
   * Space O(1) Not considering returned answer.
   * 
   * @param matches
   * @return
   */
  public List<List<Integer>> findWinnersArray(int[][] matches) {
    final List<List<Integer>> answers = new ArrayList<>();
    final List<Integer> answer0 = new ArrayList<>();
    final List<Integer> answer1 = new ArrayList<>();
    int[] loserMap = new int[100001];
    Arrays.fill(loserMap, Integer.MAX_VALUE);

    for (final int[] match : matches) {
      final int winner = match[0];
      final int loser = match[1];

      if (loserMap[winner] == Integer.MAX_VALUE) {
        loserMap[winner] = 0;
      }

      if (loserMap[loser] == Integer.MAX_VALUE) {
        loserMap[loser] = 1;
      } else {
        loserMap[loser] = loserMap[loser] + 1;
      }
    }

    for (int i = 0; i < 100001; i++) {
      if (loserMap[i] == 0) {
        answer0.add(i);
      }

      if (loserMap[i] == 1) {
        answer1.add(i);
      }
    }

    answers.add(answer0);
    answers.add(answer1);

    return answers;
  }
  
  /**
   * 1. Approach
   * TreeMap
   * 
   * 2. Complexity
   * Time O(N)
   * Space O(N)
   * 
   * @param matches
   * @return
   */
  public List<List<Integer>> findWinnersTreeMap(int[][] matches) {
    final List<List<Integer>> answers = new ArrayList<>();
    final List<Integer> answer0 = new ArrayList<>();
    final List<Integer> answer1 = new ArrayList<>();
    final TreeMap<Integer, Integer> matchesMap = new TreeMap<>();

    for (final int[] match : matches) {
      int winner = match[0];
      int loser = match[1];

      if (!matchesMap.containsKey(winner)) {
        matchesMap.put(winner, 0);
      }

      matchesMap.put(loser, matchesMap.getOrDefault(loser, 0) + 1);
    }

    for (final Map.Entry<Integer, Integer> entry: matchesMap.entrySet()) {
      if (entry.getValue() == 0) {
        answer0.add(entry.getKey());
      } else if (entry.getValue() == 1) {
        answer1.add(entry.getKey());
      }
    }

    answers.add(answer0);
    answers.add(answer1);

    return answers;
  }
}
