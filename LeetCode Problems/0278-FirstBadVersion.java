package problem;

/**
 * 1. Problem
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version
 * of your product fails the quality check. Since each version is developed based on the previous version, all the 
 * versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following 
 * ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find 
 * the first bad version. You should minimize the number of calls to the API.
 *
 * 2. Example
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 *
 * Input: n = 1, bad = 1
 * Output: 1
 *
 * 3. Constraints:
 * 1 <= bad <= n <= 2^31 - 1
 */
public class FirstBadVersion {

  private int firstBadVersion = 5;

  /**
   * 1. Approach
   * Binary Search. This problem requirement could be transformed into a sorted array. Finding the first 
   * bad version is equals to find the minimal k satisfying Condition(k) is true.
   * 
   * - Initialization/Update: including all possible values where Condition(k) is true.
   * - Condition: already exists in isBadVersion.
   * 
   * 2. Complexity
   * Time O(logN)
   * Space O(1)
   * 
   * @param n
   * @return
   */
  public int firstBadVersion(int n) {
    int left = 1, right = n;

    while (left < right) {
      int mid = (right - left) / 2 + left;

      if (isBadVersion(mid)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    return left;
  }
  
  private boolean isBadVersion(int n) {
    return n >= firstBadVersion;
  }
}
