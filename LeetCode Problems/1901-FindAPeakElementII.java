package problem;

/**
 * 1. Problem 
 * A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left,
 * right, top, and bottom. Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak 
 * element mat[i][j] and return the length 2 array [i,j].
 *
 * You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
 *
 * You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
 *
 * 2. Examples 
 * Example 1
 * Input: mat = [[1,4],[3,2]]
 * Output: [0,1]
 * Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
 * 
 * Example 2
 * Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
 * Output: [1,1]
 * Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.
 *
 * 3.Constraints
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 500
 * 1 <= mat[i][j] <= 105
 * No two adjacent cells are equal.
 */
public class FindAPeakElementII {

  /**
   * 1. Approach 
   * Binary Search. This approach fixes one position across each rows (i.e. selecting a column), then find the maximum 
   * value in this column and record the row number of it. If the number is also greater than left and right, then it 
   * is the answer this problem looks for. If the left number is greater the current number, it means that there is 
   * a greater value to its left, search left. Same is for the right.
   * 
   * Question 1: Is it possible to search column i first, then i - 1, then back to i?
   * No. Say if the current column is i, if it searches one column to the left (i.e. i - 1), then it means there is one
   * value in the i - 1 column greater than the maximum number in the current column. The maximum number in column i - 1
   * is guaranteed to be greater than the maximum number in column i. Thus it won't search back to the right column i.
   * With it, it narrows down the search space for each iteration by around half.
   *
   * 2. Complexity 
   * - Time O(NlogM)
   * - Space O(1)
   * 
   * 3. Alternative 
   * - Another approach is to use greedy algorithm. Calculate the four element around the current element, move 
   *   accordingly if there is anyone greater than the current one. However, it generates O(M*N) in the worst case
   *   Refer to: https://leetcode.com/problems/find-a-peak-element-ii/discuss/1431530/Simple-JAVA-solution-oror-100-Faster-than-any-solution-oror-Readable-code
   * 
   * @param mat
   * @return
   */
  public int[] findPeakGrid(int[][] mat) {
    int low = 0, high = mat[0].length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      int maxRow = 0;

      for (int i = 0; i < mat.length; i++) {
        maxRow = mat[i][mid] > mat[maxRow][mid] ? i : maxRow;
      }

      boolean greaterThanLeft = mid == 0 || mat[maxRow][mid] > mat[maxRow][mid - 1];
      boolean greaterThanRight = mid == mat[0].length - 1 || mat[maxRow][mid] > mat[maxRow][mid + 1];

      if (greaterThanLeft && greaterThanRight) {
        return new int[]{ maxRow, mid };
      } else if (!greaterThanLeft) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return null;
  }
}
