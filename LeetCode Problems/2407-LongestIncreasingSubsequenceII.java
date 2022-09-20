package problem;

/**
 * 1. Problem 
 * You are given an integer array nums and an integer k.
 *
 * Find the longest subsequence of nums that meets the following requirements:
 *
 * The subsequence is strictly increasing and
 * The difference between adjacent elements in the subsequence is at most k.
 * Return the length of the longest subsequence that meets the requirements.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [4,2,1,4,3,4,5,8,15], k = 3
 * Output: 5
 * Explanation:
 * The longest subsequence that meets the requirements is [1,3,4,5,8].
 * The subsequence has a length of 5, so we return 5.
 * Note that the subsequence [1,3,4,5,8,15] does not meet the requirements because 15 - 8 = 7 is larger than 3.
 * 
 * Example 2
 * Input: nums = [7,4,5,1,8,12,4,7], k = 5
 * Output: 4
 * Explanation:
 * The longest subsequence that meets the requirements is [4,5,8,12].
 * The subsequence has a length of 4, so we return 4.
 * 
 * Example 3
 * Input: nums = [1,5], k = 1
 * Output: 1
 * Explanation:
 * The longest subsequence that meets the requirements is [1].
 * The subsequence has a length of 1, so we return 1.
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * 1 <= nums[i], k <= 105
 */
public class LongestIncreasingSubsequenceII {

  /**
   * 1. Approach
   * Segment Tree. This problem could be transformed into finding an element in [num - k, num - 1] that occurred the most 
   * when encountering num. It is obvious that linear scanning will take O(N) per number, thus O(N^2) in total.
   * 
   * Instead, using segment tree query the range and update each number will take O(logN) each number, thus O(NlogN) in 
   * total, which is a great performance gain.
   * 
   * 2. Complexity
   * - Time O(NlogN)
   * - Space O(N)
   * 
   * @param nums
   * @param k
   * @return
   */
  public int lengthOfLIS(int[] nums, int k) {
    SegmentTree segmentTree = new SegmentTree(100_001);
    int length = 1;
    for (int num : nums) {
      int max = segmentTree.query(num - k, num - 1);
      length = Math.max(max + 1, length);
      segmentTree.update(num, max + 1);
    }
    return length;
  }

  private static class SegmentTree {

    private final int[] tree;
    private final int n;

    private SegmentTree(final int n) {
      this.n = n;
      this.tree = new int[4 * n];
    }

    private void update(int index, int value) {
      update(0, 0, n - 1, index, value);
    }

    private int query(int i, int j) {
      return query(0, 0, n - 1, i, j);
    }

    private int query(int treeIndex, int low, int high, int i, int j) {
      if (i <= low && high <= j) {
        return this.tree[treeIndex];
      }
      if (j < low || i > high) {
        return 0;
      }

      int mid = low + (high - low) / 2;
      if (mid < i) {
        return query(treeIndex * 2 + 2, mid + 1, high, i, j);
      } else if (mid >= j) {
        return query(treeIndex * 2 + 1, low, mid, i, j);
      }

      int left = query(treeIndex * 2 + 1, low, mid, i, j);
      int right = query(treeIndex * 2 + 2, mid + 1, high, i, j);

      return Math.max(left, right);
    }

    private void update(int treeIndex, int low, int high, int index, int value) {
      if (low == high) {
        this.tree[treeIndex] = Math.max(value, this.tree[treeIndex]);
        return;
      }
      int mid = low + (high - low) / 2;
      if (mid < index) {
        update(treeIndex * 2 + 2, mid + 1, high, index, value);
      } else {
        update(treeIndex * 2 + 1, low, mid, index, value);
      }
      this.tree[treeIndex] = Math.max(this.tree[treeIndex * 2 + 1], this.tree[treeIndex * 2 + 2]);
    }
  }
}
