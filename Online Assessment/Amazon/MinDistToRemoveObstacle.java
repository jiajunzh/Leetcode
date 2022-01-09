import java.util.LinkedList;
import java.util.Queue;

public class MinDistToRemoveObstacle {
	private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public int minDist(int numRows, int numCols, int[][] lot) {
		if (numRows == 0 || numCols == 0) return 0;
		boolean[][] visited = new boolean[numRows][numCols];
 		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{0, 0});
		visited[0][0] = true;
		int steps = 0;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] tmp = queue.poll();
				if (lot[tmp[0]][tmp[1]] == 9) return steps;
				for (int[] dir : dirs) {
					int newX = tmp[0] + dir[0];
					int newY = tmp[1] + dir[1];
					
					if (newX >= 0 && newX < numRows && newY >=0 && newY < numCols && !visited[newX][newY] && lot[newX][newY] != 0) {
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
		int[][] islands = {{9,0,0}, {1,0,0}, {0,9,0}};
		MinDistToRemoveObstacle test = new MinDistToRemoveObstacle();
		System.out.println(test.minDist(3, 3, islands));
	}
}
