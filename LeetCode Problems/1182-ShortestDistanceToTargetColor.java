package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class ShortestDistanceToTargetColor {

  /**
   * 1. Approach 
   * TreeSet floor and ceiling. 
   * - Keep an array of TreeSet which maps the color to its indices
   * - For each query:
   *   - Get the TreeSet for this color 
   *   - Use floor and ceiling to retrieve the nearest two indices around the index i 
   *   - Calculate the shortest distance
   * 2. Complexity
   * - Time O(QlogN)
   * - Space O(N)
   * 
   * @param colors
   * @param queries
   * @return
   */
  public List<Integer> shortestDistanceColorTreeSet(int[] colors, int[][] queries) {
    final TreeSet[] colorMap = constructColorMapTreeSet(colors);
    final List<Integer> result = new ArrayList<>();

    for (int i = 0; i < queries.length; i++) {
      int[] query = queries[i];
      int index = query[0];
      TreeSet<Integer> set = colorMap[query[1]];
      Integer floor = set.floor(index);
      Integer ceiling = set.ceiling(index);
      int shortestDistance = Integer.MAX_VALUE;
      if (floor != null) {
        shortestDistance = index - floor;
      }
      if (ceiling != null) {
        shortestDistance = Math.min(ceiling - index, shortestDistance);
      }
      if (floor == null && ceiling == null) {
        shortestDistance = -1;
      }
      result.add(shortestDistance);
    }

    return result;
  }

  private TreeSet[] constructColorMapTreeSet(int[] colors) {
    final TreeSet[] colorMap = new TreeSet[4];
    colorMap[1] = new TreeSet<Integer>();
    colorMap[2] = new TreeSet<Integer>();
    colorMap[3] = new TreeSet<Integer>();

    for (int i = 0; i < colors.length; i++) {
      colorMap[colors[i]].add(i);
    }

    return colorMap;
  }

  /**
   * 1. Approach
   * Binary Search. This problem could be viewed as finding the two indices that is closest to the index wrt the color.
   * Essentially, it could be achieved by searching for the insert position of this index in each color list. Since the
   * color list will be sorted as we traverse the colors array from the start. It is easy to think of binary search.
   * 
   * Compared to the TreeSet, when constructing the map, each color will experience only O(1) (list ops) instead of 
   * O(logN) time. 
   *
   * 2. Complexity
   * - Time O(QlogN)
   * - Space O(N)
   * 
   * 3. Note
   * - Collections.binarySearch(collections, target);
   *   => if target exist, then return the position
   *   => if target not exist, then return - insertPosition - 1
   *   
   * @param colors
   * @param queries
   * @return
   */
  public List<Integer> shortestDistanceColorBinarySearch(int[] colors, int[][] queries) {
    final List[] colorMap = constructColorMapBinarySearch(colors);
    final List<Integer> result = new ArrayList<>();

    for (int i = 0; i < queries.length; i++) {
      int[] query = queries[i];
      int index = query[0];
      int color = query[1];
      List<Integer> indices = colorMap[color];
      if (indices.size() == 0) {
        result.add(-1);
        continue;
      }
      int insertPosition = Collections.binarySearch(indices, index);
      if (insertPosition < 0) {
        insertPosition = -1 * (insertPosition + 1);
      }

      if (insertPosition == 0) {
        result.add(indices.get(insertPosition) - index);
      } else if (insertPosition == indices.size()) {
        result.add(index - indices.get(insertPosition - 1));
      } else {
        int min = index - indices.get(insertPosition - 1);
        min = Math.min(indices.get(insertPosition) - index, min);
        result.add(min);
      }
    }

    return result;
  }

  private List[] constructColorMapBinarySearch(int[] colors) {
    final List[] colorMap = new List[4];
    colorMap[1] = new ArrayList<Integer>();
    colorMap[2] = new ArrayList<Integer>();
    colorMap[3] = new ArrayList<Integer>();

    for (int i = 0; i < colors.length; i++) {
      colorMap[colors[i]].add(i);
    }

    return colorMap;
  }

  /**
   * 1. Approach
   * PreCompute the Distance.
   * 
   * Prerequisite:
   * - An array colorIndices[4] to keep track of the closet color indice on the left/right to the current index.
   * - An array distance[4][color.length] to keep track of the closest distance between the current index to the queried
   *   color.
   *   
   * Steps:
   * - Iterate from left to the right. For each element in colors
   *   - Record the index for its corresponding color
   *   - Calculate the closest distance between this index to all colors up till now
   * - Iterate from right to the left and do the similar as above.  
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(N)
   * 
   * @param colors
   * @param queries
   * @return
   */
  public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
    final int[][] distance = new int[4][colors.length];
    int[] colorIndices = new int[4];
    for (int i = 1; i < 4; i++) {
      Arrays.fill(distance[i], -1);
    }
    Arrays.fill(colorIndices, -1);
    for (int i = 0; i < colors.length; i++) {
      int color = colors[i];
      colorIndices[color] = i;

      for (int j = 1; j < 4; j++) {
        if (colorIndices[j] != -1) {
          distance[j][i] = i - colorIndices[j];
        }
      }
    }

    Arrays.fill(colorIndices, -1);
    for (int i = colors.length - 1; i >= 0; i--) {
      int color = colors[i];
      colorIndices[color] = i;

      for (int j = 1; j < 4; j++) {
        if (colorIndices[j] != -1) {
          distance[j][i] = distance[j][i] == -1 ? colorIndices[j] - i : Math.min(colorIndices[j] - i, distance[j][i]);
        }
      }
    }

    final List<Integer> result = new ArrayList<>();
    for (int[] query : queries) {
      result.add(distance[query[1]][query[0]]);
    }
    return result;
  }
}
