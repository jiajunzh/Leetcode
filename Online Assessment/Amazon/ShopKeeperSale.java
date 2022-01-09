import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ShopKeeperSale {
    /**
     * [2,3,1,2,4,2]
     */
    public void salePrices(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> fullPriceIndex = new ArrayList<>();
        int sale = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            int price = prices[i];
            while (!stack.isEmpty() && stack.peek() > price) {
                stack.pop();
            }
            
            if (stack.isEmpty()) {
                fullPriceIndex.add(i);
                sale += price;
            } else {
                sale += price - stack.peek();
            }
            stack.push(price);
        }
        Collections.reverse(fullPriceIndex);
        System.out.println(sale);
        System.out.println(fullPriceIndex);
    }


    public static void main(String[] args) {
        int[] prices = new int[]{1,3,3,2,5};
        ShopKeeperSale test = new ShopKeeperSale();
        test.salePrices(prices);
    }
}
