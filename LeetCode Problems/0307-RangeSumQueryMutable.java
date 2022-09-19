package problem;

/**
 * 1. Problem
 * Given an integer array nums, handle multiple queries of the following types:
 *
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 * 2. Examples
 * Example 1
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 *
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 *
 * 3. Constraints
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * At most 3 * 104 calls will be made to update and sumRange.
 */
public class RangeSumQueryMutable {
  
  private final BIT binaryIndexedTree;
  private final int[] nums;

  /**
   * 1. Approach 
   * BinaryIndexedTree
   * 
   * 2. Complexity 
   * - Time O(LogN) for update/query
   * - Space O(N)
   * 
   * @param nums
   */
  public RangeSumQueryMutable(int[] nums) {
    this.binaryIndexedTree = new BIT(nums.length);
    this.nums = nums;
    for (int i = 0; i < nums.length; i++) {
      this.binaryIndexedTree.update(i, nums[i]);
    }
  }

  public void update(int index, int val) {
    int diff = val - nums[index];
    nums[index] = val;
    this.binaryIndexedTree.update(index, diff);
  }

  public int sumRange(int left, int right) {
    return this.binaryIndexedTree.getSum(right) - this.binaryIndexedTree.getSum(left - 1);
  }

  private static class BIT {

    private final int n;
    private final int[] nodes;

    private BIT(final int n) {
      this.n = n;
      this.nodes = new int[n + 1];
    }

    private void update(int index, int diff) {
      index++;
      while (index <= this.n) {
        this.nodes[index] += diff;
        index += (index & - index);
      }
    }

    private int getSum(int index) {
      int sum = 0;
      index++;
      while (index > 0) {
        sum += this.nodes[index];
        index -= (index & - index);
      }
      return sum;
    }
  }
}
