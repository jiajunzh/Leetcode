package problem;

/**
 * 1. Problem 
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 * 2. Examples
 * Example
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 * 3. Constraints
 * 1 <= word.length <= 25
 * word in addWord consists of lowercase English letters.
 * word in search consist of '.' or lowercase English letters.
 * There will be at most 3 dots in word for search queries.
 * At most 104 calls will be made to addWord and search.
 */
public class DesignAddAndSearchWordsDataStructure {

  private final TrieNode root;

  /**
   * 1. Approach
   * Trie
   * 
   * 2. Complexity
   * - Time O(S) for add word and O(26^S) for search
   * - Space O(S * N)
   */
  public DesignAddAndSearchWordsDataStructure() {
    this.root = new TrieNode();
  }

  public void addWord(String word) {
    TrieNode curr = root;
    for (final char c : word.toCharArray()) {
      if (!curr.contains(c)) {
        curr.put(c, new TrieNode());
      }
      curr = curr.get(c);
    }
    curr.setEnd();
  }

  public boolean search(String word) {
    return search(word.toCharArray(), 0, root);
  }

  private boolean search(char[] chars, int i, TrieNode curr) {
    if (i == chars.length) return curr.isEnd();
    final char c = chars[i];
    if (c == '.') {
      for (int j = 0; j < 26; j++) {
        if (curr.children[j] != null) {
          if (search(chars, i + 1, curr.get((char) (j + 'a')))) {
            return true;
          }
        }
      }

      return false;
    } else {
      return curr.contains(c) && search(chars, i + 1, curr.get(c));
    }
  }

  private static class TrieNode {

    private final TrieNode[] children;
    private boolean isEnd;

    private TrieNode() {
      this.children = new TrieNode[26];
      this.isEnd = false;
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

    private boolean isEnd() {
      return this.isEnd;
    }

    private void setEnd() {
      this.isEnd = true;
    }
  }
}
