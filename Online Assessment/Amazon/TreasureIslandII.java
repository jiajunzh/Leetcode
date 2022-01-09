import java.util.LinkedList;
import java.util.Queue;

public class TreasureIslandII {

    private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int shortestPath(char[][] islands) {
        if (islands.length == 0 || islands[0].length== 0) return -1;
        int m = islands.length;
        int n = islands[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = addSource(islands, visited);
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                if (islands[node[0]][node[1]] == 'X') return steps;
                for (int[] dir: dirs) {
                    int newX = node[0] + dir[0];
                    int newY = node[1] + dir[1];
                    
                    if (shouldVisit(newX, newY, m, n, visited, islands)) {
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    
    public boolean shouldVisit(int i, int j, int m, int n, boolean[][] visited, char[][] islands) {
        return i >= 0 && i < m && j >= 0 && j < n && !visited[i][j] && islands[i][j] != 'D';
    }
    
    public Queue<int[]> addSource(char[][] islands, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < islands.length; i++) {
            for (int j = 0; j < islands[0].length; j++) {
                if (islands[i][j] == 'S') {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        TreasureIslandII test = new TreasureIslandII();
        char[][] islands = new char[][]{{'S','O','O','S','S'},{'D','O','D','D','D'},{'D','O','O','O','O'},{'X','D','D','O','O'},{'X','D','D','D','O'}};
        System.out.println(test.shortestPath(islands));
    }
}
