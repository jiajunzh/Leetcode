import java.util.PriorityQueue;

public class MergeTwoFile {
    public static int minCost(int[] ropes) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int rope: ropes) {
            pq.offer(rope);
        }
        int minCost = 0;
        while (pq.size() > 1) {
            int merged = pq.poll() + pq.poll();
            minCost += merged;
            pq.offer(merged);
        }
        return minCost;
    }

    public static void main(String[] args) {
        int[] ropes = new int[]{2, 2, 3, 3};
        MergeTwoFile test = new MergeTwoFile();
        System.out.println(test.minCost(ropes));
    }
}
