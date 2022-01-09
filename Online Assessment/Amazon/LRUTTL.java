import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LRUTTL {

    private Node head;
    private Node tail;
    private Map<Integer, Node> cache;
    private PriorityQueue<Node> pq;
    private int capacity;
    
    public LRUTTL(int capacity) {
        this.head = new Node(-1, -1, -1, null, null);
        this.tail = this.head;
        this.cache = new HashMap<>();
        this.capacity = capacity;
        this.pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.ttl - o2.ttl;
            }
        });
    }
    
    public int get(int key, int ts) {
//		while (!pq.isEmpty() && pq.peek().ttl < ts) {
//			Node deleted = pq.poll();
//			delete(deleted);
//		}
//		System.out.println("!" + cache.get(2));
        if (!cache.containsKey(key)) return -1;
        Node target = cache.get(key);
        if (target.ttl < ts) return -1;
        moveToHead(target);
        return target.value;
    }
    
    public void put(int key, int value, int expireTimestamp) {
        Node target = null;
        if (!cache.containsKey(key)) {
            target = new Node(key, value, expireTimestamp, null, null);
            addFirst(target);
        } else {
            target = cache.get(key);
            target.value = value;
            target.ttl = expireTimestamp;
            moveToHead(target);
        }
        
        if (cache.size() > capacity) {
            delete(tail);
        }
    }
    
    private void moveToHead(Node t) {
        delete(t);
        addFirst(t);
    }
    
    private void addFirst(Node t) {
        System.out.println("add" + t.key);
        cache.put(t.key, t);
        pq.offer(t);
        t.succ = head.succ;
        if (head.succ != null) {
            head.succ.prev = t;
        } else {
            tail = t;
        }
        head.succ = t;
        t.prev = head;
    }
    
    private void delete(Node t) {
        System.out.println("delete" + t.key);
        cache.remove(t.key);
        pq.remove(t);
        if (t.succ != null) {
            t.succ.prev = t.prev;
        } else {
            tail = t.prev;
        }
        t.prev.succ = t.succ;
    }

    private class Node {
        private int key;
        private int value;
        private int ttl;
        private Node prev;
        private Node succ;

        Node(int key, int value, int ttl, Node prev, Node succ) {
            this.key = key;
            this.value = value;
            this.ttl = ttl;
            this.prev = prev;
            this.succ = succ;
        }
    }
    
    public static void main(String[] args) {
        LRUTTL cache = new LRUTTL(2);

        cache.put(1, 3, 10);
        cache.put(2, 4, 5);
        System.out.println(cache.get(1, 1));
        System.out.println(cache.get(1, 11));
        System.out.println(cache.get(2, 5));
        System.out.println(cache.get(2, 6));
        cache.put(3, 100, 10);
        System.out.println(cache.get(2, 1));
        cache.put(4, 4, 10);
        System.out.println(cache.get(1, 1));
        System.out.println(cache.get(3, 1));
        System.out.println(cache.get(4, 1));

    }
}
