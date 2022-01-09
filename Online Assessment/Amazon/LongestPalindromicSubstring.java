package others;

public class LongestPalindromicSubstring {
    /**
     * P(i, j) = P(i + 1, j - 1) && Si == Sj
     * P(i, i) = true;
     * P(i, i + 1) = Si == Si+1
     * you want P(0, s.length - 1)
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        String res = "";
        for (int i = n - 1; i >= 0 ;i--) {
            for (int j = i; j < n; j++) {
                if (chars[i] == chars[j] && (((i + 1) > (j - 1)) || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring test = new LongestPalindromicSubstring();
        System.out.println(test.longestPalindrome("cbbcs"));
    }
}
