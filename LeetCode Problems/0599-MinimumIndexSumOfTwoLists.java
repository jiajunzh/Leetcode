package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. Problem 
 * Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants 
 * represented by strings.
 *
 * You need to help them find out their common interest with the least list index sum. If there is a choice tie between 
 * answers, output all of them with no order requirement. You could assume there always exists an answer.
 * 
 * 2. Examples 
 * Example 1
 * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"]
 * Output: ["Shogun"]
 * Explanation: The only restaurant they both like is "Shogun".
 * 
 * Example 2
 * Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["KFC","Shogun","Burger King"]
 * Output: ["Shogun"]
 * Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).
 *
 * 3. Constraints
 * 1 <= list1.length, list2.length <= 1000
 * 1 <= list1[i].length, list2[i].length <= 30
 * list1[i] and list2[i] consist of spaces ' ' and English letters.
 * All the stings of list1 are unique.
 * All the stings of list2 are unique.
 */
public class MinimumIndexSumOfTwoLists {

  /**
   * 1. Approach 
   * HashMap 
   * 
   * 2. Complexity 
   * - Time O(l1 + l2)
   * - Space O(min(l1, l2) * x) where x is the average length of strings
   * 
   * @param list1
   * @param list2
   * @return
   */
  public String[] findRestaurant(String[] list1, String[] list2) {
    if (list1.length > list2.length) {
      return findRestaurant(list2, list1);
    }
    final Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < list1.length; i++) {
      map.put(list1[i], i);
    }
    int minIndexSum = Integer.MAX_VALUE;
    final List<String> resultList = new ArrayList<>();
    for (int i = 0; i < list2.length; i++) {
      if (map.containsKey(list2[i])) {
        int indexSum = map.get(list2[i]) + i;
        if (indexSum <= minIndexSum) {
          if (indexSum < minIndexSum) {
            minIndexSum = indexSum;
            resultList.clear();
          }
          resultList.add(list2[i]);
        }
      }
    }
    final String[] result = new String[resultList.size()];
    for (int i = 0; i < resultList.size(); i++) {
      result[i] = resultList.get(i);
    }
    return result;
  }
}
