package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class DesignSearchAutocompleteSystem1 {
  
  private static final int HOT_SENTENCES_NUM = 3;

  private final TrieNode root;
  private StringBuilder sb;
  private TrieNode curr;

  /**
   * 1. Approach 
   * Trie + Caching/Updating During Insertion.
   * 
   * Unlike version DesignSearchAutocompleteSystem2 which search from top to bottom each time input() is called. This
   * approach calculates the hottest sentences globally for each node during insertion, which generates O(1) upon 
   * input() is called later.
   * 
   * This approach sacrifice a little performance in write but trade for better performance on read, which should 
   * generate better performance overall considering the nature of autocomplete system.
   * 
   * 2. Complexity 
   * - Time 
   *  - O(S) for insertion. An optimization done is move the cursor to current character instead of searching from root
   *    to the current char each time.
   *  - O(1) for input incomplete sentence
   * - Space O(N * S)
   *
   * @param sentences
   * @param times
   */
  public DesignSearchAutocompleteSystem1(String[] sentences, int[] times) {
    this.root = new TrieNode();
    this.sb = new StringBuilder();
    final int n = sentences.length;
    for (int i = 0; i < n; i++) {
      insert(sentences[i], times[i]);
    }
    curr = root;
  }

  private void insert(final String sentence, final int times) {
    final List<TrieNode> visited = new ArrayList<>();
    TrieNode curr = root;
    visited.add(curr);
    for (final char c : sentence.toCharArray()) {
      if (! curr.contains(c)) {
        curr.put(c, new TrieNode());
      }
      curr = curr.get(c);
      visited.add(curr);
    }
    curr.sentence = sentence;
    curr.times += times;
    for (TrieNode visit : visited) {
      visit.update(curr);
    }
  }

  public List<String> input(char c) {
    if (c == '#') {
      insert(sb.toString(), 1);
      this.sb = new StringBuilder();
      this.curr = root;
      return new ArrayList<>();
    } else {
      this.sb.append(c);
      this.curr = curr.get(c) == null ? new TrieNode() : curr.get(c);
      return curr.hottestSentences.stream().map(TrieNode::getSentence).collect(Collectors.toList());
    }
  }

  private static class TrieNode {

    private final TrieNode[] children;
    private final List<TrieNode> hottestSentences;
    private int times;
    private String sentence;

    private TrieNode() {
      this.children = new TrieNode[128];
      this.hottestSentences = new ArrayList<>();
      this.times = 0;
      this.sentence = null;
    }

    private boolean contains(final char c) {
      return this.children[c] != null;
    }

    private TrieNode get(final char c) {
      return this.children[c];
    }

    private void put(final char c, final TrieNode child) {
      this.children[c] = child;
    }

    private String getSentence() {
      return this.sentence;
    }

    private void update(final TrieNode node) {
      if (!this.hottestSentences.contains(node)) {
        this.hottestSentences.add(node);
      }
      this.hottestSentences.sort((a, b) -> (a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times));
      if (this.hottestSentences.size() > HOT_SENTENCES_NUM) {
        this.hottestSentences.remove(this.hottestSentences.size() - 1);
      }
    }
  }
}
