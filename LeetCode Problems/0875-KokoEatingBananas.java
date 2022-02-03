package problem;

/**
 * 1. Problem
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and
 * will come back in h hours. Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile 
 * of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and
 * will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * 2. Example
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 * 3. Constraints
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 */
public class KokoEatingBananas {
  
  /**
   * 1. Approach 
   * Binary Search. This problem is similar to problem #410 and #1011. Generally, the problem could be translated into 
   * such a way that find the minimum k where hours(k, piles) <= h. 
   * 
   * 2. Complexity 
   * Time O(NlogM) - M is the maximum element in piles array
   * Space O(1)
   * 
   * 3. Mistakes
   * 1) Searching Space Highest Boundary => Long Execution Time
   * Reflecting on problem #410 and #1011, the two problems require you to propose a capacity k which you could use to
   * iterate on continuous subarray until the sum of it reaches to capacity. So you initialize the highest boundary as 
   * the sum of all elements. However, this problem is slightly different (as "If the pile has less than k bananas, she 
   * eats all of them instead and will not eat any more bananas during this hour"). That is to say, the highest boundary 
   * of this searching space is Math.max(piles[i]) not the sum of all elements.
   * 
   * 2) Overflow 
   * This mistake happens when highest searching boundary is set to sum(pile[i]). Because the sum could go beyond 
   * Integer.MAX_VALUE, which mean potential overflow.
   * 
   * 3) Ceiling
   * Initially pile / speed + (pile % speed == 0 ? 0 : 1) is used to calculate the days. Instead, (pile - 1) / speed + 1
   * (ceiling) could be used to simplify the expression.
   */
  public int minEatingSpeed(int[] piles, int h) {
    int low = 1, high = 0;

    for (final int pile : piles) {
      high = Math.max(pile, high);
    }

    while (low < high) {
      final int mid = low + (high - low) / 2;

      if (eatableInHHours(piles, mid, h)) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }

    return low;
  }

  private boolean eatableInHHours(int[] piles, int speed, int h) {
    int hours = 0;

    for (int pile : piles) {
      hours = hours + (pile - 1) / speed + 1;
    }

    return hours <= h;
  }
}
