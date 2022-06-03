package problem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1. Problem 
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated 
 * sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated 
 * sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * 2. Examples 
 * Example 1
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * 
 * Example 2
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * 
 * Example 3
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 * 3. Constraints
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 */
public class WordBreak {

  /**
   * 1. Approach 
   * Dynamic Programming.
   * 
   * Define dp[i] as whether the string with first i chars selected is breakable or not.
   * dp[i] = true if dp[j] = true AND substring(j, i) is a word in dictionary.
   * 
   * 2. Complexity 
   * - Time O(N^3). Double loop plus substring.
   * - Space O(N)
   * 
   * 3. Improvement && Alternatives 
   * - Optimization: Consider the maximum length of a word. If length from j to i is greater than the max length, we 
   *   could end the current loop.
   * - BFS: for each current position examined so far, moving forward by considering all possible word in the dictionary.
   *   Then store the new position for each in the queue.
   * - Trie: instead of using substring, build Trie to speed up the word lookup.  
   *
   * @param s
   * @param wordDict
   * @return
   */
  public boolean wordBreak(String s, List<String> wordDict) {
    // dp[i] as whether substring(0, i) is breakable.
    final int n = s.length();
    final boolean[] dp = new boolean[n + 1];
    int maxLen = 0;
    final Set<String> set = new HashSet<>();
    for (final String word: wordDict) {
      maxLen = Math.max(maxLen, word.length());
      set.add(word);
    }

    dp[0] = true;
    for (int i = 1; i <= n; i++) {
      for (int j = Math.max(i - maxLen, 0); j < i; j++) {
        final String word = s.substring(j, i);
        dp[i] = dp[j] && set.contains(word);
        if (dp[i]) break;
      }
    }

    return dp[n];
  }
}
