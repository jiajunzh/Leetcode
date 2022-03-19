package problem;

public class ValidPalindrome {
  public boolean isPalindrome(String s) {
    int start = 0, end = s.length() - 1;

    while (start < end) {
      final char startChar = s.charAt(start);
      final char endChar = s.charAt(end);

      if (!isAlphaNumeric(startChar)) {
        start++;
        continue;
      }

      if (!isAlphaNumeric(endChar)) {
        end--;
        continue;
      }

      if (toLowerCase(startChar) != toLowerCase(endChar)) {
        return false;
      }

      start++;
      end--;
    }

    return true;
  }

  private char toLowerCase(char c) {
    if (c >= 'A' && c <= 'Z') {
      c = (char) (c - 'A' + 'a');
    }

    return c;
  }

  private boolean isAlphaNumeric(char c) {
    return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
  }
}
