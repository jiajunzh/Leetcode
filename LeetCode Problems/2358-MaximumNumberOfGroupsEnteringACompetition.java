package problem;

/**
 * 1. Problem 
 * You are given a positive integer array grades which represents the grades of students in a university. You would like 
 * to enter all these students into a competition in ordered non-empty groups, such that the ordering meets the following 
 * conditions:
 *
 * The sum of the grades of students in the ith group is less than the sum of the grades of students in the (i + 1)th group, for all groups (except the last).
 * The total number of students in the ith group is less than the total number of students in the (i + 1)th group, for all groups (except the last).
 * Return the maximum number of groups that can be formed.
 *
 * 2. Examples
 * Example 1
 * Input: grades = [10,6,12,7,3,5]
 * Output: 3
 * Explanation: The following is a possible way to form 3 groups of students:
 * - 1st group has the students with grades = [12]. Sum of grades: 12. Student count: 1
 * - 2nd group has the students with grades = [6,7]. Sum of grades: 6 + 7 = 13. Student count: 2
 * - 3rd group has the students with grades = [10,3,5]. Sum of grades: 10 + 3 + 5 = 18. Student count: 3
 * It can be shown that it is not possible to form more than 3 groups.
 * 
 * Example 2
 * Input: grades = [8,8]
 * Output: 1
 * Explanation: We can only form 1 group, since forming 2 groups would lead to an equal number of students in both groups.
 *
 * 3. Constraints
 * 1 <= grades.length <= 105
 * 1 <= grades[i] <= 105
 */
public class MaximumNumberOfGroupsEnteringACompetition {

  /**
   * 1. Approach 
   * For this problem, if we sort the array and always assign the smaller element first into the group, then we could
   * guarantee the order above is preserved.
   * 
   * Then the total number elements to have k groups is that 
   * 1 + 2 + 3 + .... + k <= n
   * 
   * 2. Complexity 
   * - Time O(sqrt(N))
   * - Space O(1)
   * 
   * 3. Alternative 
   * - Another approach is binary search, finding the maximum k such that k * (k + 1) / 2 <= n with (logN) time
   * 
   * @param grades
   * @return
   */
  public int maximumGroups(int[] grades) {
    int k = 0, total = 0, n = grades.length;
    while (total + k + 1 <= n) {
      k++;
      total += k;
    }
    return k;
  }
}
