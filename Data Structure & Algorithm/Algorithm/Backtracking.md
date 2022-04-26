# Backtracking 
Backtracking => traversal process of a decision tree. Three terms to think about include:

1. Path: the selection that have been made
2. Current Selection: the selection you could currently make 
3. End Condition: the condition under which you reach the bottom of the decision tree

Each recursion for backtracking includes below steps:
1. Define backtracking helper function and parameters. The parameters we usually need are:
 - The related object you operate on. E.g. the input string
 - Start or end index implying the part you are working on
 - Step result (selected path till now)
 - Final result
2. Define base case: when to return and when to add the result into the final result
3. Make selection.
4. The next recursive call. How to further explore? 
5. Undo the selection.

# Framework
```
/**
 * The input usuually includes
 * - Path by traversing so far
 * - The current selectionList
 */
private void backtrack(...) {

	// End Condition Here => Record the path

	for (selection : selectionList) {
		select 
		backtrack(...)
		unselect
	}
}
```

# Backtracking & Dynamic Programming 
As it seems, backtracking looks similar to dynamic programming. Actually, the brutal-force solution phase of dynamic programming is a backtracking. If the problem has overlapping sub-problems (re-calculation), you could use dp or memoization to prune the recursive tree, that's when backtracking is boiled down to dynamic programming. 

# Problems 
- 0039 Combination Sum I
- 0040 Combination Sum II
- 0046 Permutations I
- 0047 Permutations II
- 0051 NQueens I
- 0052 NQueens II
- 0078 Subset I
- 0090 Subset II

## Common Search Space Pattern 
Permutations: N!
Combinations: C(k, N) = N! / (N - k)!k!
Subsets: 2^N

# References 
- https://labuladong.gitbook.io/algo-en/iii.-algorithmic-thinking/detailsaboutbacktracking
- https://leetcode.com/problems/palindrome-partitioning/discuss/182307/Java:-Backtracking-Template-General-Approach