package problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1. Problem
 * Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').
 *
 * You are given a string array sentences and an integer array times both of length n where sentences[i] is a previously typed sentence and times[i] is the corresponding number of times the sentence was typed. For each input character except '#', return the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed.
 *
 * Here are the specific rules:
 *
 * The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same hot degree, use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, return as many as you can.
 * When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
 * Implement the AutocompleteSystem class:
 *
 * AutocompleteSystem(String[] sentences, int[] times) Initializes the object with the sentences and times arrays.
 * List<String> input(char c) This indicates that the user typed the character c.
 * Returns an empty array [] if c == '#' and stores the inputted sentence in the system.
 * Returns the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed. If there are fewer than 3 matches, return them all.
 *
 * 2. Examples 
 * Example 1
 * Input
 * ["AutocompleteSystem", "input", "input", "input", "input"]
 * [[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
 * Output
 * [null, ["i love you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]
 *
 * Explanation
 * AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]);
 * obj.input("i"); // return ["i love you", "island", "i love leetcode"]. There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
 * obj.input(" "); // return ["i love you", "i love leetcode"]. There are only two sentences that have prefix "i ".
 * obj.input("a"); // return []. There are no sentences that have prefix "i a".
 * obj.input("#"); // return []. The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 *
 * 3. Constraints
 * n == sentences.length
 * n == times.length
 * 1 <= n <= 100
 * 1 <= sentences[i].length <= 100
 * 1 <= times[i] <= 50
 * c is a lowercase English letter, a hash '#', or space ' '.
 * Each tested sentence will be a sequence of characters c that end with the character '#'.
 * Each tested sentence will have a length in the range [1, 200].
 * The words in each input sentence are separated by single spaces.
 * At most 5000 calls will be made to input.
 */
public class DesignSearchAutocompleteSystem2 {
  
  private static final int HOT_SENTENCES_NUM = 3;
  
  private final TrieNode root;
  private StringBuilder sb;

  /**
   * 1. Approach 
   * Modified Trie + Priority Queue + DFS. In this modified Trie, we assign additional two variables called:
   * - score => indicates possibility of having hot sentences, it is a sum of all hot degrees of its descedants 
   * - hotDegree => The frequency of sentences showing up
   * 
   * 1). Insertion => Same as regular Trie
   * 2). Search
   * - Find prefix end node O(S)
   * - Starting from end node, search downwards for hottest sentences O(128^(LongestLength - S)). But actual performance
   *   should be much better due to the pruning
   * 
   * 
   * @param sentences
   * @param times
   */
  public DesignSearchAutocompleteSystem2(String[] sentences, int[] times) {
    this.root = new TrieNode();
    this.sb = new StringBuilder();
    final int n = sentences.length;
    for (int i = 0; i < n; i++) {
      insert(sentences[i], times[i]);
    }
  }

  private void insert(final String sentence, final int times) {
    TrieNode curr = root;
    for (final char c : sentence.toCharArray()) {
      if (! curr.contains(c)) {
        curr.put(c, new TrieNode());
      }
      curr.score += times;
      curr = curr.get(c);
    }
    curr.score += times;
    curr.sentence = sentence;
    curr.hotDegree += times;
  }

  public List<String> input(char c) {
    if (c == '#') {
      insert(sb.toString(), 1);
      this.sb = new StringBuilder();
      return new ArrayList<>();
    } else {
      this.sb.append(c);
      return searchHottestSentences(sb.toString());
    }
  }

  private List<String> searchHottestSentences(final String sentence) {
    final PriorityQueue<TrieNode> pq = new PriorityQueue<>((a, b) -> (a.hotDegree == b.hotDegree ? b.sentence.compareTo(a.sentence) : a.hotDegree - b.hotDegree));
    TrieNode prefixEndNode = findPrefixEndNode(sentence);
    dfs(pq, prefixEndNode);
    final List<String> result = new ArrayList<>();
    while (!pq.isEmpty()) {
      result.add(pq.poll().sentence);
    }
    Collections.reverse(result);
    return result;
  }

  private TrieNode findPrefixEndNode(final String sentence) {
    TrieNode curr = root;
    for (final char c : sentence.toCharArray()) {
      if (! curr.contains(c)) {
        return null;
      }
      curr = curr.get(c);
    }
    return curr;
  }

  private void dfs(final PriorityQueue<TrieNode> pq, final TrieNode curr) {
    if (curr == null) return;
    if (curr.hotDegree > 0) {
      pq.offer(curr);
      if (pq.size() > HOT_SENTENCES_NUM) pq.poll();
    }
    final List<Character> orderedChildrenKeys = curr.getOrderedChildrenKeys();
    for (char c : orderedChildrenKeys) {
      TrieNode child = curr.get(c);
      if (pq.size() == HOT_SENTENCES_NUM && pq.peek().hotDegree > child.score) {
        return;
      } 
      dfs(pq, child);
    }
  }

  private static class TrieNode {

    private final Map<Character, TrieNode> children;
    private int score; // The score that implies which node to look at with higher priority
    private int hotDegree;
    private String sentence;

    private TrieNode() {
      this.children = new HashMap<>();
      this.score = 0;
      this.hotDegree = 0;
      this.sentence = null;
    }

    private boolean contains(final char c) {
      return this.children.containsKey(c);
    }

    private TrieNode get(final char c) {
      return this.children.get(c);
    }

    private void put(final char c, final TrieNode child) {
      this.children.put(c, child);
    }

    private List<Character> getOrderedChildrenKeys() {
      final List<Character> childrenChars = new ArrayList<>(children.keySet());
      childrenChars.sort((a, b) -> this.children.get(b).score - this.children.get(a).score);
      return childrenChars;
    }
  }
}
