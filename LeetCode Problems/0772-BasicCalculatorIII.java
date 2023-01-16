package problem;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1. Problem
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing
 * parentheses ')'. The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * 2. Examples
 * Example 1
 * Input: s = "1+1"
 * Output: 2
 *
 * Example 2
 * Input: s = "6-4/2"
 * Output: 4
 *
 * Example 3
 * Input: s = "2*(5+5*2)/3+(6/2+8)"
 * Output: 21
 *
 * 3. Constraints
 * 1 <= s <= 104
 * s consists of digits, '+', '-', '*', '/', '(', and ')'.
 * s is a valid expression.
 */
public class BasicCalculatorIII {

    private int index = 0;

    /**
     * 1. Approach
     * Stack
     *
     * 2. Complexity
     * - Time O(S)
     * - Space O(S)
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        final Deque<Integer> stack = new ArrayDeque<>();
        char sign = '+';
        while (index < s.length()) {
            final char curr = s.charAt(index);
            if (Character.isDigit(curr)) {
                int num = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    num = num * 10 + s.charAt(index) - '0';
                    index++;
                }
                updateStack(stack, sign, num);
            } else if (curr == '(') {
                index++;
                int num = calculate(s);
                updateStack(stack, sign, num);
            } else if (curr == ')') {
                index++;
                return calculateResult(stack);
            } else {
                index++;
                sign = curr;
            }
        }
        return calculateResult(stack);
    }

    private void updateStack(final Deque<Integer> stack, char sign, int num) {
        switch (sign) {
            case '+':
                stack.push(num);
                break;
            case '-':
                stack.push(-num);
                break;
            case '*':
                stack.push(num * stack.pop());
                break;
            case '/':
                stack.push(stack.pop() / num);
                break;
        }
    }

    private int calculateResult(final Deque<Integer> stack) {
        int result = 0;
        while (!stack.isEmpty()) result += stack.pop();
        return result;
    }
}
