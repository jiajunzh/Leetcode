package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1. Problem
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and 
 * j != k, and nums[i] + nums[j] + nums[k] == 0. Notice that the solution set must not contain duplicate triplets.
 *
 * 2. Example
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * 
 * Input: nums = []
 * Output: []
 *
 * Input: nums = [0]
 * Output: []
 *
 * 3. Constraints
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class ThreeSum {
  
  /**
   * 1. Approach
   * Three pointer + Array Sorting. One of the requirement of this problem is no duplicate triplet. In order to 
   * achieve requirement with minimum effort, array could be sorted and duplicate element could be skipped over 
   * by three pointers. Once this is settled, the remaining problem could be translated into 2 Sum after fixing the
   * first pointer.
   * 
   * nums[i] + nums[j] + nums[k] == 0 ==> 2 Sum with target -nums[k].
   * 
   * 2. Complexity
   * Time O(N^2)
   * Space O(1)
   * 
   * 3. Mistakes & Improvement
   * 1) Check Array Boundary When Skipping Elements
   * low < high should be checked when looping through while to skip element. Otherwise it could trigger 
   * OutOfIndexBoundaryException. Error Case [-4, -1, -1, -1, 0, 1, 2, 2].
   * 
   * 2) Don't put skipping logic at the front of each iteration.
   * The duplicate element should only be skipped once the value has been visited. Skipping the duplicates
   * before visiting could cause problem in case [-4, 2, 2]. For example the high pointer skips to point nums[1]
   * at the beginning and low pointer skips to point nums[2], then this combination [-4, 2, 2] is missed.
   * 
   * 3) Skip duplicate for first pointer only the index > 0
   * OutOfIndexBoundaryException when index = 0.
   * 
   * 4) Break the loop if nums[i] > 0 to improve performance
   * The first pointer is meant to be the smallest one among three pointers. As the array is sorted, if the first 
   * pointer visits an element that is greater than 0, then it is guaranteed the last two is also greater than 0 
   * (i.e. sum > 0)
   */
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    final List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length - 2; i++) {
      if (nums[i] > 0) {
        break;
      }

      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      int low = i + 1, high = nums.length - 1;

      while (low < high) {
        int threeSum = nums[i] + nums[low] + nums[high];

        if (threeSum == 0) {
          result.add(Arrays.asList(nums[i], nums[low], nums[high]));
          while (low < high && nums[low] == nums[low + 1]) low++;
          while (low < high && nums[high] == nums[high - 1]) high--;

          low++;
          high--;
        } else if (threeSum < 0) {
          low++;
        } else {
          high--;
        }
      }
    }

    return result;
  }
}
