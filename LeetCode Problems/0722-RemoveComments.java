package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * See https://leetcode.com/problems/remove-comments/
 */
public class RemoveComments {

  /**
   * 1. Approach 
   * StringBuilder + Case Splitting
   * 
   * 2. Complexity 
   * - Time O(N)
   * - Space O(L)
   */
  public List<String> removeComments(String[] source) {
    final List<String> result = new ArrayList<>();
    boolean isInComment = false;
    StringBuilder sb = new StringBuilder();
    for (String line : source) {
      int index = 0;
      while (index < line.length()) {
        if (isInComment) {
          if (index < line.length() - 1 && line.charAt(index) == '*' && line.charAt(index + 1) == '/') {
            isInComment = false;
            index++;
          }
        } else {
          if (index < line.length() - 1 && line.charAt(index) == '/' && line.charAt(index + 1) == '/') {
            break;
          } else if (index < line.length() - 1 && line.charAt(index) == '/' && line.charAt(index + 1) == '*') {
            isInComment = true;
            index++;
          } else {
            sb.append(line.charAt(index));
          }
        }
        index++;
      }

      if (!isInComment && sb.length() > 0) {
        result.add(sb.toString());
        sb = new StringBuilder();
      }
    }
    return result;
  }
}
