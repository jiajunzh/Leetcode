# Dynamic Programming 

## Introduction 
Dynamic Programming (DP) is a programming paradigm that can explore all possible solutions to a problem. It usually solves problem such that:
(1) The problem can be broken down into "overlapping subproblems" - smaller versions of the original problem that are re-used multiple times 
(2) The prolem has an "optimal substructure" - an optimal solution can be formed from optimal solutions to the overlapping subproblem of the original problem 

- Greedy problems have optimal substructure, but not overlapping subproblems.
 - For example, let's say that you have to get from point A to point B as fast as possible, in a given city, during rush hour. 
 - A dynamic programming algorithm will look into the entire traffic report, looking into all possible combinations of roads you might take, and will only then tell you which way is the fastest. Of course, you might have to wait for a while until the algorithm finishes, and only then can you start driving. The path you will take will be the fastest one (assuming that nothing changed in the external environment).
 - On the other hand, a greedy algorithm will start you driving immediately and will pick the road that looks the fastest at every intersection. As you can imagine, this strategy might not lead to the fastest arrival time, since you might take some "easy" streets and then find yourself hopelessly stuck in a traffic jam.
- Divide and conquer algorithm break a problem into subproblems but these subproblem are not overlapping 

## Top-down vs. Bottom-up
### Top-Down (Memoization)
Top-Down is implemented with recursion and made efficient with memoization. Take Fibonacci as an example, if we want F(n), we will try to compute F(n - 1) and F(n - 2) until we reach F(0) and F(1). It is easy to find that there exist multiple re-computation during the recursion. One solution to this is to memoize the result.

Memoization means to store the result of a function call, usually in a hashmap or an array, so that when th same function call is made again, we could simply return the memoized result instead of recalculating the result.

### Bottom-Up (Tabulation)
Bottom-up is implemented with iteration and starts at the base case. Take Fibonacci as an example, with bottom-up, we use the base case F(0) = 0 and F(1) = 1 to calculate F(2), F(3) ... F(n).

### Comparison 
- Bottom-up implementation's runtime is usually faster as iteration does not have overhead as recursion does.
- Top-down implementation is usually much easier to write as with recursion the ordering of subproblems does not matter.

## DP Framework
1. State Variable: state is a set of variables that can sufficiently describe a scenario. E.g. in Climbing Stairs, state variable is the current step we are on, i = 6 means the state of being on the 6th step.
2. A function/data structure that will compute/contain the answer to the problem for every given state. For example in Climbing Stairs, dp[i] = number of ways to climb to the ith step.
3. Recurrence relation to transition between states. For example, dp[i] = dp[i - 1] + dp[i - 2]
4. Base Case

## Multidimensional DP 
Things to look out for in DP problems that require a state variable include:
- An index along some input. For example, it could represent the answer to the problem if the input was considered only up to that index.
- A second index along some input. For example, the answer to the problem if considering the input starting at index i and ending at index j. 
- Explicit numerical constraints given in the problem. For example, you are only allowed to complete k transactions.
- Variables that describe statuses in a given state. For example, true if holding a key and false if not.
- Some sort of data like a tuple or bitmask used to indicate things being "visited" or "used".

## Convert Top-Down to Bottom-Up
1. Initialize an array dp that is sized according to your state variables. For example, let's say the input to the problem was an array nums and an integer k that represents the maximum number of actions allowed. Your array dp would be 2D with one dimension of length nums.length and the other of length k. The values should be initialized as some default value opposite of what the problem is asking for. For example, if the problem is asking for the maximum of something, set the values to negative infinity. If it is asking for the minimum of something, set the values to infinity.
2. Set your base cases, same as the ones you are using in your top-down function.
3. Write a for-loop(s) that iterate over your state variables. If you have multiple state variables, you will need nested for-loops. These loops should start iterating from the base cases.
4. Now, each iteration of the inner-most loop represents a given state, and is equivalent to a function call to the same state in top-down.
5. Return the answer to the original problem

## Common Patterns
### Iteration in the recurrence relation
Usually the recurrence relation is a static equation such as dp(i)=min(dp(i - 1) + cost[i - 1], dp(i - 2) + cost[i - 2]). However, sometimes the recurrence relation could be dynamic and need iteration. For example, dp(i)=min(dp(j) + cost[j]) for all (i - k) <= j < i 

### State Transition By Inaction 

### Counting DP vs Pathing DP vs Optimal DP 

### Kadane's Algorithm (Max Subarray)

- The problem asks for the optimal value (maximum or minimum), number of ways there are to do something or if it is possible to reach a certain point. (It might be greedy or DP)
 - What is the minimum cost of doing...
 - What is the maximum profit from...
 - How many ways are there to do...
 - What is the longest possible...
 - Is it possible to reach a certain point...
- Future decision depends on the earlier decision => Need to factor in previous decision 

## Personal Thoughts 
- Space Optimization: once having the basic version of DP, think about whether only a few elements were considered or the whole dp array for each iteration. If the former, we could optimize the space to constant space.

## Problems
- 0198 House Robber
- 0740 Delete and Earn
- 0746 Min Cost Climbing Stairs
- 1137 N-th Tribonacci Number

## Resources 
- https://leetcode.com/explore/learn/card/dynamic-programming/630/an-introduction-to-dynamic-programming/