package problem;

/**
 * 1. Problem 
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
 *
 * You call a pre-defined API int guess(int num), which returns three possible results:
 *
 * -1: Your guess is higher than the number I picked (i.e. num > pick).
 * 1: Your guess is lower than the number I picked (i.e. num < pick).
 * 0: your guess is equal to the number I picked (i.e. num == pick).
 * Return the number that I picked.
 *
 * 2. Examples 
 * Example 1
 * Input: n = 10, pick = 6
 * Output: 6
 * 
 * Example 2
 * Input: n = 1, pick = 1
 * Output: 1
 * 
 * Example 3
 * Input: n = 2, pick = 1
 * Output: 1
 *
 * 3. Constraints
 * 1 <= n <= 231 - 1
 * 1 <= pick <= n
 */
public class GuessNumberHigherOrLower {

  /**
   * 1. Approach 
   * Binary Search 
   * 
   * 2. Complexity 
   * - Time O(logN)
   * - Space O(1)
   * 
   * @param n
   * @return
   */
  public int guessNumber(int n) {
    int low = 1, high = n;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      int guess = guess(mid);

      if (guess == 0) {
        return mid;
      } else if (guess == -1) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return -1;
  }
  
  private int guess(int num) {
    // This is a placeholder for the guess interface.
    return 1;
  }
}
