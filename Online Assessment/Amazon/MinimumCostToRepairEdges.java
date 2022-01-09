import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class MinimumCostToRepairEdges {
    private int[] root;
    private int connected;

    public int minimumCost(int N, int[][] connections, int[][] brokenEdges) {
        int minCost = 0;
        root = new int[N + 1];
        connected = 0;
        Set<String> brokenSet = new HashSet<>();
        for (int[] brokenEdge: brokenEdges) {
            brokenSet.add(brokenEdge[0] + "-" + brokenEdge[1]);
            brokenSet.add(brokenEdge[1] + "-" + brokenEdge[0]);
        }
        
        Arrays.sort(brokenEdges, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[2]-arr2[2];
            }
        });
    
        for (int i = 0; i < N + 1; i ++) root[i] = i;
        for (int[] e : connections) {
            if (!brokenSet.contains(e[0] + "-" + e[1])) {
                union(e[0], e[1]);
            }
        }
    
        for (int[] e :brokenEdges) {
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
        MinimumCostToRepairEdges out = new MinimumCostToRepairEdges();
        int n1 = 6;
        int[][] newEdges1 = new int[][]{{1, 6, 410}, {2, 4, 800}};
        int[][] connections1 = new int[][]{{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 6}, {2, 4}};
        System.out.println(out.minimumCost(n1, connections1, newEdges1));
        
        int n2 = 5;
        int[][] newEdges2 = new int[][]{{1, 2, 12}, {3, 4, 30}, {1, 5, 8}};
        int[][] connections2 = new int[][]{{1,2},{2,3},{3,4},{4,5},{1,5}};
        System.out.println(out.minimumCost(n2, connections2, newEdges2));

        int n3 = 6;
        int[][] newEdges3 = new int[][]{{1, 5, 110}, {2, 4, 84}, {3, 4, 79}};
        int[][] connections3 = new int[][]{{1, 2}, {2, 3}, {4, 5}, {5, 6}, {1, 5}, {2, 4}, {3, 4}};
        System.out.println(out.minimumCost(n3, connections3, newEdges3));
    }
}
