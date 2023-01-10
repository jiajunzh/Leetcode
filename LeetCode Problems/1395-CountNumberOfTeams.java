package problem;

/**
 * 1. Problem
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 *
 * You have to form a team of 3 soldiers amongst them under the following rules:
 *
 * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
 * A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
 * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 *
 * 2. Examples
 * Example 1
 * Input: rating = [2,5,3,4,1]
 * Output: 3
 * Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
 *
 * Example 2
 * Input: rating = [2,1,3]
 * Output: 0
 * Explanation: We can't form any team given the conditions.
 *
 * Example 3
 * Input: rating = [1,2,3,4]
 * Output: 4
 *
 * 3. Constraints
 * n == rating.length
 * 3 <= n <= 1000
 * 1 <= rating[i] <= 105
 * All the integers in rating are unique.
 */
public class CountNumberOfTeams {

    /**
     * 1. Approach
     * Dynamic Programming
     *
     * 2. Complexity
     * - Time O(N^2)
     * - Space O(N)
     *
     * @param rating
     * @return
     */
    public int numTeams(int[] rating) {
        final int n = rating.length;
        int count = 0;
        final int[] left = new int[n]; // The number of items smaller than the current items
        final int[] right = new int[n];
        for (int i = 0; i < rating.length; i++) {
            for (int j = 0; j < i; j++) {
                if (rating[i] < rating[j]) {
                    count += left[j];
                    left[i]++;
                } else if (rating[i] > rating[j]) {
                    count += right[j];
                    right[i]++;
                }
            }
        }
        return count;
    }
}
