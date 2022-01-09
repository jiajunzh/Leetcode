import java.util.HashMap;
import java.util.Map;

public class SortCenter {
    public int[] Find2Sum(int[] nums, int target) {
        Map<Integer, Integer> spaceToIndex = new HashMap<>();
        int[] result = new int[]{-1, -1};
        int maxSpace = -1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int t = target - 30 - num;
            if (spaceToIndex.containsKey(t)) {
                int maxNumber = Math.max(num, t);
                if (maxSpace < maxNumber) {
                    result[0] = i;
                    result[1] = spaceToIndex.get(t);
                    maxSpace = maxNumber;
                }
            }
            spaceToIndex.put(num, i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] productSpace = new int[]{20, 50, 40, 10, 30, 10};
        int target = 90;
        SortCenter test = new SortCenter();
        System.out.println(test.Find2Sum(productSpace, target)[0] + "-" + test.Find2Sum(productSpace, target)[1]);
    }
}
