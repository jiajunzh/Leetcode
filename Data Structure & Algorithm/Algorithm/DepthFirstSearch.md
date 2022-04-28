# Depth First Search 
## Introduction 
Depth First Search, so called DFS, is a graph/tree traversal algorithm that
(1) Starts at the root node and travels as far as it can down a given branch
(2) Then backtracks until it finds another unexplored path to explore 
(3) This approach is repeated until all the nodes of the graph have been visited 

## Implementation 
(1) Push a node to the stack (or use recursion)
(2) Pop the node 
(3) Retrieve unvisited neighbors of the removed node, push them to stack 
(3) Repeat steps 1-3 as long as the stack is not empty 

## Problems 
- 0200 Number of Islands 
- 0547 Number of Provinces


## Resources
- https://www.freecodecamp.org/news/dfs-for-your-next-tech-giant-interview/