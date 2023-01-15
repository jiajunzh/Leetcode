package problem;

import java.util.*;

/**
 * 1. Problem
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words
 * beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest
 * transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 * 2. Examples
 * Example 1
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 *
 * Example 2
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * 3. Constraints
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 */
public class WordLadder {

    /**
     * 1. Approach
     * BFS
     *
     * 2. Complexity
     * - Time O(L * S ^ 2)
     * - Space O(L * S ^ 2)
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        final Map<String, List<String>> graph = buildGraph(wordList);
        int length = 0;
        final Queue<String> queue = new LinkedList<>();
        final Set<String> visited = new HashSet<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            final int size = queue.size();
            length++;
            for (int i = 0; i < size; i++) {
                final String curr = queue.poll();
                for (int j = 0; j < curr.length(); j++) {
                    final String key = curr.substring(0, j) + "*" + curr.substring(j + 1, curr.length());
                    final List<String> mappedWords = graph.getOrDefault(key, new ArrayList<>());
                    for (String mappedWord : mappedWords) {
                        if (endWord.equals(mappedWord)) {
                            return length + 1;
                        }
                        if (!visited.contains(mappedWord)) {
                            queue.offer(mappedWord);
                            visited.add(mappedWord);
                        }
                    }
                }
            }
        }

        return 0;
    }

    private Map<String, List<String>> buildGraph(final List<String> wordList) {
        final Map<String, List<String>> graph = new HashMap<>();

        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                final String key = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
                final List<String> mappedWords = graph.getOrDefault(key, new ArrayList<>());
                mappedWords.add(word);
                graph.put(key, mappedWords);
            }
        }

        return graph;
    }

    /**
     * 1. Approach
     * Bidirectional BFS
     *
     * Two Edge Cases:
     * - endWord is not in the wordList: This needs to be checked upfront. Otherwise, it might generate a case where
     *   there should have been no ladder between begin and end word.
     * - Add endWord to visited set during initialization mainly because begin word is not guaranteed to be in the
     *   word list. For example, a -> c.
     *
     * 2. Complexity
     * - Time O(L * S ^ 2)
     * - Space O(L * S ^ 2)
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        final Map<String, List<String>> graph = buildGraph(wordList);
        final Queue<String> beginQueue = new LinkedList<>();
        final Set<String> beginVisited = new HashSet<>();
        final Queue<String> endQueue = new LinkedList<>();
        final Set<String> endVisited = new HashSet<>();
        int beginLen = 0, endLen = 0;
        beginQueue.offer(beginWord);
        endQueue.offer(endWord);
        endVisited.add(endWord);
        while (!beginQueue.isEmpty() || !endQueue.isEmpty()) {
            beginLen++;
            int totalLen = visitNextLevel(beginQueue, beginVisited, endVisited, graph, beginLen, endLen);
            if (totalLen > -1) return totalLen;
            endLen++;
            totalLen = visitNextLevel(endQueue, endVisited, beginVisited, graph, endLen, beginLen);
            if (totalLen > -1) return totalLen;
        }

        return 0;
    }

    private int visitNextLevel(final Queue<String> queue, final Set<String> visited, final Set<String> otherVisited, final Map<String, List<String>> graph, final int len, final int otherLen) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            final String curr = queue.poll();
            for (int j = 0; j < curr.length(); j++) {
                final String key = curr.substring(0, j) + "*" + curr.substring(j + 1, curr.length());
                final List<String> mappedWords = graph.getOrDefault(key, new ArrayList<>());
                for (String mappedWord : mappedWords) {
                    if (otherVisited.contains(mappedWord)) {
                        return len + otherLen + 1;
                    }
                    if (!visited.contains(mappedWord)) {
                        queue.offer(mappedWord);
                        visited.add(mappedWord);
                    }
                }
            }
        }
        return -1;
    }
}
