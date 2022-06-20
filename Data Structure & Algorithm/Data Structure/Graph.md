# Graph 
Disjoint Sets will be covered by a separate primer.

## Types 
1. Undirected Graphs
The edges between two vertices in this graph do not have a direction, indicating a two-way relationship.
2. Directed Graphs
The edges between two vertices in this graph are directional.
3. Weighted Graphs 
Each edge in a “weighted graph” has an associated weight. The weight can be of any metric, such as time, distance, size, etc.

## Graph Terminology 
1. Vertex 
2. Edge: connection between two vetices 
3. Path: sequence of vertices to go through from one vertex to another
4. Path Length: number of edges in a path
5. Cycle: a path where the starting and ending point is the same vertex 
6. Negative Weight Cycle: in weighted graphs if the sum of weight in a cycle is negative 
7. Connectivity: two vertices are connected if there exist at least one path between two vertices
8. Degree of a Vertex: the term “degree” applies to undirected graphs. The degree of a vertex is the number of edges connecting the vertex. 
9. In-Degree: “in-degree” is a concept in directed graphs. If the in-degree of a vertex is d, there are d directional edges incident to the vertex. 
10. Out-Degree: “out-degree” is a concept in directed graphs. If the out-degree of a vertex is d, there are d edges incident from the vertex. 

## Graph Traversal - DFS 
### Problem Statement 
- Find all vertices in graph 
- Find all pahts between two vertices in a graph 

### Find All Vertices 
- Initialize data structures 
 - Stack: First In Last Out. Used to store the possible traversable nodes in the following steps 
 - Visited Array: Record all vertices that has been visisted so far
- For each node in stack, pop the top node from stack 
 - If the node has been visited => Skip
 - Otherwise, mark it as visited and add all nodes directly connected to it to stack
- All nodes should be traversed once the stack is empty

Time Complexity O(V + E)
Space Complexity O(V)

### Find All Path Between Vertices 
- Initialize data structures 
 - Stack: First In Last Out. Used to store all path (could be partial path) traversed so far
- For each path in stack, pop the top path from stack Retrieve the last node in the path and all nodes connecting directly with this last node. 
 - If the last node is the target node, then path is found.
 - Otherwise, for each node connecting to it:
  - If the node has been visited within the path => Skip
  - Otherwise, add it to the path and add the latest path to stack
- All paths should be traversed once the stack is empty

Time Complexity O((V - 1)!)
Space Complexity O(V^3)


