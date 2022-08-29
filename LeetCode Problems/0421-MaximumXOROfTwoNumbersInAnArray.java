package problem;

import java.util.HashSet;
import java.util.Set;

/**
 * 1. Problem
 * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
 *
 * 2. Examples 
 * Example 1
 * Input: nums = [3,10,5,25,2,8]
 * Output: 28
 * Explanation: The maximum result is 5 XOR 25 = 28.
 * 
 * Example 2
 * Input: nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * Output: 127
 *
 * 3. Constraints
 * 1 <= nums.length <= 2 * 105
 * 0 <= nums[i] <= 231 - 1
 */
public class MaximumXOROfTwoNumbersInAnArray {

  /**
   * 1. Approach 
   * Bit Prefix + HashSet.
   * 
   * In this approach, we examine bit by bit for each number each time from left to right plus a trick currXor ^ p2 = p1 
   * (where currXor = p1 ^ p2, meaning we find the maximum XOR when examining till ith bits)
   * 
   * 2. Complexity 
   * - Time O(N * L)
   * - Space O(N)
   * 
   * @param nums
   * @return
   */
  public int findMaximumXOR(int[] nums) {
    int maxNum = 0;
    for (int num : nums) maxNum = Math.max(maxNum, num);
    int L = Integer.toBinaryString(maxNum).length();
    int maxXor = 0;
    final Set<Integer> prefixes = new HashSet<>();
    for (int i = L - 1; i >= 0; i--) {
      maxXor <<= 1;
      int currXor = maxXor | 1;
      prefixes.clear();
      for (int num : nums) {
        int curr = num >> i;
        if (prefixes.contains(currXor ^ curr)) {
          maxXor = currXor;
          break;
        }
        prefixes.add(curr);
      }
    }
    return maxXor;
  }

  private final TrieNode root = new TrieNode();

  /**
   * 1. Approach 
   * Trie
   * 
   * 2. Complexity 
   * - Time O(N * L)
   * - Space O(N * L)
   * 
   * @param nums
   * @return
   */
  public int findMaximumXOR2(int[] nums) {
    int maxNum = 0;
    for (int num : nums) maxNum = Math.max(maxNum, num);
    int L = Integer.toBinaryString(maxNum).length();
    int bitmask = 1 << L;

    int maxXor = 0;
    for (int num : nums) {
      int currXor = 0;
      TrieNode currNode = root;
      String numStr = Integer.toBinaryString(bitmask | num).substring(1);
      insert(numStr);
      for (int i = 0; i < L; i++) {
        final char flippedChar = numStr.charAt(i) == '1' ? '0' : '1';
        if (currNode.contains(flippedChar)) {
          currXor = (currXor << 1) | 1;
          currNode = currNode.get(flippedChar);
        } else {
          currXor = currXor << 1;
          currNode = currNode.get(numStr.charAt(i));
        }
      }
      maxXor = Math.max(maxXor, currXor);
    }
    return maxXor;
  }

  private void insert(String numStr) {
    TrieNode currNode = root;
    for (final char c : numStr.toCharArray()) {
      if (!currNode.contains(c)) {
        currNode.put(c, new TrieNode());
      }
      currNode = currNode.get(c);
    }
  }

  private static class TrieNode {

    private final TrieNode[] children;

    private TrieNode() {
      this.children = new TrieNode[2];
    }

    private boolean contains(final char c) {
      return this.children[c - '0'] != null;
    }

    private TrieNode get(final char c) {
      return this.children[c - '0'];
    }

    private void put(final char c, final TrieNode node) {
      this.children[c - '0'] = node;
    }
  }
}
