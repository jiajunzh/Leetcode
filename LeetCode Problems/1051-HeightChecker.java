package problem;

/**
 * 1. Problem 
 * A school is trying to take an annual photo of all the students. The students are asked to stand in a single file 
 * line in non-decreasing order by height. Let this ordering be represented by the integer array expected where 
 * expected[i] is the expected height of the ith student in line.
 *
 * You are given an integer array heights representing the current order that the students are standing in. Each 
 * heights[i] is the height of the ith student in line (0-indexed).
 *
 * Return the number of indices where heights[i] != expected[i].
 *
 * 2. Examples
 * Example 1
 * Input: heights = [1,1,4,2,1,3]
 * Output: 3
 * Explanation: 
 * heights:  [1,1,4,2,1,3]
 * expected: [1,1,1,2,3,4]
 * Indices 2, 4, and 5 do not match.
 *
 * Example 2
 * Input: heights = [5,1,2,3,4]
 * Output: 5
 * Explanation:
 * heights:  [5,1,2,3,4]
 * expected: [1,2,3,4,5]
 * All indices do not match.
 * 
 * Example 3
 * Input: heights = [1,2,3,4,5]
 * Output: 0
 * Explanation:
 * heights:  [1,2,3,4,5]
 * expected: [1,2,3,4,5]
 * All indices match.
 *
 * 3. Constraints
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 */
public class HeightChecker {

  private static final int MAX_HEIGHT = 100;

  /**
   * 1. Approach 
   * Counting Sort 
   * 
   * 2. Complexity
   * - Time O(N + R)
   * - Space O(R)
   * 
   * @param heights
   * @return
   */
  public int heightChecker(int[] heights) {
    final int[] heightCounts = new int[MAX_HEIGHT + 1];
    for (int height : heights) {
      heightCounts[height]++;
    }
    int currHeight = 1, result = 0;
    for (int height : heights) {
      while (heightCounts[currHeight] == 0) currHeight++;
      if (height != currHeight) result++;
      heightCounts[currHeight]--;
    }
    return result;
  }
}
