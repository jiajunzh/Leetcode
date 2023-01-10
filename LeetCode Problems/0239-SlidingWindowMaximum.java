package problem;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1. Problem
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of
 * the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right
 * by one position.
 *
 * Return the max sliding window.
 *
 * 2. Examples
 * Example 1
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * Example 2
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * 3. Constraints
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class SlidingWindowMaximum {

    /**
     * 1. Approach
     * Monotonic Queue.
     *
     * In here, each time we move this window by one step ahead, we will have three things to do:
     * (1) Remove the older element from the container (queue)
     * (2) Add new element into the container
     * (3) Determine the maximum value in the container
     *
     * Using monotonic queue, we could keep a queue with all elements strictly decreasing, thus the first element in the queue
     * will always be the maximum value in the current container. In this case, each time we add a new element, we need to
     * remove all elements that are smaller than the current one so that the current one could be at the first position.
     * With this observation, we could use Deque to achieve this removeLast, getLast, addLast
     *
     * But how do we know if the first element should be removed from container? What if there are duplicates?
     * Remember, we have an array, meaning we could always know its value as long as we know the index. Thus, instead
     * of storing values in the container, we could use index and then we will know the boundary.
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(K)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        final int[] result = new int[nums.length - k + 1];
        final Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            cleanUp(queue, nums, k, i);
            queue.offer(i);
        }
        result[0] = nums[queue.peek()];
        for (int i = k; i < nums.length; i++) {
            cleanUp(queue, nums, k, i);
            queue.offer(i);
            result[i - k + 1] = nums[queue.peek()];
        }
        return result;
    }

    private void cleanUp(final Deque<Integer> queue, final int[] nums, int k, int i) {
        if (!queue.isEmpty() && queue.peek() < i - k + 1) {
            queue.poll();
        }
        while (!queue.isEmpty() && nums[i] > nums[queue.getLast()]) {
            queue.removeLast();
        }
    }

    /**
     * 1. Approach
     * Dynamic Programming. Split the array as blocks containing k elements and have two dp arrays left and right.
     * left[i] = maximum value from block start to index i
     * right[j] = minimum value from block index j to end
     *
     * maxSlidingWindow[a, a + k - 1] = Math.max(left[a + k - 1], right[a])
     *
     * 2. Complexity
     * - Time O(N)
     * - Space O(N)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        final int[] left = new int[nums.length], right = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i % k == 0) left[i] = nums[i];
            else left[i] = Math.max(left[i - 1], nums[i]);

            int j = nums.length - i - 1;
            if (j % k == 0 || j == nums.length - 1) right[j] = nums[j];
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        final int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            result[i] = Math.max(left[i + k - 1], right[i]);
        }
        return result;
    }
}
