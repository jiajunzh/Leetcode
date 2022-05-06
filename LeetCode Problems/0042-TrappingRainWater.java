package problem;

import java.util.Stack;

/**
 * 1. Problem 
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water 
 * it can trap after raining.
 *
 * 2. Examples
 * Example 1
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case,
 * 6 units of rain water (blue section) are being trapped.
 * 
 * Example 2
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 * 3. Constraints
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class TrappingRainWater {

  /**
   * 1. Problem 
   * Dynamic Programming. It requires one pass from left to right and then another pass from right to left.
   * For each index i, the amount of water could be trapped = Min(leftMaxHeight, rightMaxHeight) - height[i].
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param height
   * @return
   */
  public int trapDp(int[] height) {
    final int n = height.length;
    final int[] leftHighest = new int[n];
    int rightHighest = 0;
    int unit = 0;
    for (int i = 1; i < n; i++) {
      leftHighest[i] = Math.max(height[i - 1], leftHighest[i - 1]);
    }
    for (int i = n - 1; i >= 0; i--) {
      int boundary = Math.min(rightHighest, leftHighest[i]);
      unit += boundary > height[i] ? boundary - height[i] : 0;
      rightHighest = Math.max(rightHighest, height[i]);
    }
    return unit;
  }

  /**
   * 1. Approach 
   * Stack. We could keep a non-increasing monotonic stack containing the index of the height array. In this way, we are
   * sure that all index kept in the stack will be bounded by some left buildings. When iterating the current index, if 
   * the current one is greater than the top of stack, meaning the top of stack is bounded by both left and right 
   * building. However, in other cases when the current one is smaller or equal to the top of stack, we could put it into
   * the stack and we are it is bounded by the left buildings.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param height
   * @return
   */
  public int trapStack(int[] height) {
    final Stack<Integer> stack = new Stack<>();
    int trap = 0;

    for (int i = 0; i < height.length; i++) {
      while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
        int top = stack.pop();
        if (stack.isEmpty()) {
          break;
        }
        int boundedHeight = Math.min(height[i], height[stack.peek()]) -  height[top];
        trap += boundedHeight * (i - stack.peek() - 1);
      }
      stack.push(i);
    }

    return trap;
  }

  /**
   * 1. Problem 
   * Two Pointers. Notice that in the DP approach, for each building, we only care the minimum one of the leftMaxHeight[i]
   * and rightMaxHeight[i]. If leftMax > rightMax, the trapped water depends only on the rightMax. Vice versa. 
   * 
   * So another way to do it in one iteration, we could keep two pointers left and right to constantly the leftMax and
   * rightMax meet until now balanced.
   * 
   * For example, if the height[left] < height[right] <= rightMax, if height[left] >= leftMax, update the leftMax and the 
   * height[left] is not bounded by the left buildings. Otherwise, if height[left] < leftMax, it means that in previous 
   * iteration, left++ => height[left] < height[right] < rightMax until it hits the rightMax => leftMax <= rightMax.
   * Thus, the trapped the water is dependent on left bound.
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(1)
   * 
   * @param height
   * @return
   */
  public int trap(int[] height) {
    int left = 0, right = height.length - 1;
    int leftMax = 0, rightMax = 0;
    int trap = 0;

    while (left < right) {
      if (height[left] < height[right]) {
        if (height[left] >= leftMax) {
          leftMax = height[left];
        } else {
          trap += leftMax - height[left];
        }
        left++;
      } else {
        if (height[right] >= rightMax) {
          rightMax = height[right];
        } else {
          trap += rightMax - height[right];
        }
        right--;
      }
    }

    return trap;
  }
}
