import java.util.List;

public class BalancedSalesArray {
    /*
     * Complete the 'balancedSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY sales as parameter.
     */

    public static int balancedSum(List<Integer> sales) {
        // Write your code here
        int n = sales.size();
        int sum = sales.get(0);

        for (int i = 1; i < n; i++) {
            sum += sales.get(i);
        }

        int currSum = sales.get(0);
        for (int i = 1; i < n - 1; i++) {
            if (currSum * 2 + sales.get(i) == sum) {
                return i;
            }
            currSum += sales.get(i);
        }
        return 0;
    }
}
