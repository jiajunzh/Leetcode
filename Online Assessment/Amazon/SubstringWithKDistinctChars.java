import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubstringWithKDistinctChars {
    public List<String> kSubstring(String s, int k) {
        Set<String> result = new HashSet<>();
        boolean[] letters = new boolean[26];
        char[] chars = s.toCharArray();
        
        int start = 0, end = 0;
        while (end < s.length()) {
            char endChar = chars[end];
            while (letters[endChar - 'a']) {
                letters[chars[start] - 'a'] = false;
                start++;
            }
            
            letters[endChar - 'a'] = true;
            if (end - start == k - 1) {
                result.add(s.substring(start, end + 1));
                letters[chars[start] - 'a'] = false;
                start++;
            }
            end++;
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        SubstringWithKDistinctChars test = new SubstringWithKDistinctChars();
        System.out.println(test.kSubstring("abcabc", 3));
    }
}
