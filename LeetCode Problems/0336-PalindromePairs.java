package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem 
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.
 *
 * 2. Examples
 * Example 1
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * 
 * Example 2
 * Input: words = ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 * 
 * Example 3
 * Input: words = ["a",""]
 * Output: [[0,1],[1,0]]
 *
 * 3. Constraints
 * 1 <= words.length <= 5000
 * 0 <= words[i].length <= 300
 * words[i] consists of lower-case English letters.
 */
public class PalindromePairs {

  /**
   * 1. Approach 
   * Trie. Three cases when consider pair [i, j]:
   * 1) Word[i] is the reverse of Word[j]. E.g. abc <=> cba => abccba
   * 2) Word[i] is longer than Word[j]. E.g. abc[d] <=> cba => abcdcba
   * In this case Word[i] + Word[j] is palindrome if Word[i] consists of the reverse of Word[j] (abc) and a palindrome (d)
   * 3) Word[i] is shorter than Word[j]. E.g. abc <=> [d]cba => abcdcba
   * In this case Word[i] + Word[j] is palindrome if Word[j] consists of the reverse of Word[i] (abc) and a palindrome (d)
   * 
   * Since we always care about the reverse of word, we could build a trie by using the reverse of each word. To fetch all
   * pairs: 
   * 1) When not running up chars in given word, examine if it could make up for matched(word)|remaining(word)|matched(trie)
   * 2) If last char in given word, examine if the matched word is itself to remove duplicate
   * 3) When running up chars, examine if it could make up for matched(word)|remaining(trie)|matched(trie)
   * 
   * 2. Complexity 
   * - Time O(N * K * K)
   * - Space O(N^2 * K) plus the list used to cache the matched candidate index at node
   * 
   * 3. Alternative 
   * Hashmap, for each word:
   * 1) Check if itself exist in map
   * 2) Check when the word is placed to the left, consider the word could be split into left|right, check if left exists
   * in hashmap when right is a palindrome
   * e.g. ban|ana <=> nab
   * 3) Check when the word is placed to the right, consider the word could be split into left|right, check if right exists
   * in hashmap when left is a palindrome
   * e.g. anana <=> b|anana
   * 
   * @param words
   * @return
   */
  public List<List<Integer>> palindromePairs(String[] words) {
    final Trie trie = new Trie();
    final List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < words.length; i++) {
      trie.update(new StringBuilder(words[i]).reverse().toString(), i);
    }
    for (int i = 0; i < words.length; i++) {
      final List<List<Integer>> pairs = trie.getIds(words[i], i);
      result.addAll(pairs);
    }
    return result;
  }

  private static class Trie {

    private final TrieNode root;

    private Trie() {
      this.root = new TrieNode();
    }

    private void update(final String word, final int wordId) {
      TrieNode curr = root;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        if (!curr.contains(c)) {
          curr.put(c, new TrieNode());
        }
        if (isPalindromeFromStart(word, i)) {
          curr.addId(wordId);
        }
        curr = curr.get(c);
      }
      curr.setWordId(wordId);
    }

    private List<List<Integer>> getIds(final String word, int wordId) {
      final List<List<Integer>> pairs = new ArrayList<>();
      TrieNode curr = root;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        if (curr.getWordId() != -1 && isPalindromeFromStart(word, i)) {
          pairs.add(List.of(wordId, curr.getWordId()));
        }
        curr = curr.get(c);
        if (curr == null) break;
      }
      if (curr == null) return pairs;
      if (curr.getWordId() != -1 && curr.getWordId() != wordId) {
        pairs.add(List.of(wordId, curr.getWordId()));
      }
      final List<Integer> currIds = curr.getIds();
      for (Integer id : currIds) {
        pairs.add(List.of(wordId, id));
      }
      return pairs;
    }

    private boolean isPalindromeFromStart(String word, int start) {
      int end = word.length() - 1;
      while (start < end) {
        if (word.charAt(start) != word.charAt(end)) {
          return false;
        }
        start++;
        end--;
      }
      return true;
    }
  }

  private static class TrieNode {

    private final TrieNode[] children;
    private int wordId;
    private final List<Integer> ids;

    private TrieNode() {
      this.children = new TrieNode[26];
      this.wordId = -1;
      this.ids = new ArrayList<>();
    }

    private void put(final char c, final TrieNode child) {
      this.children[c - 'a'] = child;
    }

    private boolean contains(final char c) {
      return this.children[c - 'a'] != null;
    }

    private TrieNode get(final char c) {
      return this.children[c - 'a'];
    }

    private void setWordId(int wordId) {
      this.wordId = wordId;
    }

    private int getWordId() {
      return this.wordId;
    }

    private void addId(int id) {
      this.ids.add(id);
    }

    private List<Integer> getIds() {
      return this.ids;
    }
  }
}
