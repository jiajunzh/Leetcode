public class ReverseString {

  /**
   * Reverse the given string. Two pointer approach.
   * Time O(N)
   * Space O(1) cause it is modified in place
   *    
   * @param s
   */
  public void reverseString(char[] s) {
    if (s == null) return;

    int left = 0, right = s.length - 1;

    while (left < right) {
      char tmp = s[left];
      s[left] = s[right];
      s[right] = tmp;

      left++;
      right--;
    }
  }

  /**
   * Reverse the given string. This approach use recursion way to reverse string.
   * Time O(N)
   * Space O(N) although it uses recursion, recursion stack use O(N) memory.
   *
   * @param s
   */
  public void reverseString2(char[] s) {
    reverseHelper(0, s.length - 1, s);
  }
  
  public void reverseHelper(int left, int right, char[] s) {
    if (left >= right) {
      return;
    }
    
    char tmp = s[left];
    s[left] = s[right];
    s[right] = tmp;
    
    reverseHelper(left + 1, right - 1, s);
  }
  
  public static void main(String[] args) {
    ReverseString test = new ReverseString();
    char[] input = new char[]{'h', 'e', 'l', 'l', 'o'};
    char[] expected = new char[]{'o', 'l', 'l', 'e', 'h'};
    
    test.reverseString(input);
    
    for (int i = 0; i < input.length; i++) {
      assert(input[i] == expected[i]);
    }
  }
}
