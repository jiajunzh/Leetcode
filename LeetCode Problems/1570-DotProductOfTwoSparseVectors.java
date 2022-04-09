package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.Problem
 * Given two sparse vectors, compute their dot product.
 *
 * Implement class SparseVector:
 *
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute
 * the dot product between two SparseVector.
 *
 * Follow up: What if only one of the vectors is sparse?
 *
 * 2. Examples
 * Example 1:
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * 
 * Example 2:
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 * 
 * Example 3:
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 *
 * 3. Constraints:
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 */
class DotProductOfTwoSparseVectors {

  private final Map<Integer, Integer> sparseVectorMap;

  /**
   * 1. Approach
   * Hashmap. The map will be used to store any non-zero element in the sparse vector (Index-Value). The performance 
   * could be improved by iterate only thru the map with fewer elements.
   * 
   * 2. Complexity
   * - Time O(N) for initialization and O(L) for calculating dot (L is the number of non-zero elements)
   * - Space O(L)
   * 
   * 3. Alternative 
   * - Similar to Hashmap, the data structure could also be List<int[]> or two separate int array (one for index, one
   *   for numbers).
   * 
   * @param nums
   */
  DotProductOfTwoSparseVectors(int[] nums) {
    this.sparseVectorMap = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        this.sparseVectorMap.put(i, nums[i]);
      }
    }
  }

  // Return the dotProduct of two sparse vectors
  public int dotProduct(DotProductOfTwoSparseVectors vec) {
    int dotProduct = 0;
    final Map<Integer, Integer> iteratedMap = this.sparseVectorMap.size() > vec.sparseVectorMap.size() ? vec.sparseVectorMap : this.sparseVectorMap;
    final Map<Integer, Integer> comparedMap = this.sparseVectorMap.size() > vec.sparseVectorMap.size() ? this.sparseVectorMap : vec.sparseVectorMap;

    for (final Integer index: iteratedMap.keySet()) {
      if (comparedMap.containsKey(index)) {
        dotProduct += iteratedMap.get(index) * comparedMap.get(index);
      }
    }

    return dotProduct;
  }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);