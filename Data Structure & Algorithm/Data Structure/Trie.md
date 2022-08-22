# Trie

## Introduction 
Trie is a special form of N-ary tree, also called as prefix tree. It is generally used to store strings and commonly used in applications such as autocomplete, spell checker, ip routing and etc.

Each Trie node:
- Represents a string (a prefix)
- Have several children nodes while the paths to different children nodes represent different characters
- All descendants of a node have a common prefix of the string associated with that node

## Representation 
Please note we might want to add a boolean flag to both of the approaches below as not all the string represented by Trie nodes are meaningful.

### Array Based Trie
**Example Code**
```
class TrieNode {
    // change this value to adapt to different cases
    public static final N = 26;
    public TrieNode[] children = new TrieNode[N];
    
    // you might need some extra values according to different cases
};

/** Usage:
 *  Initialization: TrieNode root = new TrieNode();
 *  Return a specific child node with char c: root.children[c - 'a']
 */
```
**Key Notes**
- Fast access 
- Not all children nodes are needed, waste of space 

### Map Based Trie
**Example Code**
```
class TrieNode {
    public Map<Character, TrieNode> children = new HashMap<>();
    
    // you might need some extra values according to different cases
};

/** Usage:
 *  Initialization: TrieNode root = new TrieNode();
 *  Return a specific child node with char c: root.children.get(c)
 */
```
**Key Notes**
- A little slower than array
- Save space 
- Flexible as it is not limited by fixed length and range

## Trie Operations

### Insertion 
To insert a string s into Trie: 
- start with root node, choose a child or add a new child node depending on S[0] and then go down to second and third ... and so on.
- Traverse all characters in S sequentially and reach the end.
- The end node will be the node which represents the string S.
```
1. Initialize: cur = root
2. for each char c in target string S:
3.      if cur does not have a child c:
4.          cur.children[c] = new Trie node
5.      cur = cur.children[c]
6. cur is the node which represents the string S
```

### Search 
1. Search Prefix 
- Start with root node and go down the tree depending on the given prefix
- If we cannot find the child node, search fails
- Otherwise, search succeeds
```
1. Initialize: cur = root
2. for each char c in target string S:
3.   if cur does not have a child c:
4.     search fails
5.   cur = cur.children[c]
6. search successes
```
2. Search Word
The word could be treated as the prefix above and searched using the same way above. 
- If search fails, then no words start with the target word, meaning the target word is definitely not in the Trie
- If search succeeds, check if the target word is only a prefix of words in Trie or it is exactly a word. (A boolean flag defined in Trie Node might help)

### Full Implementation 
```
class Trie {
    
    private final TrieNode root;
    
    public Trie() {
        this.root = new TrieNode();
    }
    
    /**
     * - Time O(m) where m is the key length
     * - Space O(m)
     */
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
    
    /**
     * - Time O(m) where m is the key length
     * - Space O(1)
     */
    public boolean search(String word) {
        final TrieNode finalNode = searchPrefix(word);
        return finalNode != null && finalNode.isEnd();
    }
    
    /**
     * - Time O(m) where m is the key length
     * - Space O(1)
     */
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
```

## Resources 
- https://leetcode.com/problems/implement-trie-prefix-tree/solution/
- https://leetcode.com/explore/learn/card/trie/147/basic-operations/1047/



