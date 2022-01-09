package others;

import java.util.*;

public class CommonWord {
    public List<String> mostCommonWord(String literatureText, String[] wordsToExclude) {
        List<String> result = new ArrayList<>();
        Set<String> banned = new HashSet<>();
        Map<String, Integer> freq = new HashMap<>();
        literatureText = literatureText.toLowerCase() + ".";
        char[] chars = literatureText.toCharArray();
        StringBuilder sb = new StringBuilder();
        int max = 0;
        
        for (String wordToExclude: wordsToExclude) {
            banned.add(wordToExclude.toLowerCase());
        }
        
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isLetter(c)) {
                sb.append(c);
            } else {
                String word = sb.toString();
                if (word.length() == 0) continue;
                if (banned.contains(word)) {
                    sb = new StringBuilder();
                    continue;
                }
                int f = freq.getOrDefault(word, 0) + 1;
                freq.put(word, f);
                if (f > max) {
                    result.clear();
                    max = f;
                    result.add(word);
                } else if (f == max) {
                    result.add(word);
                }
                sb = new StringBuilder();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CommonWord test = new CommonWord();
        String[] list = new String[]{"and", "he", "the", "to", "is", "Jack", "Jill"};
        System.out.println(test.mostCommonWord("Jack and Jill went to the market to buy bread and cheese. Cheese is Jack’s and Jill’s favorite food.", list));
    }
}
