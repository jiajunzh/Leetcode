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
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences
 * from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list
 * of the words [beginWord, s1, s2, ..., sk].
 *
 * 2. Examples
 * Example 1
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 *
 * Example 2
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * 3. Constraints
 * 1 <= beginWord.length <= 5
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 500
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 * The sum of all shortest transformation sequences does not exceed 105.
 */
public class WordLadderII {

    /**
     * 1. Approach
     * BFS + Backtracking
     *
     * 2. Complexity
     * - Time O(L * S^2 + A)
     * - Space O(L * S^2)
     * 
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // Step 1 - Build adjacency graph
        final Map<String, List<String>> graph = buildGraph(wordList);
        // Step 2 - BFS => Find the endWord. However, also build a sequence graph
        final Set<String> wordSet = new HashSet<>(wordList);
        final Map<String, List<String>> sequences = new HashMap<>();
        bfs(beginWord, endWord, wordSet, sequences, graph);
        // Step 3 - Backtracking
        final List<List<String>> result = new ArrayList<>();
        backtrack(result, beginWord, endWord, sequences, new ArrayList<>());
        return result;
    }

    private void backtrack(final List<List<String>> result, final String beginWord, final String endWord, Map<String, List<String>> sequences, final List<String> path) {
        if (endWord.equals(beginWord)) {
            final List<String> pathCopy = new ArrayList<>(path);
            pathCopy.add(beginWord);
            Collections.reverse(pathCopy);
            result.add(pathCopy);
            return;
        }

        path.add(endWord);
        final List<String> prevList = sequences.getOrDefault(endWord, new ArrayList<>());
        for (String w : prevList) {
            backtrack(result, beginWord, w, sequences, path);
        }
        path.remove(path.size() - 1);
    }

    private void bfs(String beginWord, String endWord, Set<String> wordSet, Map<String, List<String>> sequences, Map<String, List<String>> graph) {
        final Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            final Set<String> visited = new HashSet<>();
            for (int i = 0; i < size; i++) {
                final String curr = queue.poll();
                if (endWord.equals(curr)) return;
                for (int j = 0; j < curr.length(); j++) {
                    final String key = curr.substring(0, j) + "*" + curr.substring(j + 1, curr.length());
                    final List<String> neighbors = graph.getOrDefault(key, new ArrayList<>());
                    for (String w : neighbors) {
                        if (wordSet.contains(w) && !w.equals(curr)) {
                            final List<String> prevList = sequences.getOrDefault(w, new ArrayList<>());
                            sequences.put(w, prevList);
                            prevList.add(curr);
                            visited.add(w);
                        }
                    }
                }
            }
            for (String word : visited) {
                queue.offer(word);
                wordSet.remove(word);
            }
        }
    }

    private Map<String, List<String>> buildGraph(List<String> wordList) {
        final Map<String, List<String>> graph = new HashMap<>();
        for (String w : wordList) {
            for (int i = 0; i < w.length(); i++) {
                final String key = w.substring(0, i) + "*" + w.substring(i + 1, w.length());
                final List<String> listWithKey = graph.getOrDefault(key, new ArrayList<>());
                graph.put(key, listWithKey);
                listWithKey.add(w);
            }
        }
        return graph;
    }
}
