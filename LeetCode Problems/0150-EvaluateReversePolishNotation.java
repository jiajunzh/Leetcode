package problem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * 1. Problem 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 *
 * Note that division between two integers should truncate toward zero.
 *
 * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to
 * a result, and there will not be any division by zero operation.
 *
 * 2. Examples
 * Example 1
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * 
 * Example 2
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * 
 * Example 3
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 * 3. Constraints
 * 1 <= tokens.length <= 104
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */
public class EvaluateReversePolishNotation {
  
  private static final Map<String, Function> FUNCTIONS_MAP = Map.of(
    "+", new AddFunction(),
    "-", new SubtractFunction(),
    "*", new MultiplyFunction(),
    "/", new DivideFunction()
  );

  public int evalRPN(String[] tokens) {
    final Deque<Integer> stack = new ArrayDeque<>();
    for (final String token : tokens) {
      if (!FUNCTIONS_MAP.containsKey(token)) {
        stack.push(Integer.parseInt(token));
        continue;
      }
      int number1 = stack.pop();
      int number2 = stack.pop();
      final Function function = FUNCTIONS_MAP.get(token);
      int result = function.apply(number1, number2);
      stack.push(result);
    }
    return stack.pop();
  }

  public interface Function {
    int apply(int number1, int number2);
  }

  public static class AddFunction implements Function {
    @Override
    public int apply(int number1, int number2) {
      return number1 + number2;
    }
  }

  public static class SubtractFunction implements Function {
    @Override
    public int apply(int number1, int number2) {
      return number2 - number1;
    }
  }

  public static class MultiplyFunction implements Function {
    @Override
    public int apply(int number1, int number2) {
      return number1 * number2;
    }
  }

  public static class DivideFunction implements Function {
    @Override
    public int apply(int number1, int number2) {
      return number2 / number1;
    }
  }
}
