package others;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
    
    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) return true;
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((c == '(') || (c == '{') || (c == '[')) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || stack.pop() != map.get(c)) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses test = new ValidParentheses();
        System.out.println(test.isValid("[]{"));
    }
}
