package problem;

/**
 * 1. Problem
 * You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].
 *
 * You have an array arr of length length with all zeros, and you have some operation to apply on arr. In the ith
 * operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.
 *
 * Return arr after applying all the updates.
 *
 * 2. Examples
 * Example 1
 * Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
 * Output: [-2,0,3,5,3]
 *
 * Example 2
 * Input: length = 10, updates = [[2,4,6],[5,6,8],[1,9,-4]]
 * Output: [0,-4,2,2,2,4,4,-4,-4,-4]
 *
 * 3. Constraints
 * 1 <= length <= 105
 * 0 <= updates.length <= 104
 * 0 <= startIdxi <= endIdxi < length
 * -1000 <= inci <= 1000
 *
 * 4. Thinking Process
 *  1) Requirement
 *  - Input 1: length - length of array
 *  - Input 2: updates - an array of operations with startIndex, endIndex, and inc (how much each element should be updated)
 *  - Output: arr with each operations in updates applied
 *
 *  length = 2, updates = [[0, 1], [1, 1]]
 *
 *  2) Constraints
 *  - the array is zero-indexed
 *  - startIdxi < endIdxi <= length - 1
 *  - Bound for inci?
 *
 *  3) Solutions
 *  - Solution 1: iterate the updates array and apply each operation one by one
 *    - Time: each operation could take O(L) => in total O(U * L)
 *    - Space: assume no storage used for results is counted, O(1)
 *  - Solution 2: focus specific index, we just need to know how many operations include this index, then we will know how much this index should be updated
 *    - Time: O(U * L)
 *    - Space O(1)
 *  - Solution 3: prefix sum + Two round
 *    - first round: each value at index i is how much the first index till end should be added. Example, length = 3, updates = [[0, 1, 1], [1, 1, 3]] => [1, 3, -4] => each operation O(1) and total is O(U)
 *    - second round: prefix sum O(L) => [1, 4, 0]
 *    - Time O(U + L)
 *    - Space O(1)
 */
public class RangeAddition {

    /**
     * 1. Approach
     * prefix sum + Two round
     *
     * first round: each value at index i is how much the first index till end should be added. Example, length = 3, updates = [[0, 1, 1], [1, 1, 3]] => [1, 3, -4] => each operation O(1) and total is O(U)
     * second round: prefix sum O(L) => [1, 4, 0]
     *
     * 2. Complexity
     * - Time O(U + L)
     * - Space O(1)
     * @param length
     * @param updates
     * @return
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        final int[] result = new int[length];

        // step 1 - for each op, add inci at index start and minus inci at index end + 1 => careful about the boundary of array
        for (int[] update : updates) {
            result[update[0]] += update[2];
            if (update[1] + 1 < length) {
                result[update[1] + 1] -= update[2];
            }
        }

        // step 2 - prefix sum
        for (int i = 1; i < length; i++) {
            result[i] += result[i - 1];
        }

        return result;
    }
}
