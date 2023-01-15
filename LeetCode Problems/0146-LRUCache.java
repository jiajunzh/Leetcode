package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to
 * the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * 2. Examples
 * Example 1
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 * 3. Constraints
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put.
 */
public class LRUCache {

    private final int capacity;
    private final Map<Integer, DLListNode<Integer, Integer>> cache;
    private int size;
    private final DLListNode<Integer, Integer> head, tail;

    /**
     * 1. Approach
     * Doubly Linked List + HashMap => Essentially LinkedHashMap
     *
     * 2. Complexity
     * - Time O(1) for constructor, O(1) for get and O(1) for put
     * - Space O(C)
     *
     * @param capacity
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.size = 0;
        this.head = new DLListNode<>(-1, -1);
        this.tail = new DLListNode<>(-1, -1);
        this.head.successor = this.tail;
        this.tail.predecessor = this.head;
    }

    public int get(int key) {
        if (!this.cache.containsKey(key)) {
            return -1;
        }
        final DLListNode<Integer, Integer> node = this.cache.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (!this.cache.containsKey(key)) {
            final DLListNode<Integer, Integer> newNode = new DLListNode<>(key, value);
            this.size++;
            this.cache.put(key, newNode);
            addFirst(newNode);
            if (this.capacity < this.size) {
                this.size--;
                this.cache.remove(this.tail.predecessor.key);
                remove(this.tail.predecessor);
            }
        } else {
            final DLListNode<Integer, Integer> node = this.cache.get(key);
            node.value = value;
            moveToHead(node);
        }
    }

    private void moveToHead(final DLListNode<Integer, Integer> node) {
        remove(node);
        addFirst(node);
    }

    private void remove(final DLListNode<Integer, Integer> node) {
        node.predecessor.successor = node.successor;
        node.successor.predecessor = node.predecessor;
    }

    private void addFirst(final DLListNode<Integer, Integer> node) {
        node.successor = this.head.successor;
        node.predecessor = this.head;
        this.head.successor.predecessor = node;
        this.head.successor = node;
    }

    private static class DLListNode<K, V> {
        private K key;
        private V value;
        private DLListNode successor;
        private DLListNode predecessor;

        private DLListNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.successor = null;
            this.predecessor = null;
        }
    }
}
