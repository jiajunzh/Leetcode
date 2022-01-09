import java.util.Arrays;
import java.util.Comparator;

public class ConnectCitiesWithMinimumCost2 {
    private int[] root;
    private int connected;
    
    public int minimumCost(int N, int[][] connections, int[][] newEdges) {
        int minCost = 0;
        root = new int[N + 1];
        connected = 0;
        Arrays.sort(newEdges, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[2]-arr2[2];
            }
        });
    
        for (int i = 0; i < N + 1; i ++) root[i] = i;
        for (int[] e : connections) {
            union(e[0], e[1]);
        }
        
        for (int[] e : newEdges) {
            if (union(e[0], e[1])) minCost += e[2];
        }
        
        return connected == N - 1 ? minCost : -1;
    }
    
    public boolean union(int node1, int node2) {
        int parent1 = find(node1);
        int parent2 = find(node2);
        if (parent1 != parent2) {
            root[parent2] = parent1;
            connected++;
            return true;
        }
        return false;
    }

    public int find(int child) {
        if(root[child] != child) {
            root[child] = find(root[child]); // split the tree
        }
        return root[child];
    }
    
    public static void main(String[] args) {
        int[][] newEdges = new int[][]{{1, 2, 5}, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}};
        int[][] connections = new int[][]{{1,4},{4,5},{2,3}};
        ConnectCitiesWithMinimumCost2 out = new ConnectCitiesWithMinimumCost2();
        System.out.println(out.minimumCost(6, connections, newEdges));
    }
}
