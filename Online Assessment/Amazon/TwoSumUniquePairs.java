import java.util.HashSet;
import java.util.Set;

public class TwoSumUniquePairs {
    public int getUniquePairs(int[] nums, int target){
        Set<Integer> pair = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        int numOfPairs = 0;
        
        for (int num: nums) {
            if (pair.contains(num)) {
                if (!visited.contains(num)) {
                    numOfPairs++;
                    visited.add(num);
                }
            } else {
                pair.add(target - num);
            }
        }
        return numOfPairs;
    }

    public static void main(String[] args) {
        int target = 7;
        int[] nums = new int[]{1, 5, 1, 5};
        TwoSumUniquePairs test = new TwoSumUniquePairs();
        System.out.println(test.getUniquePairs(nums, target));
    }
}

