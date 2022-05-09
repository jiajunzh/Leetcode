# Binary Search 
## Introduction 
Binary Search is the process of searching for a speicfic value in an ordered collection (or abstract search space). Terminology used in Binary Search includes:
- Target: the value that you are searching for 
- Index: the current location that you are searching for 
- Left/Right: the indices from which we use to maintain our search space 
- Mid: the index that we use to apply a condition C to determine if we should search left or right

## How It Works?
In its simplest form, Binary Search operates on a contiguous sequence with a specified left and right index. This is called the Search Space. Binary Search maintains the left, right, and middle indicies of the search space and compares the search target or applies the search condition to the middle value of the collection; if the condition is unsatisfied or values unequal, the half in which the target cannot lie is eliminated and the search continues on the remaining half until it is successful. If the search ends with an empty half, the condition cannot be fulfilled and target is not found.

## Identification 
As mentioned in earlier, Binary Search is an algorithm that divides the search space by 2 after every comparison. Binary Search should be considered every time you need to search for an index or element in a collection. If the collection is unordered, we can always sort it first before applying Binary Search.

## Steps 
1. Pre-processing 
  - Sort if collection is unsorted 
  - Define the boundary of search space 
2. Binary Search 
  - Use a loop or recursion to divide search space in half after each comparison
3. Post-processing 
  - Determine viable candidates in the remaining space 

## Templates
### Template I 
Template I is the most the basic and elementary form of Binary Search.

```
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length - 1;
  while(left <= right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid - 1; }
  }

  // End Condition: left > right
  return -1;
}
```

**Highlights**
- Search condition can be determined without comparing to the element's neighbors (or use sepcific element around it). In other words, it is used to search for an element or condition which can be determined by accessing a single index in the array.
- No post processing required in that at each step, you are checking to see if element has been found. It is guaranteed that the element is not found if reaching the end.
- Initial Condition: left = 0, right = length - 1
- Termination Condition: left > right
- Searching Left: right = mid - 1
- Searching Right: left = mid + 1

### Template II
Template II is used to search for an element or condition which requires accessing the current idnex and its immediate right neighbor's index in the array (Find a minimum k with condition C).
```
int binarySearch(int[] nums, int target){
  if(nums == null || nums.length == 0)
    return -1;

  int left = 0, right = nums.length;
  while(left < right){
    // Prevent (left + right) overflow
    int mid = left + (right - left) / 2;
    if(nums[mid] == target){ return mid; }
    else if(nums[mid] < target) { left = mid + 1; }
    else { right = mid; }
  }

  // Post-processing:
  // End Condition: left == right
  if(left != nums.length && nums[left] == target) return left;
  return -1;
}
```

**Highlights**
- Search condition needs to access the element's immediate right neighbor
- Use the element's right neighbor to determine if the condition is met and decide whether to go left or right
- Guarantee search space is at least 2 in size at each step
- Post-processing required. Loop/Recursion ends when you have 1 element left. No need to assess if the remaining element meets the condition.
- Initial Condition: left = 0, right = length
- Termination: left = right
- Search Left: right = mid
- Search Right: left = mid + 1

### Template III 
Template #3 is another unique form of Binary Search. It is used to search for an element or condition which requires accessing the current index and its immediate left and right neighbor's index in the array.
```
int binarySearch(int[] nums, int target) {
    if (nums == null || nums.length == 0)
        return -1;

    int left = 0, right = nums.length - 1;
    while (left + 1 < right){
        // Prevent (left + right) overflow
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            left = mid;
        } else {
            right = mid;
        }
    }

    // Post-processing:
    // End Condition: left + 1 == right
    if(nums[left] == target) return left;
    if(nums[right] == target) return right;
    return -1;
}
```
**Highlights**
- Search condition needs to access element's immediate left and right neighbors
- Use element's neighbors to determine if condition is met and decide whether to go left or right
- Gurantee Search Space is at least 3 in size at each step
- Post-Processing required. Loop/Recursion ends when you have 2 elements left. Need to assess if the remaining elements meet to condition 
- Initial Condition: left = 0, right = length - 1
- Termination: left + 1 == right
- Searching left: right = mid 
- Searching right: left = mid

## Complexity 
**Time Complexity**
O(logN) -> divide the search space each time by half 
**Space Complexity**
O(1)

## Personal Thoughts 
1. What's the typical Binary Search Patterns?
- Sorted Array
- Find the minimum value k satisfying condition C
2. Pre-Processing (Decide the initial search boundary)
- What is the definition of the target: maximum value or minimum value, the target should satisfy what condition
- What is the largest possible search space => include it if you want this value examined  
3. Search Loop & Termination condition
- left <= right (i.e. right + 1 = left when terminated): (1) Be careful when you use left = mid or right = mid. It might lead to infinite loop (2) Each candidate in search space was examined 
- left < right (i.e. right = left when terminated): (1) Be careful when you use left = mid, which might lead to infinite loop. right = mid should be okay though (left <= mid - 1 when right = mid, meaning in the next iteration, mid = (prevMid + prevMid - 1) / 2) = (2 * prevMid - 1) / 2 < prevMid (2) There is one candidate in search space unexamined
- left + 1 < right (i.e. right = left + 1 when terminated): (1) There are two candidates in search space unexamined
4. Post-Processing
- Again think about what is the definition of the target
- Think about what the problem actually wants. For example, the problem might wants the value at some index but you might find index instead or the problem wants the target value, but your binary search finds the minimum value that is greater than target value.

## Problems 
- 0033 Search in Rotated Sorted Array
- 0034 Find First and Last Position of Element in Sorted Array
- 0069 Sqrt(x)
- 0153 Find Minimum in Rotated Sorted Array
- 0162 Find Peak Element
- 0278 First Bad Version
- 0374 Guess Number Higher or Lower
- 0658 Find K Closest Elements
- 0704 Binary Search

## Resources 
- https://leetcode.com/explore/learn/card/binary-search/