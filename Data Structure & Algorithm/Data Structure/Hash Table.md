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



