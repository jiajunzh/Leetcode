package problem;

/**
 * 1. Problem 
 * Given an integer array nums, return the greatest common divisor of the smallest number and largest number in nums.
 *
 * The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [2,5,6,9,10]
 * Output: 2
 * Explanation:
 * The smallest number in nums is 2.
 * The largest number in nums is 10.
 * The greatest common divisor of 2 and 10 is 2.
 * 
 * Example 2
 * Input: nums = [7,5,6,8,3]
 * Output: 1
 * Explanation:
 * The smallest number in nums is 3.
 * The largest number in nums is 8.
 * The greatest common divisor of 3 and 8 is 1.
 * 
 * Example 3
 * Input: nums = [3,3]
 * Output: 3
 * Explanation:
 * The smallest number in nums is 3.
 * The largest number in nums is 3.
 * The greatest common divisor of 3 and 3 is 3.
 *
 * 3. Constraints
 * 2 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 */
public class FindGreatestCommonDivisorOfArray {

  /**
   * 1. Approach 
   * Euclidean Algorithm => GCD(A,B) == GCD(B, A % B).
   * 
   * Assume A = X * GCD(A, B), B = Y * GCD(A, B) and A = Z * B + R
   * Then R = A - Z * B = X * GCD(A, B) - Z * Y * GCD(A, B) = (X - Z * Y) * GCD(A, B), which means that 
   * GCD(A, B) divides R as well.
   * Then GCD(A,B) = GCD(B,R) = GCD(B, A % B)
   * 
   * https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/the-euclidean-algorithm#:~:text=The%20Algorithm,%3D%20B%E2%8B%85Q%20%2B%20R)
   * 
   * 2. Complexity 
   * - Time O(N + M) where M is the maximum value of num in nums
   * - Space O(logM)
   * 
   * @param nums
   * @return
   */
  public int findGCD(int[] nums) {
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int num : nums) {
      min = Math.min(num, min);
      max = Math.max(num, max);
    }
    return gcd(max, min);
  }

  private int gcd(int a, int b) {
    if (a % b == 0) {
      return b;
    }
    return gcd(b, a % b);
  }
}
