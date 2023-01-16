package problem;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Problem
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present.
 * When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting
 * a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least
 * recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the
 * smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter
 * for a key in the cache is incremented either a get or put operation is called on it.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 * 2. Examples
 * Example 1
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 *                  // cache=[4,3], cnt(4)=2, cnt(3)=3
 *
 * 3. Constraints
 * 0 <= capacity <= 104
 * 0 <= key <= 105
 * 0 <= value <= 109
 * At most 2 * 105 calls will be made to get and put.
 */
public class LFUCache {

    private final Map<Integer, DLListNode> cache;
    private final Map<Integer, DLList> freq;
    private final int capacity;
    private int minFreq;

    /**
     * 1. Approach
     * LinkedHashMap (HashMap + Doubly Linked List) => Variant of LRU Cache
     *
     * 2. Complexity
     * - Time O(1) for constructor, get and put
     * - Space O(N)
     *
     * @param capacity
     */
    public LFUCache(int capacity) {
        this.cache = new HashMap<>();
        this.freq = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = Integer.MAX_VALUE;
    }

    public int get(int key) {
        if (!this.cache.containsKey(key)) {
            return -1;
        }
        final DLListNode node = this.cache.get(key);
        updateFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (this.capacity == 0) return;
        if (this.cache.containsKey(key)) {
            final DLListNode node = this.cache.get(key);
            node.value = value;
            updateFrequency(node);
        } else {
            evict();
            final DLListNode node = new DLListNode(key, value);
            this.cache.put(key, node);
            final DLList list = this.freq.getOrDefault(node.freq, new DLList());
            this.freq.put(node.freq, list);
            list.addNodeToHead(node);
            this.minFreq = node.freq;
        }
    }

    private void evict() {
        if (this.cache.size() >= this.capacity) {
            final DLList minFreqList = this.freq.get(this.minFreq);
            final DLListNode removedNode = minFreqList.removeLast();
            this.cache.remove(removedNode.key);
            if (minFreqList.size == 0) {
                this.freq.remove(removedNode.freq);
            }
        }
    }

    private void updateFrequency(final DLListNode node) {
        final int oldFreq = node.freq;
        node.freq++;
        final DLList oldList = this.freq.get(oldFreq);
        final DLList newList = this.freq.getOrDefault(oldFreq + 1, new DLList());
        this.freq.put(oldFreq + 1, newList);
        oldList.removeNode(node);
        newList.addNodeToHead(node);
        if (oldList.size == 0) {
            this.freq.remove(oldFreq);
            if (this.minFreq == oldFreq) {
                minFreq++;
            }
        }
    }

    private static class DLList {

        private final DLListNode head;
        private final DLListNode tail;
        private int size;

        private DLList() {
            this.head = new DLListNode(0, 0);
            this.tail = new DLListNode(0, 0);
            this.head.next = this.tail;
            this.tail.prev = this.head;
            this.size = 0;
        }

        private void removeNode(DLListNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        private void addNodeToHead(DLListNode node) {
            node.prev = this.head;
            node.next = this.head.next;
            this.head.next.prev = node;
            this.head.next = node;
            size++;
        }

        private DLListNode removeLast() {
            DLListNode tmp = this.tail.prev;
            removeNode(tmp);
            return tmp;
        }
    }

    private static class DLListNode {
        private final int key;
        private int value;
        private DLListNode prev;
        private DLListNode next;
        private int freq;

        private DLListNode(final int key, final int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
            this.freq = 1;
        }
    }
}
