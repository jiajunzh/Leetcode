package problem;

/**
 * 1. Problem 
 * You are given an array words of size n consisting of non-empty strings.
 *
 * We define the score of a string word as the number of strings words[i] such that word is a prefix of words[i].
 *
 * For example, if words = ["a", "ab", "abc", "cab"], then the score of "ab" is 2, since "ab" is a prefix of both "ab" and "abc".
 * Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].
 *
 * Note that a string is considered as a prefix of itself.
 *
 * 2. Examples
 * Example 1
 * Input: words = ["abc","ab","bc","b"]
 * Output: [5,4,3,2]
 * Explanation: The answer for each string is the following:
 * - "abc" has 3 prefixes: "a", "ab", and "abc".
 * - There are 2 strings with the prefix "a", 2 strings with the prefix "ab", and 1 string with the prefix "abc".
 * The total is answer[0] = 2 + 2 + 1 = 5.
 * - "ab" has 2 prefixes: "a" and "ab".
 * - There are 2 strings with the prefix "a", and 2 strings with the prefix "ab".
 * The total is answer[1] = 2 + 2 = 4.
 * - "bc" has 2 prefixes: "b" and "bc".
 * - There are 2 strings with the prefix "b", and 1 string with the prefix "bc".
 * The total is answer[2] = 2 + 1 = 3.
 * - "b" has 1 prefix: "b".
 * - There are 2 strings with the prefix "b".
 * The total is answer[3] = 2.
 * 
 * Example 2
 * Input: words = ["abcd"]
 * Output: [4]
 * Explanation:
 * "abcd" has 4 prefixes: "a", "ab", "abc", and "abcd".
 * Each prefix has a score of one, so the total is answer[0] = 1 + 1 + 1 + 1 = 4.
 *
 * 3. Constraints
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] consists of lowercase English letters.
 */
public class SumOfPrefixScoresOfStrings {

  private TrieNode root;

  /**
   * 1. Approach
   * Trie
   *  
   * 2. Complexity 
   * - Time O(N * L)
   * - Space O(N * L)
   * 
   * @param words
   * @return
   */
  public int[] sumPrefixScores(String[] words) {
    this.root = new TrieNode();
    final int n = words.length;
    for (String word : words) {
      insertWord(word);
    }
    final int[] answers = new int[n];
    for (int i = 0; i < n; i++) {
      answers[i] = calculateScore(words[i]);
    }
    return answers;
  }

  private int calculateScore(String word) {
    int score = 0;
    TrieNode curr = root;
    for (char c : word.toCharArray()) {
      curr = curr.get(c);
      score += curr.getScore();
    }
    return score;
  }

  private void insertWord(String word) {
    TrieNode curr = root;
    for (char c : word.toCharArray()) {
      if (!curr.contains(c)) {
        curr.put(c, new TrieNode());
      }
      curr = curr.get(c);
      curr.setScore(curr.getScore() + 1);
    }
  }

  private static class TrieNode {

    private final TrieNode[] children;
    private int score;

    private TrieNode() {
      this.children = new TrieNode[26];
      this.score = 0;
    }

    private boolean contains(char c) {
      return this.children[c - 'a'] != null;
    }

    private TrieNode get(char c) {
      return this.children[c - 'a'];
    }

    private void put(char c, TrieNode child) {
      this.children[c - 'a'] = child;
    }

    private int getScore() {
      return this.score;
    }

    private void setScore(int score) {
      this.score = score;
    }
  }
}
