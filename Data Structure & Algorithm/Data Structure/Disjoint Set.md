# Disjoint Set
The primary use of disjoint set is to address the connectivity (direct or indirect) between components of a network. For example, if two vertices are connected.

## Teminology 
1. Parentod Node: the direct parent of a vertex. For example, if we have a parent array [-1, 0, 1], then node 0 is the root of this set and the parent node of node 1. Node 1 is the parent node of node 2.
2. Root Node: a node without parent node.

## Functions 
1. find function: it finds the root node of a given vertex. 
2. union function: it unions two vertices and makes their root nodes the same. 

## Implementation 
1. List<Integer>/Array<Integer> + Store Root Node for Each Item => [Quick Find]
List of integers where ith entry gives set number (i.e. id) of item i. For example if we have set {0, 1, 2, 4}, {3, 5} and {6}, we will have a list with values 4(0) 4(1) 4(2) 5(3) 4(4) 5(5) 6(6)
- Connect: slow, N+2 to 2N + 2 array accesses, which is BigTheta(N)
- isConnect: BigTheta(1)
```
class UnionFind {
    private int[] root;

    public UnionFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    public int find(int x) {
        return root[x];
    }
		
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            for (int i = 0; i < root.length; i++) {
                if (root[i] == rootY) {
                    root[i] = rootX;
                }
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```
2. Rooted-Tree Representation => [Quick Union]
Assign each item a parent item (instead of an id), resulting in a tree-like shape. With that, union operation will only require changing one pointer/parent only instead of changing the parent of each item in one set. For example, if we have [-1, 0, 1, -1, 0, 3, -1], then we know we have three sets [0, 1, 2, 4], [3, 5] and [6].
- Connect: find the parent node of both p and q, then point one of the parent nodes to the other. O(N)
- isConnect: examine if the parent node of both p and q is the same. O(N)
- Overall the performance is bad if tree is imbalance => Performance will be improved if tree balanced
```
class UnionFind {
    private int[] root;

    public UnionFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    public int find(int x) {
        while (x != root[x]) {
            x = root[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            root[rootY] = rootX;
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```
3. Weighted Quick Union
Modify quick union to avoid tall trees. It will:
- Track tree size (number of elements)
- Always link root of smaller tree to larger tree
The worst case tree height is BigTheta(logN)
- Constructor: BigTheta(N)
- Connect: O(logN)
- isConnected: O(logN)
```
class UnionFind {
    private int[] root;
    private int[] rank;

    public UnionFind(int size) {
        root = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 1; 
        }
    }

    public int find(int x) {
        while (x != root[x]) {
            x = root[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```
4. Path Compression 
Switch items along the finding path to point to the root of the set. It improves the performance for future queries.
- amortized (average) constant time 
- M operations on N nodes is O(N + Mlg* N) where lg* is less than 5 for any realistic input. (lg* 2^65536 = 5)
- A tighter bound: O(N + M * a(N)) where a(N) is the inverse Ackermann function that is less than 5 for all practical inputs.
```
class UnionFind {
    private int[] root;

    public UnionFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    public int find(int x) {
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            root[rootY] = rootX;
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```
5. Ultimate version (Weighted Union Find with Path Compression)
```
class UnionFind {
    private int[] root;
    // Use a rank array to record the height of each vertex, i.e., the "rank" of each vertex.
    private int[] rank;

    public UnionFind(int size) {
        root = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 1; // The initial "rank" of each vertex is 1, because each of them is
                         // a standalone vertex with no connection to other vertices.
        }
    }

	// The find function here is the same as that in the disjoint set with path compression.
    public int find(int x) {
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

	// The union function with union by rank
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```