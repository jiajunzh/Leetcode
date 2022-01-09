import java.util.List;

public class AutoScalingPolicy {
    /*
     * Complete the 'finalInstances' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER instances
     *  2. INTEGER_ARRAY averageUtil
     */
    private static final int UP_UTILIZATION = 60;
    private static final int BOTTOM_UTILIZATION = 25;
    private static final long UP_LIMIT_INSTANCE = 200000000;
    private static final long BOTTOM_LIMIT_INSTANCE = 1;

    public static int finalInstances(int instances, List<Integer> averageUtil) {
        // Write your code here
        int i = 0;
        long finalInstances = instances;

        while (i < averageUtil.size()) {
            int avgUtil = averageUtil.get(i);
            if (avgUtil > UP_UTILIZATION) {
                if (finalInstances * 2 <= UP_LIMIT_INSTANCE) {
                    finalInstances *= 2;
                    i += 11;
                    continue;
                }
            } else if (avgUtil < BOTTOM_UTILIZATION) {
                if (finalInstances > 1) {
                    finalInstances = (finalInstances + 1) / 2;
                    i += 11;
                    continue;
                }
            }
            i++;
        }

        return (int)finalInstances;
    }
}
