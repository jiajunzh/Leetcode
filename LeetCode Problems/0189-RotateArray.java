package problem;

public class RotateArray {

  /**
   * 1. Approach 
   * Cyclic Replacement. It is not hard to prove that if the original index is hit when there are still elements not being
   * replaced, the next element will certainly not be replaced yet (Otherwise, we could prove that all elements would be
   * replaced when original index hit, moving forward 1 each skipping)
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param nums
   * @param k
   */
  public void rotate(int[] nums, int k) {
    int count = 0;
    for (int start = 0; count < nums.length; start++) {
      int current = start;
      int prev = nums[start];
      do {
        int next = (current + k) % nums.length;
        int tmp = nums[next];
        nums[next] = prev;
        prev = tmp;
        count++;
        current = next;
      } while (current != start);
    }
  }

  /**
   * 1. Approach 
   * Reverse + Two Pointers
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(1)
   * 
   * @param nums
   * @param k
   */
  public void rotate2(int[] nums, int k) {
    k = k % nums.length;
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }

  private void reverse(int[] nums, int start, int end) {
    while (start < end) {
      int tmp = nums[start];
      nums[start] = nums[end];
      nums[end] = tmp;
      start++;
      end--;
    }
  }
}
