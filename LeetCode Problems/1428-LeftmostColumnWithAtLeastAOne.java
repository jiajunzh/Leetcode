package problem;

import java.util.List;

interface BinaryMatrix { 
   public int get(int row, int col);
   public List<Integer> dimensions();
};

/**
 * 1. Problem
 * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing 
 * order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. 
 * If such an index does not exist, return -1.
 *
 * You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
 *
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the 
 * matrix is rows x cols. Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. 
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary 
 * matrix directly.
 *
 * 2. Examples
 * Example 1:
 * Input: mat = [[0,0],[1,1]]
 * Output: 0
 * 
 * Example 2:
 * Input: mat = [[0,0],[0,1]]
 * Output: 1
 * 
 * Example 3:
 * Input: mat = [[0,0],[0,0]]
 * Output: -1
 *
 * 3. Constraints
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] is either 0 or 1.
 * mat[i] is sorted in non-decreasing order.
 */
public class LeftmostColumnWithAtLeastAOne {

  /**
   * 1. Approach 
   * On top of approach, it is easy to notice that we don't have to search the whole row each time. For example, if we 
   * have two rows below. 
   * 
   * 0 0 1 0 0
   * 0 0 0 1 1
   * 
   * In the first row, we have already known that the target will only be smaller than index 2, thus we don't need to 
   * search anything beyond index 2 (including index 2) in row 2. Another observation is if we found the right most 
   * zero, that means we don't have to search anything left to it (all will be zeros). 
   * 
   * Based on the two observations above, we could come up with an approach that searches from top right to left down.
   * If the element is 1, keep searching left. If the element is 0, keep searching down.
   * 
   * 2. Complexity
   * - Time O(R + C) 
   * - Space O(1)
   *
   */
  public int leftMostColumnWithOne2(BinaryMatrix binaryMatrix) {
    final List<Integer> dimensions = binaryMatrix.dimensions();
    final int r = dimensions.get(0), c = dimensions.get(1);
    int currRow = 0, currCol = c - 1;

    while (currRow < r && currCol >= 0) {
      if (binaryMatrix.get(currRow, currCol) == 1) {
        currCol--;
      } else {
        currRow++;
      }
    }

    return (currRow == r && currCol == c - 1)? -1 : currCol + 1;
  }
  
  /**
   * 1. Approach
   * Binary Search. This approach is not the best way but it will pass. It could be translated into finding the minimum 
   * column index such that there is one in column.
   * 
   * 2. Complexity 
   * - Time O(RLog(C)) R is total number of rows and C is total number of columns
   * - Space O(1)
   * 
   * 3. Improvement
   * - Upper Bound could be c instead of c - 1 to incorporate the case where we should return -1. Just think there is a
   *   additional fake column on the most right.
   */
  public int leftMostColumnWithOne1(BinaryMatrix binaryMatrix) {
    final List<Integer> dimensions = binaryMatrix.dimensions();
    final int r = dimensions.get(0), c = dimensions.get(1);
    int left = 0, right = c;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (isColumnWithOne(binaryMatrix, mid, r, c)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    if (left == c) return -1;

    return left;
  }

  private boolean isColumnWithOne(final BinaryMatrix binaryMatrix, final int columnIndex, final int r, final int c) {
    if (r == columnIndex) {
      return false;
    }

    for (int i = 0; i < r; i++) {
      if (binaryMatrix.get(i, columnIndex) == 1) {
        return true;
      }
    }

    return false;
  }
}
