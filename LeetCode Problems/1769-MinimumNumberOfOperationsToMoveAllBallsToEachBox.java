package problem;

public class MinimumNumberOfOperationsToMoveAllBallsToEachBox {

  /**
   * 1. Approach 
   * Two Passes. This problem could be resolved by noticing the below rules:
   * - totalOps = opsOfMoveLeftBalls + opsOfMoveRightBalls;
   * - opsOfMoveLeftBalls[i] = opsOfMoveLeftBalls[i - 1] + numOfBallsToLeft
   * - opsOfMoveRightBalls[i - 1] = opsOfMoveRightBalls[i] + numOfBallsToRight
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(N)
   * 
   * 3. Improvement
   * - boxChars[0] - '0' instead of checking condition
   * - Another way to think about it is totalOps[i] = totalOps[i - 1] + leftBalls - rightBalls - (boxChars[0] - '0')
   * 
   * @param boxes
   * @return
   */
  public int[] minOperations(String boxes) {
    int n = boxes.length();
    final int[] totalOps = new int[n];
    final int[] leftOps = new int[n];
    final int[] rightOps = new int[n];

    char[] boxChars = boxes.toCharArray();
    int leftCnt = boxChars[0] - '0';
    int rightCnt = boxChars[n - 1] - '0';

    for (int i = 1; i < n; i++) {
      leftOps[i] = leftCnt + leftOps[i - 1];
      leftCnt += boxChars[i] - '0';
      rightOps[n - 1 - i] = rightCnt + rightOps[n - i];
      rightCnt += boxChars[n - 1 - i] - '0';
    }

    for (int i = 0; i < n; i++) {
      totalOps[i] = leftOps[i] + rightOps[i];
    }

    return totalOps;
  }
}
