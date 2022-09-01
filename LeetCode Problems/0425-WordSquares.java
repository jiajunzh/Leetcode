package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given an array of unique strings words, return all the word squares you can build from words. The same word from words can be used multiple times. You can return the answer in any order.
 *
 * A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 *
 * 2. Examples
 * Example 1
 * Input: words = ["area","lead","wall","lady","ball"]
 * Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 * 
 * Example 2
 * Input: words = ["abat","baba","atan","atal"]
 * Output: [["baba","abat","baba","atal"],["baba","abat","baba","atan"]]
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 *
 * 3. Constraints
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 4
 * All words[i] have the same length.
 * words[i] consists of only lowercase English letters.
 * All words[i] are unique.
 */
public class WordSquares {

  /**
   * 1. Approach 
   * Backtracking + Trie. 
   * 
   * One feature of word square is once you fill in the K th row, you will get prefix till K th column for each following row.
   * A very naive implementation is to backtrack each row (i.e. try each word at current row and error out if not applicable).
   * One expensive operation is to get all words with some specific settled prefix. However, one could use Trie to improve
   * the performance of this operations. (Another appraoch is to use HashTable for fast look up as well)
   * 
   * 2. Complexity 
   * - Time O(N * L + N ^ L * L) where N is the number of word in words array and L is the length of each word.
   * - Space O(N * L + L)
   * 
   * @param words
   * @return
   */
  private final TrieNode root = new TrieNode();
  private String[] words;

  public List<List<String>> wordSquares(String[] words) {
    this.words = words;
    final List<List<String>> result = new ArrayList<>();
    final int n = words[0].length();
    buildTrie(words);
    backtrack(n, new ArrayList<>(), result);
    return result;
  }

  private void buildTrie(final String[] words) {
    for (int i = 0; i < words.length; i++) {
      final String word = words[i];
      TrieNode curr = root;
      curr.addWordIndex(i);
      for (final char c : word.toCharArray()) {
        if (!curr.contains(c)) {
          curr.put(c, new TrieNode());
        }
        curr = curr.get(c);
        curr.addWordIndex(i);
      }
    }
  }

  private void backtrack(int n, List<String> wordSquare, List<List<String>> result) {
    if (n == wordSquare.size()) {
      result.add(new ArrayList<>(wordSquare));
      return;
    }
    final String prefix = getPrefixAtI(wordSquare, wordSquare.size());
    final List<Integer> wordsWithPrefix = getWordsWithPrefix(prefix);
    for (final Integer wordWithPrefixIndex : wordsWithPrefix) {
      final String wordWithPrefix = words[wordWithPrefixIndex];
      wordSquare.add(wordWithPrefix);
      backtrack(n, wordSquare, result);
      wordSquare.remove(wordSquare.size() - 1);
    }
  }

  private List<Integer> getWordsWithPrefix(final String prefix) {
    TrieNode curr = root;
    for (final char c : prefix.toCharArray()) {
      if (!curr.contains(c)) {
        return new ArrayList<>();
      }
      curr = curr.get(c);
    }
    return curr.getWordList();
  }

  private String getPrefixAtI(final List<String> wordSquare, final int i) {
    final StringBuilder sb = new StringBuilder();
    for (final String row : wordSquare) {
      sb.append(row.charAt(i));
    }
    return sb.toString();
  }

  private static class TrieNode {

    private final TrieNode[] children;
    private final List<Integer> wordList;

    private TrieNode() {
      this.children = new TrieNode[26];
      this.wordList = new ArrayList<>();
    }

    private boolean contains(final char c) {
      return this.children[c - 'a'] != null;
    }

    private TrieNode get(final char c) {
      return this.children[c - 'a'];
    }

    private void put(final char c, final TrieNode child) {
      this.children[c - 'a'] = child;
    }

    private void addWordIndex(final Integer index) {
      this.wordList.add(index);
    }

    private List<Integer> getWordList() {
      return this.wordList;
    }
  }
}
