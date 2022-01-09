import java.util.Comparator;
import java.util.PriorityQueue;

public class LongestStringWithoutThreeConsecutiveLetters {
    private class Node{
        private int count;
        private char letter;
        
        Node(int count, char letter) {
            this.count = count;
            this.letter = letter;
        }
    }

//	public static String generateString(Map<Character, Integer> map) {
//		PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
//			new PriorityQueue<Map.Entry<Character, Integer>>((a, b) -> b.getValue() - a.getValue());
//	
//		int cnt = 0;
//		for (Map.Entry<Character, Integer> e: map.entrySet()) {
//			cnt += e.getValue();
//			maxHeap.add(e);
//		}
//	
//		// only one char can be on hold
//		Map.Entry<Character, Integer> onHold = null;
//	
//		StringBuilder sb = new StringBuilder();
//	
//		while (!maxHeap.isEmpty()) {
//			Map.Entry<Character, Integer> cur = maxHeap.poll();
//			sb.append(cur.getKey());
//	
//			if (onHold != null) {
//				maxHeap.add(onHold);
//				onHold = null;
//			}
//			int curValue = cur.getValue();
//			if (curValue > 1) {
//				cur.setValue(curValue-1);
//				if (sb.length() >= 2 && cur.getKey() == sb.charAt(sb.length()-2)) { // on hold
//					onHold = cur;
//				} else {  // add back to heap
//					maxHeap.add(cur);
//				}
//			}
//	
//		}
//		return sb.toString();
//	}

    public String generateString(int A, int B, int C) {
        StringBuilder sb = new StringBuilder();
        Node twoConseqSame = null;
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.count == o2.count) return o1.letter - o2.letter;
                return o2.count - o1.count;
            }
        });

        pq.offer(new Node(A, 'a'));
        pq.offer(new Node(B, 'b'));
        pq.offer(new Node(C, 'c'));
        
        while (!pq.isEmpty()) {
            Node letter = pq.poll();
            sb.append(letter.letter);
            letter.count--;
            
            if (twoConseqSame != null) {
                pq.offer(twoConseqSame);
                twoConseqSame = null;
            }
            
            if (letter.count > 0) {
                int len = sb.length();
                if (len < 2 || letter.letter != sb.charAt(len - 2)) {
                    pq.offer(letter);
                } else {
                    twoConseqSame = letter;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LongestStringWithoutThreeConsecutiveLetters test = new LongestStringWithoutThreeConsecutiveLetters();
//		Map<Character, Integer> map = new HashMap<>();
//		map.put('a', 1);
//		map.put('b', 2);
//		map.put('c', 100);
        System.out.println(test.generateString(1, 1, 100));
    }
    
}
