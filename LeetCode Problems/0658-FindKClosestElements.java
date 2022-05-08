package problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1. Problem 
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result 
 * should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 * 2. Examples 
 * Example 1
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * 
 * Example 2
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 * 3. Constraints
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr is sorted in ascending order.
 * -104 <= arr[i], x <= 104
 */
public class FindKClosestElements {

  /**
   * 1. Approach 
   * Array Sorting. 
   * 1) Sort the array with the order of distance to the x 
   * 2) Sort the array with the order of element value
   * 
   * 2. Complexity 
   * - Time O(NlogN + KlogK)
   * - Space O(1)
   * 
   * @param arr
   * @param k
   * @param x
   * @return
   */
  public List<Integer> findClosestElementsSort(int[] arr, int k, int x) {
    List<Integer> result = new ArrayList<>();
    for (int num : arr) {
      result.add(num);
    }
    result.sort((a, b) -> Math.abs(a - x) - Math.abs(b - x));
    result = result.subList(0, k);
    Collections.sort(result);
    return result;
  }

  /**
   * 1. Approach 
   * Binary Search + Sliding Window. As the problem suggests, the array is sorted already which reminds us of binary 
   * search. With Binary Search, we could first find the minimum number Ai such that Ai is greater than or equal to x. 
   * Naturally, the number on the other side of target x will be Ai-1. (Ai or Ai-1 might be an imaginary element, for 
   * example, arr = [1,2,3,4,5], x = 0 => Ai = A0 = 1, Ai-1 = A-1 = Imaginary element)
   * 
   * Once we find the two numbers that are the closest to target x, we could keep a sliding window which expands from 
   * the middle to incorporate all the k elements closest to the arr.
   * 
   * Initial State 
   * left   target   right
   * Ai-1     x       Ai
   * Let's define that in this initial state, left points to Ai-1 and right points to Ai and nothing has been selected 
   * yet, so the number of the closest elements at this moment is zero = (right - 1) - (left + 1) + 1, which could be 
   * used within the loop termination condition (right - left - 1) = k.
   * 
   * 2. Complexity 
   * - Time O(logN + k)
   * - Space O(1)
   * 
   * @param arr
   * @param k
   * @param x
   * @return
   */
  public List<Integer> findClosestElementsBinarySearchSlidingWindow(int[] arr, int k, int x) {
    int left = 0, right = arr.length;

    while (left < right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] >= x) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    left--;
    while (right - left - 1 < k) {
      if (left == -1) {
        right++;
        continue;
      }

      if (right == arr.length) {
        left--;
        continue;
      }

      if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
        left--;
      } else {
        right++;
      }
    }

    List<Integer> result = new ArrayList<>();
    for (int i = left + 1; i < right; i++) {
      result.add(arr[i]);
    }
    return result;
  }

  /**
   * 1. Approach 
   * Binary Search. One observation regarding the solution is the length of the answer could only be of size k and the 
   * array needs to be continuous, meaning if we already know the left boundary of the solution, we could derive the 
   * right boundary of the solution pretty easily by plus k. With it, binary search could be used to look up for the
   * left boundary of the final solution. Consider the below example 
   * A0 ... Ai-1 Ai Ai+1 Ai+2 ... An, k = 2
   * In this case, whether we want to select Ai-1 or Ai as the left boundary could be decided by comparing which one of 
   * Ai-1 and Ai+1 is closer to target x. If Ai-1 is closer, search left. Otherwise, search right.
   * 
   * 2. Complexity 
   * - Time O(logN + k)
   * - Space O(1)
   * 
   * 3. Mistakes 
   * - When deciding which element is closer to target x, instead of using abs function, use expression
   *   x - arr[mid] <= arr[mid + k] - x. One edge of using abs function is arr = [1,1,2,2,2,2,2,3,3] where k = 3, x = 3.
   *   In the first round of search, the mid = 3. However, if you use abs, the distance of both element at index 3 and
   *   index 6 is the same. However, the one at index 6 is apparently closer to target 3. 
   *   In other words, the distance is the absolute difference between the element and the target if you compare two 
   *   different elements. However, if you compare two elements with the same value, the position of the element along 
   *   with the difference between it and the target needs to be taken into consideration because you want the k closest 
   *   element instead of just one (other k - 1 element matters too, which is impacted by the left boundary of the solution).
   *   
   * @param arr
   * @param k
   * @param x
   * @return
   */
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    int left = 0, right = arr.length - k;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (x - arr[mid] <= arr[mid + k] - x) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    final List<Integer> result = new ArrayList<>();
    for (int i = left; i < left + k; i++) {
      result.add(arr[i]);
    }
    return result;
  }
}
