import java.util.LinkedList;
import java.util.Queue;

public class ReachingPoints {
    /*
     * Complete the 'canReach' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER x1
     *  2. INTEGER y1
     *  3. INTEGER x2
     *  4. INTEGER y2
     */
    private static final String REACHABLE = "Yes";
    private static final String UNREACHABLE = "No";

    public static String canReach(int x1, int y1, int x2, int y2) {
        // Write your code here
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x1, y1});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            if (x > x2 || y > y2) {
                continue;
            }
            if (x == x2 && y == y2) {
                return REACHABLE;
            }

            queue.offer(new int[]{x + y, y});
            queue.offer(new int[]{x, x + y});
        }

        return UNREACHABLE;
    }
}
