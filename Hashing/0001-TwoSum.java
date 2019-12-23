import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /**
     * Return an array of indices of two numbers which add to
     * the target.
     * 
     * Each input has exactly one solution.
     * The same element cannot be used for twice.
     * 
     * @param nums input array
     * @param target target sum
     * @return an array of indices of two numbers which add to
     * the target.
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return new int[0];
    }
    
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println("[" + result[0] + ", " + result[1] + "]");
    }
}
