public class ExactlyKDistinctChars {
    public int SubstringWithKDistinctChars(String s, int K) {
        if (K == 0) return 0; // special cases
        char[] chars = s.toCharArray();
        int[] count = new int[26];
        int prefix = 0;
        int cnt = 0; // number of distinct chars in the window
        int res = 0;
        int start = 0, end = 0;
        while (end < s.length()) {
            if (count[chars[end] - 'a'] == 0) cnt++;
            count[chars[end] - 'a']++;
            
            if (cnt > K) {
                count[chars[start] - 'a']--;
                start++;
                cnt--;
                prefix = 0;
            }
            
            while (count[chars[start] - 'a'] > 1) {
                count[chars[start] - 'a']--;
                start++;
                prefix++;
            }
            
            if (cnt == K) {
                res += prefix + 1;
            }
            end++;
        }
        return res;
    }

    public static void main(String[] args) {
        ExactlyKDistinctChars test = new ExactlyKDistinctChars();
        System.out.println(test.SubstringWithKDistinctChars("sadsa", 0));
    }
}
