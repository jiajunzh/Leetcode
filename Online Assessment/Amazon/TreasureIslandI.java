import java.util.LinkedList;
import java.util.Queue;

public class TreasureIslandI {
    public final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int treasureIsland(char[][] island) {
        int m = island.length;
        if (m == 0) return 0;
        int n = island[0].length;
        
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                int x = point[0];
                int y = point[1];
                if (island[x][y] == 'X') return steps;
                
                for (int[] dir: dirs) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY] && island[newX][newY] != 'D') {
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        char[][] islands = {{'O','O','O','O'}, {'D','D','D','O'}, {'O','O','O','O'}, {'X','D','D','O'}};
        TreasureIslandI test = new TreasureIslandI();
        System.out.println(test.treasureIsland(islands));
    }
}
