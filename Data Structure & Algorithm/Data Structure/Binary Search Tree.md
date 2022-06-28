# Binary Search Tree

## Definition 
A binary search tree (BST) satisfies the binary search property:
1. The value in each node must be greater than (or equal to) any value stored in the left subtree
2. The value in each node must be less than (or equal to) any value stored in its right subtree

## Search Operation 
1. Return the node if the target value is equal to the value of the node 
2. Continue searching in the left subtree if the target value is less than the value of the node 
3. Continue searching in the right subtree if the target value is larger than the value of the node 

Time O(H)
Space O(1)

## Insertion Operation 
The idea is to find out a proper leaf position for the target node and then insert the node as a leaf.
1. Search the left or right subtrees according to the relation of the value of the node and the value of our target node
2. Repeat STEP 1 until reaching an external node
3. Add the new node as its left or right child depending on the relation of the value of the node and the value of our target node

Time O(H)
Space O(1)

## Deletion Operation 
The idea is to replace the target node with proper child node. It could be split into three cases based on the number of children:
1) No Child => Simply remove the node 
2) One child => use its child to replace itself
3) Two children, replace the node with its in-order successor or predecessor node and delete that node

## Height-Balanced BST
### Definition 
- Depth of node: the number of edges from the tree's root node to the node 
- Height of node: the number of edges on the longest path between that node and a leaf
- Height of Tree: maximum height of nodes
- Height-Balanced BST: a BST that automatically keeps its height small in the face of arbitrary item insertions and deletions 
 - O(logN) height 
 - The depth of the two subtrees of each node never differ by more than 1.
