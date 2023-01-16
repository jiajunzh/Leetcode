package problem;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Problem
 * Given a string num that contains only digits and an integer target, return all possibilities to insert the binary
 * operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
 *
 * Note that operands in the returned expressions should not contain leading zeros.
 *
 * 2. Examples
 * Example 1
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 *
 * Example 2
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 *
 * Example 3
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 *
 * 3. Constraints
 * 1 <= num.length <= 10
 * num consists of only digits.
 * -231 <= target <= 231 - 1
 */
public class ExpressionAddOperators {

    private int target = 0;
    private List<String> result;
    private String num;

    /**
     * 1. Approach
     * Backtracking
     *
     * 2. Complexity
     * - Time O(4^N * N)
     * - Space O(N)
     *
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperators(String num, int target) {
        this.result = new ArrayList<>();
        this.target = target;
        this.num = num;
        backtracking(0, 0, new StringBuilder(), 0);
        return result;
    }

    private void backtracking(int i, long currVal, StringBuilder expression, long prevOperand) {
        if (i == num.length()) {
            if (currVal == this.target) {
                this.result.add(expression.toString());
            }
            return;
        }
        int len = expression.length();
        for (int j = i + 1; j <= this.num.length(); j++) {
            if(num.charAt(i) == '0' && j != i + 1) break; // Avoid having operand such as 005
            final String currOperand = this.num.substring(i, j);
            final Long currOperandLong = Long.parseLong(currOperand);
            if (i == 0) {
                expression.append(currOperand);
                backtracking(j, currOperandLong, expression, currOperandLong);
                expression.setLength(len);
            } else {
                expression.append("+");
                expression.append(currOperand);
                backtracking(j, currVal + currOperandLong, expression, currOperandLong);
                expression.setLength(len);

                expression.append("-");
                expression.append(currOperand);
                backtracking(j, currVal - currOperandLong, expression, -currOperandLong);
                expression.setLength(len);

                expression.append("*");
                expression.append(currOperand);
                backtracking(j, currVal - prevOperand + currOperandLong * prevOperand, expression, currOperandLong * prevOperand);
                expression.setLength(len);
            }
        }
    }
}
