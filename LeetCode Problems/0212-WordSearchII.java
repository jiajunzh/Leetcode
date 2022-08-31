package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1. Problem 
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or 
 * vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * 2. Examples
 * Example 1
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 * 
 * Example 2
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 *
 * 3. Constraints
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 */
public class WordSearchII {
  
  private static final int[][] DIRS = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

  private final TrieNode root = new TrieNode();

  /**
   * 1. Approach 
   * Backtracking + Trie.
   * 
   * A naive way is to backtrack as problem WordSearchI and then check if the current examined word exist in words.
   * This approach would presumably take O(M * 4 * 3 ^ (L - 1)) where M is the number of cells and L is the maximum 
   * length of word (i.e. 10 in this problem). However, this approach still requires to traverse all possible directions,
   * which might be unnecessary in some cases.
   * 
   * Another idea which is used here is to build up a trie to easily retrieve the prefix from word. It helps with 
   * eliminating some directions that you don't need to explore.
   * 
   * 2. Complexity 
   * - Time O(M * 4 * 3 ^ (L - 1))
   * - Space O(W * L)
   * 
   * 3. Optimization 
   * - A further optimization is to trim the Trie tree along with backtracking as the performance highly depends on the 
   *   size of Trie
   *   if (next.isEmpty()) {
   *     curr.remove(c);
   *   }
   *   
   * @param board
   * @param words
   * @return
   */
  public List<String> findWords(char[][] board, String[] words) {
    final List<String> result = new ArrayList<>();

    for (String word : words) {
      insert(word);
    }

    final int n = board.length, m = board[0].length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (root.contains(board[i][j])) {
          backtrack(board, i, j, result, root);
        }
      }
    }

    return result;
  }

  private void insert(final String word) {
    TrieNode curr = root;
    for (final char c : word.toCharArray()) {
      if (!curr.contains(c)) {
        curr.put(c, new TrieNode());
      }
      curr = curr.get(c);
    }
    curr.setWord(word);
  }

  private void backtrack(char[][] board, int i, int j, List<String> result, TrieNode curr) {
    final int n = board.length, m = board[0].length;
    if (i < 0 || i >= n || j < 0 || j >= m || !curr.contains(board[i][j])) return;
    final char c = board[i][j];
    TrieNode next = curr.get(c);
    if (next.word != null) {
      result.add(next.word);
      next.setWord(null);
    }
    board[i][j] = '-';
    for (int[] dir : DIRS) {
      int x = dir[0] + i;
      int y = dir[1] + j;
      backtrack(board, x, y, result, next);
    }
    board[i][j] = c;
    if (next.isEmpty()) {
      curr.remove(c);
    }
  }

  private class TrieNode {

    private final Map<Character, TrieNode> children;
    private String word;

    private TrieNode() {
      this.children = new HashMap<>();
      this.word = null;
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

    private void remove(final char c) {
      this.children.remove(c);
    }

    private String getWord() {
      return this.word;
    }

    private void setWord(final String word) {
      this.word = word;
    }

    private boolean isEmpty() {
      return this.children.isEmpty();
    }
  }
}
