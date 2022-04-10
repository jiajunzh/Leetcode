package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the 
 * buildings in the line. The ocean is to the right of the buildings. A building has an ocean view if the building can
 * see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a 
 * smaller height. Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing 
 * order.
 *
 * 2. Examples
 * Example 1:
 * Input: heights = [4,2,3,1]
 * Output: [0,2,3]
 * Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
 * 
 * Example 2:
 * Input: heights = [4,3,2,1]
 * Output: [0,1,2,3]
 * Explanation: All the buildings have an ocean view.
 * 
 * Example 3:
 * Input: heights = [1,3,2,4]
 * Output: [3]
 * Explanation: Only building 3 has an ocean view.
 *
 * 3. Constraints
 * 1 <= heights.length <= 105
 * 1 <= heights[i] <= 109
 */
public class BuildingsWithAnOceanView {

  /**
   * 1. Approach 
   * Traversal From Right to Left (Monotonic Stack Space Optimization). This approach keeps track of the current tallest 
   * building to the right so far. The building has ocean view if heights > tallest height.
   * 
   * 2. Complexity
   * - Time O(N)
   * - Space O(N)
   * 
   * 3. Improvement/Note
   * - Note: despite that it is not the best way to solve the problem, this problem brings an important data structure
   *   Monotonic Stack where the element in the stack is either in ascending or descending order. It is commonly used 
   *   to find the next greater element problem. During traversal, it will pop any element that is shorter than the 
   *   current building to guarantee that 1) each element in the stack so far has the ocean view 2) the order of elements
   *   in the stack is in descending order.
   * - Compared to use List to dynamically keep track of all building having ocean view, boolean array could be used for
   *   faster access.
   * 
   * @param heights
   * @return
   */
  public int[] findBuildings1(int[] heights) {
    // OceanView if heights[i] > max(heights from i + 1 to n- 1)
    final List<Integer> buildingsWithOceanView = new ArrayList<>();
    int maxHeight = 0;
    for (int i = heights.length - 1; i >= 0; i--) {
      if (heights[i] > maxHeight) {
        buildingsWithOceanView.add(i);
      }
      maxHeight = Math.max(maxHeight, heights[i]);
    }

    int[] buildingsWithOceanViewArray = new int[buildingsWithOceanView.size()];
    for (int i = 0; i < buildingsWithOceanViewArray.length; i++) {
      buildingsWithOceanViewArray[i] = buildingsWithOceanView.get(buildingsWithOceanView.size() - i - 1);
    }

    return buildingsWithOceanViewArray;
  }
  
  public int[] findBuildings2(int[] heights) {
    // OceanView if heights[i] > max(heights from i + 1 to n- 1)
    final boolean[] hasOceanView = new boolean[heights.length];
    int cnt = 0;
    int maxHeight = 0;
    for (int i = heights.length - 1; i >= 0; i--) {
      if (heights[i] > maxHeight) {
        hasOceanView[i] = true;
        cnt++;
      }
      maxHeight = Math.max(maxHeight, heights[i]);
    }

    int[] buildingsWithOceanView = new int[cnt];
    int currIndex = 0;
    for (int i = 0; i < heights.length; i++) {
      if (hasOceanView[i]) {
        buildingsWithOceanView[currIndex] = i;
        currIndex++;
      }
    }

    return buildingsWithOceanView;
  }
}
