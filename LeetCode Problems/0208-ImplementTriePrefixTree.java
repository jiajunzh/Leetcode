package problem;

/**
 * 1. Problem 
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a
 * dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 * 2. Examples 
 * Example 1
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 * 3. Constraints
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 */
public class ImplementTriePrefixTree {

  /**
   * 1. Approach 
   * Map based Trie
   * 
   * 2. Complexity 
   * - Time O(1) for constructor, O(L) for insert where L is the length of word, O(L) for search and startWith
   * - Space O(N * L)
   */
  private final TrieNode root;

  public ImplementTriePrefixTree() {
    this.root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode curr = root;
    for (char c : word.toCharArray()) {
      if (!curr.containsKey(c)) {
        curr.put(c, new TrieNode());
      }
      curr = curr.get(c);
    }
    curr.setEnd();
  }

  public boolean search(String word) {
    final TrieNode finalNode = searchPrefix(word);
    return finalNode != null && finalNode.isEnd();
  }

  public boolean startsWith(String prefix) {
    final TrieNode finalNode = searchPrefix(prefix);
    return finalNode != null;
  }

  private TrieNode searchPrefix(String prefix) {
    TrieNode curr = root;
    for (char c : prefix.toCharArray()) {
      if (!curr.containsKey(c)) {
        return null;
      }
      curr = curr.get(c);
    }
    return curr;
  }
  

  public class TrieNode {

    private static final int R = 26;
    private final TrieNode[] links;
    private boolean isEnd;

    public TrieNode() {
      this.links = new TrieNode[R];
    }

    public boolean containsKey(char c) {
      return this.links[c - 'a'] != null;
    }

    public TrieNode get(char c) {
      return this.links[c - 'a'];
    }

    public void put(char c, TrieNode node) {
      this.links[c - 'a'] = node;
    }

    public void setEnd() {
      this.isEnd = true;
    }

    public boolean isEnd() {
      return this.isEnd;
    }
  }
}
