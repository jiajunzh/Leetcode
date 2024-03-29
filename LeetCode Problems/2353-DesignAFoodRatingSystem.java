package problem;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 1. Problem 
 * Design a food rating system that can do the following:
 *
 * Modify the rating of a food item listed in the system.
 * Return the highest-rated food item for a type of cuisine in the system.
 * Implement the FoodRatings class:
 *
 * FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are described by
 * foods, cuisines and ratings, all of which have a length of n.
 * foods[i] is the name of the ith food,
 * cuisines[i] is the type of cuisine of the ith food, and
 * ratings[i] is the initial rating of the ith food.
 * void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
 * String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the given type 
 * of cuisine. If there is a tie, return the item with the lexicographically smaller name.
 * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, 
 * either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in 
 * alphabetic order.
 *
 * 2. Examples 
 * Example 1
 * Input
 * ["FoodRatings", "highestRated", "highestRated", "changeRating", "highestRated", "changeRating", "highestRated"]
 * [[["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", "japanese", "japanese", "greek", "japanese", 
 * "korean"], [9, 12, 8, 15, 14, 7]], ["korean"], ["japanese"], ["sushi", 16], ["japanese"], ["ramen", 16], ["japanese"]]
 * Output
 * [null, "kimchi", "ramen", null, "sushi", null, "ramen"]
 *
 * Explanation
 * FoodRatings foodRatings = new FoodRatings(["kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"], ["korean", 
 * "japanese", "japanese", "greek", "japanese", "korean"], [9, 12, 8, 15, 14, 7]);
 * foodRatings.highestRated("korean"); // return "kimchi"
 *                                     // "kimchi" is the highest rated korean food with a rating of 9.
 * foodRatings.highestRated("japanese"); // return "ramen"
 *                                       // "ramen" is the highest rated japanese food with a rating of 14.
 * foodRatings.changeRating("sushi", 16); // "sushi" now has a rating of 16.
 * foodRatings.highestRated("japanese"); // return "sushi"
 *                                       // "sushi" is the highest rated japanese food with a rating of 16.
 * foodRatings.changeRating("ramen", 16); // "ramen" now has a rating of 16.
 * foodRatings.highestRated("japanese"); // return "ramen"
 *                                       // Both "sushi" and "ramen" have a rating of 16.
 *                                       // However, "ramen" is lexicographically smaller than "sushi".
 *
 * 3. Constraints
 * 1 <= n <= 2 * 104
 * n == foods.length == cuisines.length == ratings.length
 * 1 <= foods[i].length, cuisines[i].length <= 10
 * foods[i], cuisines[i] consist of lowercase English letters.
 * 1 <= ratings[i] <= 108
 * All the strings in foods are distinct.
 * food will be the name of a food item in the system across all calls to changeRating.
 * cuisine will be a type of cuisine of at least one food item in the system across all calls to highestRated.
 * At most 2 * 104 calls in total will be made to changeRating and highestRated.
 */
public class DesignAFoodRatingSystem {

  /**
   * 1. Approach 
   * TreeSet + HashMap + Object Food
   * 
   * 2. Complexity 
   * - Time O(NlogN) for constructor, O(M*logN) for changeRating and O(1) for highestRated
   * - Space O(N)
   * 
   * 3. Mistakes
   * - One initial approach for changing rating is to remove the object from priority queue first and then add it back
   *   to the queue to re-shuffle the queue. It gave TLE in the end as remove(object) for priority queue takes O(N)
   *   instead of O(logN) => you have to find the object O(N) and then delete it.
   *   Instead, we could simply add one more object with the same rating into the queue and invalidate the previous rating 
   */
  private final Map<String, TreeSet<Food>> foodsByCuisine;
  private final Map<String, Food> foodMap;

  public DesignAFoodRatingSystem(String[] foods, String[] cuisines, int[] ratings) {
    this.foodsByCuisine = new HashMap<>();
    this.foodMap = new HashMap<>();
    for (int i = 0; i < foods.length; i++) {
      final TreeSet<Food> foodSet = foodsByCuisine.getOrDefault(cuisines[i], new TreeSet<>(new FoodComparator()));
      final Food food = new Food(foods[i], cuisines[i], ratings[i]);
      foodSet.add(food);
      foodsByCuisine.put(cuisines[i], foodSet);
      foodMap.put(foods[i], food);
    }
  }

  public void changeRating(String food, int newRating) {
    final Food foodObj = foodMap.get(food);
    final TreeSet<Food> foods = foodsByCuisine.get(foodObj.cuisine);
    foods.remove(foodObj);
    foodObj.rating = newRating;
    foods.add(foodObj);
  }

  public String highestRated(String cuisine) {
    return foodsByCuisine.get(cuisine).first().food;
  }

  class FoodComparator implements Comparator<Food> {
    @Override
    public int compare(final Food food1, final Food food2) {
      if (food1.rating == food2.rating) {
        return food1.food.compareTo(food2.food);
      }
      return food2.rating - food1.rating;
    }
  }
  
  class Food {
    private String food, cuisine;
    private int rating;

    Food(final String food, final String cuisine, final int rating) {
      this.food = food;
      this.cuisine = cuisine;
      this.rating = rating;
    }
  }
}
