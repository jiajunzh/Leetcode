package problem;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1. Problem 
 * You are given an array of words where each word consists of lowercase English letters.
 *
 * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing 
 * the order of the other characters to make it equal to wordB.
 *
 * For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
 * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2,
 * word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
 *
 * Return the length of the longest possible word chain with words chosen from the given list of words.
 *
 * 2. Examples
 * Example 1
 * Input: words = ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
 * 
 * Example 2
 * Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * Output: 5
 * Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
 * 
 * Example 3
 * Input: words = ["abcd","dbqca"]
 * Output: 1
 * Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
 * ["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 *
 * 3. Constraints
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] only consists of lowercase English letters.
 */
public class LongestStringChain {

  /**
   * 1. Approach 
   * Memoization 
   * 
   * 2. Complexity 
   * - Time O(L^2 * N) where L is the maximum possible length of a word and N be the number of word in list 
   * - Space O(L * N)
   * 
   * @param words
   * @return
   */
  public int longestStrChain1(String[] words) {
    final Set<String> set = new HashSet<>();
    final Map<String, Integer> memo = new HashMap<>();
    Collections.addAll(set, words);
    int maxLength = 0;
    for (String word : words) maxLength = Math.max(maxLength, dfs(set, memo, word));
    return maxLength;
  }

  private int dfs(final Set<String> set, final Map<String, Integer> memo, final String word) {
    if (memo.containsKey(word)) return memo.get(word);
    if (!set.contains(word)) return 0;
    int maxLength = 1;
    for (int i = 0; i < word.length(); i++) {
      final String predecessor = word.substring(0, i) + word.substring(i + 1, word.length());
      maxLength = Math.max(maxLength, dfs(set, memo, predecessor) + 1);
    }
    memo.put(word, maxLength);
    return maxLength;
  }

  /**
   * 1. Approach 
   * Dynamic Programming + Array Sorting 
   * 
   * 2. Complexity 
   * - Time O(N(LogN + L^2))
   * - Space O(N * L)
   * 
   * @param words
   * @return
   */
  public int longestStrChain2(String[] words) {
    Arrays.sort(words, Comparator.comparingInt(String::length));
    final Map<String, Integer> map = new HashMap<>();
    int maxLength = 0;
    for (final String word : words) {
      int currLength = 0;
      final StringBuilder sb = new StringBuilder(word);
      for (int i = 0; i < word.length(); i++) {
        sb.deleteCharAt(i);
        currLength = Math.max(currLength, map.getOrDefault(sb.toString(), 0) + 1);
        sb.insert(i, word.charAt(i));
      }
      map.put(word, currLength);
      maxLength = Math.max(currLength, maxLength);
    }
    return maxLength;
  }
}
