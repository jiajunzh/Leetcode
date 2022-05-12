# Recursion 

## Principle of Recursion 
Recursion is an approach to solving problems using a function that calls itself as a subroutine. By that, it reduces the given problem into subproblems until it reaches a point where the subproblem can be solved without further recursion.

**Highlights**
- Base Case(s) => Termination scenario that does not use recursion to produce an answer
- Recurrence Relation: the relationship between the result of a problem and the result of its subproblems

### Recursion Function 
1. Define the problem as the function F(X) to implement, where X is the input of the function which also defines the scope of the problem 
2. Break the problem down into smaller scopes
3. Call function F(X0), F(X1) ... F(Xn) recursively to solve the subproblems of X
4. Finally, process the results from recursive function calls to solve the problem corresponding to X

### Memoization - Duplicate Calculation in Recursion 
Recursion brings duplicate calculations in if we do not do it wisely. A Technique called memoization could be used to avoid this problem. Memoization stores the intermediate results in cache that will be reused later without re-calculation.

### Problems 
- 0024 Swap Nodes in Pairs
- 0070 Climbing Stairs
- 0119 Pascal's Triangle II
- 0206 Reverse Linked List
- 0509 Fibonacci Number
- 0700 Search in a Binary Search Tree

### Resources 
- https://leetcode.com/explore/learn/card/recursion-i/251/scenario-i-recurrence-relation/
