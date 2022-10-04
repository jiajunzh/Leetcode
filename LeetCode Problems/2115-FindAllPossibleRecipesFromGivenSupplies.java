package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1. Problem 
 * You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients.
 * The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i].
 * Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
 *
 * You are also given a string array supplies containing all the ingredients that you initially have, and you have an
 * infinite supply of all of them.
 *
 * Return a list of all the recipes that you can create. You may return the answer in any order.
 *
 * Note that two recipes may contain each other in their ingredients.
 *
 * 2. Examples
 * Example 1
 * Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
 * Output: ["bread"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * 
 * Example 2
 * Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * 
 * Example 3
 * Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
 * Output: ["bread","sandwich","burger"]
 * Explanation:
 * We can create "bread" since we have the ingredients "yeast" and "flour".
 * We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
 * We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".
 *
 * 3. Constraints
 * n == recipes.length == ingredients.length
 * 1 <= n <= 100
 * 1 <= ingredients[i].length, supplies.length <= 100
 * 1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
 * recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
 * All the values of recipes and supplies combined are unique.
 * Each ingredients[i] does not contain any duplicate values.
 */
public class FindAllPossibleRecipesFromGivenSupplies {

  /**
   * 1. Approach
   * Topological Sort 
   * 
   * 2. Complexity 
   * - Time O(V + E)
   * - Space O(V + E)
   * 
   * @param recipes
   * @param ingredients
   * @param supplies
   * @return
   */
  public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
    final Set<String> supplySet = new HashSet<>(Arrays.asList(supplies));
    final Map<String, List<String>> ingredientsToRecipes = new HashMap<>();
    final Map<String, Integer> recipesIndegrees = new HashMap<>();
    final List<String> result = new ArrayList<>();
    for (int i = 0; i < recipes.length; i++) {
      boolean needAnotherRecipe = false;
      for (final String ingredient : ingredients.get(i)) {
        if (!supplySet.contains(ingredient)) {
          final List<String> recipesPerIngredient = ingredientsToRecipes.getOrDefault(ingredient, new ArrayList<>());
          recipesPerIngredient.add(recipes[i]);
          ingredientsToRecipes.put(ingredient, recipesPerIngredient);
          recipesIndegrees.put(recipes[i], recipesIndegrees.getOrDefault(recipes[i], 0) + 1);
          needAnotherRecipe = true;
        }
      }
      if (!needAnotherRecipe) {
        result.add(recipes[i]);
      }
    }
    for (int i = 0; i < result.size(); i++) {
      for (final String recipe : ingredientsToRecipes.getOrDefault(result.get(i), new ArrayList<>())) {
        int indegree = recipesIndegrees.getOrDefault(recipe, 0) - 1;
        recipesIndegrees.put(recipe, indegree);
        if (indegree == 0) {
          result.add(recipe);
        }
      }
    }
    return result;
  }

  /**
   * 1. Approach
   * Topological Sort + DFS
   * 
   * 2. Complexity 
   * - Time O(V + E)
   * - Space O(V + E)
   */
  private static final int NOT_VISITED = 0;
  private static final int VISITING = 1;
  private static final int VISITED = 2;

  public List<String> findAllRecipes2(String[] recipes, List<List<String>> ingredients, String[] supplies) {
    final Map<String, List<String>> recipeToIngredients = new HashMap<>();
    final Map<String, Integer> recipeStatusMap = new HashMap<>();
    for (int i = 0; i < recipes.length; i++) {
      recipeToIngredients.put(recipes[i], ingredients.get(i));
      recipeStatusMap.put(recipes[i], NOT_VISITED);
    }
    for (String supply : supplies) {
      recipeStatusMap.put(supply, VISITED);
    }
    final List<String> result = new ArrayList<>();
    for (String recipe : recipes) {
      dfs(recipe, recipeToIngredients, recipeStatusMap, result);
    }
    return result;
  }

  private boolean dfs(final String recipe, final Map<String, List<String>> recipeToIngredients, final Map<String, Integer> recipeStatusMap, final List<String> result) {
    if (!recipeStatusMap.containsKey(recipe)) {
      return false;
    }
    if (recipeStatusMap.get(recipe) == VISITED) {
      return true;
    }
    if (recipeStatusMap.get(recipe) == VISITING) {
      return false;
    }
    recipeStatusMap.put(recipe, VISITING);
    for (String ingredient : recipeToIngredients.get(recipe)) {
      if (!dfs(ingredient, recipeToIngredients, recipeStatusMap, result)) {
        return false;
      }
    }
    recipeStatusMap.put(recipe, VISITED);
    result.add(recipe);
    return true;
  }
}
