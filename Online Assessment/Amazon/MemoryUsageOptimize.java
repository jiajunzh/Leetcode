import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MemoryUsageOptimize {
    /**
     * [1, 2], [id, route]
     * @param maxTravelDist
     * @param forwardRouteList
     * @param returnRouteList
     * @return
     */
    public List<List<Integer>> aircraftUtilization(int maxTravelDist, int[][] forwardRouteList, int[][] returnRouteList){
        int len1 = forwardRouteList.length, len2 = returnRouteList.length;
        List<List<Integer>> result = new ArrayList<>();
        if (len1 == 0 || len2 == 0) {
            return result;
        }
        Arrays.sort(forwardRouteList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        Arrays.sort(returnRouteList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int i = 0, j = len2 - 1;
        int max = 0;
        while (i < len1 && j >= 0) {
            int sum = forwardRouteList[i][1] + returnRouteList[j][1];
            if (sum > maxTravelDist) {
                j--;
                continue;
            }
            if (max > sum) {
                i++;
                continue;
            }
            if (max < sum) {
                result.clear();
                max = sum;
            }
            
            int tmp = j;
            while (tmp >= 0 && returnRouteList[j][1] == returnRouteList[tmp][1]) {
                result.add(Arrays.asList(forwardRouteList[i][0], returnRouteList[tmp][0]));
                tmp--;
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        MemoryUsageOptimize test = new MemoryUsageOptimize();
        int[] maxTravelDists = {7000, 10000, 10000, 6};
        int[][][] forwardRouteLists = {{{1, 2000}, {2, 4000}, {3, 6000}},
            {{1, 2000}, {2, 5000}, {3, 7000}, {4, 10000}},
            {{1, 3000}, {2, 5000}, {3, 7000}, {4, 10000}},
            {{1, 1}, {1, 2}, {1, 4}}};
        int[][][] returnRouteLists = {{{1, 2000}},
            {{1, 2000}, {2, 3000}, {3, 4000}, {4, 5000}},
            {{1, 2000}, {2, 2000}, {3, 4000}, {4, 4000}},
            {{1,3}, {1,5}}};
        for (int i = 0; i < maxTravelDists.length; ++i) {
            System.out.println(test.aircraftUtilization(maxTravelDists[i], forwardRouteLists[i], returnRouteLists[i]));
        }
    }
    
}
