# Hash Table 

## Overview 
- Use hash function 
- Support quick insertion and search 
- HashSet and HashMap are two practical applications of Hash Table

## Design Hash Table 
The key principle of hash table is to map keys to buckets.
- When inserting a new key, the hash function will decide which bucket the key should be assigned and the key will be stored in the corresponding bucket
- When we want to search for a key, the hash table will use the same hash function to find the corresponding bucket and search only in the bucket

### Hash Function 
The hash function has two critical factors (The key idea is to assign the key to the bucket as uniformly as you can):
- range of key values 
- number of buckets

### Collision Resolution
Assume that each bucket can hold the maximum number of keys as n. Then:
- If N is constant and small, use array/list to store keys in the same bucket
- If N is variable or large, use height-balanced binary search tree instead.

## Practical Application 
### HashSet 
Hash Set is an implementation of Set which stores no repeated values. 
- Duplicate Check: if a value has ever appeared or not.

### HashMap
Hash Map is an implementation of Map which stores (key, value) pairs.
- Build Mapping Relationship when needing more information rather than just one key.
- Aggregate all the information by key (e.g. count occurrence of key)

## HashKey Design 
1. When the order of each element doesn't matter => use sorted string/array as the key
(x0, x5, x3, x4, x2, x1) and (x3, x2, x5, x1, x0, x4) could both be mapped to (x0, x1, x2, x3, x4, x5)
2. If you only care about the offset of each value, usually the offset from the first value, you can use the offset as the key
(x0, x1, x2, x3, x4, x5) => (0, x1-x0, x2-x0, x3-x0, x4-x0, x5-x0)
3. In a tree, you might want to directly use the TreeNode as key OR serialization of the subtree.
4. In a matrix, you might want to use the row index or the column index as key
5. In a Sudoku, you can combine the row index and the column index to identify which block this element belongs to. Sometimes, you might also want to aggregate the value in the same diagonal line (Anti-Diagonal Order (i,j) -> i + j | Diagonal Order (i,j) -> i - j).
