package problem;

public class RangeSumQueryMutable2 {
  
  private final SegmentTree segmentTree;
  private final int[] nums;

  /**
   * 1. Approach 
   * Segment Tree
   * 
   * 2. Complexity
   * - Time O(logN)
   * - Space O(N)
   * 
   * @param nums
   */
  public RangeSumQueryMutable2(int[] nums) {
    this.segmentTree = new SegmentTree(nums);
    this.nums = nums;
  }

  public void update(int index, int val) {
    int diff = val - this.nums[index];
    this.nums[index] = val;
    this.segmentTree.update(index, diff);
  }

  public int sumRange(int left, int right) {
    return this.segmentTree.getRangeSum(left, right);
  }

  private static class SegmentTree {

    private final int[] segmentTree;
    private final int n;

    private SegmentTree(final int[] nums) {
      this.n = nums.length;
      this.segmentTree = new int[4 * n];
      buildSegmentTree(nums, 0, 0, n - 1);
    }

    private void buildSegmentTree(int[] nums, int treeIndex, int low, int high) {
      if (low == high) {
        this.segmentTree[treeIndex] = nums[low];
        return;
      }
      int mid = low + (high - low) / 2;
      buildSegmentTree(nums, treeIndex * 2 + 1, low, mid);
      buildSegmentTree(nums, treeIndex * 2 + 2, mid + 1, high);
      this.segmentTree[treeIndex] = this.segmentTree[treeIndex * 2 + 1] +  this.segmentTree[treeIndex * 2 + 2];
    }

    private int getRangeSum(int i, int j) {
      return getRangeSum(0, 0, n - 1, i, j);
    }

    private int getRangeSum(int treeIndex, int low, int high, int i, int j) {
      if (i > high || j < low) {
        return 0;
      }
      if (i <= low && j >= high) {
        return this.segmentTree[treeIndex];
      }
      int mid = low + (high - low) / 2;
      if (mid < i) {
        return getRangeSum(treeIndex * 2 + 2, mid + 1, high, i, j);
      } else if (mid >= j) {
        return getRangeSum(treeIndex * 2 + 1, low, mid, i, j);
      }

      int left = getRangeSum(treeIndex * 2 + 1, low, mid, i, j);
      int right = getRangeSum(treeIndex * 2 + 2, mid + 1, high, i, j);

      return left + right;
    }

    private void update(int index, int diff) {
      update(0, 0, n - 1, index, diff);
    }

    private void update(int treeIndex, int low, int high, int index, int diff) {
      if (low == high) {
        this.segmentTree[treeIndex] += diff;
        return;
      }
      int mid = low + (high - low) / 2;
      if (mid >= index) {
        update(treeIndex * 2 + 1, low, mid, index, diff);
      } else {
        update(treeIndex * 2 + 2, mid + 1, high, index, diff);
      }
      this.segmentTree[treeIndex] = this.segmentTree[treeIndex * 2 + 1] +  this.segmentTree[treeIndex * 2 + 2];
    }
  }
}
