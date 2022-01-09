import java.util.*;

public class CriticalConnection {
    private int[] root;
    private int connected;

//    /**
//     * Brute Force => remove each connection to see if the graph connected
//     * O(E * (V + E))
//     * @param N
//     * @param newEdges
//     * @return
//     */
//	public List<List<Integer>> minimumCost(int N, int[][] newEdges) {
//		List<List<Integer>> result = new ArrayList<>();
//        root = new int[N + 1];
//        
//        for (int i = 0; i < newEdges.length; i++) {
//        	connected = 0;
//			for (int k = 0; k < N + 1; k++) root[k] = k;
//        	for (int j = 0; j < newEdges.length; j++) {
//        		if (i == j) continue;
//        		union(newEdges[j][0], newEdges[j][1]);
//			}
//        	if (connected != N - 1) {
//        		result.add(Arrays.asList(newEdges[i][0], newEdges[i][1]));
//			}
//		}
//        return result;
//    }
    
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
    
    
    
    private int time;
    private int[] dis;
    private int[] low;
    
    /**
     * 
     * @param N
     * @param edges
     * @return
     */
    public List<List<Integer>> minimumCost(int N, int[][] edges) {
        List<List<Integer>> result = new ArrayList<>();
        time = 1;
        dis = new int[N + 1];
        low = new int[N + 1];
        List<Integer>[] graph = new List[N + 1];
        Set<Integer>[] edgeOrder = new Set[N + 1];
        storeEdgeOrder(edgeOrder, edges);
        buildGraph(N, edges, graph);
        dfs(result, graph, -1, 1, edgeOrder);
        return result;
    }

    private void storeEdgeOrder(Set<Integer>[] givenEdgeOrder, int[][] edges) {
        for (int i = 0; i  < givenEdgeOrder.length; i++) {
            givenEdgeOrder[i] = new HashSet<>();
        }
        
        for (int[] edge: edges) {
            givenEdgeOrder[edge[0]].add(edge[1]);
        }
    }

    private void dfs(List<List<Integer>> result, List<Integer>[] graph, int parent, int node, Set<Integer>[] givenEdgeOrder) {
        dis[node] = time;
        low[node] = time;
        time++;

        for (int children: graph[node]) {
            if (children == parent) continue;
            if (dis[children] == 0) {
                dfs(result, graph, node, children, givenEdgeOrder);
            }
            low[node] = Math.min(low[node], low[children]);
            if (dis[node] < low[children]) {
                if (givenEdgeOrder[node].contains(children)) result.add(Arrays.asList(node, children));
                else result.add(Arrays.asList(children, node));
            }
        }
    }
    
    private void buildGraph(int N, int[][] edges, List<Integer>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
    }
    
    public static void main(String[] args) {
        int[] n = new int[]{5,5,6,9,1};
        int[][][] edges = {
            // given
            {{1,4},{4,5},{2,3},{1,2}},
            {{2, 1}, {1, 3}, {3, 4}, {1, 4}, {4, 5}},
            {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {2, 5}, {4, 6}, {5, 6}},
            {{1, 2}, {1, 3}, {2, 3}, {3, 4}, {3, 6}, {4, 5}, {6, 7}, {6, 9}, {7, 8}, {8, 9}},
            // Empty
            {}
        };
        CriticalConnection out = new CriticalConnection();
        int i = 0;
        for (int[][] testCase: edges) {
            System.out.println(out.minimumCost(n[i], testCase));
            i++;
        }
    }
}
